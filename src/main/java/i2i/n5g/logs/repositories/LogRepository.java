package i2i.n5g.logs.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import i2i.n5g.logs.domain.Log;
import i2i.n5g.logs.entity.LogSearch;

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

	public List<Log> findBySearch(LogSearch logSearch, String[] nfTypes, String[] logLevels, String[] from,
			String[] to) {

		String sql = "SELECT * FROM app_logs  where ( message like '%:search%' OR data_detail LIKE '%:search%' ) "
				+ " AND `from` IN (:fromList)  AND `to` IN (:toList)  AND nf_type IN (:nfTypes)  "
				+ " AND level IN (:logLevels) AND status like '%:status%' order by id desc limit :limit ";

		sql = sql.replace(":search", logSearch.getSearch());
		sql = sql.replace(":limit", logSearch.getLogLimit() + "");
		sql = sql.replace(":nfTypes", formatINSql(nfTypes));
		sql = sql.replace(":fromList", formatINSql(from));
		sql = sql.replace(":toList", formatINSql(to));
		sql = sql.replace(":logLevels", formatINSql(logLevels));
		sql = sql.replace(":status",logSearch.getHttpStatus());
		
		sql = sql.replace("AND `from` IN ()", "");
		sql = sql.replace("AND `to` IN ()", "");
		sql = sql.replace("AND nf_type IN ()", "");
		sql = sql.replace("AND level IN ()", "");
		sql = sql.replace("AND status IN ()", "");
		
		logSearch.setSql(sql);
		return jdbcTemplate.query(sql, logRowMapper);
	}

	private String formatINSql(String[] parameters) {
		String result = String.join(",", getSingleQuote(parameters));
		return result;
	}

	private ArrayList<String> getSingleQuote(String[] logLevels) {
		ArrayList<String> s2 = new ArrayList<String>();
		if (logLevels != null) {
			for (int i = 0; i < logLevels.length; i++) {
				s2.add("'" + logLevels[i] + "'");
			}
		}
		return s2;
	}

}
