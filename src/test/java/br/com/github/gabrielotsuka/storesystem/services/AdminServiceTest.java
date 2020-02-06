package br.com.github.gabrielotsuka.storesystem.services;

import br.com.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import br.com.github.gabrielotsuka.storesystem.models.Admin;
import br.com.github.gabrielotsuka.storesystem.repositories.AdminRepository;
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
public class AdminServiceTest {
    @InjectMocks
    private AdminService adminService;

    @Mock
    private AdminRepository adminRepository;
    @Mock
    private CustomerService customerService;
    @Mock
    private ProductService productService;

    private Admin admin;
    @Before
    public void setup(){
        adminService = new AdminService(customerService, adminRepository, productService);
        admin = new Admin("Gabriel Otsuka", "gabrielotsuka@gmail.com", "abcde");
        admin.setId(1L);
    }

//                                              Admin
//    Save Admin
    @Test
    public void saveAdmin_success(){
        adminService.saveAdmin(admin);
        verify(adminRepository, times(1)).save(admin);
    }

//    Get All Amins
    @Test
    public void getAdmins(){
        adminService.getAdmins();
        verify(adminRepository, times(1)).findAll();
    }

//    Get Admin By Id
    @Test
    public void getAdminById_success(){
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        Admin adminResponse = adminService.getAdminById(1L);
        Assert.assertEquals("gabrielotsuka@gmail.com", adminResponse.getEmail());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getAdminById_adminDoesNotExist(){
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        adminService.getAdminById(2L);
    }

//    Edit Admin
    @Test
    public void editAdmin_success(){
        Admin request = new Admin("Gabriel Zezin", "gabriel.otsuka@zup.com.br", "123");
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        Admin adminResponse = adminService.editAdmin(admin.getId(), request);
        Assert.assertEquals(Optional.of(1L), Optional.ofNullable(adminResponse.getId()));
        Assert.assertEquals("Gabriel Zezin", adminResponse.getName());
        Assert.assertEquals("abcde", adminResponse.getPwd());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void editAdmin_adminDoesNotExist(){
        Admin request = new Admin("Gabriel Zezin", "gabriel.otsuka@zup.com.br", "123");
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        adminService.editAdmin(2L, request);
    }

//    Delete Admin
    @Test
    public void deleteAdmin_success(){
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        adminService.deleteAdmin(1L);
        verify(adminRepository, times(1)).delete(admin);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteAdmin_adminDoesNotExist(){
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        adminService.deleteAdmin(2L);
    }

//    Change Admin Password
    @Test
    public void changeAdminPwd_success(){
        Admin request = new Admin("123");
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        Admin adminResponse = adminService.changeAdminPwd(1L, request);
        Assert.assertEquals("123", adminResponse.getPwd());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void changeAdminPwd_adminDoesNotExist(){
        Admin request = new Admin("123");
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        Admin adminResponse = adminService.changeAdminPwd(2L, request);
    }

    

//


}
