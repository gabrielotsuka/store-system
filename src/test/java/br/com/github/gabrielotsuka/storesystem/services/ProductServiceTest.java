package br.com.github.gabrielotsuka.storesystem.services;

import br.com.github.gabrielotsuka.storesystem.models.Product;
import br.com.github.gabrielotsuka.storesystem.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @Before
    public void setup(){
        product = new Product("batata", 1.50, 100);
    }

    @Test
    public void leaveMoreProductsThanStockHas(){

    }

}
