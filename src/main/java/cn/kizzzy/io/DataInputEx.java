package cn.kizzzy.io;

import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * extension of DataInput interface
 *
 * @author Kizzzy
 * @see DataInput
 * @see DataOutputEx
 */
public interface DataInputEx extends DataInput {
    
    int read() throws IOException;
    
    default byte[] readBytes(int count) throws IOException {
        byte[] arr = new byte[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = (byte) read();
        }
        return arr;
    }
    
    default int[] readUnsignedBytes(int count) throws IOException {
        int[] arr = new int[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = read();
        }
        return arr;
    }
    
    default int[] readShorts(int count) throws IOException {
        int[] arr = new int[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readShort();
        }
        return arr;
    }
    
    /**
     * Ex suffix means method will handle bytes in LITTLE_ENDIAN style
     *
     * @see DataInputEx#readShort()
     */
    default short readShortEx() throws IOException {
        int ch1 = this.read();
        int ch2 = this.read();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        return (short) ((ch1 << 0) + (ch2 << 8));
    }
    
    default int[] readShortExs(int count) throws IOException {
        int[] arr = new int[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readShortEx();
        }
        return arr;
    }
    
    /**
     * Ex suffix means method will handle bytes in LITTLE_ENDIAN style
     *
     * @see DataInput#readShort()
     */
    default int readUnsignedShortEx() throws IOException {
        int ch1 = this.read();
        int ch2 = this.read();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        return (ch1 << 0) + (ch2 << 8);
    }
    
    default int[] readUnsignedShortExs(int count) throws IOException {
        int[] arr = new int[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readUnsignedShortEx();
        }
        return arr;
    }
    
    default long[] readInts(int count) throws IOException {
        long[] arr = new long[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readInt();
        }
        return arr;
    }
    
    /**
     * @see DataInput#readInt() ()
     */
    default long readUnsignedInt() throws IOException {
        return readInt() & 0xffffffffL;
    }
    
    default long[] readUnsignedInts(int count) throws IOException {
        long[] arr = new long[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readUnsignedInt();
        }
        return arr;
    }
    
    /**
     * Ex suffix means method will handle bytes in LITTLE_ENDIAN style
     *
     * @see DataInput#readInt()
     */
    default int readIntEx() throws IOException {
        int ch1 = this.read();
        int ch2 = this.read();
        int ch3 = this.read();
        int ch4 = this.read();
        if ((ch1 | ch2 | ch3 | ch4) < 0)
            throw new EOFException();
        return ((ch1 << 0) + (ch2 << 8) + (ch3 << 16) + (ch4 << 24));
    }
    
    default long[] readIntExs(int count) throws IOException {
        long[] arr = new long[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readIntEx();
        }
        return arr;
    }
    
    /**
     * Ex suffix means method will handle bytes in LITTLE_ENDIAN style
     *
     * @see DataInputEx#readUnsignedInt()
     */
    default long readUnsignedIntEx() throws IOException {
        return readIntEx() & 0xffffffffL;
    }
    
    default long[] readUnsignedIntExs(int count) throws IOException {
        long[] arr = new long[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readUnsignedIntEx();
        }
        return arr;
    }
    
    /**
     * Ex suffix means method will handle bytes in LITTLE_ENDIAN style
     *
     * @see DataInput#readFloat()
     */
    default float readFloatEx() throws IOException {
        return Float.intBitsToFloat(readIntEx());
    }
    
    default String readString(int count) throws IOException {
        return readString(count, 0, StandardCharsets.UTF_8);
    }
    
    default String readString(int count, Charset charset) throws IOException {
        return readString(count, 0, charset);
    }
    
    default String readString(int count, int offset) throws IOException {
        return readString(count, offset, StandardCharsets.UTF_8);
    }
    
    default String readString(int count, int offset, Charset charset) throws IOException {
        byte[] pathBuf = readBytes(count);
        
        for (int m = offset; m < pathBuf.length; ++m) {
            if (pathBuf[m] == 0) {
                return new String(pathBuf, offset, m - offset, charset);
            }
            if (m == pathBuf.length - 1) {
                return new String(pathBuf, offset, m - offset + 1, charset);
            }
        }
        return null;
    }
}
