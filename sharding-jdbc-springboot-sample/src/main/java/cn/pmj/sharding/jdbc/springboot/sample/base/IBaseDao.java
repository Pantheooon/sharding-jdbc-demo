package cn.pmj.sharding.jdbc.springboot.sample.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author: Pantheon
 * @date: 2019/8/26 23:16
 * @comment
 */
public interface IBaseDao<T> extends Mapper<T>, MySqlMapper<T> {
}
