package cn.kizzzy.io;

import java.io.DataInputStream;
import java.io.InputStream;

/**
 * extension of DataInputStream
 *
 * @author Kizzzy
 * @see DataInputStream
 */
public class DataInputStreamEx extends DataInputStream implements DataInputEx {
    
    public DataInputStreamEx(InputStream in) {
        super(in);
    }
}
