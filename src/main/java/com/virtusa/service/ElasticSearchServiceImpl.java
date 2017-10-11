package com.virtusa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.ElasticSearchConfig;
import com.virtusa.model.Address;
import com.virtusa.repository.ElasticSearchRepository;

import io.searchbox.client.JestClient;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {
  
  final JestClient jestClient = ElasticSearchConfig.configureAndCreateJestClient();
  
  public ElasticSearchServiceImpl() {};
  
  @Autowired
  private ElasticSearchRepository elasticSearchRepository;

  @Override
  public void bulkIndex(int numberOfEntries) throws Exception {
    AutoGenerateService autoGenerateService = new AutoGenerateService();
    List<Address> bulkAddresses = autoGenerateService.autoGenerateBulkIndex(numberOfEntries);
    elasticSearchRepository.bulkIndex(jestClient, bulkAddresses);
  }
  
  @Override
  public void indexData(Address address) throws Exception {
    elasticSearchRepository.indexData(jestClient, address);
  }

  @Override
  public void createIndex() {

    jestClient.shutdownClient();
  }

  @Override
  public void deleteIndex() {

    jestClient.shutdownClient();
  }

  @Override
  public void readAllData() {

    jestClient.shutdownClient();
  }

  @Override
  public List<Address> searchQuery(String searchQuery) throws Exception {
    return elasticSearchRepository.searchQuery(jestClient, searchQuery);
  }

}
