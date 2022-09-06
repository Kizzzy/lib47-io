package cn.kizzzy.io;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamReader extends FullyWriter {
    
    private final OutputStream out;
    
    private long position;
    private byte[] buffer_0 = new byte[1];
    
    public OutputStreamReader(OutputStream out) {
        this.out = out;
    }
    
    @Override
    public long position() throws IOException {
        return position;
    }
    
    @Override
    public void write(int b) throws IOException {
        buffer_0[0] = (byte) b;
        write(buffer_0);
    }
    
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        out.write(b, off, len);
        position += len;
    }
    
    @Override
    public void seek(long pos, SeekType seekType) throws IOException {
        throw new IOException("seek not support");
    }
    
    @Override
    public void close() throws IOException {
        out.close();
    }
}
