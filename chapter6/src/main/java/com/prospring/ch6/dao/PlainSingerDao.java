package com.prospring.ch6.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prospring.ch6.entities.Singer;


public class PlainSingerDao implements SingerDao{
	private static Logger logger =  LoggerFactory.getLogger(PlainSingerDao.class);			
	static {	
		try {		
			Class.forName("org.postgresql.Driver");			
		} catch (ClassNotFoundException ex) {		
			logger.error("Prblem loadng DB dDiver!", ex);			
		}		
	}
	
	private Connection getConnection() throws SQLException {	
		return DriverManager.getConnection(			 
				"jdbc:postgresql://localhost:5432/postgres?useSSL=true","postgres", "admin");			 
	} 

	
	private void closeConnection(Connection connection) {	
		if (connection == null) {		
			return;			
		}
		
		try {		
			connection.close();			
		} catch (SQLException ex) {		
			logger.error("Problem closing connection to the database!",ex);		
		}
		
	}

	@Override
	public List<Singer> findAll() {
		List<Singer> result = new ArrayList<>();
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement("select * from \"MUSICDB\".singer");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Singer singer = new Singer();
				singer.setId(resultSet.getLong("id"));
				singer.setFirstName(resultSet.getString("first_name"));
				singer.setLastName(resultSet.getString("last_name"));
				singer.setBirthDate(resultSet.getDate("birth_date"));
				result.add(singer);
			}
			statement.close();
		} catch (SQLException ex) {
			logger.error("Problem when executing SELECT!",ex);
		} finally {
			closeConnection(connection);
		}
		return result;
	}
		 
	@Override
	public void insert(Singer singer) {
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
			"insert into \"MUSICDB\".Singer (first_name, last_name, birth_date) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, singer.getFirstName());
			statement.setString(2, singer.getLastName());
			statement.setDate(3, singer.getBirthDate());
			statement.execute();
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				singer.setId(generatedKeys.getLong(1));
			}
			
			statement.close();
		} catch (SQLException ex) {
			logger.error("Problem executing INSERT", ex);
		} finally {
			closeConnection(connection);
		}
	}
	@Override
	public void delete(Long singerId) {
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement
			("delete from \"MUSICDB\".singer where id=?");
			statement.setLong(1, singerId);
			statement.execute();
			statement.close();
		} catch (SQLException ex) {
			logger.error("Prblem executing DELETE", ex);
		} finally {
			closeConnection(connection);
		}
	}


	@Override
	public List<Singer> findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String findLastNameById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String findFirstNameById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Singer> findAllWithDetail() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void update(Singer singer) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void insertWithDetail(Singer singer) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String findNameById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Singer> findAllWithAlbums() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void insertWithAlbum(Singer singer) {
		// TODO Auto-generated method stub
		
	}
}

