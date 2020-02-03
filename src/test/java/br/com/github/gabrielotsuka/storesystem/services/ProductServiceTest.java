package br.com.github.gabrielotsuka.storesystem.services;

import br.com.github.gabrielotsuka.storesystem.models.Product;
import br.com.github.gabrielotsuka.storesystem.repositories.ProductRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Product product;

//    @Before
//    public void setUp(){
//
//    }

    @Test
    public void saveTest_blankName() throws MethodArgumentNotValidException {
        //cenário
        product = new Product(" ", 1.5, 100);
        Mockito.when(productRepository.save(product)).thenThrow(MethodArgumentNotValidException.class);

        //ação
        productService.saveProduct(product);

        //verificação
    }
}
