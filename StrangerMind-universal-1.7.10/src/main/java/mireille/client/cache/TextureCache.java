package mireille.client.cache;

import net.minecraft.client.*;
import org.apache.commons.codec.binary.Base64;
import mireille.client.opf.*;
import org.apache.commons.io.*;
import java.util.zip.*;
import java.io.*;
import java.util.*;

import org.apache.commons.codec.binary.*;

public class TextureCache
{
    private File cacheDirectory;
    private File index;
    private Map<String, CacheEntry> entries;
    
    public TextureCache() {
        this.cacheDirectory = new File(Minecraft.getMinecraft().mcDataDir, "opframe_cache");
        this.index = new File(this.cacheDirectory, "index");
        this.entries = new HashMap<String, CacheEntry>();
        if (!this.cacheDirectory.exists()) {
            this.cacheDirectory.mkdirs();
        }
        this.loadIndex();
    }
    
    public void save(final String url, final String etag, final long time, final long expireTime, final byte[] data) {
        final CacheEntry entry = new CacheEntry(url, etag, time, expireTime);
        boolean saved = false;
        OutputStream out = null;
        try {
            out = new FileOutputStream(entry.getFile());
            out.write(data);
            saved = true;
        }
        catch (IOException e) {
            DownloadThread.LOGGER.error("Failed to save cache entry {}", new Object[] { e, url });
        }
        finally {
            IOUtils.closeQuietly(out);
        }
        if (saved) {
            this.entries.put(url, entry);
            this.saveIndex();
        }
    }
    
    public CacheEntry getEntry(final String url) {
        return this.entries.get(url);
    }
    
    private void loadIndex() {
        if (this.index.exists()) {
            final Map<String, CacheEntry> previousEntries = this.entries;
            this.entries = new HashMap<String, CacheEntry>();
            DataInputStream in = null;
            try {
                in = new DataInputStream(new GZIPInputStream(new FileInputStream(this.index)));
                for (int length = in.readInt(), i = 0; i < length; ++i) {
                    final String url = in.readUTF();
                    final String etag = in.readUTF();
                    final long time = in.readLong();
                    final long expireTime = in.readLong();
                    final CacheEntry entry = new CacheEntry(url, (etag.length() > 0) ? etag : null, time, expireTime);
                    this.entries.put(entry.getUrl(), entry);
                }
            }
            catch (IOException e) {
                DownloadThread.LOGGER.error("Failed to load cache index", (Throwable)e);
                this.entries = previousEntries;
            }
            finally {
                IOUtils.closeQuietly((InputStream)in);
            }
        }
    }
    
    private void saveIndex() {
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(this.index)));
            out.writeInt(this.entries.size());
            for (final Map.Entry<String, CacheEntry> mapEntry : this.entries.entrySet()) {
                final CacheEntry entry = mapEntry.getValue();
                out.writeUTF(entry.getUrl());
                out.writeUTF((entry.getEtag() == null) ? "" : entry.getEtag());
                out.writeLong(entry.getTime());
                out.writeLong(entry.getExpireTime());
            }
        }
        catch (IOException e) {
            DownloadThread.LOGGER.error("Failed to save cache index", (Throwable)e);
        }
        finally {
            IOUtils.closeQuietly((OutputStream)out);
        }
    }
    
    public void deleteEntry(final String url) {
        this.entries.remove(url);
        final File file = getFile(url);
        if (file.exists()) {
            file.delete();
        }
    }
    
    private static File getFile(final String url) {
        return new File(DownloadThread.TEXTURE_CACHE.cacheDirectory, Base64.encodeBase64String(url.getBytes()));
    }
    
    public static class CacheEntry
    {
        private String url;
        private String etag;
        private long time;
        private long expireTime;
        
        public CacheEntry(final String url, final String etag, final long time, final long expireTime) {
            this.url = url;
            this.etag = etag;
            this.time = time;
            this.expireTime = expireTime;
        }
        
        public void setEtag(final String etag) {
            this.etag = etag;
        }
        
        public void setTime(final long time) {
            this.time = time;
        }
        
        public void setExpireTime(final long expireTime) {
            this.expireTime = expireTime;
        }
        
        public String getUrl() {
            return this.url;
        }
        
        public String getEtag() {
            return this.etag;
        }
        
        public long getTime() {
            return this.time;
        }
        
        public long getExpireTime() {
            return this.expireTime;
        }
        
        public File getFile() {
            return TextureCache.getFile(this.url);
        }
    }
}
