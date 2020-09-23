package com.livegoods.search.service.impl;

import com.livegoods.commons.Results;
import com.livegoods.search.dao.ItemDao4ES;
import com.livegoods.search.pojo.Item4ES;
import com.livegoods.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.stereotype.Service;

/**
 * @Auther: Elton Ge
 * @Date: 20/9/20
 * @Description: com.livegoods.search.service.impl
 * @version: 1.0
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private  ItemDao4ES itemDao4ES;
    @Override
    public Results search(int page, int rows, String city, String content) {
        AggregatedPage<Item4ES> aggregatedPage = itemDao4ES.queryForPage(page, rows, city, content);
        Results results = Results.ok(aggregatedPage.getContent());
        if (page < (aggregatedPage.getTotalPages()-1)) { //判断hasmore
            results.setHasMore(true);
        }else {
            results.setHasMore(false);
        }
        return results;
    }
}
