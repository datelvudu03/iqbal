package com.example.demo;

import com.example.demo.menu.InsertItem;
import com.example.demo.repo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication

public class DemoApplication extends JFrame {


	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);


	}

}
