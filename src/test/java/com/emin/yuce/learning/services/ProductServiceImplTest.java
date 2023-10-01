package com.emin.yuce.learning.services;

import com.emin.yuce.learning.commands.ProductForm;
import com.emin.yuce.learning.converters.ProductFormToProduct;
import com.emin.yuce.learning.domain.Product;
import com.emin.yuce.learning.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductFormToProduct productFormToProduct;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListAll() {
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product(1, "product1", new BigDecimal(10), "product1"));

        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> products = productService.listAll();

        assertEquals(1, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetById() {
        Product mockProduct = new Product(1, "product1", new BigDecimal(10), "product1");
        when(productRepository.findById(1)).thenReturn(Optional.of(mockProduct));

        Product product = productService.getById(1);

        Assertions.assertEquals(mockProduct, product);
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    public void testSaveOrUpdate() {
        Product inputProduct = new Product(1, "product1", new BigDecimal(10), "product1");

        Product savedProduct = productService.saveOrUpdate(inputProduct);

        Assertions.assertEquals(inputProduct, savedProduct);
        verify(productRepository, times(1)).save(inputProduct);
    }

    @Test
    public void testDelete() {
        productService.delete(1);

        verify(productRepository, times(1)).deleteById(1);
    }

    @Test
    public void testSaveOrUpdateProductForm() {
        ProductForm productForm = new ProductForm(1, "product1", new BigDecimal(10), "product1");
        Product mockProduct = new Product(1, "product1", new BigDecimal(10), "product1");

        when(productFormToProduct.convert(productForm)).thenReturn(mockProduct);

        Product savedProduct = productService.saveOrUpdateProductForm(productForm);

        Assertions.assertEquals(mockProduct, savedProduct);
        verify(productRepository).save(mockProduct);
        verify(productFormToProduct).convert(productForm);
    }

}