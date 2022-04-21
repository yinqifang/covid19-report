package com.yinqifang.covid19report.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris Yin
 * @date 2020/2/4
 */
public enum StatisticsTypeEnum {
    CONFIRMED("确诊"),
    ASYMPTOMATIC("无症状"),
    COMMUNITY_CONFIRMED("社会面确诊"),
    COMMUNITY_ASYMPTOMATIC("社会面无症状"),
    SUSPECTED("疑似"),
    SEVERE("重症"),
    DEAD("死亡"),
    CURATIVE("治愈"),
    CLOSE_CONTACTS("密切接触者"),
    OBSERVATION_RELEASED("解除医学观察"),
    UNDER_OBSERVATION("医学观察中");
    private String desc;

    StatisticsTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static List<String> getAllTypeDesc() {
        List<String> rst = new ArrayList<>();
        for (StatisticsTypeEnum entity : StatisticsTypeEnum.values()) {
            rst.add(entity.getDesc());
        }
        return rst;
    }

    /**
     * 通过名称查找
     */
    public static StatisticsTypeEnum getByName(String name) {
        for (StatisticsTypeEnum type : StatisticsTypeEnum.values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
}
