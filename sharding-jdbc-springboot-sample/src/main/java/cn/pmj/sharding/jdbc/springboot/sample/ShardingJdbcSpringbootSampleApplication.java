package cn.pmj.sharding.jdbc.springboot.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author: Pantheon
 * @date: 2019/8/26 23:10
 * @comment
 */
@SpringBootApplication
@MapperScan(value = "cn.pmj.sharding.jdbc.springboot.sample.mapper")
public class ShardingJdbcSpringbootSampleApplication {


    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcSpringbootSampleApplication.class, args);
    }


}
