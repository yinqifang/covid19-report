

use covid19_report;


-- 每日新增表
DROP TABLE IF EXISTS `t_covid19_daily_increment`;
CREATE TABLE `t_covid19_daily_increment` (
     `id` bigint NOT NULL AUTO_INCREMENT,
     `report_date` date DEFAULT NULL COMMENT '报告日期',
     `area_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '区域名称',
     `area_type` int DEFAULT NULL COMMENT '区域类型',
     `confirmed` int DEFAULT NULL COMMENT '新增确诊',
     `asymptomatic` int DEFAULT NULL COMMENT '新增无症状',
     `community_confirmed` int DEFAULT NULL COMMENT '社会面新增确诊',
     `community_asymptomatic` int DEFAULT NULL COMMENT '社会面新增无症状',
     `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
     `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='新冠肺炎每日新增数据表';