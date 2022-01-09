package cn.kizzzy.io;

import java.io.IOException;

public class SliceFullReader extends FullyReader {
    
    private final IFullyReader parent;
    private final long startInParent;
    private final long endInParent;
    
    private long currentInParent;
    
    public SliceFullReader(IFullyReader parent) throws IOException {
        this(parent, 0, parent.length());
    }
    
    public SliceFullReader(IFullyReader parent, long startInParent, long size) {
        this.parent = parent;
        this.startInParent = startInParent;
        this.endInParent = startInParent + size;
        
        currentInParent = startInParent;
    }
    
    @Override
    public long length() throws IOException {
        return endInParent - startInParent;
    }
    
    @Override
    public long position() throws IOException {
        return currentInParent - startInParent;
    }
    
    @Override
    public int read() throws IOException {
        if (parent.position() != currentInParent) {
            parent.seek(currentInParent, SeekType.BEGIN);
        }
        currentInParent++;
        return parent.read();
    }
    
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (parent.position() != currentInParent) {
            parent.seek(currentInParent, SeekType.BEGIN);
        }
        if (currentInParent + len > endInParent) {
            len = (int) (endInParent - currentInParent);
        }
        int num = parent.read(b, off, len);
        currentInParent += num;
        return num;
    }
    
    @Override
    public String readLine() throws IOException {
        return parent.readLine();
    }
    
    @Override
    public void seek(long pos, SeekType seekType) throws IOException {
        switch (seekType) {
            case BEGIN:
                currentInParent = startInParent + pos;
                break;
            case CURRENT:
                currentInParent += pos;
                break;
        }
        parent.seek(currentInParent, SeekType.BEGIN);
    }
}
