package cn.pmj.sharding.jdbc.springboot.sample.endpoint;

import cn.pmj.sharding.jdbc.springboot.sample.bean.TOrder;
import cn.pmj.sharding.jdbc.springboot.sample.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Pantheon
 * @date: 2019/8/26 23:16
 * @comment
 */
@RestController
public class TOrderEndPoint {

    @Autowired
    private TOrderService orderService;

    @GetMapping(value = "/selelctAll")
    public List<TOrder> selelctAll() {
        return orderService.selectAll();
    }

    @GetMapping(value = "/findById")
    public TOrder findById(Long id) {
        return orderService.findById(id);
    }

    @PostMapping(value = "/insert")
    public TOrder insert(@RequestBody TOrder tOrder) {
        return orderService.insert(tOrder);
    }


    @GetMapping(value = "/deleteById")
    public void deleteById(Long id) {
        orderService.deleteById(id);
    }

    /**
     * 此处对分区键的更新,sharding-jdbc并没有将原有的数据重新入库
     * 还是在原来的库里,所以对于更新操作,如果是对分区键更新,需要自己删除掉
     * 原有数据重新插入
     *
     * @param id
     */
    @GetMapping(value = "/updateById")
    public void updateById(Long id) {
        TOrder order = new TOrder();
        order.setId(id);
        order.setOrderId(2L);
        order.setUserId(6L);
        orderService.updateById(order);
    }
}
