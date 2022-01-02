package cn.kizzzy.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

/**
 * use to resolve over handled abd inputstream
 *
 * @author Kizzzy
 * @see FilterInputStream
 */
public abstract class AdbInputStream extends FilterInputStream {
    protected final Stack<Integer> backups = new Stack<>();
    
    protected final int value;
    protected final int[] features;
    
    public AdbInputStream(InputStream in) {
        this(in, null, 0);
    }
    
    public AdbInputStream(InputStream in, int[] features, int value) {
        super(in);
        this.features = features;
        this.value = value;
    }
    
    @Override
    public int read() throws IOException {
        return readImpl();
    }
    
    protected abstract int readImpl() throws IOException;
    
    protected int next0() throws IOException {
        if (!backups.empty()) {
            return backups.pop();
        }
        return in.read();
    }
}
