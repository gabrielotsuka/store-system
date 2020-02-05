package br.com.github.gabrielotsuka.storesystem.services;

import br.com.github.gabrielotsuka.storesystem.error.NotEnoughProductsException;
import br.com.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import br.com.github.gabrielotsuka.storesystem.models.Product;
import br.com.github.gabrielotsuka.storesystem.repositories.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;
    private Product product;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productService = new ProductService(productRepository);
        product = new Product("batata", 1.5, 25);
        product.setId((long) 1);
    }

//    Leave Item Function
    @Test
    public void leaveItem_success(){
        when(productService.saveProduct(product)).thenReturn(product);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productService.leaveItem(product.getId(), 23)).thenReturn(product);
        Assert.assertEquals(2, product.getQuantity());
    }

    @Test(expected = NotEnoughProductsException.class)
    public void leaveItem_notEnoughStock(){
        when(productService.saveProduct(product)).thenReturn(product);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productService.leaveItem(product.getId(), 28)).thenReturn(product);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void leaveItem_productDoesNotExist(){
        when(productService.saveProduct(product)).thenReturn(product);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productService.leaveItem((long) 2, 28)).thenReturn(product);
    }
}
