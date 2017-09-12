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

import com.mysql.fabric.xmlrpc.base.Array;
import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;
import com.virtusa.model.Address;

@Repository
public class AddressRepositoryImpl implements AddressRepository {
  
  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  private static class EmployeeRowMapper implements RowMapper<Address> {

    public Address mapRow(ResultSet resultSet, int i) throws SQLException {
        Address address = new Address();
        address.setCustomerId(resultSet.getInt("id"));
        address.setCity(resultSet.getString("City"));
        address.setBuildingNumber(resultSet.getInt("BuildingNum"));
        address.setCountry(resultSet.getString("Country"));
        address.setState(resultSet.getString("State"));
        address.setRoadwayType(resultSet.getString("RoadwayType"));
        address.setRoadwayName(resultSet.getString("RoadwayName"));
        return address;
    }
}


  @Override
  public List retreive(int customerId) {
    List<Address> list= new ArrayList<>();
   String sql = "Select * from results where id = ?";
    return this.jdbcTemplate.queryForList(sql,new Object[] {customerId});
    /*List<Map<String, Object>> coll = this.jdbcTemplate.queryForList("SELECT City,BuildingNum,Country,State,RoadwayType,RoadwayName,Unit from results where id = ?", 
        new Object[] {customerId});*/
    
/*   Collection col = this.jdbcTemplate.queryForList("SELECT City,BuildingNum,Country,State,RoadwayType,RoadwayName,Unit from results where id = ?", 
        new Object[] {customerId});*/
  //  List<Address> addressList = jdbcTemplate.query("SELECT City,BuildingNum,Country,State,RoadwayType,RoadwayName,Unit from results where id = ?", new Object[] {customerId}, new EmployeeRowMapper());
    /*Address address = jdbcTemplate.queryForObject("SELECT City,BuildingNum,Country,State,RoadwayType,RoadwayName,Unit from results where id = ?", new Object[] {1001}, new EmployeeRowMapper());
   jdbcTemplate.queryforob
    */
    /*    System.out.println(col.getClass());
    System.out.println(col.getClass().getClass());*/
    //System.out.println(" address list "+address);
   // System.out.println(coll);
     //list = jdbcTemplate.query("SELECT City,BuildingNum,Country,State,RoadwayType,RoadwayName,Unit from results where id = ?", new Object[] {customerId}, new EmployeeRowMapper());
    //System.out.println(coll.get(0).get(customerId).getClass());
    
   // return (Address) coll.get(0).get(customerId);
     //jdbcTemplate.queryfor
     
     //return list.get(0);
     /* this.jdbcTemplate.queryForList
        ("SELECT City,BuildingNum,Country,State,RoadwayType,RoadwayName,Unit from results where id = ?", 
            new Object[] {customerId});*/
  }

}
