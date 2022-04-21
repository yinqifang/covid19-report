package com.yinqifang.covid19report.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinqifang.covid19report.dto.DailyIncrementDTO;
import com.yinqifang.covid19report.entity.DailyIncrementDO;
import com.yinqifang.covid19report.mapper.DailyIncrementMapper;
import com.yinqifang.covid19report.service.DailyIncrementService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 每日新增数据服务层
 *
 * @author Yin Qifang
 * @date 2022-04-19
 */
@Service
public class DailyIncrementServiceImpl extends ServiceImpl<DailyIncrementMapper, DailyIncrementDO>
    implements DailyIncrementService {

    @Override
    public List<String> listAreaNames() {
        // 构造查询条件
        QueryWrapper<DailyIncrementDO> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT area_name, area_type").orderBy(true, true, "area_type")
            .orderBy(true, true, "area_name");
        // 获取原始数据
        List<DailyIncrementDO> entities = this.list(wrapper);
        return Optional.ofNullable(entities).orElse(Collections.emptyList()).stream().map(DailyIncrementDO::getAreaName)
            .collect(Collectors.toList());
    }

    @Override
    public List<DailyIncrementDTO> getByAreaName(String areaName) {
        // 构造查询条件
        LambdaQueryWrapper<DailyIncrementDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DailyIncrementDO::getAreaName, areaName);

        // 获取原始数据
        List<DailyIncrementDO> entities = this.list(wrapper);

        // 封装返回结果
        return convert2Dto(entities);
    }

    @Override
    public List<DailyIncrementDTO> getAll() {
        // 获取原始数据
        List<DailyIncrementDO> entities = this.list(null);

        // 封装返回结果
        return convert2Dto(entities);
    }

    /**
     * do => dto
     */
    private List<DailyIncrementDTO> convert2Dto(List<DailyIncrementDO> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }
        return entities.stream().map(this::convert2Dto).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * do => dto
     */
    private DailyIncrementDTO convert2Dto(DailyIncrementDO entity) {
        if (entity == null) {
            return null;
        }
        DailyIncrementDTO dto = new DailyIncrementDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}