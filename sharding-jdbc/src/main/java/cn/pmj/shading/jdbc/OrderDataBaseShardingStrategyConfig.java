package cn.pmj.shading.jdbc;


import io.shardingsphere.api.algorithm.sharding.ListShardingValue;
import io.shardingsphere.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.api.config.strategy.ShardingStrategyConfiguration;
import io.shardingsphere.core.routing.strategy.ShardingStrategy;

import java.util.Arrays;
import java.util.Collection;

public class OrderDataBaseShardingStrategyConfig implements ShardingStrategyConfiguration {
    public ShardingStrategy build() {
        return new OrderDataBaseShardingStrategy();
    }

    private class OrderDataBaseShardingStrategy implements ShardingStrategy {
        @Override
        public Collection<String> getShardingColumns() {
            return Arrays.asList("user_id");
        }


        /**
         * user_id的值对5 取余,小于3的放ds1库,大于3的放ds0库
         *
         * @param availableTargetNames
         * @param shardingValues
         * @return
         */
        @Override
        public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {
            ShardingValue next = shardingValues.iterator().next();
            ListShardingValue shardingValue = (ListShardingValue) next;
            Collection values = shardingValue.getValues();
            Integer userId = (Integer) values.iterator().next();
            if (userId % 5 > 3) {
                return Arrays.asList("ds1");
            }
            return Arrays.asList("ds0");
        }
    }
}
