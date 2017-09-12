package com.virtusa.repository;

import java.util.List;

import com.virtusa.model.Address;

public interface AddressRepository {
  List retreive(int customerId);
}
