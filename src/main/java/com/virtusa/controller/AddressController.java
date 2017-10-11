package com.virtusa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.aspect.logging.Log;
import com.virtusa.model.Address;
import com.virtusa.service.AddressService;

/**
 * Contains all methods for using application features that are related to MySql operations
 * @author tchowdhury
 *
 */
@Controller
public class AddressController {
  
  public int enteredId;
  public int newCustomerId = 9999;
  
  @Autowired
  public AddressService addressService;
  
  @RequestMapping(value="/", method=RequestMethod.GET)
  public String homepage () {
    return "index";
  }
  
  @RequestMapping(value = "/enterCustomerId", method=RequestMethod.GET)
  public String enterCustomerId (Model model) {
    
    Address address = new Address();
    address.setCustomerId(5000);
    model.addAttribute("address", address);
    
    return "enterCustomerId";
  }
  
  @RequestMapping(value="/enterCustomerId", method=RequestMethod.POST)
  public String enterForm(Model model, @RequestParam String customerId) {
    
    int customerIdToInt = Integer.parseInt(customerId);
    
    @SuppressWarnings("unchecked")
    List<Address> resultAddress = addressService.retreiveById(customerIdToInt);
    model.addAttribute("address", resultAddress);
    
    return "addressReport";
  }
  
  @RequestMapping(value="/enterCustomerCity", method=RequestMethod.GET)
  public String enterCustomerCity(Model model) {
    
    Address address = new Address();
    model.addAttribute("address", address);
    
    return "enterCustomerCity";
  }
  
  @RequestMapping(value="/enterCustomerCity", method=RequestMethod.POST)
  public String enterCustomerCityForm(Model model, @RequestParam String customerCity) {

    @SuppressWarnings("unchecked")
    List<Address> resultAddresses = addressService.retreiveByStr(customerCity, "City");
    model.addAttribute("address", resultAddresses);
    
    return "addressReport";
  }
  
  @RequestMapping(value="/enterCustomerState", method=RequestMethod.GET)
  public String enterCustomerState(Model model) {
    
    Address address = new Address();
    model.addAttribute("address", address);
    
    return "enterCustomerState";
  }
  
  @RequestMapping(value="/enterCustomerState", method=RequestMethod.POST)
  public String enterCustomerStateForm(Model model, @RequestParam String customerState) {

    @SuppressWarnings("unchecked")
    List<Address> resultAddresses = addressService.retreiveByStr(customerState, "State");
    System.out.println(resultAddresses);
    model.addAttribute("address", resultAddresses);
    
    return "addressReport";
  }
  
  @RequestMapping(value="/enterCustomerCountry", method=RequestMethod.GET)
  public String enterCustomerCountry(Model model) {

    Address address = new Address();
    model.addAttribute("address", address);
    
    return "enterCustomerCountry";
  }
  
  @RequestMapping(value="/enterCustomerCountry", method=RequestMethod.POST)
  public String enterCustomerCountryForm(Model model, @RequestParam String customerCountry) {

    @SuppressWarnings("unchecked")
    List<Address> resultAddresses = addressService.retreiveByStr(customerCountry, "Country");
    model.addAttribute("address", resultAddresses);
    
    return "addressReport";
  }
  
  @RequestMapping(value="/insertNewCustomer", method=RequestMethod.GET)
  public String insertNewCustomer(Model model) {
    
    Address address = new Address();
    model.addAttribute("address", address);
    
    return "insertNewCustomer";
  }
  
  /**
   * Parameters are Address object properties abbreviated. Creates a new instance of Address with given parameters and
   * then sends it to the Service for processing.
   * @param model
   * @param bn
   * @param ci
   * @param co
   * @param rwn
   * @param rwt
   * @param s
   * @param u
   * @return
   */
  @RequestMapping(value="/insertNewCustomer", method=RequestMethod.POST)
  public String insertNewCustomerForm(Model model, 
      @RequestParam String bn, @RequestParam String ci, 
      @RequestParam String co, @RequestParam String rwn, 
      @RequestParam String rwt,@RequestParam String s, 
      @RequestParam String u) {
    
      Address address = new Address();
      int buildingNumToInt = Integer.parseInt(bn.trim());
      
      address.setCustomerId(0); 
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
  
  @RequestMapping(value="/updateCustomer", method=RequestMethod.GET)
  public String updateCustomer(Model model) {
    return "updateCustomer";
  }
  
  /**
   * Parameters are Address object's properties abbreviated. When user requests to update an Address, they will be required to specify
   * which address by passing an id of the address. Then, the service tier is called for further processing.
   * @param model
   * @param cid
   * @param bn
   * @param ci
   * @param co
   * @param rwn
   * @param rwt
   * @param s
   * @param u
   * @return
   */
  @RequestMapping(value="/updateCustomer", method=RequestMethod.POST)
  public String updateCustomerForm(Model model, 
      @RequestParam String cid, @RequestParam String bn, 
      @RequestParam String ci, @RequestParam String co, 
      @RequestParam String rwn, @RequestParam String rwt, 
      @RequestParam String s, @RequestParam String u) {
    
      Address address = new Address();
      int buildingNumToInt = Integer.parseInt(bn.trim());
      int customerId = Integer.parseInt(cid);
      
      address.setCustomerId(customerId);
      address.setBuildingNumber(buildingNumToInt);
      address.setCity(ci);
      address.setCountry(co);
      address.setRoadwayName(rwn);
      address.setRoadwayType(rwt);
      address.setState(s);
      address.setUnit(u);
      addressService.update(address);
      
      return "index";
  }
  
  @RequestMapping(value="/deleteCustomer", method=RequestMethod.GET)
  public String deleteCustomer(Model model) {
    return "deleteCustomer";
  }
  
  @RequestMapping(value="/deleteCustomer", method=RequestMethod.POST)
  public String deleteCustomerForm(Model model, @RequestParam String cid) {
    addressService.delete(cid);
    return "index";
  }
  
  @RequestMapping("/autoGenerate")
  public String autoGenerate(Model model) {
    return "autoGenerate";
  }
  
  @RequestMapping(value="/autoGenerate", method=RequestMethod.POST)
  public String autoGenerateForm(Model model, @RequestParam String numberOfNewEntries) {
    addressService.autoGenerate(Integer.parseInt(numberOfNewEntries));
    return "index";
  }
  
  @RequestMapping(value="/getAllRecords", method=RequestMethod.GET)
  public String getAllRecords(Model model) {
    return "getAllRecords";
  }
  
  @RequestMapping(value="/getAllRecords", method=RequestMethod.POST)
  public String getAllRecords2(Model model) {
    List<Address> allAddresses = addressService.getAll();
    model.addAttribute("address", allAddresses);
    return "addressReport";
  }
}
