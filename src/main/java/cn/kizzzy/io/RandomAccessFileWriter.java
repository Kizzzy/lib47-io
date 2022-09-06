package cn.kizzzy.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileWriter extends FullyWriter {
    
    private final RandomAccessFile file;
    
    public RandomAccessFileWriter(String path) throws IOException {
        this(new RandomAccessFile(path, "rw"));
    }
    
    public RandomAccessFileWriter(RandomAccessFile file) throws IOException {
        this.file = file;
        file.setLength(0);
    }
    
    @Override
    public void write(int b) throws IOException {
        file.write(b);
    }
    
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        file.write(b, off, len);
    }
    
    @Override
    public void close() throws IOException {
        file.close();
    }
    
    @Override
    public long position() throws IOException {
        return file.getFilePointer();
    }
    
    @Override
    public void seek(long pos, SeekType seekType) throws IOException {
        switch (seekType) {
            case BEGIN:
                file.seek(pos);
                break;
            case CURRENT:
                file.seek(position() + pos);
                break;
        }
    }
}
