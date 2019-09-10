package cn.pmj.shading.jdbc;

import com.google.common.collect.Lists;
import io.shardingsphere.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.api.config.strategy.ShardingStrategyConfiguration;
import io.shardingsphere.core.routing.strategy.ShardingStrategy;

import java.util.Arrays;
import java.util.Collection;

public class OrderTableShardingStrategyConfig implements ShardingStrategyConfiguration {
    public ShardingStrategy build() {
        return new OrderShardingStrategy();
    }


    public class OrderShardingStrategy implements ShardingStrategy {
        @Override
        public Collection<String> getShardingColumns() {
            return Arrays.asList("order_id");

        }

        /**
         * 全部分到t_order_1这张表
         *
         * @param availableTargetNames
         * @param shardingValues
         * @return
         */
        @Override
        public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {
            return Arrays.asList("t_order_1");
        }
    }
}
