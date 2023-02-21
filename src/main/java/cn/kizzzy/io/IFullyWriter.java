package cn.kizzzy.io;

import java.io.Closeable;
import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public interface IFullyWriter extends DataOutput, Closeable {
    
    long position() throws IOException;
    
    void seek(long pos, SeekType seekType) throws IOException;
    
    /* *********************************************************
        Boolean
     ********************************************************* */
    
    @Override
    default void writeBoolean(boolean v) throws IOException {
        write(v ? 1 : 0);
    }
    
    default void writeBooleans(boolean[] arr) throws IOException {
        writeBooleans(arr, 0, arr.length);
    }
    
    default void writeBooleans(boolean[] arr, int off, int len) throws IOException {
        if (off < 0 || len < 0 || (off + len) > arr.length) {
            throw new IndexOutOfBoundsException();
        }
        
        for (int i = 0; i < len; ++i) {
            writeBoolean(arr[off + i]);
        }
    }
    
    /* *********************************************************
        Byte
     ********************************************************* */
    
    @Override
    default void writeByte(int v) throws IOException {
        write(v);
    }
    
    default void writeBytes(byte[] arr) throws IOException {
        writeBytes(arr, 0, arr.length);
    }
    
    default void writeBytes(byte[] arr, int off, int len) throws IOException {
        write(arr, off, len);
    }
    
    /* *********************************************************
        UnsignedByte
     ********************************************************* */
    
    default void writeUnsignedByte(short v) throws IOException {
        writeByte(v);
    }
    
    default void writeUnsignedBytes(short[] arr) throws IOException {
        writeUnsignedBytes(arr, 0, arr.length);
    }
    
    default void writeUnsignedBytes(short[] arr, int off, int len) throws IOException {
        if (off < 0 || len < 0 || (off + len) > arr.length) {
            throw new IndexOutOfBoundsException();
        }
        
        for (int i = 0; i < len; ++i) {
            writeUnsignedByte(arr[off + i]);
        }
    }
    
    /* *********************************************************
        Char
     ********************************************************* */
    
    @Override
    default void writeChar(int v) throws IOException {
        write((v >>> 8) & 0xFF);
        write((v >>> 0) & 0xFF);
    }
    
    default void writeChars(char[] arr) throws IOException {
        writeChars(arr, 0, arr.length);
    }
    
    default void writeChars(char[] arr, int off, int len) throws IOException {
        if (off < 0 || len < 0 || (off + len) > arr.length) {
            throw new IndexOutOfBoundsException();
        }
        
        for (int i = 0; i < len; ++i) {
            writeChar(arr[off + i]);
        }
    }
    
    /* *********************************************************
        Short
     ********************************************************* */
    
    @Override
    default void writeShort(int v) throws IOException {
        writeShort(v, isLittleEndian());
    }
    
    default void writeShort(int v, boolean littleEndian) throws IOException {
        if (littleEndian) {
            write((v >>> 0) & 0xFF);
            write((v >>> 8) & 0xff);
        } else {
            write((v >>> 8) & 0xFF);
            write((v >>> 0) & 0xFF);
        }
    }
    
    default void writeShorts(short[] arr) throws IOException {
        writeShorts(arr, isLittleEndian());
    }
    
    default void writeShorts(short[] arr, boolean littleEndian) throws IOException {
        writeShorts(arr, 0, arr.length, littleEndian);
    }
    
    default void writeShorts(short[] arr, int off, int len) throws IOException {
        writeShorts(arr, off, len, isLittleEndian());
    }
    
    default void writeShorts(short[] arr, int off, int len, boolean littleEndian) throws IOException {
        if (off < 0 || len < 0 || (off + len) > arr.length) {
            throw new IndexOutOfBoundsException();
        }
        
        for (int i = 0; i < len; ++i) {
            writeShort(arr[off + i], littleEndian);
        }
    }
    
    /* *********************************************************
        UnsignedShort
     ********************************************************* */
    
    default void writeUnsignedShort(int v) throws IOException {
        writeUnsignedShort(v, isLittleEndian());
    }
    
    default void writeUnsignedShort(int v, boolean littleEndian) throws IOException {
        writeShort(v, littleEndian);
    }
    
    default void writeUnsignedShorts(int[] arr) throws IOException {
        writeUnsignedShorts(arr, isLittleEndian());
    }
    
    default void writeUnsignedShorts(int[] arr, boolean littleEndian) throws IOException {
        writeUnsignedShorts(arr, 0, arr.length, littleEndian);
    }
    
    default void writeUnsignedShorts(int[] arr, int off, int len) throws IOException {
        writeUnsignedShorts(arr, off, len, isLittleEndian());
    }
    
    default void writeUnsignedShorts(int[] arr, int off, int len, boolean littleEndian) throws IOException {
        if (off < 0 || len < 0 || (off + len) > arr.length) {
            throw new IndexOutOfBoundsException();
        }
        
        for (int i = 0; i < len; ++i) {
            writeUnsignedShort(arr[off + i], littleEndian);
        }
    }
    
    /* *********************************************************
        Int
     ********************************************************* */
    
    @Override
    default void writeInt(int v) throws IOException {
        writeInt(v, isLittleEndian());
    }
    
    default void writeInt(int v, boolean littleEndian) throws IOException {
        if (littleEndian) {
            write((v >>> 0) & 0xFF);
            write((v >>> 8) & 0xFF);
            write((v >>> 16) & 0xFF);
            write((v >>> 24) & 0xFF);
        } else {
            write((v >>> 24) & 0xFF);
            write((v >>> 16) & 0xFF);
            write((v >>> 8) & 0xFF);
            write((v >>> 0) & 0xFF);
        }
    }
    
    default void writeInts(int[] arr) throws IOException {
        writeInts(arr, isLittleEndian());
    }
    
    default void writeInts(int[] arr, boolean littleEndian) throws IOException {
        writeInts(arr, 0, arr.length, littleEndian);
    }
    
    default void writeInts(int[] arr, int off, int len) throws IOException {
        writeInts(arr, off, len, isLittleEndian());
    }
    
    default void writeInts(int[] arr, int off, int len, boolean littleEndian) throws IOException {
        if (off < 0 || len < 0 || (off + len) > arr.length) {
            throw new IndexOutOfBoundsException();
        }
        
        for (int i = 0; i < len; ++i) {
            writeInt(arr[off + i], littleEndian);
        }
    }
    
    /* *********************************************************
        UnsignedInt
     ********************************************************* */
    
    default void writeUnsignedInt(long v) throws IOException {
        writeUnsignedInt(v, isLittleEndian());
    }
    
    default void writeUnsignedInt(long v, boolean littleEndian) throws IOException {
        writeInt((int) v, littleEndian);
    }
    
    default void writeUnsignedInts(long[] arr) throws IOException {
        writeUnsignedInts(arr, isLittleEndian());
    }
    
    default void writeUnsignedInts(long[] arr, boolean littleEndian) throws IOException {
        writeUnsignedInts(arr, 0, arr.length, littleEndian);
    }
    
    default void writeUnsignedInts(long[] arr, int off, int len) throws IOException {
        writeUnsignedInts(arr, off, len, isLittleEndian());
    }
    
    default void writeUnsignedInts(long[] arr, int off, int len, boolean littleEndian) throws IOException {
        if (off < 0 || len < 0 || (off + len) > arr.length) {
            throw new IndexOutOfBoundsException();
        }
        
        for (int i = 0; i < len; ++i) {
            writeUnsignedInt(arr[off + i], littleEndian);
        }
    }
    
    /* *********************************************************
        Long
     ********************************************************* */
    
    @Override
    default void writeLong(long v) throws IOException {
        writeLong(v, isLittleEndian());
    }
    
    default void writeLong(long v, boolean littleEndian) throws IOException {
        if (littleEndian) {
            this.writeInt((int) (v & 0xffffffffL));
            this.writeInt((int) ((v >> 32) & 0xffffffffL));
        } else {
            writeInt((int) ((v >> 32) & 0xffffffffL));
            writeInt((int) (v & 0xffffffffL));
        }
    }
    
    default void writeLongs(long[] arr) throws IOException {
        writeLongs(arr, isLittleEndian());
    }
    
    default void writeLongs(long[] arr, boolean littleEndian) throws IOException {
        writeLongs(arr, 0, arr.length, littleEndian);
    }
    
    default void writeLongs(long[] arr, int off, int len) throws IOException {
        writeLongs(arr, off, len, isLittleEndian());
    }
    
    default void writeLongs(long[] arr, int off, int len, boolean littleEndian) throws IOException {
        if (off < 0 || len < 0 || (off + len) > arr.length) {
            throw new IndexOutOfBoundsException();
        }
        
        for (int i = 0; i < len; ++i) {
            writeLong(arr[off + i], littleEndian);
        }
    }
    
    /* *********************************************************
        Float
     ********************************************************* */
    
    @Override
    default void writeFloat(float v) throws IOException {
        writeFloat(v, isLittleEndian());
    }
    
    default void writeFloat(float v, boolean littleEndian) throws IOException {
        writeInt(Float.floatToIntBits(v), littleEndian);
    }
    
    default void writeFloats(float[] arr) throws IOException {
        writeFloats(arr, isLittleEndian());
    }
    
    default void writeFloats(float[] arr, boolean littleEndian) throws IOException {
        writeFloats(arr, 0, arr.length, littleEndian);
    }
    
    default void writeFloats(float[] arr, int off, int len) throws IOException {
        writeFloats(arr, off, len, isLittleEndian());
    }
    
    default void writeFloats(float[] arr, int off, int len, boolean littleEndian) throws IOException {
        if (off < 0 || len < 0 || (off + len) > arr.length) {
            throw new IndexOutOfBoundsException();
        }
        
        for (int i = 0; i < len; ++i) {
            writeFloat(arr[off + i], littleEndian);
        }
    }
    
    /* *********************************************************
        Double
     ********************************************************* */
    
    @Override
    default void writeDouble(double v) throws IOException {
        writeDouble(v, isLittleEndian());
    }
    
    default void writeDouble(double v, boolean littleEndian) throws IOException {
        writeLong(Double.doubleToLongBits(v), littleEndian);
    }
    
    default void writeDoubles(double[] arr) throws IOException {
        writeDoubles(arr, isLittleEndian());
    }
    
    default void writeDoubles(double[] arr, boolean littleEndian) throws IOException {
        writeDoubles(arr, 0, arr.length, littleEndian);
    }
    
    default void writeDoubles(double[] arr, int off, int len) throws IOException {
        writeDoubles(arr, off, len, isLittleEndian());
    }
    
    default void writeDoubles(double[] arr, int off, int len, boolean littleEndian) throws IOException {
        if (off < 0 || len < 0 || (off + len) > arr.length) {
            throw new IndexOutOfBoundsException();
        }
        
        for (int i = 0; i < len; ++i) {
            writeDouble(arr[off + i], littleEndian);
        }
    }
    
    /* *********************************************************
        String
     ********************************************************* */
    
    @Override
    default void writeBytes(String s) throws IOException {
        for (int i = 0, len = s.length(); i < len; i++) {
            write((byte) s.charAt(i));
        }
    }
    
    @Override
    default void writeChars(String s) throws IOException {
        for (int i = 0, len = s.length(); i < len; i++) {
            int v = s.charAt(i);
            writeChar(v);
        }
    }
    
    @Override
    default void writeUTF(String s) throws IOException {
        throw new IOException("NotImplementation");
        // todo DataOutputStream.writeUTF(s, this);
    }
    
    default void writeString(String s) throws IOException {
        writeString(s, StandardCharsets.UTF_8, false);
    }
    
    default void writeString(String s, boolean appendZero) throws IOException {
        writeString(s, StandardCharsets.UTF_8, appendZero);
    }
    
    default void writeString(String s, Charset charset) throws IOException {
        writeString(s, charset, false);
    }
    
    default void writeString(String s, Charset charset, boolean appendZero) throws IOException {
        write(s.getBytes(charset));
        if (appendZero) {
            write(0);
        }
    }
    
    /* *********************************************************
        Other
     ********************************************************* */
    
    boolean isLittleEndian();
    
    void setLittleEndian(boolean littleEndian);
    
    OutputStream asOutputStream();
}
