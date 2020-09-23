package com.livegoods.banner.service.impl;

import com.livegoods.banner.dao.BannerDao;
import com.livegoods.banner.service.BannerService;
import com.livegoods.commons.Results;
import com.livegoods.pojo.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 17/9/20
 * @Description: com.livegoods.banner.service.impl
 * @version: 1.0
 */
@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;
    @Value("${fdfs.nginx.prefix}")
    private String prefix;
    /**
     * 通过mongodb查询轮播图数据,并根据创建时间倒序排列
     * @return
     */
    @Override
    public Results getBanners() {
        Results results = new Results();
        try {
            Query query = new Query();
            query.with(PageRequest.of(0,4, Sort.by(Sort.Direction.DESC,"createTime")));
            List<Banner> banners = bannerDao.selectBanners(query);

            results.setStatus(200);
            List<String> imgs = new ArrayList<>();
            for (Banner banner : banners) {
                  imgs.add(prefix+banner.getUrl());
            }
            results.setResults(imgs);
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            results.setStatus(500);
            results.setMsg("查询失败");
        }
        return results;
    }
}
