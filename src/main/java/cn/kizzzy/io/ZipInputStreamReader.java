package cn.kizzzy.io;

import java.io.IOException;
import java.io.InputStream;

public class ZipInputStreamReader extends FullyReader {
    
    private final InputStream in;
    private final long size;
    
    public ZipInputStreamReader(InputStream inputStream, long size) {
        this.in = inputStream;
        this.size = size;
    }
    
    @Override
    public long length() throws IOException {
        return size;
    }
    
    @Override
    public long position() throws IOException {
        // todo
        return 0;
    }
    
    @Override
    public int read() throws IOException {
        return in.read();
    }
    
    @Override
    public String readLine() throws IOException {
        // todo
        return null;
    }
    
    @Override
    public void seek(long pos, SeekType seekType) throws IOException {
        // todo
    }
}
