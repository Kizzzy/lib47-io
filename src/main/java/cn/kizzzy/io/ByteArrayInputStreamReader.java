package cn.kizzzy.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteArrayInputStreamReader extends ByteArrayInputStream implements IFullyReader {
    
    private boolean littleEndian = false;
    
    public ByteArrayInputStreamReader(byte[] buf) {
        super(buf);
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
        return count;
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
                break;
            case CURRENT:
                this.pos += pos;
                break;
        }
    }
    
    @Override
    public InputStream asInputStream() {
        return this;
    }
}
