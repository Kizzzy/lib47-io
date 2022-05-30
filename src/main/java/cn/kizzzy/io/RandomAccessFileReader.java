package cn.kizzzy.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileReader extends FullyReader {
    
    private final RandomAccessFile file;
    
    public RandomAccessFileReader(RandomAccessFile file) {
        this.file = file;
    }
    
    @Override
    public long length() throws IOException {
        return file.length();
    }
    
    @Override
    public long position() throws IOException {
        return file.getFilePointer();
    }
    
    @Override
    public int read() throws IOException {
        return file.read();
    }
    
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return file.read(b, off, len);
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
    
    @Override
    public void close() throws IOException {
        file.close();
    }
}
