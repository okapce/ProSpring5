package com.prospring.ch6.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.prospring.ch6.InsertSinger;
import com.prospring.ch6.InsertSingerAlbum;
import com.prospring.ch6.SelectAllSingers;
import com.prospring.ch6.SelectSingerByFirstName;
import com.prospring.ch6.StoredFunctionFirstNameById;
import com.prospring.ch6.UpdateSinger;
import com.prospring.ch6.entities.Album;
import com.prospring.ch6.entities.Singer;

@Repository("singerDao")
public class JdbcSingerDao implements SingerDao, InitializingBean{
	private static Logger logger = LoggerFactory.getLogger(JdbcSingerDao.class);
	private DataSource dataSource;
	private SelectAllSingers selectAllSingers;
	private SelectSingerByFirstName selectSingerByFirstName;
	private UpdateSinger updateSinger;
	private InsertSinger insertSinger;
	private InsertSingerAlbum insertSingerAlbum;
	private StoredFunctionFirstNameById storedFunctionFirstNameById;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.selectAllSingers = new SelectAllSingers(dataSource);	
		//this.selectSingerByFirstName = new SelectSingerByFirstName(dataSource);
		this.updateSinger = new UpdateSinger(dataSource);
		this.insertSinger = new InsertSinger(dataSource);
	}
	 
	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public List<Singer> findAll() {
		return selectAllSingers.execute();
	}

	@Override
	 public List<Singer> findAllWithAlbums() {
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		 String sql = "SELECT s.id, s.first_name, s.last_name, s.birth_date" +
		 ", a.id AS album_id, a.title, a.release_date FROM \"MUSICDB\".singer s " +
		 "LEFT JOIN \"MUSICDB\".album a ON s.id = a.singer_id";
		 return jdbcTemplate.query(sql,  rs -> {
			 Map<Long, Singer> map = new HashMap<>();
			 Singer singer;
			 while (rs.next()) {
				 Long id = rs.getLong("id");
				 singer = map.get(id);
				 if (singer == null) {
					 singer = new Singer();
					 singer.setId(id);
					 singer.setFirstName(rs.getString("first_name"));
					 singer.setLastName(rs.getString("last_name"));
					 singer.setBirthDate(rs.getDate("birth_date"));
					 singer.setAlbums(new ArrayList<>());
					 map.put(id, singer);
				 }
			 Long albumId = rs.getLong("album_id");
			 	if (albumId > 0) {
				 Album album = new Album();
				 album.setId(albumId);
				 album.setSingerId(id);
				 album.setTitle(rs.getString("title"));
				 album.setReleaseDate(rs.getDate("release_date"));
				 singer.getAlbums().add(album);
			 	}
			 }
			 return new ArrayList<>(map.values());
			 });
	 }
	
	@Override
	public List<Singer> findByFirstName(String firstName) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("first_name", firstName);
		return selectSingerByFirstName.executeByNamedParam(paramMap);
	}

	@Override
	public String findLastNameById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findFirstNameById(Long id) {
		List<String> result = storedFunctionFirstNameById.execute(id); 
		return result.get(0);
	}

	@Override
	public List<Singer> findAllWithDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findNameById(Long id) {
		//return jdbcTemplate.queryForObject(
		//		 "SELECT first_name || ' ' || last_name FROM singer WHERE id = ?",
		//		 new Object(id), String.class);
		return null;
	}

	@Override
	public void insert(Singer singer) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("first_name", singer.getFirstName());
		paramMap.put("last_name", singer.getLastName());
		paramMap.put("birth_date", singer.getBirthDate());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		insertSinger.updateByNamedParam(paramMap, keyHolder);
		
		singer.setId(keyHolder.getKey().longValue());
		logger.info("New singer inserted with id: " + singer.getId());
		
		}

	@Override
	public void update(Singer singer) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("first_name", singer.getFirstName());
		paramMap.put("last_name", singer.getLastName());
		paramMap.put("birth_date", singer.getBirthDate());
		paramMap.put("id", singer.getId());
		updateSinger.updateByNamedParam(paramMap);
		logger.info("Existing singer updated with id: " + singer.getId());
	}

	@Override
	public void delete(Long singerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertWithDetail(Singer singer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertWithAlbum(Singer singer) {
		insertSingerAlbum = new InsertSingerAlbum(dataSource);
		Map<String, Object> paramMap = new HashMap<>();
		 
		paramMap.put("first_name", singer.getFirstName());
		paramMap.put("last_name", singer.getLastName());
		paramMap.put("birth_date", singer.getBirthDate());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		insertSinger.updateByNamedParam(paramMap, keyHolder);
		
		singer.setId(keyHolder.getKey().longValue());
		logger.info("New singer inserted with id: " + singer.getId());
		
		List<Album> albums = singer.getAlbums();
		
		if (albums != null) {
		 for (Album album : albums) {
			 paramMap = new HashMap<>();
			 
			 paramMap.put("singer_id", singer.getId());
			 paramMap.put("title", album.getTitle());
			 paramMap.put("release_date", album.getReleaseDate());
			 
			 insertSingerAlbum.updateByNamedParam(paramMap);
			 }
		 }
		 
		 insertSingerAlbum.flush();
		
	}
	
	private static final class SingerWithAlbumExtractor implements ResultSetExtractor<List<Singer>> {
		public List<Singer> extractData(ResultSet rs) throws SQLException, DataAccessException {
			 Map<Long, Singer> map = new HashMap<>();
			 Singer singer;
			 while (rs.next()) {
				 Long id = rs.getLong("id");
				 singer = map.get(id);
				 if (singer == null) {
					 singer = new Singer();
					 singer.setId(id);
					 singer.setFirstName(rs.getString("first_name"));
					 singer.setLastName(rs.getString("last_name"));
					 singer.setBirthDate(rs.getDate("birth_date"));
					 singer.setAlbums(new ArrayList<>());
					 map.put(id, singer);
			 }
			 Long albumId = rs.getLong("album_id");
			 if (albumId > 0) {
				 Album album = new Album();
				 album.setId(albumId);
				 album.setSingerId(id);
				 album.setTitle(rs.getString("title"));
				 album.setReleaseDate(rs.getDate("release_date"));
				 singer.getAlbums().add(album);
			 }
			 }
			 return new ArrayList<>(map.values());
		}
	 }
}
