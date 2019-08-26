package cn.pmj.sharding.jdbc.springboot.sample.mapper;

import cn.pmj.sharding.jdbc.springboot.sample.base.IBaseDao;
import cn.pmj.sharding.jdbc.springboot.sample.bean.TOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * @author: Pantheon
 * @date: 2019/8/26 23:16
 * @comment
 */
public interface TOrderMapper extends IBaseDao<TOrder> {


    @Override
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    @Insert("insert into t_order(user_id,order_id) values (#{userId},#{orderId})")
    int insertSelective(TOrder tOrder);
}
