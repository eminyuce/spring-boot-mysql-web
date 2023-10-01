package com.emin.yuce.learning.services;

import com.emin.yuce.learning.domain.Log;
import com.emin.yuce.learning.entity.LogSearch;

import java.util.List;

public interface LogService {

    List<Log> listAll(LogSearch logSearch);

    Log getById(int id);

    void delete(int id);

    void deleteAll();

    List<Log> listAllByMessageContains(LogSearch logSearch);

    List<Log> listAllByLogSearch(LogSearch logSearch,
                                 String[] selectedNfNames,
                                 String[] loglevelNames,
                                 String[] from,
                                 String[] to);

    List<Log> findBySearch(LogSearch logSearch, String[] nfTypes, String[] logLevels,
                           String[] from,
                           String[] to);
}
