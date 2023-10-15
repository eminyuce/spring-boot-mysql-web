package com.emin.yuce.learning.controllers;

import com.emin.yuce.learning.commands.ProductForm;
import com.emin.yuce.learning.converters.ProductToProductForm;
import com.emin.yuce.learning.domain.Product;
import com.emin.yuce.learning.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by jt on 1/10/17.
 */
@Controller("/product")
public class ProductController {

}
