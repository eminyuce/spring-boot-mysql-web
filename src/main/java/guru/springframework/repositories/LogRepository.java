package guru.springframework.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Log;
import guru.springframework.entity.LogSearch;

@Component
public class LogRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private LogRowMapper logRowMapper;

	public List<Log> findByMessageContains(LogSearch logSearch) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("search", logSearch.getSearch());
		parameters.addValue("limit", logSearch.getLogLimit());
		return jdbcTemplate.query(
				"SELECT * FROM app_logs where ( message like %:search% OR data_detail LIKE '%:search%' ) order by id desc  limit :limit",
				parameters, logRowMapper);
	}

	public List<Log> findAll(LogSearch logSearch) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("limit", logSearch.getLogLimit());
		return jdbcTemplate.query("SELECT * FROM app_logs  order by id desc limit :limit", parameters, logRowMapper);
	}

	public List<Log> findBySearch(LogSearch logSearch, String[] nfTypes, String[] logLevels) {

		ArrayList<String> s1 = getSingleQuote(nfTypes);
		ArrayList<String> s2 = getSingleQuote(logLevels);
		
		return jdbcTemplate.query(
				"SELECT * FROM app_logs  where ( message like '%:search%' OR data_detail LIKE '%:search%' ) AND nf_type IN (:nfTypes) AND level IN (:logLevels) order by id desc limit :limit"
						.replace(":search", logSearch.getSearch())
						.replace(":limit", logSearch.getLogLimit() + "")
						.replace(":nfTypes", String.join(",", s1)).replace(":logLevels", String.join(",", s2)),
				logRowMapper);
	}

	private ArrayList<String> getSingleQuote(String[] logLevels) {
		ArrayList<String> s2 = new ArrayList<String>();
		if (logLevels != null) {
			for (int i = 0; i < logLevels.length; i++) {
				s2.add("'" + logLevels[i] + "'");
			}
		} else {
			s2.add("' '");
		}
		return s2;
	}

}
