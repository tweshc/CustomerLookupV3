package com.virtusa.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.base.Array;
import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;
import com.virtusa.model.Address;

/**
 * This is the DAO for Address objects stored in the MySql database
 * Methods that implement all basic CRUD operations are found in this class.
 * It uses Spring's JDBCTemplate for data access and operations. Each method creates its own
 * sql method to perform a data access operation.
 * @author tchowdhury
 *
 */
@Repository
public class AddressRepositoryImpl implements AddressRepository {
  
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List retreiveById(int customerId) {
    String sql = "Select * from results where id = ?";
    return this.jdbcTemplate.queryForList(sql,new Object[] {customerId});
  }

  @Override
  public List retreiveByStr(String CityCountryOrState, String ColumnName) {
    String sql = "Select * from results where " + ColumnName +" = ?";
    return this.jdbcTemplate.queryForList(sql, new Object[] {CityCountryOrState});
  }
  
  public int generateId() {
    return jdbcTemplate.queryForObject("select count(*) from results",
        Integer.class) + 1004;
  }

  @Transactional
  @Override
  public void insert(Address address) {
    
    String sql = "INSERT INTO results (id, BuildingNum, City, Country, RoadwayName, RoadwayType, State, Unit) "
        + "VALUES ('"+generateId()+"', '"+address.getBuildingNumber()+"', '"+address.getCity()+"', "
            + "'"+address.getCountry()+"', '"+address.getRoadwayName()+"', '"+address.getRoadwayType()+"', "
                + "'"+address.getState()+"', '"+address.getUnit()+"')";
    this.jdbcTemplate.update(sql);
  }

  @Override
  public List retreiveAllRoadwayNames() {
    String sql = "Select RoadwayName from results";
    return this.jdbcTemplate.queryForList(sql, String.class);
  }

  @Override
  public List getAll() {
    List<Address> result;
    String sql = "Select * from results";
    return this.jdbcTemplate.queryForList(sql);
  }

  @Override
  public void update(Address address) {
    String sql = 
        "Update results "
        + "Set BuildingNum = " + address.getBuildingNumber() + ", City = '" + address.getCity()+ "', Country = '" + address.getCountry()
    +"', RoadwayName = '"+ address.getRoadwayName() + "', RoadwayType = '"+address.getRoadwayType() + "', State = '" + address.getState() + "', Unit = '" 
    +address.getUnit() + "' Where id = "+address.getCustomerId();
    System.out.println(sql);
    this.jdbcTemplate.execute(sql);
  }

  @Override
  public void delete(int customerId) {
    String sql = "Delete From results Where id = '"+customerId+"';";
    this.jdbcTemplate.execute(sql);
  }

}
