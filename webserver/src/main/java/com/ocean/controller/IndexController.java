package com.ocean.controller;

import com.ocean.entity.BalloonResult;
import com.ocean.entity.ShipLocationResult;
import com.ocean.service.BalloonService;
import com.ocean.service.ShipLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xingzhe
 * @date 2019-07-11
 */

@RestController
@CrossOrigin
public class IndexController {
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    BalloonService balloonService;

    @Autowired
    ShipLocationService shipLocationService;


    @GetMapping("/balloon")
    public Object balloon(String balloonCode,String interval) {
        BalloonResult balloonResult = new BalloonResult();
        balloonResult.setBalloonCode(balloonCode);
        balloonResult.setBalloons(balloonService.findByBallCode(balloonCode));
        return balloonResult;
    }

    @GetMapping("/ship_location")
    public Object shipLocation(String lineCode){
        ShipLocationResult shipLocationResult = new ShipLocationResult();
        shipLocationResult.setLineCode(lineCode);
        shipLocationResult.setShipLocationVos(shipLocationService.findByLineCode(lineCode));
        return shipLocationResult;
    }


    @GetMapping("/lineCodeCollection")
    public Object lineCodeCollection(){
         java.util.List<String> list= shipLocationService.findAllLineCode();
        return list;
    }


}


