package cn.kizzzy.io;

import java.io.OutputStream;

public abstract class FullyWriter extends OutputStream implements IFullyWriter {
    
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
