package cn.pmj.sharding.jdbc.springboot.sample.sharding;

/**
 * @author: Pantheon
 * @date: 2019/8/26 23:16
 * @comment
 */
public class KeyGenerator implements io.shardingsphere.core.keygen.KeyGenerator {
    @Override
    public Number generateKey() {
        return System.currentTimeMillis();
    }
}
