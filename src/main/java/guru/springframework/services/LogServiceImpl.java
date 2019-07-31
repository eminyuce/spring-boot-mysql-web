package guru.springframework.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Log;
import guru.springframework.entity.LogSearch;
import guru.springframework.entity.N5gLogLevel;
import guru.springframework.entity.NfType;
import guru.springframework.repositories.LogRepository;
import guru.springframework.utils.N5gLogLevelUtil;
import guru.springframework.utils.NfTypeUtil;

@Service
public class LogServiceImpl implements LogService {

	private LogRepository logRepository;

	@Autowired
	public LogServiceImpl(LogRepository logRepository) {
		this.logRepository = logRepository;
	}

	@Override
	public List<Log> listAll(LogSearch logSearch) {
		List<Log> logs = new ArrayList<>();
		logRepository.findAll(logSearch).forEach(logs::add); // fun with Java 8
		return logs;
	}

	@Override
	public List<Log> listAllByMessageContains(LogSearch logSearch) {
		return logRepository.findByMessageContains(logSearch);
	}

	@Override
	public Log getById(int id) {
		return null;// logRepository.findById(id).orElse(null);
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
	public List<Log> findBySearch(LogSearch logSearch, String[] nfTypes, String[] logLevels) {
		return logRepository.findBySearch(logSearch, nfTypes, logLevels);
	}

	@Override
	public List<Log> listAllByLogSearch(LogSearch logSearch, String[] selectedNfNames, String[] loglevelNames) {
		List<Log> logsResult = new ArrayList<Log>();

		List<NfType> nfTypes = NfTypeUtil.getNfTypes(selectedNfNames);
		List<N5gLogLevel> n5gLogLevels = N5gLogLevelUtil.getLogLevels(loglevelNames);
		logsResult = findBySearch(logSearch, selectedNfNames, loglevelNames);

		String dataDetailExcluded = logSearch.getDataDetailExcluded();
		logsResult = extractData(logsResult, dataDetailExcluded,true);
		logsResult = extractData(logsResult, logSearch.getMessageExcluded(),false);
		logSearch.setLogLevels(n5gLogLevels);
		logSearch.setNfTypes(nfTypes);

		return logsResult;
	}

	private List<Log> extractData(List<Log> logsResult, String searchKey,boolean isDataDetail) {
		if (searchKey != null && StringUtils.isNotEmpty(searchKey)) {
			String[] elements = searchKey.split(";", -1);
			List<Log> resultSet = new ArrayList<Log>();
			for (String element : elements) {
				String trimmedElement = element.trim();
				for (int i = 0; i < logsResult.size(); i++) {
					if (isDataDetail && !logsResult.get(i).getData_detail().contains(trimmedElement)) {
						resultSet.add(logsResult.get(i));
					}else if (!isDataDetail && !logsResult.get(i).getMessage().contains(trimmedElement)) {
						resultSet.add(logsResult.get(i));
					}
				}
			}
			return resultSet;
		} else {
			return logsResult;
		}
	}

}
