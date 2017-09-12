package com.virtusa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.virtusa.model.Address;
import com.virtusa.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
  
  public AddressServiceImpl() {};
  
  @Autowired
  private AddressRepository addressRepository;

  @Transactional
  public List<Address> retreive(int customerId) {
    List<Address> address = addressRepository.retreive(customerId); 
    return address;
  }

}