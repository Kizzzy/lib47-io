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
        return readChar(isLittleEndian());
    }
    
    default char readChar(boolean littleEndian) throws IOException {
        int ch1 = read();
        int ch2 = read();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        if (littleEndian) {
            return (char) ((ch1 << 0) + (ch2 << 8));
        } else {
            return (char) ((ch1 << 8) + (ch2 << 0));
        }
    }
    
    default char[] readChars(int count) throws IOException {
        return readChars(count, isLittleEndian());
    }
    
    default char[] readChars(int count, boolean littleEndian) throws IOException {
        char[] arr = new char[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readChar(littleEndian);
        }
        return arr;
    }
    
    @Override
    default short readShort() throws IOException {
        return readShort(isLittleEndian());
    }
    
    default short readShort(boolean littleEndian) throws IOException {
        return (short) readUnsignedShort(littleEndian);
    }
    
    default short[] readShorts(int count) throws IOException {
        return readShorts(count, isLittleEndian());
    }
    
    default short[] readShorts(int count, boolean littleEndian) throws IOException {
        short[] arr = new short[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readShort(littleEndian);
        }
        return arr;
    }
    
    @Override
    default int readUnsignedShort() throws IOException {
        return readUnsignedShort(isLittleEndian());
    }
    
    default int readUnsignedShort(boolean littleEndian) throws IOException {
        int ch1 = read();
        int ch2 = read();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        if (littleEndian) {
            return (ch1 << 0) + (ch2 << 8);
        } else {
            return (ch1 << 8) + (ch2 << 0);
        }
    }
    
    default int[] readUnsignedShorts(int count) throws IOException {
        return readUnsignedShorts(count, isLittleEndian());
    }
    
    default int[] readUnsignedShorts(int count, boolean littleEndian) throws IOException {
        int[] arr = new int[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readUnsignedShort(littleEndian);
        }
        return arr;
    }
    
    @Override
    default int readInt() throws IOException {
        return readInt(isLittleEndian());
    }
    
    default int readInt(boolean littleEndian) throws IOException {
        return (int) readUnsignedInt(littleEndian);
    }
    
    default int[] readInts(int count) throws IOException {
        return readInts(count, isLittleEndian());
    }
    
    default int[] readInts(int count, boolean littleEndian) throws IOException {
        int[] arr = new int[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readInt(littleEndian);
        }
        return arr;
    }
    
    default long readUnsignedInt() throws IOException {
        return readUnsignedInt(isLittleEndian());
    }
    
    default long readUnsignedInt(boolean littleEndian) throws IOException {
        long ch1 = read();
        long ch2 = read();
        long ch3 = read();
        long ch4 = read();
        if ((ch1 | ch2 | ch3 | ch4) < 0)
            throw new EOFException();
        if (littleEndian) {
            return ((ch1 << 0) + (ch2 << 8) + (ch3 << 16) + (ch4 << 24));
        } else {
            return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
        }
    }
    
    default long[] readUnsignedInts(int count) throws IOException {
        return readUnsignedInts(count, isLittleEndian());
    }
    
    default long[] readUnsignedInts(int count, boolean littleEndian) throws IOException {
        long[] arr = new long[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readUnsignedInt(littleEndian);
        }
        return arr;
    }
    
    @Override
    default long readLong() throws IOException {
        return readLong(isLittleEndian());
    }
    
    default long readLong(boolean littleEndian) throws IOException {
        if (littleEndian) {
            return (this.readInt(true) & 0xFFFFFFFFL) + (((long) this.readInt(true)) << 32);
        } else {
            return ((long) (readInt(false)) << 32) + (readInt(false) & 0xFFFFFFFFL);
        }
    }
    
    default long[] readLongs(int count) throws IOException {
        return readLongs(count, isLittleEndian());
    }
    
    default long[] readLongs(int count, boolean littleEndian) throws IOException {
        long[] arr = new long[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readLong(littleEndian);
        }
        return arr;
    }
    
    @Override
    default float readFloat() throws IOException {
        return readFloat(isLittleEndian());
    }
    
    default float readFloat(boolean littleEndian) throws IOException {
        return Float.intBitsToFloat(readInt(littleEndian));
    }
    
    default float[] readFloats(int count) throws IOException {
        return readFloats(count, isLittleEndian());
    }
    
    default float[] readFloats(int count, boolean littleEndian) throws IOException {
        float[] arr = new float[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readFloat(littleEndian);
        }
        return arr;
    }
    
    @Override
    default double readDouble() throws IOException {
        return readDouble(isLittleEndian());
    }
    
    default double readDouble(boolean littleEndian) throws IOException {
        return Double.longBitsToDouble(readLong(littleEndian));
    }
    
    default double[] readDoubles(int count) throws IOException {
        return readDoubles(count, isLittleEndian());
    }
    
    default double[] readDoubles(int count, boolean littleEndian) throws IOException {
        double[] arr = new double[count];
        for (int i = 0, n = arr.length; i < n; ++i) {
            arr[i] = readDouble(littleEndian);
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
        
        String s = new String(pathBuf, offset, count - offset, charset);
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
    
    default void copyTo(IFullyWriter writer) throws IOException {
        copyTo(writer, length());
    }
    
    default void copyTo(IFullyWriter writer, long size) throws IOException {
        if (size > length()) {
            throw new IndexOutOfBoundsException("size is greater then size");
        }
        
        byte[] buffer = new byte[8192];
        for (int n = 0, rest = (int) size; (n = read(buffer)) > 0 && rest > 0; rest -= n) {
            n = Math.min(n, rest);
            writer.write(buffer, 0, n);
        }
    }
    
    InputStream asInputStream();
}
