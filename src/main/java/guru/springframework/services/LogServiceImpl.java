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
	public List<Log> listAll() {
		List<Log> logs = new ArrayList<>();
		logRepository.findAll().forEach(logs::add); // fun with Java 8
		return logs;
	}

	@Override
	public List<Log> listAllByMessageContains(LogSearch logSearch) {
		return logRepository.findByMessageContains(logSearch.getSearch());
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
	public List<Log> listAllByLogSearch(LogSearch logSearch, String[] selectedNfNames, String[] loglevelNames) {
		List<Log> logsResult = new ArrayList<Log>();
		if (StringUtils.isNotEmpty(logSearch.getSearch())) {
			logsResult = listAllByMessageContains(logSearch);
		} else {
			logsResult = listAll();
		}
		List<NfType> nfTypes = NfTypeUtil.getNfTypes(selectedNfNames);
		logsResult = NfTypeUtil.extractLogByNfTypes(selectedNfNames, logsResult);
		List<N5gLogLevel> n5gLogLevels = N5gLogLevelUtil.getLogLevels(loglevelNames);
		logsResult = N5gLogLevelUtil.extractByLogLevel(loglevelNames, logsResult);

		if (logSearch.getDataDetailExcluded() != null && StringUtils.isNotEmpty(logSearch.getDataDetailExcluded())) {
			String[] elements = logSearch.getDataDetailExcluded().split(";", -1);
			for (String element : elements) {
				String trimmedElement = element.trim();
				logsResult = logsResult.stream().filter(t -> !t.getData_detail().contains(trimmedElement))
						.collect(Collectors.toList());
			}
		}
		if (logSearch.getMessageExcluded() != null && StringUtils.isNotEmpty(logSearch.getMessageExcluded())) {
			String[] elements = logSearch.getMessageExcluded().split(";", -1);
			for (String element : elements) {
				String trimmedElement = element.trim();
				logsResult = logsResult.stream().filter(t -> !t.getMessage().contains(trimmedElement))
						.collect(Collectors.toList());
			}
		}
		logSearch.setLogLevels(n5gLogLevels);
		logSearch.setNfTypes(nfTypes);

		return logsResult;
	}
}
