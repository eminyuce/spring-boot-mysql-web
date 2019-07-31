package guru.springframework.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Log;
import guru.springframework.entity.LogSearch;

@Component
public class LogRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private LogRowMapper logRowMapper;

	public List<Log> findByMessageContains(LogSearch logSearch) {
		return jdbcTemplate.query("SELECT * FROM app_logs where message like %?% order by id desc  limit 100",
				new Object[] { logSearch.getSearch() }, logRowMapper);
	}

	public List<Log> findAll() {
		return jdbcTemplate.query("SELECT * FROM app_logs  order by id desc limit 100", new Object[] {}, logRowMapper);
	}
	// @Query("Select L FROM Log L WHERE (L.message LIKE '%:searchKey%' OR L.data_detail LIKE '%:searchKey%' ) AND
	// L.Nf_type IN (:nfTypes) and L.level IN (:logLevels) ORDER BY L.id desc")
	// public List<Log> findLogs(String searchKey, String[] nfTypes, String[] logLevels);

}
