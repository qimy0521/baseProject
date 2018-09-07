package com.gcx.api.common.elasticSerachs;


import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ElasticSerachsConf {


    private static String cluster="cluster.name";
    @Value("${elastic.serviceName}")
    private String elasticServiceName;
    @Value("${elastic.port}")
    private int elasticServicePort;
    @Value("${elasticsearch.cluster-name}")
    private static String clusterName;


    private static Settings esettings =Settings.settingsBuilder()
            .put(cluster,"elastic")//集群名称和实例名称
            .put("client.transport.sniff", true)//设置自动探测集群的其他的IP
            .build();

    private static Client transportEsClient=null;

    @Bean
    public Client client(){
        try {
            transportEsClient= TransportClient.builder().settings(esettings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(elasticServiceName),elasticServicePort));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return transportEsClient;
    }
}
