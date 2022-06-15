package cn.kizzzy.io;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class ByteArrayOutputStreamReader extends ByteArrayOutputStream implements IFullyWriter {
    
    @Override
    public OutputStream asOutputStream() {
        return this;
    }
}
