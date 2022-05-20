package cn.kizzzy.io;

import java.io.Closeable;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Ex suffix means method will handle bytes in LITTLE_ENDIAN
 */
public interface IFullyWriter extends DataOutput, Closeable {
    
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
            write(b);
        }
    }
    
    @Override
    default void writeShort(int v) throws IOException {
        write((v >>> 8) & 0xFF);
        write((v >>> 0) & 0xFF);
    }
    
    default void writeShorts(int[] arr) throws IOException {
        for (int b : arr) {
            writeShort(b);
        }
    }
    
    default void writeShortEx(int v) throws IOException {
        write(v & 0xff);
        write((v >> 8) & 0xff);
    }
    
    default void writeShortExs(int[] arr) throws IOException {
        for (int b : arr) {
            writeShortEx(b);
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
    
    default void writeUnsignedShortEx(int v) throws IOException {
        writeShortEx(v);
    }
    
    default void writeUnsignedShortExs(int[] arr) throws IOException {
        for (int b : arr) {
            writeUnsignedShortEx(b);
        }
    }
    
    @Override
    default void writeChar(int v) throws IOException {
        write((v >>> 8) & 0xFF);
        write((v >>> 0) & 0xFF);
    }
    
    @Override
    default void writeInt(int v) throws IOException {
        write((v >>> 24) & 0xFF);
        write((v >>> 16) & 0xFF);
        write((v >>> 8) & 0xFF);
        write((v >>> 0) & 0xFF);
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
    
    default void writeIntEx(int v) throws IOException {
        write((v >>> 0) & 0xFF);
        write((v >>> 8) & 0xFF);
        write((v >>> 16) & 0xFF);
        write((v >>> 24) & 0xFF);
    }
    
    default void writeIntEx(int[] arr) throws IOException {
        for (int b : arr) {
            writeInt(b);
        }
    }
    
    default void writeUnsignedIntEx(long value) throws IOException {
        writeIntEx((int) value);
    }
    
    default void writeUnsignedIntExs(long[] arr) throws IOException {
        for (long b : arr) {
            writeUnsignedIntEx(b);
        }
    }
    
    @Override
    default void writeLong(long v) throws IOException {
        writeInt((int) (v & 0xffffffffL));
        writeInt((int) ((v >> 32) & 0xffffffffL));
    }
    
    default void writeLongs(long[] arr) throws IOException {
        for (long b : arr) {
            writeLong(b);
        }
    }
    
    default void writeLongEx(long v) throws IOException {
        writeIntEx((int) (v & 0xffffffffL));
        writeIntEx((int) ((v >> 32) & 0xffffffffL));
    }
    
    default void writeLongExs(long[] arr) throws IOException {
        for (long b : arr) {
            writeLongEx(b);
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
    
    default void writeFloatEx(float v) throws IOException {
        writeIntEx(Float.floatToIntBits(v));
    }
    
    default void writeFloatExs(float[] arr) throws IOException {
        for (float b : arr) {
            writeFloatEx(b);
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
    
    default void writeDoubleEx(double v) throws IOException {
        writeLongEx(Double.doubleToLongBits(v));
    }
    
    default void writeDoubleExs(double[] arr) throws IOException {
        for (double b : arr) {
            writeDoubleEx(b);
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
        writeString(s, StandardCharsets.UTF_8);
    }
    
    default void writeString(String s, Charset charset) throws IOException {
        write(s.getBytes(charset));
    }
}
