package com.virtusa.repository;

import java.util.List;

import com.virtusa.model.Address;

public interface AddressRepository {
  List retreiveById(int customerId);
  List retreiveByStr(String CityCountryOrState, String ColumnName);
  void insert(Address address);
  List retreiveAllRoadwayNames();
}

