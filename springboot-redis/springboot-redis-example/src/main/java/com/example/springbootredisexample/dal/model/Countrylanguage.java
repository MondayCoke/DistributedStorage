package com.example.springbootredisexample.dal.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Countrylanguage extends Model {

    private static final long serialVersionUID = 1L;

    @TableId("CountryCode")
    private String countrycode;

    @TableField("Language")
    private String language;

    @TableField("IsOfficial")
    private String isofficial;

    @TableField("Percentage")
    private Float percentage;


}
