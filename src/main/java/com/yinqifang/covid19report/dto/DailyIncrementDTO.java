package com.yinqifang.covid19report.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author yinqifang
 * @description 新冠肺炎每日新增数据
 * @date 2022-04-19
 */
@Data
public class DailyIncrementDTO implements Comparable<DailyIncrementDTO> {

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

    @Override
    public int compareTo(DailyIncrementDTO o) {
        return this.getReportDate().compareTo(o.getReportDate());
    }
}