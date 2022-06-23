/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.converter;

import javazoom.jl.converter.WaveFile;
import javazoom.jl.decoder.Obuffer;

public class WaveFileObuffer
extends Obuffer {
    private short[] buffer;
    private short[] bufferp;
    private int channels;
    private WaveFile outWave;
    short[] myBuffer = new short[2];

    public WaveFileObuffer(int number_of_channels, int freq, String FileName) {
        if (FileName == null) {
            throw new NullPointerException("FileName");
        }
        this.buffer = new short[2304];
        this.bufferp = new short[2];
        this.channels = number_of_channels;
        for (int i = 0; i < number_of_channels; ++i) {
            this.bufferp[i] = (short)i;
        }
        this.outWave = new WaveFile();
        int rc = this.outWave.OpenForWrite(FileName, freq, (short)16, (short)this.channels);
    }

    @Override
    public void append(int channel, short value) {
        this.buffer[this.bufferp[channel]] = value;
        int n = channel;
        this.bufferp[n] = (short)(this.bufferp[n] + this.channels);
    }

    @Override
    public void write_buffer(int val) {
        boolean k = false;
        int rc = 0;
        rc = this.outWave.WriteData(this.buffer, this.bufferp[0]);
        for (int i = 0; i < this.channels; ++i) {
            this.bufferp[i] = (short)i;
        }
    }

    @Override
    public void close() {
        this.outWave.Close();
    }

    @Override
    public void clear_buffer() {
    }

    @Override
    public void set_stop_flag() {
    }
}

