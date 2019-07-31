package guru.springframework.services;

import java.util.List;

import guru.springframework.domain.Log;
import guru.springframework.entity.LogSearch;

public interface LogService {

	List<Log> listAll(LogSearch logSearch);

	Log getById(int id);

	void delete(int id);

	void deleteAll();

	List<Log> listAllByMessageContains(LogSearch logSearch);

	List<Log> listAllByLogSearch(LogSearch logSearch, String[] selectedNfNames, String[] loglevelNames);
	List<Log> findBySearch(LogSearch logSearch, String[] nfTypes, String[] logLevels) ;
}
