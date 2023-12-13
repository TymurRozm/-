package com.tyims.shell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;

public class View extends JFrame{
    private JList<String> fileList;
    private DefaultListModel<String> listModel;
    private JTextField currentDirField;
    private JButton chooseDirButton;
    private JButton deleteButton;
    private JButton copyButton;
    private JButton moveButton;
    private JTextField searchField;

    public View() {
        super("Shell (Total Commander) TR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        listModel = new DefaultListModel<>();
        fileList = new JList<>(listModel);
        
        JScrollPane scrollPane = new JScrollPane(fileList);

        currentDirField = new JTextField();
        currentDirField.setEditable(false);

        chooseDirButton = new JButton("Обрати папку");

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(currentDirField, BorderLayout.CENTER);
        topPanel.add(chooseDirButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(150, 25));
        searchField.setToolTipText("Search");
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        
        setVisible(true);
        deleteButton = new JButton("Видалити");
        copyButton = new JButton("Копіювати");
        moveButton = new JButton("Перенести");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(deleteButton);
        buttonPanel.add(copyButton);
        buttonPanel.add(moveButton);
        add(searchPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    public void addSearchFieldListener(KeyListener listener) {
        searchField.addKeyListener(listener);
    }

    public void addMoveButtonListener(ActionListener listener) {
        moveButton.addActionListener(listener);
    }
    
    public void addCopyButtonListener(ActionListener listener) {
        copyButton.addActionListener(listener);
    }

    public void addDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }
    
    public void addChooseDirButtonListener(ActionListener listener) {
        chooseDirButton.addActionListener(listener);
    }

    public String getSelectedFile() {
        return fileList.getSelectedValue();
    }
    
    public String getSearchText() {
        return searchField.getText();
    }

    public void updateFileList(File[] files) {
        listModel.clear();
        for (File file : files) {
            listModel.addElement(file.getName());
        }
    }

    public void setCurrentDirectory(String directory) {
        currentDirField.setText(directory);
    }
}
