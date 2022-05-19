package cn.kizzzy.io;

import java.io.IOException;
import java.io.InputStream;

public class XorInputStream extends InputStream {
    
    private final InputStream in;
    
    private final byte[] keys;
    
    private int index;
    
    public XorInputStream(InputStream in, byte... keys) {
        this.in = in;
        this.keys = keys;
    }
    
    @Override
    public int read() throws IOException {
        return in.read() ^ keys[(index++) % keys.length];
    }
}
