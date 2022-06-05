package com.example.demo.menu;

import com.example.demo.entity.Product;
import com.example.demo.repo.service.ProductService;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteItem {
    private final ProductService productService;

    public DeleteItem(ProductService productService) {
        this.productService = productService;
    }
    private JFrame jFrame = new JFrame("Delete Product");
    private JTextField searchTextField;

    private JLabel searchLabel,nameLabel,priceLabel,quantityLabel;
    void showDeleteItem(){
        searchLabel = new JLabel("Find product by ID(Type only number):");


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

                    nameLabel = new JLabel(product.getName());
                    priceLabel = new JLabel(String.valueOf(product.getPrice()));
                    quantityLabel = new JLabel(String.valueOf(product.getQuantity()));

                    JButton updateButton = new JButton( new AbstractAction("Delete Product") {
                        @Override
                        public void actionPerformed( ActionEvent e ) {
                                deleteProduct(product);
                                jFrame.dispose();
                        }
                    });



                    jPanel.add(nameLabel);jPanel.add(new JSeparator());
                    jPanel.add(priceLabel);jPanel.add(new JSeparator());
                    jPanel.add(quantityLabel);jPanel.add(new JSeparator());
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
    public static boolean isStringInteger(String number ){
        try{
            Long.parseLong(number);
        }catch(Exception e ){
            return false;
        }
        return true;
    }
    public Product findData(Long id){
        return productService.findById(id);
    }

    public void deleteProduct(Product product){
        productService.deleteProduct(product);
    }
}
