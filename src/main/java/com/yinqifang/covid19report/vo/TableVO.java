package com.yinqifang.covid19report.vo;

import lombok.Data;

import java.util.List;

/**
 * 表格数据VO
 *
 * @author Chris Yin
 * @date 2022-04-21
 */
@Data
public class TableVO {

    /**
     * 表头
     */
    private List<String> header;

    /**
     * 表格数据
     */
    private List<List<Object>> data;

}
