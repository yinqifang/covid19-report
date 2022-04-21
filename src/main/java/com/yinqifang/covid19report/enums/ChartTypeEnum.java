package com.yinqifang.covid19report.enums;

import lombok.Getter;

/**
 * 报表类型
 *
 * @author Chris Yin
 * @date 2022-04-21
 */
public enum ChartTypeEnum {
    TOTAL("total", "合计"),
    COMMUNITY("community", "社会面"),
    ;

    @Getter
    private String code;
    @Getter
    private String desc;

    ChartTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ChartTypeEnum getByCode(String code) {
        for (ChartTypeEnum entity : ChartTypeEnum.values()) {
            if (entity.getCode().equalsIgnoreCase(code)) {
                return entity;
            }
        }
        return null;
    }

}
