package cn.kizzzy.io;

import java.io.IOException;
import java.io.OutputStream;

public class XorOutputStream extends OutputStream {
    
    private final OutputStream out;
    
    private final int key;
    
    public XorOutputStream(OutputStream out, int key) {
        this.out = out;
        this.key = key;
    }
    
    @Override
    public void write(int b) throws IOException {
        out.write(b ^ key);
    }
}
