package com.virtusa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import io.searchbox.annotations.JestId;

@Entity
@Table(name="results")
@Component
public class Address {

  public Address() {  }
  
  @Id
  @GeneratedValue
  @Column(name="id")
  private int customerId; //customer id
  
  @JestId
  public String jestId;
  
  @Column(name="BuildingNum")
  private int buildingNumber;
  @Column(name="RoadwayName")
  private String roadwayName;
  @Column(name="RoadwayType")
  private String roadwayType;
  @Column(name="Unit")
  private String unit;
  @Column(name="City")
  private String city;
  @Column(name="State")
  private String state;
  @Column(name="Country")
  private String country;

  public int getCustomerId() {
    return customerId;
  }
  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }
  public int getBuildingNumber() {
    return buildingNumber;
  }
  public void setBuildingNumber(int buildingNumber) {
    this.buildingNumber = buildingNumber;
  }
  public String getRoadwayName() {
    return roadwayName;
  }
  public void setRoadwayName(String roadwayName) {
    this.roadwayName = roadwayName;
  }
  public String getRoadwayType() {
    return roadwayType;
  }
  public void setRoadwayType(String roadwayType) {
    this.roadwayType = roadwayType;
  }
  public String getUnit() {
    return unit;
  }
  public void setUnit(String unit) {
    this.unit = unit;
  }
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }
  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }
  public String getCountry() {
    return country;
  }
  public void setCountry(String country) {
    this.country = country;
  }
  @Override
  public String toString() {
    return "Address [customerId=" + customerId + ", buildingNumber=" + buildingNumber + ", roadwayName=" + roadwayName
        + ", roadwayType=" + roadwayType + ", unit=" + unit + ", city=" + city + ", state=" + state + ", country="
        + country + "]";
  }
  public void setJestId(String jestId) {
    this.jestId = jestId;
  }
  
  public String getJestId() {
    return this.jestId;
  }
}