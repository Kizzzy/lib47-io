package cn.kizzzy.io;

import java.io.Closeable;
import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public interface IFullyWriter extends DataOutput, Closeable {
    
    boolean isLittleEndian();
    
    void setLittleEndian(boolean littleEndian);
    
    @Override
    default void writeBoolean(boolean v) throws IOException {
        write(v ? 1 : 0);
    }
    
    default void writeBooleans(boolean[] arr) throws IOException {
        for (boolean b : arr) {
            writeBoolean(b);
        }
    }
    
    @Override
    default void writeByte(int v) throws IOException {
        write(v);
    }
    
    default void writeBytes(byte[] arr) throws IOException {
        for (byte b : arr) {
            writeByte(b);
        }
    }
    
    default void writeUnsignedByte(short v) throws IOException {
        writeByte(v);
    }
    
    default void writeUnsignedBytes(short[] arr) throws IOException {
        for (short b : arr) {
            writeUnsignedByte(b);
        }
    }
    
    @Override
    default void writeChar(int v) throws IOException {
        write((v >>> 8) & 0xFF);
        write((v >>> 0) & 0xFF);
    }
    
    default void writeChars(char[] arr) throws IOException {
        for (int i = 0, n = arr.length; i < n; ++i) {
            writeChar(arr[i]);
        }
    }
    
    @Override
    default void writeShort(int v) throws IOException {
        if (isLittleEndian()) {
            write(v & 0xff);
            write((v >> 8) & 0xff);
        } else {
            write((v >>> 8) & 0xFF);
            write((v >>> 0) & 0xFF);
        }
    }
    
    default void writeShorts(short[] arr) throws IOException {
        for (short b : arr) {
            writeShort(b);
        }
    }
    
    default void writeUnsignedShort(int v) throws IOException {
        writeShort(v);
    }
    
    default void writeUnsignedShorts(int[] arr) throws IOException {
        for (int b : arr) {
            writeUnsignedShort(b);
        }
    }
    
    @Override
    default void writeInt(int v) throws IOException {
        if (isLittleEndian()) {
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
        for (int b : arr) {
            writeInt(b);
        }
    }
    
    default void writeUnsignedInt(long v) throws IOException {
        writeInt((int) v);
    }
    
    default void writeUnsignedInts(long[] arr) throws IOException {
        for (long b : arr) {
            writeUnsignedInt(b);
        }
    }
    
    @Override
    default void writeLong(long v) throws IOException {
        if (isLittleEndian()) {
            this.writeInt((int) (v & 0xffffffffL));
            this.writeInt((int) ((v >> 32) & 0xffffffffL));
        } else {
            writeInt((int) ((v >> 32) & 0xffffffffL));
            writeInt((int) (v & 0xffffffffL));
        }
    }
    
    default void writeLongs(long[] arr) throws IOException {
        for (long b : arr) {
            writeLong(b);
        }
    }
    
    @Override
    default void writeFloat(float v) throws IOException {
        writeInt(Float.floatToIntBits(v));
    }
    
    default void writeFloats(float[] arr) throws IOException {
        for (float b : arr) {
            writeFloat(b);
        }
    }
    
    @Override
    default void writeDouble(double v) throws IOException {
        writeLong(Double.doubleToLongBits(v));
    }
    
    default void writeDoubles(double[] arr) throws IOException {
        for (double b : arr) {
            writeDouble(b);
        }
    }
    
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
    
    OutputStream asOutputStream();
}
