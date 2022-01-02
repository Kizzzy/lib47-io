package cn.kizzzy.io;

import java.io.DataOutput;
import java.io.IOException;

/**
 * extension of DataOutput interface
 *
 * @author Kizzzy
 * @see DataOutput
 * @see DataInputEx
 */
public interface DataOutputEx extends DataOutput {
    
    default void writeUnsignedInt(long value) throws IOException {
        writeInt((int) value);
    }
}
