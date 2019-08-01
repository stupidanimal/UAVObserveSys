package com.ocean.service;

import com.ocean.entity.BalloonResult;
import com.ocean.entity.BalloonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;




import java.util.List;

/**
 * @author xingzhe
 * @date 2019-07-13
 */
@Service
public class BalloonService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveBalloon(BalloonVo balloonVo) {
        mongoTemplate.save(balloonVo);
    }

    public List<BalloonVo> findByBallCode(String bCode){
        Query query = new Query(Criteria.where("balloonCode").is(bCode).and("secondOrder").is(0));
        return mongoTemplate.find(query,BalloonVo.class);
    }

    public List<BalloonVo> findByDateRange(long rangeStart,long rangeEnd){
        System.out.println(rangeStart+"||"+rangeEnd);
        Query query = new Query(Criteria.where("timeStamp").gte(rangeStart/1000).lte(rangeEnd/1000));
        return mongoTemplate.find(query,BalloonVo.class);
    }
}
