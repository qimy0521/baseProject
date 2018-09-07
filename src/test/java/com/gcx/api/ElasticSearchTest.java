package com.gcx.api;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;

import java.net.InetAddress;
import java.util.concurrent.ExecutionException;

public class ElasticSearchTest {
    //构建集群名字

    private static Settings settings =Settings.settingsBuilder().put("cluster.name","elastic").build();

    private static Client transportEsClient=null;



    public void init(){
        if(transportEsClient == null){
            try {
                transportEsClient= TransportClient.builder().settings(settings).build()
                        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("test118"),9300));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void Test1() throws ExecutionException, InterruptedException {
        init();
        Thread.sleep(1000);
        SearchResponse searchResponse = transportEsClient
                .prepareSearch("goodscodebrandref")
                .setTypes("goodscodebrandref")
                .setQuery(QueryBuilders.matchQuery("路标","路标"))
                .execute().get();
        SearchHits hits = searchResponse.getHits();
        long totalHits = hits.getTotalHits();
        System.out.println(totalHits);


    }
}
