package it.objectmethod.worldmvcspring.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.objectmethod.worldmvcspring.config.ConnectionFactory;
import it.objectmethod.worldmvcspring.dao.ICityDao;
import it.objectmethod.worldmvcspring.domain.City;

@Component
public class CityDaoImpl implements ICityDao {

	@Override
	public City getCityByName(String nameCity) {
		City city = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM city WHERE Name = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nameCity);
			rs = stmt.executeQuery();
			while (rs.next()) {
				city = new City();
				city.setId(rs.getInt("ID"));
				city.setCountryCode(rs.getString("CountryCode"));
				city.setName(rs.getString("Name"));
				city.setDistrict(rs.getString("District"));
				city.setPopulation(rs.getInt("Population"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return city;
	}

	@Override
	public List<City> getCitiesByCountryCode(String countryCode) {
		List<City> list = new ArrayList<>();
		City city = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM city WHERE city.CountryCode= ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, countryCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				city = new City();
				city.setId(rs.getInt("ID"));
				city.setCountryCode(rs.getString("CountryCode"));
				city.setName(rs.getString("Name"));
				city.setDistrict(rs.getString("District"));
				city.setPopulation(rs.getInt("Population"));
				list.add(city);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<City> getCityByNameOrByCountry(String cityName, String country) {
		List<City> list = new ArrayList<>();
		City city = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM city JOIN country ON city.countrycode = country.code WHERE city.name LIKE ? "
					+ "AND (? = '' OR country.code= ?) LIMIT 10";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + cityName + "%");
			stmt.setString(2, country);
			stmt.setString(3, country);
			rs = stmt.executeQuery();
			while (rs.next()) {
				city = new City();
				city.setId(rs.getInt("ID"));
				city.setCountryCode(rs.getString("CountryCode"));
				city.setName(rs.getString("Name"));
				city.setDistrict(rs.getString("District"));
				city.setPopulation(rs.getInt("Population"));
				list.add(city);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return list;

	}

	@Override
	public void postNewCity(String name, String countryCode, String district, int population) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			String sql = "INSERT INTO city (name, countryCode, district, population) VALUES (?, ?, ?, ?);";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, countryCode);
			stmt.setString(3, district);
			stmt.setInt(4, population);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	@Override
	public void putCity(int id, String name, String countryCode, String district, int population) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			String sql = "UPDATE city SET name= ?, countryCode= ?, district = ?, population = ? WHERE ID= ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, countryCode);
			stmt.setString(3, district);
			stmt.setInt(4, population);
			stmt.setInt(5, id);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	@Override
	public City getCityById(int id) {
		City city = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM city WHERE ID = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				city = new City();
				city.setId(rs.getInt("ID"));
				city.setCountryCode(rs.getString("CountryCode"));
				city.setName(rs.getString("Name"));
				city.setDistrict(rs.getString("District"));
				city.setPopulation(rs.getInt("Population"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return city;
	}

}
