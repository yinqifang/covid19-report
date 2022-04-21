package com.yinqifang.covid19report.enums;

import lombok.Getter;

/**
 * 区域类型
 * @author Yin Qifang
 * @date 2022-04-19
 */
public enum AreaTypeEnum {
    COUNTRY(1,"国家"),
    PROVINCE(2,"省"),
    CITY(3,"市"),
    DISTRICT(4,"区"),
    ;

    @Getter
    private int code;
    @Getter
    private String desc;

    AreaTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
