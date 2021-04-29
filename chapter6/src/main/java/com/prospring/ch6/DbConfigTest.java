package com.prospring.ch6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.prospring.ch6.config.AppConfig;
import com.prospring.ch6.config.DbConfig;

public class DbConfigTest {
	private static Logger logger = LoggerFactory.getLogger(DbConfigTest.class);
	
	private static void testDataSource(DataSource dataSource) throws SQLException{
		 Connection connection = null;
		 try {
			 System.out.println("trying to connect:");
			 
			 connection = dataSource.getConnection();
			 System.out.println("connected?");
			 PreparedStatement statement = connection.prepareStatement("select * from \"MUSICDB\".singer");
			 ResultSet resultSet = statement.executeQuery();
			 while (resultSet.next()) {
				 System.out.println("we got it");
			 }
			 statement.close();
			 } catch (Exception e) {
				logger.debug("Something unexpected happened.", e);
			 } finally {
				 if (connection != null) {
					 connection.close();
				 }
			 }
	}
	
	public static void main(String... args) throws SQLException {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
		
		testDataSource(dataSource);
		
		ctx.close();	
	}
}

