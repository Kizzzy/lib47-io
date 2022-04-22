package cn.kizzzy.io;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public interface IFullyReader extends DataInput {
    
    long length() throws IOException;
    
    long position() throws IOException;
    
    void seek(long pos, SeekType seekType) throws IOException;
    
    int read() throws IOException;
    
    int read(byte b[], int off, int len) throws IOException;
    
    @Override
    default int skipBytes(int n) throws IOException {
        long pos;
        long len;
        long newpos;
        
        if (n <= 0) {
            return 0;
        }
        pos = position();
        len = length();
        newpos = pos + n;
        if (newpos > len) {
            newpos = len;
        }
        seek(newpos, SeekType.CURRENT);
        
        /* return the actual number of bytes skipped */
        return (int) (newpos - pos);
    }
    
    @Override
    default void readFully(byte[] b) throws IOException {
        readFully(b, 0, b.length);
    }
    
    @Override
    default void readFully(byte[] b, int off, int len) throws IOException {
        if (len < 0)
            throw new IndexOutOfBoundsException();
        int n = 0;
        while (n < len) {
            int count = read(b, off + n, len - n);
            if (count < 0)
                throw new EOFException();
            n += count;
        }
    }
    
    @Override
    default boolean readBoolean() throws IOException {
        int ch = this.read();
        if (ch < 0)
            throw new EOFException();
        return (ch != 0);
    }
    
    default boolean[] readBooleans(int count) throws IOException {
        boolean[] arr = new boolean[count];
        for (int i = 0; i < count; ++i) {
            arr[i] = readBoolean();
        }
        return arr;
    }
    
    @Override
    default byte readByte() throws IOException {
        int ch = read();
        if (ch < 0)
            throw new EOFException();
        return (byte) (ch);
    }
    
    default byte[] readBytes(int count) throws IOException {
        byte[] arr = new byte[count];
        readFully(arr);
        return arr;
    }
    
    @Override
    default int readUnsignedByte() throws IOException {
        int ch = read();
        if (ch < 0)
            throw new EOFException();
        return ch;
    }
    
    default int[] readUnsignedBytes(int count) throws IOException {
        int[] arr = new int[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = read();
        }
        return arr;
    }
    
    @Override
    default short readShort() throws IOException {
        int ch1 = read();
        int ch2 = read();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        return (short) ((ch1 << 8) + (ch2 << 0));
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
     * @see IFullyReader#readShort()
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
    
    @Override
    default int readUnsignedShort() throws IOException {
        int ch1 = read();
        int ch2 = read();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        return (ch1 << 8) + (ch2 << 0);
    }
    
    default int[] readUnsignedShorts(int count) throws IOException {
        int[] arr = new int[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readUnsignedShort();
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
    
    @Override
    default char readChar() throws IOException {
        int ch1 = read();
        int ch2 = read();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        return (char) ((ch1 << 8) + (ch2 << 0));
    }
    
    @Override
    default int readInt() throws IOException {
        int ch1 = read();
        int ch2 = read();
        int ch3 = read();
        int ch4 = read();
        if ((ch1 | ch2 | ch3 | ch4) < 0)
            throw new EOFException();
        return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
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
     * @see IFullyReader#readUnsignedInt()
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
    
    @Override
    default long readLong() throws IOException {
        return ((long) (readInt()) << 32) + (readInt() & 0xFFFFFFFFL);
    }
    
    default long[] readLongs(int count) throws IOException {
        long[] arr = new long[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readLong();
        }
        return arr;
    }
    
    default long readLongEx() throws IOException {
        return ((long) (readIntEx()) << 32) + (readIntEx() & 0xFFFFFFFFL);
    }
    
    default long[] readLongExs(int count) throws IOException {
        long[] arr = new long[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readLongEx();
        }
        return arr;
    }
    
    @Override
    default float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }
    
    default float[] readFloats(int count) throws IOException {
        float[] arr = new float[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readFloat();
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
    
    default float[] readFloatExs(int count) throws IOException {
        float[] arr = new float[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readFloatEx();
        }
        return arr;
    }
    
    @Override
    default double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }
    
    default double[] readDoubles(int count) throws IOException {
        double[] arr = new double[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readDouble();
        }
        return arr;
    }
    
    default double readDoubleEx() throws IOException {
        return Double.longBitsToDouble(readLongEx());
    }
    
    default double[] readDoubleExs(int count) throws IOException {
        double[] arr = new double[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readDoubleEx();
        }
        return arr;
    }
    
    @Override
    default String readUTF() throws IOException {
        return DataInputStream.readUTF(this);
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
