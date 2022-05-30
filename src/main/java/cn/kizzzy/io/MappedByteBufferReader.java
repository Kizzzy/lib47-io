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
