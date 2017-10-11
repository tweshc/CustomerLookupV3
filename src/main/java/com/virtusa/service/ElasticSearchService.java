package com.virtusa.service;

import java.util.List;

import com.virtusa.model.Address;

public interface ElasticSearchService {
  
  void indexData(Address address) throws Exception;
  void bulkIndex(int numberOfentries) throws Exception;
  
  void createIndex();
  void deleteIndex();
  
  void readAllData();
  List<Address> searchQuery(String searchQuery) throws Exception;

}
