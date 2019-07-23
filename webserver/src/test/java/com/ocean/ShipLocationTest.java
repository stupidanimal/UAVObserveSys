package com.ocean;

import com.ocean.entity.ShipLocationVo;
import com.ocean.service.ShipLocationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author xingzhe
 * @date 2019-07-13
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ShipLocationTest {
    @Autowired
    ShipLocationService shipLocationService;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Test
    public void saveShipLocation(){
        ShipLocationVo shipLocationVo = new ShipLocationVo();
        shipLocationVo.setAvgRise(252.7f);
        shipLocationVo.setBalloonCode("15185334");
        shipLocationVo.setStartTime(1469080741);
        shipLocationVo.setEndTime(1469098741);
        shipLocationVo.setMaxHeightTime(1469098741);
        shipLocationVo.setMaxHeight(17689.3f);
        shipLocationVo.setLocationName("IND-STC082");
        shipLocationVo.setLineCode("1");
        Double[] point = new Double[]{104.00252,-7.99161};
        shipLocationVo.setPoint(point);
        shipLocationService.saveShipLocationVo(shipLocationVo);
    }

    @Test
    public void delAll(){
        mongoTemplate.dropCollection("ship_location");
    }
}
