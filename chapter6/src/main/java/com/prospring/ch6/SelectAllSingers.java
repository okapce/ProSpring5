package com.prospring.ch6;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

import com.prospring.ch6.entities.Singer;

public class SelectAllSingers extends MappingSqlQuery<Singer>{
	private static final String SQL_SELECT_ALL_SINGER = "select id, first_name, last_name, birth_date from \"MUSICDB\".singer";
	
	public SelectAllSingers(DataSource dataSource) {
		 super(dataSource, SQL_SELECT_ALL_SINGER);
	}
	
	protected Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
		 Singer singer = new Singer();
		 
		 singer.setId(rs.getLong("id"));
		 singer.setFirstName(rs.getString("first_name"));
		 singer.setLastName(rs.getString("last_name"));
		 singer.setBirthDate(rs.getDate("birth_date"));
		 
		 return singer;
	}
}
