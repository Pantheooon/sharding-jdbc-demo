package cn.pmj.sharding.jdbc.springboot.sample.bean;


import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: Pantheon
 * @date: 2019/8/26 23:16
 * @comment
 */
@Data
@Repository
@Table(name = "t_order")
public class TOrder {

    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;


    @Column(name = "order_id")
    private Long orderId;
}
