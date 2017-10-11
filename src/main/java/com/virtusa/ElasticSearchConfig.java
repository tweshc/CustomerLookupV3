package com.virtusa;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

public class ElasticSearchConfig {
  
  public static final String TYPE_NAME = "notes";
  public static final String INDEX_NAME = "addresses2";
  //public static JestClient jestClient;
  
  public static JestClient configureAndCreateJestClient() {
    
    HttpClientConfig clientConfig = new HttpClientConfig.Builder(
        "http://localhost:9200").multiThreaded(true).build();
    
    JestClientFactory factory = new JestClientFactory();
    
    factory.setHttpClientConfig(clientConfig);
    
    JestClient jestClient = factory.getObject();
    
    return jestClient;
  }

}
