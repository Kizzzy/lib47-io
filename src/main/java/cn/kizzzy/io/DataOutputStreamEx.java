package cn.kizzzy.io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * extension of DataOutputStream
 *
 * @author Kizzzy
 * @see DataOutputStream
 */
public class DataOutputStreamEx extends DataOutputStream {
    
    public DataOutputStreamEx(OutputStream out) {
        super(out);
    }
    
    /**
     * Ex suffix means method will handle bytes in LITTLE_ENDIAN style
     *
     * @see DataOutputStream#writeShort(int)
     */
    public void writeUnsignedShortEx(int v) throws IOException {
        out.write((v >>> 0) & 0xFF);
        out.write((v >>> 8) & 0xFF);
        incCount(2);
    }
    
    /**
     * Ex suffix means method will handle bytes in LITTLE_ENDIAN style
     *
     * @see DataOutputStream#writeInt(int)
     */
    public void writeIntEx(int v) throws IOException {
        out.write((v >>> 0) & 0xFF);
        out.write((v >>> 8) & 0xFF);
        out.write((v >>> 16) & 0xFF);
        out.write((v >>> 24) & 0xFF);
        incCount(4);
    }
    
    /**
     * Ex suffix means method will handle bytes in LITTLE_ENDIAN style
     *
     * @see DataOutputStream#writeInt(int)
     */
    public void writeUnsignedIntEx(long value) throws IOException {
        writeIntEx((int) value);
    }
    
    /**
     * copy from DataOutputStream
     *
     * @see DataOutputStream#incCount(int)
     */
    private void incCount(int value) {
        int temp = written + value;
        if (temp < 0) {
            temp = Integer.MAX_VALUE;
        }
        written = temp;
    }
}
