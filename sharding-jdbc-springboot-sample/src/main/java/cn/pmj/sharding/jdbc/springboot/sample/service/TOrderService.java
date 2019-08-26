package cn.pmj.sharding.jdbc.springboot.sample.service;


import cn.pmj.sharding.jdbc.springboot.sample.bean.TOrder;
import cn.pmj.sharding.jdbc.springboot.sample.mapper.TOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Pantheon
 * @date: 2019/8/26 23:16
 * @comment
 */
@Service
public class TOrderService {

    @Autowired
    private TOrderMapper orderMapper;

    public List<TOrder> selectAll() {
        return orderMapper.selectAll();
    }

    public TOrder findById(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    public TOrder insert(TOrder tOrder) {
        orderMapper.insertSelective(tOrder);
        return tOrder;
    }

    public void deleteById(Long id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    public void updateById(TOrder order) {
        orderMapper.updateByPrimaryKey(order);
    }
}
