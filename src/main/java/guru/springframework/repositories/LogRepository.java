package guru.springframework.repositories;

import guru.springframework.domain.Log;
import guru.springframework.domain.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LogRepository extends JpaRepository<Log, Integer> {
	 public List<Log> findByMessageContains(String message);
}
