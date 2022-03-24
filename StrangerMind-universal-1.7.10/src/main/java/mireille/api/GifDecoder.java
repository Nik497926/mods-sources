package mireille.api;

import java.util.*;
import java.awt.image.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class GifDecoder
{
    public static final int STATUS_OK = 0;
    public static final int STATUS_FORMAT_ERROR = 1;
    public static final int STATUS_OPEN_ERROR = 2;
    protected BufferedInputStream in;
    protected int status;
    protected int width;
    protected int height;
    protected boolean gctFlag;
    protected int gctSize;
    protected int loopCount;
    protected int[] gct;
    protected int[] lct;
    protected int[] act;
    protected int bgIndex;
    protected int bgColor;
    protected int lastBgColor;
    protected int pixelAspect;
    protected boolean lctFlag;
    protected boolean interlace;
    protected int lctSize;
    protected int ix;
    protected int iy;
    protected int iw;
    protected int ih;
    protected Rectangle lastRect;
    protected BufferedImage image;
    protected BufferedImage lastImage;
    protected byte[] block;
    protected int blockSize;
    protected int dispose;
    protected int lastDispose;
    protected boolean transparency;
    protected int delay;
    protected int transIndex;
    protected static final int MaxStackSize = 4096;
    protected short[] prefix;
    protected byte[] suffix;
    protected byte[] pixelStack;
    protected byte[] pixels;
    protected ArrayList<GifFrame> frames;
    protected int frameCount;
    
    public GifDecoder() {
        this.loopCount = 1;
        this.block = new byte[256];
        this.blockSize = 0;
        this.dispose = 0;
        this.lastDispose = 0;
        this.transparency = false;
        this.delay = 0;
    }
    
    public int getDelay(final int n) {
        this.delay = -1;
        if (n >= 0 && n < this.frameCount) {
            this.delay = this.frames.get(n).delay;
        }
        return this.delay;
    }
    
    public int getFrameCount() {
        return this.frameCount;
    }
    
    public BufferedImage getImage() {
        return this.getFrame(0);
    }
    
    public int getLoopCount() {
        return this.loopCount;
    }
    
    protected void setPixels() {
        final int[] dest = ((DataBufferInt)this.image.getRaster().getDataBuffer()).getData();
        if (this.lastDispose > 0) {
            if (this.lastDispose == 3) {
                final int pass = this.frameCount - 2;
                if (pass > 0) {
                    this.lastImage = this.getFrame(pass - 1);
                }
                else {
                    this.lastImage = null;
                }
            }
            if (this.lastImage != null) {
                final int[] prev = ((DataBufferInt)this.lastImage.getRaster().getDataBuffer()).getData();
                System.arraycopy(prev, 0, dest, 0, this.width * this.height);
                if (this.lastDispose == 2) {
                    final Graphics2D g = this.image.createGraphics();
                    Color c;
                    if (this.transparency) {
                        c = new Color(0, 0, 0, 0);
                    }
                    else {
                        c = new Color(this.lastBgColor);
                    }
                    g.setColor(c);
                    g.setComposite(AlphaComposite.Src);
                    g.fill(this.lastRect);
                    g.dispose();
                }
            }
        }
        int pass = 1;
        int inc = 8;
        int iline = 0;
        for (int i = 0; i < this.ih; ++i) {
            int line = i;
            if (this.interlace) {
                if (iline >= this.ih) {
                    switch (++pass) {
                        case 2: {
                            iline = 4;
                            break;
                        }
                        case 3: {
                            iline = 2;
                            inc = 4;
                            break;
                        }
                        case 4: {
                            iline = 1;
                            inc = 2;
                            break;
                        }
                    }
                }
                line = iline;
                iline += inc;
            }
            line += this.iy;
            if (line < this.height) {
                final int k = line * this.width;
                int dx = k + this.ix;
                int dlim = dx + this.iw;
                if (k + this.width < dlim) {
                    dlim = k + this.width;
                }
                int var10 = i * this.iw;
                while (dx < dlim) {
                    final int index = this.pixels[var10++] & 0xFF;
                    final int c2 = this.act[index];
                    if (c2 != 0) {
                        dest[dx] = c2;
                    }
                    ++dx;
                }
            }
        }
    }
    
    public BufferedImage getFrame(final int n) {
        BufferedImage im = null;
        if (n >= 0 && n < this.frameCount) {
            im = this.frames.get(n).image;
        }
        return im;
    }
    
    public Dimension getFrameSize() {
        return new Dimension(this.width, this.height);
    }
    
    public int read(final BufferedInputStream is) {
        this.init();
        if (is != null) {
            this.in = is;
            this.readHeader();
            if (!this.err()) {
                this.readContents();
                if (this.frameCount < 0) {
                    this.status = 1;
                }
            }
        }
        else {
            this.status = 2;
        }
        try {
            if (is != null) {
                is.close();
            }
        }
        catch (IOException var3) {
            var3.printStackTrace();
        }
        return this.status;
    }
    
    public int read(InputStream is) {
        this.init();
        if (is != null) {
            if (!(is instanceof BufferedInputStream)) {
                is = new BufferedInputStream(is);
            }
            this.in = (BufferedInputStream)is;
            this.readHeader();
            if (!this.err()) {
                this.readContents();
                if (this.frameCount < 0) {
                    this.status = 1;
                }
            }
        }
        else {
            this.status = 2;
        }
        try {
            if (is != null) {
                is.close();
            }
        }
        catch (IOException var3) {
            var3.printStackTrace();
        }
        return this.status;
    }
    
    public int read(String name) {
        this.status = 0;
        try {
            name = name.trim().toLowerCase();
            if (!name.startsWith("file:") && name.indexOf(":/") <= 0) {
                this.in = new BufferedInputStream(new FileInputStream(name));
            }
            else {
                final URL url = new URL(name);
                this.in = new BufferedInputStream(url.openStream());
            }
            this.status = this.read(this.in);
        }
        catch (IOException var3) {
            this.status = 2;
        }
        return this.status;
    }
    
    protected void decodeImageData() {
        final int NullCode = -1;
        final int npix = this.iw * this.ih;
        if (this.pixels == null || this.pixels.length < npix) {
            this.pixels = new byte[npix];
        }
        if (this.prefix == null) {
            this.prefix = new short[4096];
        }
        if (this.suffix == null) {
            this.suffix = new byte[4096];
        }
        if (this.pixelStack == null) {
            this.pixelStack = new byte[4097];
        }
        final int data_size = this.read();
        final int clear = 1 << data_size;
        final int end_of_information = clear + 1;
        int available = clear + 2;
        int old_code = NullCode;
        int code_size = data_size + 1;
        int code_mask = (1 << code_size) - 1;
        for (int code = 0; code < clear; ++code) {
            this.prefix[code] = 0;
            this.suffix[code] = (byte)code;
        }
        int bi = 0;
        int pi = 0;
        int top = 0;
        int first = 0;
        int count = 0;
        int bits = 0;
        int datum = 0;
        int i = 0;
        while (i < npix) {
            if (top == 0) {
                if (bits < code_size) {
                    if (count == 0) {
                        count = this.readBlock();
                        if (count <= 0) {
                            break;
                        }
                        bi = 0;
                    }
                    datum += (this.block[bi] & 0xFF) << bits;
                    bits += 8;
                    ++bi;
                    --count;
                    continue;
                }
                int code = datum & code_mask;
                datum >>= code_size;
                bits -= code_size;
                if (code > available) {
                    break;
                }
                if (code == end_of_information) {
                    break;
                }
                if (code == clear) {
                    code_size = data_size + 1;
                    code_mask = (1 << code_size) - 1;
                    available = clear + 2;
                    old_code = NullCode;
                    continue;
                }
                if (old_code == NullCode) {
                    this.pixelStack[top++] = this.suffix[code];
                    old_code = code;
                    first = code;
                    continue;
                }
                final int in_code;
                if ((in_code = code) == available) {
                    this.pixelStack[top++] = (byte)first;
                    code = old_code;
                }
                while (code > clear) {
                    this.pixelStack[top++] = this.suffix[code];
                    code = this.prefix[code];
                }
                first = (this.suffix[code] & 0xFF);
                if (available >= 4096) {
                    break;
                }
                this.pixelStack[top++] = (byte)first;
                this.prefix[available] = (short)old_code;
                this.suffix[available] = (byte)first;
                if ((++available & code_mask) == 0x0 && available < 4096) {
                    ++code_size;
                    code_mask += available;
                }
                old_code = in_code;
            }
            --top;
            this.pixels[pi++] = this.pixelStack[top];
            ++i;
        }
        for (i = pi; i < npix; ++i) {
            this.pixels[i] = 0;
        }
    }
    
    protected boolean err() {
        return this.status != 0;
    }
    
    protected void init() {
        this.status = 0;
        this.frameCount = 0;
        this.frames = new ArrayList<GifFrame>();
        this.gct = null;
        this.lct = null;
    }
    
    protected int read() {
        int curByte = 0;
        try {
            curByte = this.in.read();
        }
        catch (IOException var3) {
            this.status = 1;
        }
        return curByte;
    }
    
    protected int readBlock() {
        this.blockSize = this.read();
        int n = 0;
        if (this.blockSize > 0) {
            try {
                while (n < this.blockSize) {
                    final int count = this.in.read(this.block, n, this.blockSize - n);
                    if (count == -1) {
                        break;
                    }
                    n += count;
                }
            }
            catch (IOException var3) {
                var3.printStackTrace();
            }
            if (n < this.blockSize) {
                this.status = 1;
            }
        }
        return n;
    }
    
    protected int[] readColorTable(final int ncolors) {
        final int nbytes = 3 * ncolors;
        int[] tab = null;
        final byte[] c = new byte[nbytes];
        int n = 0;
        try {
            n = this.in.read(c);
        }
        catch (IOException var11) {
            var11.printStackTrace();
        }
        if (n < nbytes) {
            this.status = 1;
        }
        else {
            tab = new int[256];
            int i = 0;
            int var12 = 0;
            while (i < ncolors) {
                final int r = c[var12++] & 0xFF;
                final int g = c[var12++] & 0xFF;
                final int b = c[var12++] & 0xFF;
                tab[i++] = (0xFF000000 | r << 16 | g << 8 | b);
            }
        }
        return tab;
    }
    
    protected void readContents() {
        boolean done = false;
        while (!done && !this.err()) {
            int code = this.read();
            switch (code) {
                case 0: {
                    continue;
                }
                case 33: {
                    code = this.read();
                    switch (code) {
                        case 249: {
                            this.readGraphicControlExt();
                            continue;
                        }
                        case 255: {
                            this.readBlock();
                            final StringBuilder app = new StringBuilder();
                            for (int i = 0; i < 11; ++i) {
                                app.append((char)this.block[i]);
                            }
                            if (app.toString().equals("NETSCAPE2.0")) {
                                this.readNetscapeExt();
                                continue;
                            }
                            this.skip();
                            continue;
                        }
                        default: {
                            this.skip();
                            continue;
                        }
                    }
                }
                case 44: {
                    this.readImage();
                    continue;
                }
                case 59: {
                    done = true;
                    continue;
                }
                default: {
                    this.status = 1;
                    continue;
                }
            }
        }
    }
    
    protected void readGraphicControlExt() {
        this.read();
        final int packed = this.read();
        this.dispose = (packed & 0x1C) >> 2;
        if (this.dispose == 0) {
            this.dispose = 1;
        }
        this.transparency = ((packed & 0x1) != 0x0);
        this.delay = this.readShort() * 10;
        this.transIndex = this.read();
        this.read();
    }
    
    protected void readHeader() {
        final StringBuilder id = new StringBuilder();
        for (int i = 0; i < 6; ++i) {
            id.append((char)this.read());
        }
        if (!id.toString().startsWith("GIF")) {
            this.status = 1;
        }
        else {
            this.readLSD();
            if (this.gctFlag && !this.err()) {
                this.gct = this.readColorTable(this.gctSize);
                this.bgColor = this.gct[this.bgIndex];
            }
        }
    }
    
    protected void readImage() {
        this.ix = this.readShort();
        this.iy = this.readShort();
        this.iw = this.readShort();
        this.ih = this.readShort();
        final int packed = this.read();
        this.lctFlag = ((packed & 0x80) != 0x0);
        this.interlace = ((packed & 0x40) != 0x0);
        this.lctSize = 2 << (packed & 0x7);
        if (this.lctFlag) {
            this.lct = this.readColorTable(this.lctSize);
            this.act = this.lct;
        }
        else {
            this.act = this.gct;
            if (this.bgIndex == this.transIndex) {
                this.bgColor = 0;
            }
        }
        int save = 0;
        if (this.transparency) {
            save = this.act[this.transIndex];
            this.act[this.transIndex] = 0;
        }
        if (this.act == null) {
            this.status = 1;
        }
        if (!this.err()) {
            this.decodeImageData();
            this.skip();
            if (!this.err()) {
                ++this.frameCount;
                this.image = new BufferedImage(this.width, this.height, 3);
                this.setPixels();
                this.frames.add(new GifFrame(this.image, this.delay));
                if (this.transparency) {
                    this.act[this.transIndex] = save;
                }
                this.resetFrame();
            }
        }
    }
    
    protected void readLSD() {
        this.width = this.readShort();
        this.height = this.readShort();
        final int packed = this.read();
        this.gctFlag = ((packed & 0x80) != 0x0);
        this.gctSize = 2 << (packed & 0x7);
        this.bgIndex = this.read();
        this.pixelAspect = this.read();
    }
    
    protected void readNetscapeExt() {
        do {
            this.readBlock();
            if (this.block[0] == 1) {
                final int b1 = this.block[1] & 0xFF;
                final int b2 = this.block[2] & 0xFF;
                this.loopCount = (b2 << 8 | b1);
            }
        } while (this.blockSize > 0 && !this.err());
    }
    
    protected int readShort() {
        return this.read() | this.read() << 8;
    }
    
    protected void resetFrame() {
        this.lastDispose = this.dispose;
        this.lastRect = new Rectangle(this.ix, this.iy, this.iw, this.ih);
        this.lastImage = this.image;
        this.lastBgColor = this.bgColor;
        this.lct = null;
    }
    
    protected void skip() {
        do {
            this.readBlock();
        } while (this.blockSize > 0 && !this.err());
    }
    
    static class GifFrame
    {
        public BufferedImage image;
        public int delay;
        
        public GifFrame(final BufferedImage im, final int del) {
            this.image = im;
            this.delay = del;
        }
    }
}
