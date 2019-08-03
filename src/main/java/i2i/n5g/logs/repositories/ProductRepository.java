package i2i.n5g.logs.repositories;

import i2i.n5g.logs.domain.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jt on 1/10/17.
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
