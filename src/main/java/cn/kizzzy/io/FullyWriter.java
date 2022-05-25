package cn.kizzzy.io;

import java.io.OutputStream;

public abstract class FullyWriter extends OutputStream implements IFullyWriter {
    
    @Override
    public OutputStream asOutputStream() {
        return this;
    }
}
