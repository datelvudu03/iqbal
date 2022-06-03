package com.example.demo.menu;

import com.example.demo.repo.service.ProductService;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainMenu {

    private final ProductService productService;



    private static JFrame frame;
    private static JButton insertButton,viewButton,updateButton,deleteButton;

    public MainMenu(ProductService productService) {

        this.productService = productService;
    }

    public void showMenu(){

        frame = new JFrame();
        JButton viewButton = new JButton( new AbstractAction("View Product") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                ViewItem viewItem = new ViewItem(productService);
                viewItem.showViewItem();
            }
        });
        JButton addButton = new JButton( new AbstractAction("Add product") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                InsertItem insertItem = new InsertItem(productService);
                insertItem.showInsertItem();
            }
        });

        JButton updateButton = new JButton( new AbstractAction("Update Product") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                UpdateItem updateItem = new UpdateItem(productService);
                updateItem.showUpdateItem();
            }
        });
        JButton deleteButton = new JButton( new AbstractAction("Delete Product") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                DeleteItem deleteItem = new DeleteItem(productService);
                deleteItem.showDeleteItem();
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(addButton);panel.add(new JSeparator());
        panel.add(viewButton);panel.add(new JSeparator());
        panel.add(updateButton);panel.add(new JSeparator());
        panel.add(deleteButton);panel.add(new JSeparator());
        frame.add(panel);
        frame.setSize(500, 300);
        frame.setVisible(true);

    }

}
