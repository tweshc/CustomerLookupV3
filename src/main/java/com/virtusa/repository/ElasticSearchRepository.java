package com.virtusa.repository;

import java.util.List;

import com.virtusa.model.Address;

import io.searchbox.client.JestClient;

public interface ElasticSearchRepository {
  void indexData(final JestClient jestClient, Address address) throws Exception;
  void bulkIndex(final JestClient jestClient, List<Address> bulkAddresses) throws Exception;
  
  void createIndex(final JestClient jestClient);
  void deleteIndex(final JestClient jestClient);
  
  void readAllData(final JestClient jestClient);
  List<Address> searchQuery(final JestClient jestClient, String searchQuery) throws Exception;
}
