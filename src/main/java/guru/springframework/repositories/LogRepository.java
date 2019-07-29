package guru.springframework.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.domain.Log;

public interface LogRepository extends JpaRepository<Log, Integer> {
	public List<Log> findByMessageContains(String message);

	// @Query("Select L FROM Log L WHERE (L.message LIKE '%:searchKey%' OR L.data_detail LIKE '%:searchKey%' ) AND
	// L.Nf_type IN (:nfTypes) and L.level IN (:logLevels) ORDER BY L.id desc")
	// public List<Log> findLogs(String searchKey, String[] nfTypes, String[] logLevels);

}
