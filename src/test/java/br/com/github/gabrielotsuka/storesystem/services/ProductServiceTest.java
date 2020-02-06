package br.com.github.gabrielotsuka.storesystem.services;

import br.com.github.gabrielotsuka.storesystem.error.NotEnoughProductsException;
import br.com.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import br.com.github.gabrielotsuka.storesystem.models.Product;
import br.com.github.gabrielotsuka.storesystem.repositories.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;
    @Before
    public void setup(){
        productService = new ProductService(productRepository);
        product = new Product("batata", 1.5, 25);
        product.setId((long) 1);
    }

//    Leave Item Function
    @Test
    public void leaveItem_success(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Product productResponse  = productService.leaveItem(product.getId(), 23);
        Assert.assertEquals(2, productResponse.getQuantity());
    }

    @Test(expected = NotEnoughProductsException.class)
    public void leaveItem_notEnoughStock(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Product productResponse = productService.leaveItem(product.getId(), 28);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void leaveItem_productDoesNotExist(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Product productResponse = productService.leaveItem((long) 2, 22);
    }

//    Return Item Function
    @Test
    public void returnItem_success(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        productService.returnItem(product.getId(), 50);
        Assert.assertEquals(75, product.getQuantity());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void returnItem_productDoesNotExist(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        productService.returnItem((long) 2, 50);
    }

//    Get Product By id
    @Test
    public void getProductById_success(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Product productResponse = productService.getProductById(product.getId());
        Assert.assertEquals(25, productResponse.getQuantity());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getProductById_productDoesNotExist(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Product productResponse = productService.getProductById((long) 2);
    }

    

}
