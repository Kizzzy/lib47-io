package cn.kizzzy.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * extension of RandomAccessFile
 *
 * @author Kizzzy
 * @see RandomAccessFile
 */
public class RandomAccessFileEx extends RandomAccessFile implements DataInputEx {
    
    public RandomAccessFileEx(String name, String mode) throws FileNotFoundException {
        super(name, mode);
    }
    
    public RandomAccessFileEx(File file, String mode) throws FileNotFoundException {
        super(file, mode);
    }
}
