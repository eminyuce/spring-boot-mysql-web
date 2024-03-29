package com.emin.yuce.learning.services;

import com.emin.yuce.learning.domain.Log;
import com.emin.yuce.learning.entity.LogSearch;
import com.emin.yuce.learning.repositories.LogRepository;
import com.emin.yuce.learning.utils.N5gLogLevelUtil;
import com.emin.yuce.learning.utils.NfTypeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public List<Log> listAll(LogSearch logSearch) {
        List<Log> logs = new ArrayList<>();
        // logRepository.findAll(logSearch).forEach(logs::add); // fun with Java 8
        return logs;
    }

    @Override
    public List<Log> listAllByMessageContains(LogSearch logSearch) {
        // return logRepository.findByMessageContains(logSearch);
        List<Log> logs = new ArrayList<>();
        // logRepository.findAll(logSearch).forEach(logs::add); // fun with Java 8
        return logs;
    }

    @Override
    public Log getById(int id) {
        return logRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        // logRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        // logRepository.deleteAll();
    }

    @Override
    public List<Log> findBySearch(LogSearch logSearch, String[] nfTypes, String[] logLevels, String[] from,
                                  String[] to) {
        return logRepository.findBySearch(logSearch, nfTypes, logLevels, from, to);
    }

    @Override
    public List<Log> listAllByLogSearch(LogSearch logSearch, String[] selectedNfNames, String[] loglevelNames,
                                        String[] from, String[] to) {
        List<Log> logsResult = new ArrayList<Log>();

        logsResult = findBySearch(logSearch, selectedNfNames, loglevelNames, from, to);

        // String dataDetailExcluded = logSearch.getDataDetailExcluded();
        // logsResult = extractData(logsResult, dataDetailExcluded, true);
        // logsResult = extractData(logsResult, logSearch.getMessageExcluded(), false);
        logSearch.setLogLevels(N5gLogLevelUtil.getLogLevels(loglevelNames));
        logSearch.setNfTypes(NfTypeUtil.getNfTypes(selectedNfNames));
        logSearch.setFromNfTypesList(NfTypeUtil.getNfTypes(from));
        logSearch.setToNfTypesList(NfTypeUtil.getNfTypes(to));

        return logsResult;
    }

    private List<Log> extractData(List<Log> logsResult, String searchKey, boolean isDataDetail) {
        if (StringUtils.isNotEmpty(searchKey)) {
            String[] elements = searchKey.split(";", -1);
            List<Log> logsResultCopy = new ArrayList<>(logsResult);
            for (int i = 0; i < logsResult.size(); i++) {
                boolean foundRecordForDataDetail = false;
                boolean foundRecordForMessage = false;
                Log log = logsResult.get(i);
                for (String element : elements) {
                    String trimmedElement = element.trim();
                    foundRecordForDataDetail = isDataDetail && log.getData_detail().contains(trimmedElement);
                    foundRecordForMessage = !isDataDetail && log.getMessage().contains(trimmedElement);
                    if (foundRecordForDataDetail || foundRecordForMessage) {
                        logsResultCopy.remove(log);
                    }
                }

            }
            return logsResultCopy;
        } else {
            return logsResult;
        }
    }

}
