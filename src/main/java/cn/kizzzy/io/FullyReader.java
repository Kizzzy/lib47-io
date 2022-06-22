package cn.kizzzy.io;

import java.io.InputStream;

public abstract class FullyReader extends InputStream implements IFullyReader {
    
    private boolean littleEndian = false;
    
    @Override
    public boolean isLittleEndian() {
        return littleEndian;
    }
    
    @Override
    public void setLittleEndian(boolean littleEndian) {
        this.littleEndian = littleEndian;
    }
    
    @Override
    public InputStream asInputStream() {
        return this;
    }
}
