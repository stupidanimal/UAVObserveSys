package com.ocean.service;

import com.ocean.entity.ShipLocationVo;
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
public class ShipLocationService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveShipLocationVo(ShipLocationVo shipLocationVo) {
        mongoTemplate.save(shipLocationVo);
    }

    public List<ShipLocationVo> findByLineCode(String lCode){
        Query query = new Query(Criteria.where("lineCode").is(lCode));
        return mongoTemplate.find(query,ShipLocationVo.class);
    }

    public List<String> findAllLineCode(){
        com.mongodb.client.DistinctIterable<String> result = mongoTemplate.getCollection("ship_location").distinct("lineCode",String.class);
        List<String> list = new java.util.ArrayList<String>();
        for(String code: result){
            list.add(code);
        }
        return list;
    }
}
