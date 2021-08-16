package com.gupao.lottery.lotteryredis.dal.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author mic
 * @since 2021-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LotteryRecord extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String accountIp;

    private Integer itemId;

    private String prizeName;

    private LocalDateTime createTime;


}
