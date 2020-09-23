package com.livegoods.banner.dao.impl;

import com.livegoods.banner.dao.BannerDao;
import com.livegoods.pojo.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 17/9/20
 * @Description: com.livegoods.banner.dao.impl
 * @version: 1.0
 */
@Repository
public class BannerDaoImpl implements BannerDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public List<Banner> selectBanners(Query query) {
        List<Banner> bannerList = mongoTemplate.find(query, Banner.class);
        return bannerList;
    }
}
