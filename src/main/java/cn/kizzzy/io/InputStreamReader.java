package cn.kizzzy.io;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamReader extends FullyReader {
    
    private final InputStream in;
    private final long size;
    
    private long position;
    private byte[] buffer_0 = new byte[1];
    
    public InputStreamReader(InputStream inputStream, long size) {
        this.in = inputStream;
        this.size = size;
    }
    
    @Override
    public long length() throws IOException {
        return size;
    }
    
    @Override
    public long position() throws IOException {
        return position;
    }
    
    @Override
    public int read() throws IOException {
        int n = read(buffer_0);
        if (n > 0) {
            return buffer_0[0] & 0xFF;
        }
        return -1;
    }
    
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int n = in.read(b, off, len);
        if (n > 0) {
            position += n;
        }
        return n;
    }
    
    @Override
    public void seek(long pos, SeekType seekType) throws IOException {
        switch (seekType) {
            case BEGIN:
                throw new IOException("seek(begin) not support");
            case CURRENT:
                skip(pos);
                break;
        }
    }
    
    @Override
    public void close() throws IOException {
        in.close();
    }
}
