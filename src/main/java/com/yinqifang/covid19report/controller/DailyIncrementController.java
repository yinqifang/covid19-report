package com.yinqifang.covid19report.controller;

import com.yinqifang.covid19report.dto.DailyIncrementDTO;
import com.yinqifang.covid19report.enums.ChartTypeEnum;
import com.yinqifang.covid19report.enums.StatisticsTypeEnum;
import com.yinqifang.covid19report.service.DailyIncrementService;
import com.yinqifang.covid19report.vo.ChartVO;
import com.yinqifang.covid19report.vo.TableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 每日新增
 *
 * @author Yin Qifang
 * @date 2022-04-19
 */
@RestController
@RequestMapping("/daily/increment")
@Slf4j
public class DailyIncrementController {

    @Autowired
    private DailyIncrementService dailyIncrementService;

    private DateTimeFormatter fmtMonthDay = DateTimeFormatter.ofPattern("MM-dd");
    private DateTimeFormatter fmtYMD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 测试
     *
     * @return
     */
    @RequestMapping("/echo")
    public String test() {
        return "Echo " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 获取区域列表
     */
    @GetMapping("/area/name/list")
    public List<String> listAreaNames() {
        return dailyIncrementService.listAreaNames();
    }

    /**
     * 每日新增数据图表
     */
    @GetMapping("/report/{chartType}/{areaName}")
    public ChartVO getTotalReport(@PathVariable("chartType") String chartTypeCode,
        @PathVariable("areaName") String areaName) {

        // 查询原始数据
        List<DailyIncrementDTO> dtos = dailyIncrementService.getByAreaName(areaName);
        ChartTypeEnum chartTypeEnum = ChartTypeEnum.getByCode(chartTypeCode);
        // 封装vo
        return convert2ChartVo(dtos, chartTypeEnum);
    }

    /**
     * 原始数据
     */
    @GetMapping("/original/data/{areaName}")
    public TableVO getOriginalData(@PathVariable("areaName") String areaName) {
        // 查询原始数据
        List<DailyIncrementDTO> dtos = dailyIncrementService.getByAreaName(areaName);

        // 封装vo
        return convert2TableVo(dtos);
    }

    /**
     * ChartVO封装
     */
    private ChartVO convert2ChartVo(List<DailyIncrementDTO> dtos, ChartTypeEnum chartType) {
        ChartVO vo = new ChartVO();
        if (CollectionUtils.isEmpty(dtos)) {
            return vo;
        }
        Collections.sort(dtos);
        // 封装X轴数据（日期）
        List<String> axisX = dtos.stream().map(DailyIncrementDTO::getReportDate).map(t -> t.format(fmtMonthDay))
            .collect(Collectors.toList());
        vo.setAxisX(axisX);

        // 图例数据
        List<StatisticsTypeEnum> types = new ArrayList<StatisticsTypeEnum>() {{
            switch (chartType) {
                case TOTAL:
                    add(StatisticsTypeEnum.CONFIRMED);
                    add(StatisticsTypeEnum.ASYMPTOMATIC);
                    break;
                case COMMUNITY:
                    add(StatisticsTypeEnum.COMMUNITY_CONFIRMED);
                    add(StatisticsTypeEnum.COMMUNITY_ASYMPTOMATIC);
                    break;
            }
        }};
        List<String> legend = types.stream().map(StatisticsTypeEnum::getDesc).collect(Collectors.toList());
        vo.setLegend(legend);

        // 数量数据
        List<List<Integer>> data = new ArrayList<>();
        for (StatisticsTypeEnum staType : types) {
            List<Integer> cols = new ArrayList<>();
            for (DailyIncrementDTO dto : dtos) {
                switch (staType) {
                    case CONFIRMED:
                        cols.add(dto.getConfirmed());
                        break;
                    case ASYMPTOMATIC:
                        cols.add(dto.getAsymptomatic());
                        break;
                    case COMMUNITY_CONFIRMED:
                        cols.add(dto.getCommunityConfirmed());
                        break;
                    case COMMUNITY_ASYMPTOMATIC:
                        cols.add(dto.getCommunityAsymptomatic());
                        break;
                }
            }
            if (cols.stream().anyMatch(Objects::nonNull)) {
                data.add(cols);
            }
        }
        vo.setData(data);

        return vo;
    }

    /**
     * TableVO封装
     */
    private TableVO convert2TableVo(List<DailyIncrementDTO> dtos) {
        TableVO vo = new TableVO();
        // 表头
        List<String> headers = new ArrayList<String>() {{
            add("日期");
            add("区域");
            add("确诊");
            add("无症状");
            add("社会面确诊");
            add("社会面无症状");
        }};
        vo.setHeader(headers);

        if (CollectionUtils.isEmpty(dtos)) {
            return vo;
        }
        // 倒序排列
        Collections.sort(dtos);
        Collections.reverse(dtos);

        List<List<Object>> dataList = new ArrayList<>();
        for (DailyIncrementDTO dto : dtos) {
            if (dto == null) {
                continue;
            }
            List<Object> cols = new ArrayList<>();
            if (dto.getReportDate() != null) {
                String date = dto.getReportDate().format(fmtYMD);
                cols.add(date);
            } else {
                cols.add("");
            }
            cols.add(dto.getAreaName());
            cols.add(dto.getConfirmed());
            cols.add(dto.getAsymptomatic());
            cols.add(dto.getCommunityConfirmed());
            cols.add(dto.getCommunityAsymptomatic());

            dataList.add(cols);
        }
        vo.setData(dataList);

        return vo;
    }

}
