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
}
