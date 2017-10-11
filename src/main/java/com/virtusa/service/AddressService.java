package com.virtusa.service;

import java.util.List;

import com.virtusa.model.Address;

public interface AddressService {

  List retreiveById(int customerId);
  List retreiveByStr(String CityStateOrCountry, String ColumnName);
  void insert(Address address);
  void autoGenerate(int numberOfEntries);
  List<Address> getAll();
  void update(Address address);
  void delete(String cid);
  
}
