package com.yinqifang.covid19report.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 新冠肺炎每日新增数据表
 *
 * @author Yin Qifang
 * @date 2022-04-19
 */
@Data
@TableName(value = "t_covid19_daily_increment")
public class DailyIncrementDO implements Serializable {

    private static final long serialVersionUID = -3564239150710175898L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 报告日期
     */
    private LocalDate reportDate;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域类型
     */
    private Integer areaType;

    /**
     * 新增确诊
     */
    private Integer confirmed;

    /**
     * 新增无症状
     */
    private Integer asymptomatic;

    /**
     * 社会面新增确诊
     */
    private Integer communityConfirmed;

    /**
     * 社会面新增无症状
     */
    private Integer communityAsymptomatic;


}