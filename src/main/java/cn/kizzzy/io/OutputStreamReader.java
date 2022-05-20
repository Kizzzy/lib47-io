package cn.kizzzy.io;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamReader extends FullyWriter {
    
    private final OutputStream out;
    
    public OutputStreamReader(OutputStream out) {
        this.out = out;
    }
    
    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }
    
    @Override
    public void close() throws IOException {
        out.close();
    }
}
