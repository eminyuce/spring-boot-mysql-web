package i2i.n5g.logs.services;

import java.util.List;

import i2i.n5g.logs.domain.Log;
import i2i.n5g.logs.entity.LogSearch;

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
			String[] to) ;
}
