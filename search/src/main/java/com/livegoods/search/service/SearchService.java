package com.livegoods.search.service;

import com.livegoods.commons.Results;

/**
 * @Auther: Elton Ge
 * @Date: 20/9/20
 * @Description: com.livegoods.search.service
 * @version: 1.0
 */
public interface SearchService {
    Results search(int page, int rows, String city, String content);
}
