package com.example.demo.menu;

import com.example.demo.entity.Product;
import com.example.demo.repo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.List;

public class ViewItem {
    @Autowired
    private final ProductService productService;
    public ViewItem(ProductService productService) {
        this.productService = productService;
    }

//    public ViewItem(ProductService productService) {
//        this.productService = productService;
//    }

    private JFrame frame = new JFrame("View Product");
    private JTable jTable;
    String[] columnName = {
      "ID","Name","Price","Quantity"
    };

    public void showViewItem(){
        String[][] data = getData();
        jTable = new JTable(data,columnName);
        jTable.setBounds(30, 40, 200, 300);

        JScrollPane jScrollPane = new JScrollPane(jTable);

        frame.add(jScrollPane);
        frame.setSize(500,300);
        frame.setVisible(true);

    }

    public String[][] getData(){
        List<Product> productList = productService.findAll();
        String[][] mainA =new String[productList.size()][4];
        for (int i = 0; i < productList.size(); i++) {
            mainA[i][0] = String.valueOf(productList.get(i).getId());
            mainA[i][1] = productList.get(i).getName();
            mainA[i][2] = String.valueOf(productList.get(i).getPrice());
            mainA[i][3] = String.valueOf(productList.get(i).getQuantity());
            };

        return mainA;
    }


}
