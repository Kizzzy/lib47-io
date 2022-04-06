package cn.kizzzy.io;

import java.io.IOException;
import java.nio.MappedByteBuffer;

public class MappedByteBufferReader extends FullyReader {
    
    private final MappedByteBuffer byteBuffer;
    
    public MappedByteBufferReader(MappedByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }
    
    @Override
    public long length() throws IOException {
        return byteBuffer.capacity();
    }
    
    @Override
    public long position() throws IOException {
        return byteBuffer.position();
    }
    
    @Override
    public int read() throws IOException {
        return byteBuffer.get();
    }
    
    @Override
    public String readLine() throws IOException {
        StringBuffer input = new StringBuffer();
        int c = -1;
        boolean eol = false;
        
        while (!eol) {
            switch (c = read()) {
                case -1:
                case '\n':
                    eol = true;
                    break;
                case '\r':
                    eol = true;
                    long cur = position();
                    if ((read()) != '\n') {
                        seek(cur, SeekType.BEGIN);
                    }
                    break;
                default:
                    input.append((char) c);
                    break;
            }
        }
        
        if ((c == -1) && (input.length() == 0)) {
            return null;
        }
        return input.toString();
    }
    
    @Override
    public void seek(long pos, SeekType seekType) throws IOException {
        switch (seekType) {
            case BEGIN:
                byteBuffer.position((int) pos);
                break;
            case CURRENT:
                byteBuffer.position((int) (position() + pos));
                break;
        }
    }
}
