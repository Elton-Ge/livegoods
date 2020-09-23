package com.livegoods.buytime.service.impl;

import com.livegoods.buytime.dao.BuytimeDao;
import com.livegoods.buytime.service.BuytimeService;
import com.livegoods.commons.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Auther: Elton Ge
 * @Date: 20/9/20
 * @Description: com.livegoods.buytime.service.impl
 * @version: 1.0
 */
@Service
public class BuytimeServiceImpl implements BuytimeService {
    @Autowired
    private BuytimeDao buytimeDao;
    @Override
    public Results getBuytime(String itemId) {
        Date date = buytimeDao.selectDate(itemId);
        long time = date.getTime();
        Results results = Results.ok();
        results.setTime(time);
        return results;
    }
}
