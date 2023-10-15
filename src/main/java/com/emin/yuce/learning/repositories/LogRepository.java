package com.emin.yuce.learning.repositories;

import com.emin.yuce.learning.domain.Log;
import com.emin.yuce.learning.entity.LogSearch;
import com.emin.yuce.learning.utils.N5gStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogRepository {

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
                + " AND dest_ip_port like '%:dest_ip_port%'   AND source_ip_port like '%:source_ip_port%'   AND snssai like '%:snssai%' order by log_time desc  ";

        if (StringUtils.isAllEmpty(logSearch.getSqlExcluded())) {
            logSearch.setSqlExcluded(sqlExcluded);
        }

        sql = sql.replace(":sqlExcluded", logSearch.getSqlExcluded());
        sql = sql.replace(":search", logSearch.getSearch().trim());
        //  sql = sql.replace(":limit", logSearch.getLogLimit() + "");
        sql = sql.replace(":nfTypes", N5gStringUtils.formatINSql(nfTypes));
        sql = sql.replace(":fromList", N5gStringUtils.formatINSql(from));
        sql = sql.replace(":toList", N5gStringUtils.formatINSql(to));
        sql = sql.replace(":logLevels", N5gStringUtils.formatINSql(logLevels));
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
        // java -jar i2i.n5g.logs-0.0.1-SNAPSHOT.jar --server.port=8181 &

        logSearch.setSql(sql);
        JdbcTemplate jdbcPrimary = jdbcDev;
        return jdbcPrimary.query(sql, logRowMapper);
    }

    public Log findById(int id) {
        String sql = "SELECT * FROM app_logs WHERE  id=" + id;
        JdbcTemplate jdbcPrimary = jdbcDev;
        return jdbcPrimary.queryForObject(sql, logRowMapper);
    }

}
