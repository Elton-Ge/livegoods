package com.livegoods.search.dao;

import com.livegoods.search.pojo.Item4ES;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;

import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 18/9/20
 * @Description: com.livegoods.search.dao
 * @version: 1.0
 */
public interface ItemDao4ES {
    //批量新增
    void batchInsert(List<Item4ES> esList) ;
    //单一新增
    void insert(Item4ES item4ES);
    //分页查询 ,设置高亮
    AggregatedPage<Item4ES>  queryForPage(int page, int rows, String city, String content);
}
