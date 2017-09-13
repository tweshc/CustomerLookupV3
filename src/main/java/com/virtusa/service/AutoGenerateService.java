package com.virtusa.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.model.Address;
import com.virtusa.repository.AddressRepository;

@Service
public class AutoGenerateService {
  
  public AutoGenerateService() {};
  
  @Autowired
  private AddressRepository addressRepository;
  
  //data for auto-generate
  List<String> countries = Arrays.asList("USA","India");
    List<String> usaStates = Arrays.asList("NJ", "CT", "NY");
      List<String> njCities = Arrays.asList("Paramus", "Jersey City", "Atlantic City");
      List<String> ctCities = Arrays.asList("Cheshire","Stamford","New Haven");
      List<String> nyCities = Arrays.asList("Albany", "NYC", "Rochester");
    List<String> indiaStates = Arrays.asList("West Bengal","Uttar Pradesh","Rajasthan");
      List<String> westBengalCities = Arrays.asList("Kolkata", "Durgapur", "Siliguri");
      List<String> uttarPradeshCities = Arrays.asList("Agra","Lucknow","Kanpur");
      List<String> rajasthanCities = Arrays.asList("Jaipur","Kota","Bundi");
      
   List<String> roadwayTypes = Arrays.asList("Street","Avenue","Boulevard","Place",
        "Court","Circle","Alley","Route","Highway","Centre","Lane"
        ,"Route", "Way","Drive");
    
   String unit = "abcdefghijklmn";
   
   public void autoGenerate(int numberOfEntries) {
     
     for(int i = 0; i<numberOfEntries; i++) {
       String autoCountry = autoGenCountry();
       String state = autoGenState(autoCountry);
       String city = autoGenCity(state);
       
       Address autoAddress = new Address();
       autoAddress.setBuildingNumber(autoGenBuildingNum());
       autoAddress.setCountry(autoCountry);
       autoAddress.setState(state);
       autoAddress.setCity(city);
       autoAddress.setRoadwayName(autoGenRoadwayName());
       autoAddress.setRoadwayType(autoGenRoadwayType());
       autoAddress.setUnit(autoGenUnit());
       
       addressRepository.insert(autoAddress);
       System.out.println("===========================");
       System.out.println(autoAddress.getBuildingNumber());
       System.out.println(autoAddress.getCountry());
       System.out.println(autoAddress.getState());
       System.out.println(autoAddress.getCity());
       System.out.println(autoAddress.getRoadwayName());
       System.out.println(autoAddress.getRoadwayType());
       System.out.println(autoAddress.getUnit());
       System.out.println("===========================");
     }
   }
   
   private int autoGenBuildingNum() {
     return randomWithRange(1,1000);
   }
   
   private String autoGenCountry() {
     int random = randomWithRange(0,1);
     return countries.get(random);
   }
   
   private String autoGenState(String country) {
     int random = randomWithRange(0,2);
     if(country.equalsIgnoreCase("USA")) {
       return usaStates.get(random);
     }else
       return indiaStates.get(random);
   }
   
   private String autoGenCity(String state) {
     int random = randomWithRange(0,2);
     if(state.equalsIgnoreCase("NJ")) {
       return njCities.get(random);
     }else if(state.equalsIgnoreCase("NY")) {
       return nyCities.get(random);
     }else if(state.equalsIgnoreCase("CT")) {
       return ctCities.get(random);
     }else if(state.equalsIgnoreCase("West Bengal")) {
       return westBengalCities.get(random);
     }else if(state.equalsIgnoreCase("Uttar Pradesh")) {
       return uttarPradeshCities.get(random);
     }else
       return rajasthanCities.get(random);
   }
   
   private String autoGenRoadwayName() {
     List<String> roadwaysFromDB = addressRepository.retreiveAllRoadwayNames();
     return roadwaysFromDB.get(randomWithRange(0,roadwaysFromDB.size()-1));
   }
   
   private String autoGenRoadwayType() {
     return roadwayTypes.get(randomWithRange(0,13));
   }
   
   private String autoGenUnit() {
     String unitNumberToStr = String.valueOf(randomWithRange(1, 40));;
     char unitLetter = unit.charAt(randomWithRange(0,unit.length()-1));
     return unitNumberToStr+unitLetter;
   }
   
   private int randomWithRange(int min, int max) {
     int range = (max - min) + 1;     
     return (int)(Math.random() * range) + min;
   }
  
}
