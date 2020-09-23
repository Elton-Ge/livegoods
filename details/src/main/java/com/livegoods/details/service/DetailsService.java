package com.livegoods.details.service;

import com.livegoods.commons.Results;
import com.livegoods.pojo.Item;

/**
 * @Auther: Elton Ge
 * @Date: 20/9/20
 * @Description: com.livegoods.details.service
 * @version: 1.0
 */
public interface DetailsService {
    Item selectById(String id);
}
