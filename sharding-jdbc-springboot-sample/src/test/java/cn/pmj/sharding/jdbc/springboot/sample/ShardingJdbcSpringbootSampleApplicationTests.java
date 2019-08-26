package cn.pmj.sharding.jdbc.springboot.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbcSpringbootSampleApplicationTests {



    @Autowired
    private DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        boolean execute =statement .execute("select * from t_order");
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()){
            long anInt = resultSet.getLong(1);
            System.out.println(anInt);
        }

    }

}
