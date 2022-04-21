package com.yinqifang.covid19report.vo;

import lombok.Data;

import java.util.List;

/**
 * 图表数据VO
 */
@Data
public class ChartVO {
    /**
     * X轴数据
     */
    private List<String> axisX;
    /**
     * 图例
     */
    private List<String> legend;
    /**
     * 数据
     */
    private List<List<Integer>> data;
    /**
     * 标示线
     */
    private List<Integer> markLine;

}
