package com.livegoods.hotproduct.service;

import com.livegoods.commons.Results;

/**
 * @Auther: Elton Ge
 * @Date: 18/9/20
 * @Description: com.livegoods.hotproduct.service
 * @version: 1.0
 */
public interface HotProductService {
    Results getHotProducts (String city);
}
