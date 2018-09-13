package com.gcx.api.common.elasticSerachs;


import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;


@Configuration
public class EsConfig {

    @Value("${spring.data.elasticsearch.properties.clustername}")
    private String clustername;
    @Value("${spring.data.elasticsearch.properties.hosts}")
    private String hosts;
    @Value("${spring.data.elasticsearch.properties.port}")
    private Integer port;

    @Bean
    public TransportClient transportClient() throws UnknownHostException {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        Settings settings = Settings.builder()
                .put("cluster.name", clustername).build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hosts), port));
        return client;
    }

    @Bean("elasticsearchTemplate")
    public ElasticsearchTemplate template()throws UnknownHostException{
        return new ElasticsearchTemplate(transportClient());
    }


}
