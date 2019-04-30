package guru.springframework.services;

import guru.springframework.commands.ProductForm;
import guru.springframework.domain.Product;

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
