package cn.kizzzy.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileWriter extends FullyWriter {
    
    private final RandomAccessFile file;
    
    public RandomAccessFileWriter(RandomAccessFile file) {
        this.file = file;
    }
    
    @Override
    public void write(int b) throws IOException {
        file.write(b);
    }
    
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        file.write(b, off, len);
    }
}
