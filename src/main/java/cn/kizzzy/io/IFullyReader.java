package cn.kizzzy.io;

import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public interface IFullyReader extends DataInput, Closeable {
    
    boolean isLittleEndian();
    
    void setLittleEndian(boolean littleEndian);
    
    long length() throws IOException;
    
    long position() throws IOException;
    
    void seek(long pos, SeekType seekType) throws IOException;
    
    int read() throws IOException;
    
    default int read(byte[] buffer) throws IOException {
        return read(buffer, 0, buffer.length);
    }
    
    int read(byte[] b, int off, int len) throws IOException;
    
    default byte[] readAll() throws IOException {
        byte[] buf = new byte[(int) length()];
        read(buf);
        return buf;
    }
    
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
        return (byte) readUnsignedByte();
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
    
    default short[] readUnsignedBytes(int count) throws IOException {
        short[] arr = new short[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = (short) readUnsignedByte();
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
    
    default char[] readChars(int count) throws IOException {
        char[] arr = new char[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readChar();
        }
        return arr;
    }
    
    @Override
    default short readShort() throws IOException {
        return (short) readUnsignedShort();
    }
    
    default short[] readShorts(int count) throws IOException {
        short[] arr = new short[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readShort();
        }
        return arr;
    }
    
    @Override
    default int readUnsignedShort() throws IOException {
        int ch1 = read();
        int ch2 = read();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        if (isLittleEndian()) {
            return (ch1 << 0) + (ch2 << 8);
        } else {
            return (ch1 << 8) + (ch2 << 0);
        }
    }
    
    default int[] readUnsignedShorts(int count) throws IOException {
        int[] arr = new int[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readUnsignedShort();
        }
        return arr;
    }
    
    @Override
    default int readInt() throws IOException {
        return (int) readUnsignedInt();
    }
    
    default int[] readInts(int count) throws IOException {
        int[] arr = new int[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readInt();
        }
        return arr;
    }
    
    default long readUnsignedInt() throws IOException {
        long ch1 = read();
        long ch2 = read();
        long ch3 = read();
        long ch4 = read();
        if ((ch1 | ch2 | ch3 | ch4) < 0)
            throw new EOFException();
        if (isLittleEndian()) {
            return ((ch1 << 0) + (ch2 << 8) + (ch3 << 16) + (ch4 << 24));
        } else {
            return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
        }
    }
    
    default long[] readUnsignedInts(int count) throws IOException {
        long[] arr = new long[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readUnsignedInt();
        }
        return arr;
    }
    
    @Override
    default long readLong() throws IOException {
        if (isLittleEndian()) {
            return (this.readInt() & 0xFFFFFFFFL) + (((long) this.readInt()) << 32);
        } else {
            return ((long) (readInt()) << 32) + (readInt() & 0xFFFFFFFFL);
        }
    }
    
    default long[] readLongs(int count) throws IOException {
        long[] arr = new long[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readLong();
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
    
    @Override
    default String readUTF() throws IOException {
        return DataInputStream.readUTF(this);
    }
    
    default String readString() throws IOException {
        StringBuilder builder = new StringBuilder();
        for (int val = read(); !(val == 0 || val == -1); val = read()) {
            builder.append((char) val);
        }
        return builder.toString();
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
        
        String s = new String(pathBuf, offset, count, charset);
        for (int i = 0, n = s.length(); i < n; ++i) {
            if (s.charAt(i) == 0) {
                return s.substring(0, i);
            }
        }
        return s;
    }
    
    @Override
    default String readLine() throws IOException {
        StringBuilder input = new StringBuilder();
        int c = -1;
        boolean eol = false;
        
        while (!eol) {
            switch (c = read()) {
                case -1:
                case '\n':
                    eol = true;
                    break;
                case '\r':
                    eol = true;
                    long cur = position();
                    if ((read()) != '\n') {
                        seek(cur, SeekType.BEGIN);
                    }
                    break;
                default:
                    input.append((char) c);
                    break;
            }
        }
        
        if ((c == -1) && (input.length() == 0)) {
            return null;
        }
        return input.toString();
    }
    
    InputStream asInputStream();
}
