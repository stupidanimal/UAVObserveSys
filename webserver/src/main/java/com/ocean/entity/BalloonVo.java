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
@Document(collection = "balloon")
@Setter
@Getter
@ToString
public class BalloonVo {

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
     * 分钟序号
     */
    private int minuteOrder = -1;
    /**
     * 秒序号
     */
    private int secondOrder = -1;
    /**
     * 时间戳
     */
    private long timeStamp = -1;
    /**
     * 字符串时间
     */
    private String timeStr = "";
    /**
     * 气压
     */
    private float pre = -1;
    /**
     * 温度
     */
    private float temp = -1;
    /**
     * 湿度
     */
    private float hum = -1;
    /**
     * 位势高度
     */
    private float qpm = -1;
    /**
     * 风向
     */
    private float dir = -1;
    /**
     * 风速
     */
    private float vel = -1;
    /**
     * 露点温度
     */
    private float dew = -1;
    /**
     * 距离
     */
    private float dis = -1;
    /**
     * 升速
     */
    private float rise = -1;
    /**
     * 海拔高度
     */
    private float alt = -1;

    /**
     * 经纬度
     */
    @GeoSpatialIndexed
    private Double[] point;
}
