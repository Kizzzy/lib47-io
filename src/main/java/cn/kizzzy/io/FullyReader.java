package cn.kizzzy.io;

import java.io.InputStream;

public abstract class FullyReader extends InputStream implements IFullyReader {
    
    @Override
    public InputStream asInputStream() {
        return this;
    }
}
