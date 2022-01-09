package cn.kizzzy.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ByteArrayInputStreamReader extends ByteArrayInputStream implements IFullyReader {
    
    public ByteArrayInputStreamReader(byte[] buf) {
        super(buf);
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
    public String readLine() throws IOException {
        throw new UnsupportedEncodingException();
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
}
