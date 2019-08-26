package cn.pmj.shading.jdbc;


import io.shardingsphere.core.keygen.KeyGenerator;

public class OrderKeyGenerate implements KeyGenerator {
    @Override
    public Number generateKey() {
        long l = System.currentTimeMillis();
        System.out.println("生成主建-->"+l);
        return l;
    }
}
