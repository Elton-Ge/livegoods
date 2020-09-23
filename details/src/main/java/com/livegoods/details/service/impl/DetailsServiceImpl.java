package com.livegoods.details.service.impl;

import com.livegoods.commons.Results;
import com.livegoods.details.dao.DetailsDao;
import com.livegoods.details.service.DetailsService;
import com.livegoods.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 20/9/20
 * @Description: com.livegoods.details.service.impl
 * @version: 1.0
 */
@Service
public class DetailsServiceImpl implements DetailsService {
    @Autowired
    private DetailsDao detailsDao;
    @Value("${fdfs.nginx.prefix}")
    private String nginxPrefix;

    @Override
    @Cacheable(cacheNames = {"com.livegoods.details:"}, key = "'selectById:'+#id")
    public Item selectById(String id) {
        Item item = detailsDao.selectById(id);
        List<String> imgs = item.getImgs();
        List<String> newList = new ArrayList<>();
        for (String img : imgs) {
            newList.add(nginxPrefix + img);
        }
        item.setImgs(newList);
        return item;
    }
}
