package com.ocean.controller;

import com.ocean.entity.BalloonResult;
import com.ocean.entity.ShipLocationResult;
import com.ocean.service.BalloonService;
import com.ocean.service.ShipLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

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


    @GetMapping("/getBalloonByDateRange")
    public Object getBalloonByDateRange(String dateStartStr,String dateEndStr) throws ParseException {
        Date dateStart=null,dateEnd=null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateStart=dateStartStr.isEmpty()?formatter.parse("1990-05-23 00:00:00"):formatter.parse(dateStartStr);
        dateEnd=dateEndStr.isEmpty()?formatter.parse("2045-10-25 00:00:00"):formatter.parse(dateEndStr);
        BalloonResult  br = new BalloonResult();
        dateStart.setHours(0);
        dateStart.setMinutes(0);
        dateStart.setSeconds(0);
        dateEnd.setHours(23);
        dateEnd.setMinutes(59);
        dateEnd.setSeconds(59);
        long timestampstart = dateStart.getTime();
        long timestampend = dateEnd.getTime();

        Object result = balloonService.findByDateRange(timestampstart,timestampend);

        return result;
    }


}


