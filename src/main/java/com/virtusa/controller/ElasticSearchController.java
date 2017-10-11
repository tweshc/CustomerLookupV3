package com.virtusa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.virtusa.model.Address;
import com.virtusa.service.ElasticSearchService;

/**
 * Holds all methods for Elastic Search Engine functionality
 * @author tchowdhury
 *
 */
@Controller
public class ElasticSearchController {
  
  @Autowired
  public ElasticSearchService elasticSearchService;
  
  @RequestMapping(value="ElasticSearch/elasticSearchMenu", method=RequestMethod.GET)
  public String elasticSearchMenu (Model model) {
    Address address = new Address();
    address.setCustomerId(5000);
    model.addAttribute("address", address);
    System.out.println("customerId: " + address.getCustomerId());
    
    return "ElasticSearch/elasticSearchMenu";
  }
  
  /**
   * Parameters are Address object's parameters. This method creates a new Address 
   * instance and sends it to the service tier for processing
   * @param model
   * @param bn
   * @param ci
   * @param co
   * @param rwn
   * @param rwt
   * @param s
   * @param u
   * @return
   * @throws Exception
   */
  @RequestMapping(value="ElasticSearch/elasticSearchMenu", method=RequestMethod.POST)
  public String elasticSearchMenuIndex(Model model, @RequestParam String bn, @RequestParam String ci, 
      @RequestParam String co, @RequestParam String rwn, @RequestParam String rwt, 
      @RequestParam String s, @RequestParam String u) throws Exception {
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
    
    elasticSearchService.indexData(address);
    
    return "/index";
  }
  
  @RequestMapping(value="ElasticSearch/BulkIndex", method=RequestMethod.GET)
  public String elasticSearchBulkIndexPage(Model model) {
    return "ElasticSearch/BulkIndex";
  }
  
  @RequestMapping(value="ElasticSearch/BulkIndex", method=RequestMethod.POST)
  public String elasticSearchMenuBulkIndex(Model model2, @RequestParam String numberOfentries) throws Exception {
    
    elasticSearchService.bulkIndex(Integer.parseInt(numberOfentries));
    
    return "ElasticSearch/Search";
  }
  
  @RequestMapping(value="ElasticSearch/Search", method=RequestMethod.GET)
  public String SearchPage (Model model) {
    return "ElasticSearch/Search";
  }
  
  @RequestMapping(value="ElasticSearch/Search", method=RequestMethod.POST)
  public String SearchPageQuery (Model model, @RequestParam String searchQuery) throws Exception {
    
    List<Address> resultAddresses = elasticSearchService.searchQuery(searchQuery);
    return "/index";
  }
  
}
