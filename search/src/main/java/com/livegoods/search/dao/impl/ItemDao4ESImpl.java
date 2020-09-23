package com.livegoods.search.dao.impl;

import com.livegoods.search.dao.ItemDao4ES;
import com.livegoods.search.pojo.Item4ES;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Elton Ge
 * @Date: 18/9/20
 * @Description: com.livegoods.search.dao.impl
 * @version: 1.0
 */
@Repository
public class ItemDao4ESImpl implements ItemDao4ES {
    @Autowired
    private ElasticsearchRestTemplate restTemplate;
    @Value("${search.init.enable}")
    private   boolean initEnabled=false;

    @Override
    public AggregatedPage<Item4ES> queryForPage(int page, int rows, String city, String content) {
        //高亮设置
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        //搜索条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //必要条件
        List<QueryBuilder> must = boolQueryBuilder.must();
        must.add(QueryBuilders.matchQuery("city",city));
        //可选条件
        List<QueryBuilder> should = boolQueryBuilder.should();
        should.add(QueryBuilders.matchQuery("title",content));
        should.add(QueryBuilders.matchQuery("rentType",content));
        should.add(QueryBuilders.matchQuery("houseType",content));
         //执行query
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withHighlightBuilder(highlightBuilder)
                .withPageable(PageRequest.of(page,rows))     //分页 
                .build();
        //处理query结果
        AggregatedPage<Item4ES> resultPage = restTemplate.queryForPage(nativeSearchQuery, Item4ES.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<T> resultList = new ArrayList<>();
                //获取搜索结果
                SearchHit[] hits = response.getHits().getHits();
                for (SearchHit hit : hits) {
                    //搜索的结果对象
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    Item4ES item4ES = new Item4ES();

                    // 数据的处理
                    item4ES.setId(sourceAsMap.get("id").toString());
                    item4ES.setRentType(sourceAsMap.get("rentType").toString());
                    item4ES.setPrice(sourceAsMap.get("price").toString());
                    item4ES.setImg(sourceAsMap.get("img").toString());
                    item4ES.setHouseType(sourceAsMap.get("houseType").toString());
                    item4ES.setCity(sourceAsMap.get("city").toString());
                    // 处理高亮
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    if (highlightFields.containsKey("title")){
                        item4ES.setTitle(highlightFields.get("title").getFragments()[0].toString());
                    }else {
                        item4ES.setTitle(sourceAsMap.get("title").toString());
                    }
                    resultList.add((T) item4ES);
                }
                return new AggregatedPageImpl<T>( resultList, // 搜索结果集合
                        pageable, // 分页辅助对象
                        response.getHits().getTotalHits(), // 总计多少行数据， 常规返回时，前三个构造参数就足够了
                        response.getAggregations(), // 聚合对象
                        response.getScrollId(), // 窗口搜索主键
                        response.getHits().getMaxScore()); // 搜索结果的最大分数。
            }

            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> type) {
                return null;
            }
        });

        return resultPage;
    }

    @Override
    public void batchInsert(List<Item4ES> esList) {
        if (initEnabled){
           createIndex();
           putMapping();
        }
       List<IndexQuery> indexQueries = new ArrayList<>();
        for (Item4ES es : esList) {
            indexQueries.add(new IndexQueryBuilder().withObject(es).build());
        }
        restTemplate.bulkIndex(indexQueries);
    }

    @Override
    public void insert(Item4ES item4ES) {
        batchInsert(Arrays.asList(item4ES));
    }

    private void createIndex(){
        restTemplate.createIndex(Item4ES.class);
    }
    private void putMapping(){
        restTemplate.putMapping(Item4ES.class);
    }
}
