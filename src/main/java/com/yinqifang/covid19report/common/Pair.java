package com.yinqifang.covid19report.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Chris Yin
 * @date 2020/1/14
 */
@Data
@AllArgsConstructor
public class Pair<K, V> {
    private K key;
    private V value;
}
