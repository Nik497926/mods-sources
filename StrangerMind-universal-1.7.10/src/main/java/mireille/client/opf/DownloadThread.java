package mireille.client.opf;

import cpw.mods.fml.relauncher.*;
import mireille.client.cache.*;
import mireille.api.*;
import org.apache.commons.io.*;
import java.awt.image.*;
import java.net.*;
import java.io.*;
import javax.imageio.stream.*;
import javax.imageio.*;
import mireille.*;
import org.apache.logging.log4j.*;
import java.text.*;
import java.util.*;

@SideOnly(Side.CLIENT)
public class DownloadThread extends Thread
{
    public static final Logger LOGGER;
    public static final TextureCache TEXTURE_CACHE;
    public static final DateFormat FORMAT;
    public static final Object LOCK;
    public static final int MAXIMUM_ACTIVE_DOWNLOADS = 5;
    public static int activeDownloads;
    public static HashMap<String, PictureTexture> loadedImages;
    public static Set<String> loadingImages;
    private String url;
    private ProcessedImageData processedImage;
    private String error;
    private boolean complete;
    
    public DownloadThread(final String url) {
        this.url = url;
        synchronized (DownloadThread.LOCK) {
            DownloadThread.loadingImages.add(url);
            ++DownloadThread.activeDownloads;
        }
        this.setName("OPF Download \"" + url + "\"");
        this.setDaemon(true);
        this.start();
    }
    
    public boolean hasFinished() {
        return this.complete;
    }
    
    public boolean hasFailed() {
        return this.hasFinished() && this.error != null;
    }
    
    public String getError() {
        return this.error;
    }
    
    @Override
    public void run() {
        Exception exception = null;
        try {
            final byte[] data = load(this.url);
            final String type = readType(data);
            ByteArrayInputStream in = null;
            try {
                in = new ByteArrayInputStream(data);
                if (type.equalsIgnoreCase("gif")) {
                    final GifDecoder gif = new GifDecoder();
                    final int status = gif.read(in);
                    if (status == 0) {
                        this.processedImage = new ProcessedImageData(gif);
                    }
                    else {
                        DownloadThread.LOGGER.error("Failed to read gif: {}", new Object[] { status });
                    }
                }
                else {
                    try {
                        final BufferedImage image = ImageIO.read(in);
                        if (image != null) {
                            this.processedImage = new ProcessedImageData(image);
                        }
                    }
                    catch (IOException e1) {
                        exception = e1;
                        DownloadThread.LOGGER.error("Failed to parse BufferedImage from stream", (Throwable)e1);
                    }
                }
            }
            finally {
                IOUtils.closeQuietly((InputStream)in);
            }
        }
        catch (Exception e2) {
            exception = e2;
            DownloadThread.LOGGER.error("An exception occurred while loading OPFrame image", (Throwable)e2);
        }
        if (this.processedImage == null) {
            if (exception == null) {
                this.error = "download.exception.gif";
            }
            if (exception != null) {
                if (exception.getMessage().startsWith("Server returned HTTP response code: 403")) {
                    this.error = "download.exception.forbidden";
                }
                else if (exception.getMessage().startsWith("Server returned HTTP response code: 404")) {
                    this.error = "download.exception.notfound";
                }
                else {
                    this.error = "download.exception.invalid";
                }
            }
            DownloadThread.TEXTURE_CACHE.deleteEntry(this.url);
        }
        this.complete = true;
        synchronized (DownloadThread.LOCK) {
            DownloadThread.loadingImages.remove(this.url);
            --DownloadThread.activeDownloads;
        }
    }
    
    public static byte[] load(final String url) throws IOException {
        final TextureCache.CacheEntry entry = DownloadThread.TEXTURE_CACHE.getEntry(url);
        final long requestTime = System.currentTimeMillis();
        final URLConnection connection = new URL(url).openConnection();
        connection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
        int responseCode = -1;
        if (connection instanceof HttpURLConnection) {
            final HttpURLConnection httpConnection = (HttpURLConnection)connection;
            if (entry != null) {
                if (entry.getEtag() != null) {
                    httpConnection.setRequestProperty("If-None-Match", entry.getEtag());
                }
                else if (entry.getTime() != -1L) {
                    httpConnection.setRequestProperty("If-Modified-Since", DownloadThread.FORMAT.format(new Date(entry.getTime())));
                }
            }
            responseCode = httpConnection.getResponseCode();
        }
        InputStream in = null;
        try {
            in = connection.getInputStream();
            final String etag = connection.getHeaderField("ETag");
            long expireTimestamp = -1L;
            final String maxAge = connection.getHeaderField("max-age");
            if (maxAge != null && !maxAge.isEmpty()) {
                try {
                    expireTimestamp = requestTime + Long.parseLong(maxAge) * 1000L;
                }
                catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            final String expires = connection.getHeaderField("Expires");
            if (expires != null && !expires.isEmpty()) {
                try {
                    expireTimestamp = DownloadThread.FORMAT.parse(expires).getTime();
                }
                catch (ParseException e2) {
                    e2.printStackTrace();
                }
            }
            final String lastModified = connection.getHeaderField("Last-Modified");
            long lastModifiedTimestamp;
            if (lastModified != null && !lastModified.isEmpty()) {
                try {
                    lastModifiedTimestamp = DownloadThread.FORMAT.parse(lastModified).getTime();
                }
                catch (ParseException e3) {
                    lastModifiedTimestamp = requestTime;
                }
            }
            else {
                lastModifiedTimestamp = requestTime;
            }
            if (entry != null) {
                if (etag != null && !etag.isEmpty()) {
                    entry.setEtag(etag);
                }
                entry.setTime(lastModifiedTimestamp);
                if (responseCode == 304) {
                    final File file = entry.getFile();
                    if (file.exists()) {
                        return IOUtils.toByteArray((InputStream)new FileInputStream(file));
                    }
                }
            }
            final byte[] data = IOUtils.toByteArray(in);
            DownloadThread.TEXTURE_CACHE.save(url, etag, lastModifiedTimestamp, expireTimestamp, data);
            return data;
        }
        finally {
            IOUtils.closeQuietly(in);
        }
    }
    
    private static String readType(final byte[] input) throws IOException {
        InputStream in = null;
        try {
            in = new ByteArrayInputStream(input);
            return readType(in);
        }
        finally {
            IOUtils.closeQuietly(in);
        }
    }
    
    private static String readType(final InputStream input) throws IOException {
        final ImageInputStream stream = ImageIO.createImageInputStream(input);
        final Iterator<ImageReader> iter = ImageIO.getImageReaders(stream);
        if (!iter.hasNext()) {
            return "";
        }
        final ImageReader reader = iter.next();
        if (reader.getFormatName().equalsIgnoreCase("gif")) {
            return "gif";
        }
        final ImageReadParam param = reader.getDefaultReadParam();
        reader.setInput(stream, true, true);
        try {
            reader.read(0, param);
        }
        catch (IOException e) {
            DownloadThread.LOGGER.error("Failed to parse input format", (Throwable)e);
        }
        finally {
            reader.dispose();
            IOUtils.closeQuietly((Closeable)stream);
        }
        input.reset();
        return reader.getFormatName();
    }
    
    public static PictureTexture loadImage(final DownloadThread thread) {
        PictureTexture texture = null;
        if (!thread.hasFailed()) {
            if (thread.processedImage.isAnimated()) {
                texture = new AnimatedPictureTexture(thread.processedImage);
            }
            else {
                texture = new OrdinaryTexture(thread.processedImage);
            }
        }
        if (texture != null) {
            synchronized (DownloadThread.LOCK) {
                DownloadThread.loadedImages.put(thread.url, texture);
            }
        }
        return texture;
    }
    
    static {
        LOGGER = LogManager.getLogger((Class)StrangerMind.class);
        TEXTURE_CACHE = new TextureCache();
        FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        LOCK = new Object();
        DownloadThread.activeDownloads = 0;
        DownloadThread.loadedImages = new HashMap<String, PictureTexture>();
        DownloadThread.loadingImages = new HashSet<String>();
    }
}
