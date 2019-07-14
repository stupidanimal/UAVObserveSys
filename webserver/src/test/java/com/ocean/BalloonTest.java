package com.ocean;

import com.ocean.entity.BalloonVo;
import com.ocean.service.BalloonService;
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
public class BalloonTest {

    @Autowired
    BalloonService balloonService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void save(){
        BalloonVo balloonVo = new BalloonVo();
        balloonVo.setTimeStamp(1469080741);
        balloonVo.setTimeStr("2016-07-21 13:59:01");
        balloonVo.setPre(1009.27f);
        balloonVo.setTemp(28.35f);
        balloonVo.setHum(76.8f);
        balloonVo.setQpm(13.9f);
        balloonVo.setDir(55.2f);
        balloonVo.setVel(0.20f);
        balloonVo.setDew(23.9f);
        balloonVo.setDis(0.03f);
        balloonVo.setRise(-0.6f);
        balloonVo.setAlt(13.9f);
        balloonVo.setBalloonCode("15188603");
        balloonVo.setLineCode("1");
        balloonVo.setMinuteOrder(0);
        balloonVo.setSecondOrder(1);
        Double[] point = new Double[]{104.51253,-7.50500};
        balloonVo.setPoint(point);
        balloonService.saveBalloon(balloonVo);
    }

    @Test
    public void delAll(){
        mongoTemplate.dropCollection("balloon");
    }
}
