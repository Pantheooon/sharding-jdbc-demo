package cn.pmj.shading.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import io.shardingsphere.api.config.ShardingRuleConfiguration;
import io.shardingsphere.api.config.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Pantheon
 * @date: 2019/8/23 17:20
 * @comment:
 */
public class JdbcMain {

    public static void main(String[] args) throws SQLException {
        insertOrder();

    }


    private static void insertOrder() throws SQLException {
        try (Connection connection = getConnection()) {
            String sql = "insert into t_order (user_id,order_id) values(19,22)";
            Statement statement = connection.createStatement();
            statement.execute(sql);
        }
    }


    private static void generateTable() throws SQLException {
        try (Connection connection = getConnection();) {
            String sql = "CREATE TABLE t_order (\n" +
                    "  `id` bigint NOT NULL ,\n" +
                    "  `user_id` int(11) DEFAULT NULL,\n" +
                    "  `order_id` int(11) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ")";
            Statement statement = connection.createStatement();
            statement.execute(sql);

        }
    }


    private static Connection getConnection() throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = createDataSource();

        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = orderTableConfiguration();
        //配置不分表的user
        TableRuleConfiguration userTableRuleConfig = userTableRuleConfig();

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);
        shardingRuleConfig.getTableRuleConfigs().add(userTableRuleConfig);
        // 省略配置order_item表规则...
        // ...
        // 获取数据源对象
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new ConcurrentHashMap(), new Properties());
        return dataSource.getConnection();
    }

    private static TableRuleConfiguration orderTableConfiguration() {
        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        orderTableRuleConfig.setLogicTable("t_order");
        orderTableRuleConfig.setActualDataNodes("ds${0..1}.t_order_${0..1}");
        orderTableRuleConfig.setKeyGeneratorColumnName("id");
        orderTableRuleConfig.setKeyGenerator(new OrderKeyGenerate());
        // 配置分库 + 分表策略
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
//        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new OrderDataBaseShardingStrategyConfig());
        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order_${order_id % 2}"));
//        orderTableRuleConfig.setTableShardingStrategyConfig(new OrderTableShardingStrategyConfig());
        return orderTableRuleConfig;
    }


    private static TableRuleConfiguration userTableRuleConfig() {
        TableRuleConfiguration userTableRuleConfig = new TableRuleConfiguration();
        userTableRuleConfig.setLogicTable("t_user");
        userTableRuleConfig.setActualDataNodes("ds0.t_user");
        return userTableRuleConfig;
    }


    private static Map<String, DataSource> createDataSource() {
        HashMap<String, DataSource> dataSourceMap = new HashMap<>();
        // 配置第一个数据源
        DataSource source0 = getBasicDataSource("");
        dataSourceMap.put("ds0", source0);
        // 配置第二个数据源
        DataSource source1 = getBasicDataSource("");
        dataSourceMap.put("ds1", source1);
        return dataSourceMap;
    }


    private static DataSource getBasicDataSource(String url) {
        DruidDataSource source = new DruidDataSource();
        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUrl(url);
        source.setUsername("pmj");
        source.setPassword("pmj");
        return source;
    }


}
