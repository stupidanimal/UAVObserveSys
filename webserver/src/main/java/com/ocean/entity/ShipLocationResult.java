package com.ocean.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

/**
 * @author xingzhe
 * @date 2019-07-13
 */
@Setter
@Getter
@ToString
public class ShipLocationResult {
    private String lineCode;
    private List<ShipLocationVo> shipLocationVos = Collections.emptyList();
}
