package cn.kizzzy.io;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class ByteArrayOutputStreamReader extends ByteArrayOutputStream implements IFullyWriter {
    
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
    public OutputStream asOutputStream() {
        return this;
    }
}
