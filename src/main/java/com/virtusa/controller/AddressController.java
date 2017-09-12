package com.virtusa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.virtusa.model.Address;
import com.virtusa.service.AddressService;

@Controller
public class AddressController {
  
  public int enteredId;
  
  @Autowired
  public AddressService addressService;
  
  @RequestMapping(value = "/enterCustomerId", method=RequestMethod.GET)
  public String enterCustomerId (Model model) {
    Address address = new Address();
    address.setCustomerId(5000);
    model.addAttribute("address", address);
    System.out.println("customerId: " + address.getCustomerId());
    
    return "enterCustomerId";
  }
  
  @RequestMapping(value="/enterCustomerId", method=RequestMethod.POST)
  public String enterForm(Model model, @RequestParam String customerId) {
    
    int customerIdToInt = Integer.parseInt(customerId);
    System.out.println(customerIdToInt + " String: " + customerId);
    List<Address> resultAddress = addressService.retreive(customerIdToInt);
    System.out.println(resultAddress);
    model.addAttribute("address", resultAddress);
    
    return "addressReport";
  }
}
