package guru.springframework.repositories;

import guru.springframework.domain.Log;
import guru.springframework.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<Log, Integer> {
}
