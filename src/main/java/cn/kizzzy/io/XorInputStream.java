package cn.kizzzy.io;

import java.io.IOException;
import java.io.InputStream;

public class XorInputStream extends InputStream {
    
    private final InputStream in;
    
    private final int key;
    
    public XorInputStream(InputStream in, int key) {
        this.in = in;
        this.key = key;
    }
    
    @Override
    public int read() throws IOException {
        return in.read() ^ key;
    }
}
