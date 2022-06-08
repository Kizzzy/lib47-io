package cn.kizzzy.io;

import java.io.IOException;
import java.io.InputStream;

public class XorInputStream extends InputStream {
    
    private final InputStream in;
    
    private final int[] keys;
    
    private int index;
    
    public XorInputStream(InputStream in, int... keys) {
        this.in = in;
        this.keys = keys;
    }
    
    @Override
    public int read() throws IOException {
        int val = in.read();
        if (val == -1) {
            return -1;
        }
        val ^= keys[(index++) % keys.length];
        return val;
    }
}
