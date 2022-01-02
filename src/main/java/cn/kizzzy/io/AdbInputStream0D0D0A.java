package cn.kizzzy.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * over dealed features 0x0D 0x0D 0x0A
 *
 * @author Kizzzy
 */
public class AdbInputStream0D0D0A extends AdbInputStream {
    
    public AdbInputStream0D0D0A(InputStream stream) {
        super(stream);
    }
    
    public AdbInputStream0D0D0A(InputStream stream, int[] features, int value) {
        super(stream, features, value);
    }
    
    /**
     * 0D 0D 0A
     */
    @Override
    protected int readImpl() throws IOException {
        int first = next0();
        if (first == 0x0D) {
            int second = next0();
            if (second == 0x0D) {
                int third = next0();
                if (third == 0x0A) {
                    return 0x0A;
                }
                backups.push(third);
            }
            backups.push(second);
        }
        return first;
    }
}
