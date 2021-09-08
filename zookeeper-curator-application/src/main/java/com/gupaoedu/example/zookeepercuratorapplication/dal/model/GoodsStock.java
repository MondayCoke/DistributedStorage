package com.gupaoedu.example.zookeepercuratorapplication.dal.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2021-08-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GoodsStock extends Model {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 商品编号
     */
    private Integer goodsNo;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 是否上架（1上，0不是）
     */
    @TableField("isActive")
    private Integer isactive;


}
