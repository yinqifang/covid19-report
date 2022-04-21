package com.yinqifang.covid19report.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yinqifang.covid19report.entity.DailyIncrementDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 新冠肺炎每日新增数据Mapper
 *
 * @author Yin Qifang
 * @date 2022-04-19
 */
@Mapper
public interface DailyIncrementMapper extends BaseMapper<DailyIncrementDO> {

}