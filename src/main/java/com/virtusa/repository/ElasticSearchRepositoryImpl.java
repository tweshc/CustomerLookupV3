package com.virtusa.repository;

import com.virtusa.model.Address;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.client.JestResultHandler;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Repository;

/**
 * DAO for working with data stored in Elastic Search. The search functionality
 * is currently using ajax on client side to make queries. The service class that calls methods 
 * in this class must pass in an instance of JestClient. The indexName and typeName refer to 
 * document property names that are required for indexing.
 * @author tchowdhury
 *
 */
@Repository
public class ElasticSearchRepositoryImpl implements ElasticSearchRepository {
  
  private final String indexName = "addresses2";
  private final String typeName = "data";
  
  @Override
  public void indexData(JestClient jestClient, Address address) throws Exception {
    
    final Address addressToBeIndexed = address;
    
    Index index = new Index.Builder(addressToBeIndexed).index(indexName).type(typeName).build();
    jestClient.execute(index);
    
    jestClient.shutdownClient();

  }

  @Override
  public void bulkIndex(JestClient jestClient, List<Address> bulkAddresses) throws Exception {
    //String temp = ".addAction(new Index.Builder(note3).index(DIARY_INDEX_NAME).type(NOTES_TYPE_NAME).build()).build()";

    for(Address address : bulkAddresses) {
      address.setCustomerId(randomWithRange(1,20000));
      Bulk bulk = new Bulk.Builder()
          .addAction(
              new Index.Builder(address).index("addresses2")
              .type("data").build()).build();
      JestResult result = jestClient.execute(bulk);
    }
    jestClient.shutdownClient();
  }

  //@SuppressWarnings("deprecation")
  @Override
  public List<Address> searchQuery(JestClient jestClient, String searchQuery) throws Exception {
    System.out.println("searchQuery method has been called for Elastic Search =========");
    searchQuery = searchQuery.trim();
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.matchQuery("roadwayType", searchQuery));
    
    Search search = new Search.Builder(searchSourceBuilder.toString())
        .addIndex(indexName)
        .addType(typeName)
        .build();
    
    SearchResult result = jestClient.execute(search);
    
    List<Address> resultAddresses = new ArrayList<Address>();
    return resultAddresses;
  }
  
  private int randomWithRange(int min, int max) {
    int range = (max - min) + 1;     
    return (int)(Math.random() * range) + min;
  }

  @Override
  public void createIndex(JestClient jestClient) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void deleteIndex(JestClient jestClient) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void readAllData(JestClient jestClient) {
    // TODO Auto-generated method stub
    
  }

}
