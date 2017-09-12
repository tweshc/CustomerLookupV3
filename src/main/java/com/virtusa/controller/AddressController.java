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
  public int newCustomerId = 9999;
  
  @Autowired
  public AddressService addressService;
  
  @RequestMapping(value="/", method=RequestMethod.GET)
  public String homepage (Model model) {
    return "index";
  }
  
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
    //System.out.println(customerIdToInt + " String: " + customerId);
    @SuppressWarnings("unchecked")
    List<Address> resultAddress = addressService.retreiveById(customerIdToInt);
    System.out.println(resultAddress);
    model.addAttribute("address", resultAddress);
    
    return "addressReport";
  }
  
  @RequestMapping(value="/enterCustomerCity", method=RequestMethod.GET)
  public String enterCustomerCity(Model model) {
    //System.out.println("enterCustomerCity method is working".toUpperCase());
    Address address = new Address();
    model.addAttribute("address", address);
    return "enterCustomerCity";
  }
  
  @RequestMapping(value="/enterCustomerCity", method=RequestMethod.POST)
  public String enterCustomerCityForm(Model model, @RequestParam String customerCity) {
    System.out.println(customerCity + " was entered");
    @SuppressWarnings("unchecked")
    List<Address> resultAddresses = addressService.retreiveByStr(customerCity, "City");
    System.out.println(resultAddresses);
    model.addAttribute("address", resultAddresses);
    return "addressReport";
  }
  
  @RequestMapping(value="/enterCustomerState", method=RequestMethod.GET)
  public String enterCustomerState(Model model) {
    System.out.println("enterCustomerState method is working".toUpperCase());
    Address address = new Address();
    model.addAttribute("address", address);
    return "enterCustomerState";
  }
  
  @RequestMapping(value="/enterCustomerState", method=RequestMethod.POST)
  public String enterCustomerStateForm(Model model, @RequestParam String customerState) {
    System.out.println(customerState + " was entered");
    @SuppressWarnings("unchecked")
    List<Address> resultAddresses = addressService.retreiveByStr(customerState, "State");
    System.out.println(resultAddresses);
    model.addAttribute("address", resultAddresses);
    return "addressReport";
  }
  
  @RequestMapping(value="/enterCustomerCountry", method=RequestMethod.GET)
  public String enterCustomerCountry(Model model) {
    System.out.println("enterCustomerCountry method is working".toUpperCase());
    Address address = new Address();
    model.addAttribute("address", address);
    return "enterCustomerCountry";
  }
  
  @RequestMapping(value="/enterCustomerCountry", method=RequestMethod.POST)
  public String enterCustomerCountryForm(Model model, @RequestParam String customerCountry) {
    System.out.println(customerCountry + " was entered");
    @SuppressWarnings("unchecked")
    List<Address> resultAddresses = addressService.retreiveByStr(customerCountry, "Country");
    System.out.println(resultAddresses);
    model.addAttribute("address", resultAddresses);
    return "addressReport";
  }
  
  @RequestMapping(value="/insertNewCustomer", method=RequestMethod.GET)
  public String insertNewCustomer(Model model) {
    System.out.println("insertNewCustomer method is working".toUpperCase());
    Address address = new Address();
    model.addAttribute("address", address);
    return "insertNewCustomer";
  }
  
  @RequestMapping(value="/insertNewCustomer", method=RequestMethod.POST)
  public String insertNewCustomerForm(Model model, @RequestParam String bn, @RequestParam String ci, 
      @RequestParam String co, @RequestParam String rwn, @RequestParam String rwt, 
      @RequestParam String s, @RequestParam String u) {
      Address address = new Address();
      int buildingNumToInt = Integer.parseInt(bn.trim());
      address.setCustomerId(0); //set in repo tier
      address.setBuildingNumber(buildingNumToInt);
      address.setCity(ci);
      address.setCountry(co);
      address.setRoadwayName(rwn);
      address.setRoadwayType(rwt);
      address.setState(s);
      address.setUnit(u);
      addressService.insert(address);
      return "index";
  }
}
