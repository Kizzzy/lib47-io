package cn.kizzzy.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BufferedInputStreamReader extends BufferedInputStream implements IFullyReader {
    
    private final IFullyReader reader;
    
    private boolean littleEndian = false;
    
    public BufferedInputStreamReader(FullyReader in) {
        super(in);
        this.reader = in;
    }
    
    @Override
    public boolean isLittleEndian() {
        return littleEndian;
    }
    
    @Override
    public void setLittleEndian(boolean littleEndian) {
        this.littleEndian = littleEndian;
    }
    
    @Override
    public long length() throws IOException {
        return reader.length();
    }
    
    @Override
    public long position() throws IOException {
        return reader.position();
    }
    
    @Override
    public void seek(long pos, SeekType seekType) throws IOException {
        reader.seek(pos, seekType);
        
        this.pos = 0;
        this.count = 0;
    }
    
    @Override
    public InputStream asInputStream() {
        return this;
    }
}
