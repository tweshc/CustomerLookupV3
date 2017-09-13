package com.virtusa.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.base.Array;
import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;
import com.virtusa.model.Address;

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
        Integer.class) + 1001;
  }

  @Transactional
  @Override
  public void insert(Address address) {
    
    String sql = "INSERT INTO results (id, BuildingNum, City, Country, RoadwayName, RoadwayType, State, Unit) "
        + "VALUES ('"+generateId()+"', '"+address.getBuildingNumber()+"', '"+address.getCity()+"', "
            + "'"+address.getCountry()+"', '"+address.getRoadwayName()+"', '"+address.getRoadwayType()+"', "
                + "'"+address.getState()+"', '"+address.getUnit()+"')";
    this.jdbcTemplate.update(sql);
    //System.out.println("SUCCESS ON INSERT, CHECK TABLE");
  }

  @Override
  public List retreiveAllRoadwayNames() {
    String sql = "Select RoadwayName from results";
    return this.jdbcTemplate.queryForList(sql, String.class);
  }

}
