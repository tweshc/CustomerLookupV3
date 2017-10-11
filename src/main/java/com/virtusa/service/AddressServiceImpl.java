package com.virtusa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.model.Address;
import com.virtusa.repository.AddressRepository;

/**
 * This class delegates all necessary information to Repository tier. For Bulk creations
 * of randomly generated addresses, work gets delegated to AutoGenerateService.
 * @author tchowdhury
 *
 */
@Service
public class AddressServiceImpl implements AddressService {
  
  public AddressServiceImpl() {};
  
  @Autowired
  private AddressRepository addressRepository;
  
  @Autowired AutoGenerateService autoGenerateService;
   
  @Override
  public List<Address> retreiveById(int customerInt) {
    @SuppressWarnings("unchecked")
    List<Address> address = addressRepository.retreiveById(customerInt); 
    return address;
  }

  @Override
  public List retreiveByStr(String CityStateOrCountry, String ColumnName) {
    @SuppressWarnings("unchecked")
    List<Address> address = addressRepository.retreiveByStr(CityStateOrCountry, ColumnName); 
    return address;
  }

  @Override
  public void insert(Address address) {
    addressRepository.insert(address);
  }

  @Override
  public void autoGenerate(int numberOfEntries) {
    autoGenerateService.autoGenerate(numberOfEntries);
  }

  @Override
  public List<Address> getAll() {
    List<Address> result = addressRepository.getAll();
    return result;
  }

  @Override
  public void update(Address address) {
    addressRepository.update(address);
    
  }

  @Override
  public void delete(String cid) {
    int customerId = Integer.parseInt(cid);
    addressRepository.delete(customerId);
  }

  
}