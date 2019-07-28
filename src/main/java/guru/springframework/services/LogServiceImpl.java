package guru.springframework.services;

import guru.springframework.domain.Log;
import guru.springframework.domain.Product;
import guru.springframework.entity.LogSearch;
import guru.springframework.entity.N5gLogLevel;
import guru.springframework.entity.NfType;
import guru.springframework.repositories.LogRepository;
import guru.springframework.utils.N5gLogLevelUtil;
import guru.springframework.utils.NfTypeUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {


    private LogRepository logRepository;


    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }


    @Override
    public List<Log> listAll() {
        List<Log> logs = new ArrayList<>();
        logRepository.findAll().forEach(logs::add); //fun with Java 8
        return logs;
    }
    
    @Override
    public List<Log> listAllByMessageContains(String search) {
    	List<Log> logs = new ArrayList<>();
        logRepository.findByMessageContains(search).forEach(logs::add); //fun with Java 8
        return logs;
    }

    @Override
    public Log getById(int id) {
        return logRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        logRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        logRepository.deleteAll();
    }


	@Override
	public List<Log> listAllByLogSearch(LogSearch logSearch, String[] selectedNfNames, String[] loglevelNames) {
		List<Log> logsResult = new ArrayList<Log>();
		if (StringUtils.isNotEmpty(logSearch.getSearch())) {
			logsResult = listAllByMessageContains(logSearch.getSearch());
		} else {
			logsResult = listAll();
		}
		List<NfType> nfTypes = NfTypeUtil.getNfTypes(selectedNfNames); 
		logsResult = NfTypeUtil.extractLogByNfTypes(selectedNfNames, logsResult);
		List<N5gLogLevel> n5gLogLevels = N5gLogLevelUtil.getLogLevels(loglevelNames);
		logsResult = N5gLogLevelUtil.extractByLogLevel(loglevelNames, logsResult);
		logSearch.setLogLevels(n5gLogLevels);
		logSearch.setNfTypes(nfTypes);
		return logsResult;
	}
}
