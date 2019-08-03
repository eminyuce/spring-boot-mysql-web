package i2i.n5g.logs.services;

import i2i.n5g.logs.commands.ProductForm;
import i2i.n5g.logs.domain.Product;

import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
public interface ProductService {

    List<Product> listAll();

    Product getById(int id);

    Product saveOrUpdate(Product product);

    void delete(int id);

    Product saveOrUpdateProductForm(ProductForm productForm);
}
