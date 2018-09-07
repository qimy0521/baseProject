package com.gcx.api.controller.elasticSerach;

import com.gcx.api.common.elasticSerachs.ElasticSerachUtil;
import com.gcx.api.common.util.MyResult;
import com.gcx.api.model.Flicker;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupSerachServiceImpl implements GroupSerachService {
    @Autowired
    Client client;

    /**
     * must 相当于 与 & =
     * must not 相当于 非 ~   ！=
     * should 相当于 或  |   or
     * filter  过滤
     * @param flicker 查询对象
     */
    @Override
    public MyResult serach(Flicker flicker) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //需要搜索的字段（matchQuery 匹配查询）
        MatchQueryBuilder matchQueryBuilder=QueryBuilders.matchQuery("organizationName",flicker.getOrganizationName());
        MatchQueryBuilder matchQueryBuilder1 = QueryBuilders.matchQuery("county", flicker.getCounty());
        matchQueryBuilder.boost(150l);
        matchQueryBuilder1.boost(50l);
        NestedQueryBuilder organizationName = QueryBuilders.nestedQuery("organizationName", matchQueryBuilder1);
        boolQueryBuilder.should(matchQueryBuilder);//should 相当于 OR
        boolQueryBuilder.must(matchQueryBuilder1);
        boolQueryBuilder.should(organizationName);



        //创建搜索的请求
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(ElasticSerachUtil.NAME_INDEX)
                .setTypes(ElasticSerachUtil.NAME_TYPE)
                .setSearchType(SearchType.DFS_QUERY_AND_FETCH)
                .setQuery(boolQueryBuilder);
        System.out.print(searchRequestBuilder.toString());
        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
        SearchHits hits = searchResponse.getHits();
        long l = hits.totalHits();
        return MyResult.ok(l);
    }
}
