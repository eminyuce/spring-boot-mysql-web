package i2i.n5g.logs.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import i2i.n5g.logs.domain.Log;
import i2i.n5g.logs.entity.LogSearch;
import i2i.n5g.logs.utils.StringUtils;

@Component
public class LogRepository {
	@Autowired
	@Qualifier("jdbcTest")
	private JdbcTemplate jdbcTest;

	@Autowired
	@Qualifier("jdbcDev")
	private JdbcTemplate jdbcDev;

	@Autowired
	private LogRowMapper logRowMapper;

	@Value("${app.sql.excluded}")
	private String sqlExcluded;

	public List<Log> findBySearch(LogSearch logSearch, String[] nfTypes, String[] logLevels, String[] from,
			String[] to) {

		String sql = "SELECT * FROM app_logs WHERE  :sqlExcluded AND ( message like '%:search%' OR data_detail LIKE '%:search%' ) "
				+ " AND \"FROM\" IN (:fromList)  AND \"TO\" IN (:toList)  AND nf_type IN (:nfTypes)  "
				+ " AND level IN (:logLevels) AND status like '%:status%' AND supi like '%:supi%'  "
				+ " AND dest_ip_port like '%:dest_ip_port%'   AND source_ip_port like '%:source_ip_port%'   AND snssai like '%:snssai%' order by log_time desc limit :limit ";

		sql = sql.replace(":sqlExcluded", sqlExcluded);
		sql = sql.replace(":search", logSearch.getSearch().trim());
		sql = sql.replace(":limit", logSearch.getLogLimit() + "");
		sql = sql.replace(":nfTypes", StringUtils.formatINSql(nfTypes));
		sql = sql.replace(":fromList", StringUtils.formatINSql(from));
		sql = sql.replace(":toList", StringUtils.formatINSql(to));
		sql = sql.replace(":logLevels", StringUtils.formatINSql(logLevels));
		sql = sql.replace(":status", logSearch.getHttpStatus().trim());
		sql = sql.replace(":supi", logSearch.getSupi().trim());
		sql = sql.replace(":snssai", logSearch.getSnssai().trim());
		sql = sql.replace(":dest_ip_port", logSearch.getDestinationIp().trim());
		sql = sql.replace(":source_ip_port", logSearch.getSourceIp().trim());

		sql = sql.replace("AND source_ip_port like '%%'", " ");
		sql = sql.replace("AND dest_ip_port like '%%'", " ");
		sql = sql.replace("AND snssai like '%%'", " ");
		sql = sql.replace("AND supi like '%%'", " ");
		sql = sql.replace("AND status like '%%'", " ");
		sql = sql.replace("AND ( message like '%%' OR data_detail LIKE '%%' ) ", " ");
		sql = sql.replace("AND \"FROM\" IN ()", " ");
		sql = sql.replace("AND \"TO\" IN ()", " ");
		sql = sql.replace("AND nf_type IN ()", " ");
		sql = sql.replace("AND level IN ()", " ");
		sql = sql.replace("AND status IN ()", " ");

		logSearch.setSql(sql);
		return jdbcDev.query(sql, logRowMapper);
	}

}
