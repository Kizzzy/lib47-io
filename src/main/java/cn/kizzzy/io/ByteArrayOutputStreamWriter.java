package cn.kizzzy.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ByteArrayOutputStreamWriter extends ByteArrayOutputStream implements IFullyWriter {
    
    private boolean littleEndian;
    
    @Override
    public boolean isLittleEndian() {
        return littleEndian;
    }
    
    @Override
    public void setLittleEndian(boolean littleEndian) {
        this.littleEndian = littleEndian;
    }
    
    @Override
    public long position() throws IOException {
        return count;
    }
    
    @Override
    public void seek(long pos, SeekType seekType) throws IOException {
        throw new IOException("seek not support");
    }
    
    @Override
    public OutputStream asOutputStream() {
        return this;
    }
}
