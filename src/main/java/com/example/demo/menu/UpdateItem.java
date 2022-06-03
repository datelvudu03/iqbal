package com.example.demo.menu;

import com.example.demo.entity.Product;
import com.example.demo.repo.service.ProductService;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UpdateItem {
    private final ProductService productService;

    public UpdateItem(ProductService productService) {
        this.productService = productService;
    }
    private JFrame frame = new JFrame("Update Product");

    private JTextField searchTextField, nameTextField, priceTextField, quantityTextField;

    private JLabel searchLabel,nameLabel,priceLabel,quantityLabel;

    public void showUpdateItem(){
        searchLabel = new JLabel("Search");
        nameLabel = new JLabel("Name:");
        priceLabel = new JLabel("Price:");
        quantityLabel = new JLabel("Quantity:");
        nameTextField = new JTextField(20);
        priceTextField = new JTextField(20);
        quantityTextField = new JTextField(20);

        searchTextField = new JTextField(20);

        searchLabel.setLabelFor(searchTextField);
        JButton updateButton = new JButton( new AbstractAction("Search") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                String text = searchTextField.getText();
                System.out.println(text);

                if (findData(text) != null){
                    Product product = findData(text);
                    nameTextField = new JTextField(product.getName(),20);
                    priceTextField =new JTextField(String.valueOf(product.getPrice()),20);
                    quantityTextField = new JTextField(String.valueOf(product.getQuantity()),20);
                    JPanel panel2 = new JPanel();
// panel2 = new JPanel(new GridLayout(1, 2 ));//why this it will overwrite the above layout
                    panel2.add(nameTextField);
                    panel2.add(priceTextField);
                    panel2.add(quantityTextField);
                    frame.add(panel2);
                    frame.revalidate();
                    frame.repaint();
                }else {
                    System.out.println("nope");
                }
            }
        });


        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.add(searchLabel);jPanel.add(searchTextField);jPanel.add(updateButton);jPanel.add(new JSeparator());


        frame.add(jPanel);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }
    public Product findData(String id){
        return productService.findById(id);
    }
    public void saveData(Product product){
        productService.saveProduct(product);
        System.out.println("Product saved.");
    }
}
