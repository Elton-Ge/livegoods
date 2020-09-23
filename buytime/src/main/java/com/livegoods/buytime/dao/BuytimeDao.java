package com.livegoods.buytime.dao;

import java.util.Date;

/**
 * @Auther: Elton Ge
 * @Date: 20/9/20
 * @Description: com.livegoods.buytime.dao
 * @version: 1.0
 */
public interface BuytimeDao {

    Date selectDate(String itemId);
}
