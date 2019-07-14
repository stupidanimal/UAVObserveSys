package com.ocean.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author xingzhe
 * @date 2019-07-13
 */
@Setter
@Getter
@ToString
@Document(collection = "ship_location")
public class ShipLocationVo {
    @Id
    @JsonIgnore
    private String _id;

    /**
     * 航次编号，文件夹名
     */
    private String lineCode = "";
    /**
     * 探空仪编号
     */
    private String balloonCode = "";
    /**
     * 释放起始时间
     */
    private long startTime = -1;
    /**
     * 释放结束时间
     */
    private long endTime = -1;
    /**
     * 到达最大高度时间
     */
    private long maxHeightTime = -1;
    /**
     * 最大高度
     */
    private float maxHeight = -1;
    /**
     * 平均升速
     */
    private float avgRise = -1;
    /**
     * 站名
     */
    private String locationName = "";
    /**
     * 日期yyyy-MM-dd
     */
    private String date = "";
    /**
     * 经纬度
     */
    @GeoSpatialIndexed
    private Double[] point;
}
