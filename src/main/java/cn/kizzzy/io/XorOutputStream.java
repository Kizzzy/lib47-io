package cn.kizzzy.io;

import java.io.IOException;
import java.io.OutputStream;

public class XorOutputStream extends OutputStream {
    
    private final OutputStream out;
    
    private final byte[] keys;
    
    private int index;
    
    public XorOutputStream(OutputStream out, byte... keys) {
        this.out = out;
        this.keys = keys;
    }
    
    @Override
    public void write(int b) throws IOException {
        out.write(b ^ keys[(index++) % keys.length]);
    }
}
