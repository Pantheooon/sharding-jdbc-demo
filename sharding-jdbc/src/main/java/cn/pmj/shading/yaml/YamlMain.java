package cn.pmj.shading.yaml;

import io.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

public class YamlMain {


    public static void main(String[] args) throws IOException, SQLException {
        URL resource = YamlMain.class.getClassLoader().getResource("sharding.yaml");
        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(new File(resource.getFile()));
        Connection connection = dataSource.getConnection();
        String sql = "insert into t_order(order_id,user_id) values(10,19)";
        connection.createStatement().execute(sql);
    }


}
