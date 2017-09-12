package com.virtusa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="results")
public class Address {
  
/*  @Id
  @GeneratedValue
  @Column(name="GOAL_ID")
  private Long id;*/

  public Address() {  }
  
  @Id
  @Column(name="id")
  private int customerId; //customer id
  
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
  
/*  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }*/
  //@Range(min = 1001, max = 9999)
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
}