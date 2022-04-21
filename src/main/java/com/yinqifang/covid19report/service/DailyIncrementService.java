package com.yinqifang.covid19report.service;

import com.yinqifang.covid19report.dto.DailyIncrementDTO;

import java.util.List;

/**
 * 每日新增数据 service
 *
 * @author Yin Qifang
 * @date 2022-04-19
 */
public interface DailyIncrementService {

    /**
     * 查询所有的区域名称
     */
    List<String> listAreaNames();

    /**
     * 根据区域名字查询
     */
    List<DailyIncrementDTO> getByAreaName(String areaName);


    /**
     * 查询所有数据
     */
    List<DailyIncrementDTO> getAll();


}
