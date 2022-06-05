package com.example.demo.menu;

import com.example.demo.entity.Product;
import com.example.demo.repo.service.ProductService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InsertItem implements ActionListener {

    private final ProductService productService;

    public InsertItem(ProductService productService) {
        this.productService = productService;
    }

    static JTextField name,price,quantity;
    // JFrame
    static JFrame jFrame;
    // JButton
    static JButton submit;
    // label to display text
    static JLabel nameLabel,priceLabel,quantityLabel;

    public void showInsertItem(){

        jFrame = new JFrame("Add Product");

        // create a label to display text
        nameLabel = new JLabel("Product name:");
        priceLabel = new JLabel("Price:");
        quantityLabel = new JLabel("Quantity:");

        nameLabel.setLabelFor(name);
        priceLabel.setLabelFor(price);
        quantityLabel.setLabelFor(quantity);


        // create a new button
        submit = new JButton("submit");
        // create a object of the text class

        // addActionListener to button
        submit.addActionListener(this);
        // create a object of JTextField with 16 columns
        name = new JTextField(20);
        price = new JTextField(20);
        quantity = new JTextField(20);
        // create a panel to add buttons and textfield
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        // add buttons and textfield to panel
        jPanel.add(nameLabel); jPanel.add(name);jPanel.add(new JSeparator());
        jPanel.add(priceLabel);jPanel.add(price);jPanel.add(new JSeparator());
        jPanel.add(quantityLabel);jPanel.add(quantity);jPanel.add(new JSeparator());
        jPanel.add(submit);
        // add panel to frame
        jFrame.add(jPanel);
        // set the size of frame
        jFrame.setSize(500, 300);
        jFrame.setVisible(true);

    }
    // if the button is pressed

    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();
        if (s.equals("submit")) {

                try{
                    Product product = new Product( name.getText(), Integer.parseInt(price.getText()),Integer.parseInt(quantity.getText()));
                    saveData(product);
                    jFrame.dispose();
                }catch(Exception exception){
                    JOptionPane.showMessageDialog(jFrame, "Price or Quantity has to a number.");
                }

        }
    }

    public void saveData(Product productV){
        productService.saveProduct(productV);
        System.out.println("Product saved.");
    }

}
