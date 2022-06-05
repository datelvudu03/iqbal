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
    private JFrame jFrame = new JFrame("Update Product");

    private JTextField searchTextField, nameTextField, priceTextField, quantityTextField;

    private JLabel searchLabel,nameLabel,priceLabel,quantityLabel;

    public void showUpdateItem(){
        searchLabel = new JLabel("Find product by ID(Type only number):");

        nameTextField = new JTextField(20);
        priceTextField = new JTextField(20);
        quantityTextField = new JTextField(20);

        searchTextField = new JTextField(20);

        searchLabel.setLabelFor(searchTextField);

        JPanel jPanel = new JPanel();
        JButton updateButton = new JButton( new AbstractAction("Search") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                String text = searchTextField.getText();
                boolean isInteger = isStringInteger(text);
                //text = "PD-"+ text;
                if (isInteger && findData(Long.parseLong(text)) != null){
                    Product product = findData(Long.parseLong(text));
                    nameTextField = new JTextField(product.getName(),20);
                    priceTextField =new JTextField(String.valueOf(product.getPrice()),20);
                    quantityTextField = new JTextField(String.valueOf(product.getQuantity()),20);

                    nameLabel = new JLabel("Name:");
                    priceLabel = new JLabel("Price:");
                    quantityLabel = new JLabel("Quantity:");

                    nameLabel.setLabelFor(nameTextField);
                    priceLabel.setLabelFor(priceLabel);
                    quantityLabel.setLabelFor(quantityLabel);

                    JButton updateButton = new JButton( new AbstractAction("Update Product") {
                        @Override
                        public void actionPerformed( ActionEvent e ) {
                            product.setName(nameTextField.getText());
                            try{
                                product.setPrice(Integer.parseInt(priceTextField.getText()));
                                product.setQuantity(Integer.parseInt(quantityTextField.getText()));
                                saveData(product);
                                jFrame.dispose();
                            }catch(Exception exception){
                                JOptionPane.showMessageDialog(jFrame, "Price or Quantity has to a number.");
                            }

                        }
                    });



                    jPanel.add(nameLabel);jPanel.add(nameTextField);jPanel.add(new JSeparator());
                    jPanel.add(priceLabel);jPanel.add(priceTextField);jPanel.add(new JSeparator());
                    jPanel.add(quantityLabel);jPanel.add(quantityTextField);jPanel.add(new JSeparator());
                    jPanel.add(updateButton);jPanel.add(new JSeparator());

                    jFrame.invalidate();
                    jFrame.revalidate();
                    jFrame.repaint();

                    jFrame.add(jPanel);

                    jFrame.setVisible(true);
                }else {
                    if (searchTextField.getText() == ""){
                        JOptionPane.showMessageDialog(jFrame, "ID cant be empty");
                    }else {
                        if (isInteger){
                            JOptionPane.showMessageDialog(jFrame, "The product with id :"+searchTextField.getText()+" cant be founded.");
                        }else {
                            JOptionPane.showMessageDialog(jFrame, "Only number please.");
                        }

                    }


                }
            }
        });

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.add(searchLabel);jPanel.add(searchTextField);jPanel.add(updateButton);jPanel.add(new JSeparator());
        jFrame.add(jPanel);
        jFrame.setSize(500, 300);
        jFrame.setVisible(true);
    }
    public Product findData(Long id){
        return productService.findById(id);
    }
    public void saveData(Product product){
        productService.saveProduct(product);
    }

    public static boolean isStringInteger(String number ){
        try{
            Long.parseLong(number);
        }catch(Exception e ){
            return false;
        }
        return true;
    }
}
