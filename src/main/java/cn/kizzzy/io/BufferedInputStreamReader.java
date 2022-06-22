package cn.kizzzy.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BufferedInputStreamReader extends BufferedInputStream implements IFullyReader {
    
    private final long size;
    
    private boolean littleEndian = false;
    
    public BufferedInputStreamReader(InputStream in, long size) {
        super(in, (int) size);
        this.size = size;
    }
    
    @Override
    public boolean isLittleEndian() {
        return littleEndian;
    }
    
    @Override
    public void setLittleEndian(boolean littleEndian) {
        this.littleEndian = littleEndian;
    }
    
    @Override
    public long length() throws IOException {
        return size;
    }
    
    @Override
    public long position() throws IOException {
        return pos;
    }
    
    @Override
    public void seek(long pos, SeekType seekType) throws IOException {
        switch (seekType) {
            case BEGIN:
                this.pos = (int) pos;
            case CURRENT:
                skip(pos);
        }
    }
    
    @Override
    public InputStream asInputStream() {
        return this;
    }
}
