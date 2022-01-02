package cn.kizzzy.io;

import java.io.IOException;
import java.io.InputStream;

public class SubStream extends DataInputStreamEx {
    
    private final long offset;
    
    public final long size;
    
    private boolean first;
    
    private long position;
    
    public SubStream(InputStream inputStream, long offset, long size) {
        super(inputStream);
        this.offset = offset;
        this.size = size;
        first = true;
    }
    
    public long getPosition() {
        return position;
    }
    
    @Override
    public int read() throws IOException {
        if (first && offset != 0) {
            first = false;
            skip(offset);
        }
        position++;
        return in.read();
    }
    
    @Override
    public long skip(long n) throws IOException {
        long count = super.skip(n);
        position += count;
        return count;
    }
}
