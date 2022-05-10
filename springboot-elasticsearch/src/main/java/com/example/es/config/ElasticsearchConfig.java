package com.example.es.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import java.time.Duration;

/**
 * @Author: mxx
 * @Description:
 */
//@Configuration
public class ElasticsearchConfig {

    /**
     * 可以使用配置文件或者配置类配置
     *
     * @return
     */
//    @Bean
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration configuration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .withConnectTimeout(Duration.ofSeconds(10))
                .withSocketTimeout(Duration.ofSeconds(10))
                .withBasicAuth("es-1", "")
                .build();
        return RestClients.create(configuration).rest();
    }

}
