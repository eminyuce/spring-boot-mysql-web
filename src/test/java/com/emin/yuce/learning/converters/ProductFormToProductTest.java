package com.emin.yuce.learning.converters;

import com.emin.yuce.learning.commands.ProductForm;
import com.emin.yuce.learning.domain.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductFormToProductTest {

    private ProductFormToProduct converter = new ProductFormToProduct();
//ctx.getEnvironment().getPropertySources()
//  .addFirst(new MapPropertySource("runtimeProps", Map.of("my.prop", "newValue")));
    @Test
    public void testConvert() {
        ProductForm productForm = new ProductForm(1, "desc", new BigDecimal(10.0), "url");

        Product product = converter.convert(productForm);

        assertEquals(1, product.getId());
        assertEquals("desc", product.getDescription());
        assertEquals(new BigDecimal(10.0), product.getPrice());
        assertEquals("url", product.getImageUrl());
    }

    @Test
    public void testConvertWithEmptyId() {
        ProductForm productForm = new ProductForm(0, "desc", new BigDecimal(10.0), "url");

        Product product = converter.convert(productForm);

        assertEquals(0, product.getId());
    }

}