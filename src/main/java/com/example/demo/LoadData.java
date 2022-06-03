package com.example.demo;

import com.example.demo.entity.Product;
import com.example.demo.menu.MainMenu;
import com.example.demo.repo.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadData {
    @Bean
    CommandLineRunner initDatabase(ProductService productService){
        return args ->{
            productService.saveProduct(new Product("Asd",123,132));
            MainMenu mainMenu = new MainMenu(productService);
            mainMenu.showMenu();
        };
    }



}
