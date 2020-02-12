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

import static org.mockito.Mockito.*;

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
        product.setId( 1L );
    }

//    Save Product
    @Test
    public void saveProduct_success(){
        productService.saveProduct(product);
        verify(productRepository, times(1)).save(product);
    }

//    Get All Products
    @Test
    public void getProducts_success(){
        productService.getProducts();
        verify(productRepository, times(1)).findAll();
    }


//    Leave Item Function
    @Test
    public void leaveItem_success(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Product productResponse = productService.leaveItem(product.getId(), 23);
        Assert.assertEquals(2, productResponse.getQuantity());
    }

    @Test(expected = NotEnoughProductsException.class)
    public void leaveItem_notEnoughStock(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        productService.leaveItem(product.getId(), 28);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void leaveItem_productDoesNotExist(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        productService.leaveItem( 2L, 22);
    }

//    Return Item Function
    @Test
    public void returnItem_success(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Product productResponse = productService.returnItem(product.getId(), 50);
        Assert.assertEquals(75, productResponse.getQuantity());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void returnItem_productDoesNotExist(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        productService.returnItem( 2L, 50);
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
        productService.getProductById( 2L );
    }

//    Edit Product
    @Test
    public void editProduct_success(){
        Product newProd = new Product("baotata", 2.0, 50);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Product productResponse = productService.editProduct(newProd, product.getId());
        Assert.assertEquals(productResponse.getId(), product.getId());
        Assert.assertEquals("baotata", productResponse.getName());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void editProduct_productDoesNotExist(){
        Product newProd = new Product("baotata", 2.0, 50);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        productService.editProduct(newProd,  2L);
    }

//    Delete Product
    @Test
    public void deleteProduct_success(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        productService.deleteProduct(product.getId());
        verify(productRepository, times(1)).delete(product);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteProduct_ProductDoesNotExist(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        productService.deleteProduct(2L);
        verify(productRepository, times(1)).delete(product);
    }

//    Change Product Quantity
    @Test
    public void changeProductQuantity_success(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Product request = new Product(5);
        Product productResponse = productService.changeProductQuantity(product.getId(), request);
        Assert.assertEquals(30, productResponse.getQuantity());
        request.setQuantity(-10);
        productResponse = productService.changeProductQuantity(product.getId(), request);
        Assert.assertEquals(20, productResponse.getQuantity());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void changeProductQuantity_productDoesNotExist(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Product request = new Product(5);
        productService.changeProductQuantity(2L, request);
    }

//    Change Product Price
    @Test
    public void changeProductPrice_success(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Product request = new Product(0.1);
        Product productResponse = productService.changeProductPrice(product.getId(),request);
        Assert.assertEquals(0.1, productResponse.getPrice(), 0.1);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void changeProductPrice_productDoesNotExist(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Product request = new Product(0.1);
        productService.changeProductPrice(2L,request);
    }
}
