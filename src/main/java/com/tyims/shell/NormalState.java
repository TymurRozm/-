package com.tyims.shell;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class NormalState implements State{
    @Override
    public void handleSearch(Shell shell, String searchText) {
        File[] files = shell.getModel().getFiles();

        if (!searchText.isEmpty()) {
            files = Arrays.stream(files)
                    .filter(file -> file.getName().toLowerCase().contains(searchText.toLowerCase()))
                    .collect(Collectors.toList())
                    .toArray(new File[0]);
        }

        shell.getView().updateFileList(files);
    }

    @Override
    public void handleMove(Shell shell) {
        String selectedFileName = shell.getView().getSelectedFile();
        if (selectedFileName != null) {
            File sourceFile = new File(shell.getModel().getCurrentDirectory(), selectedFileName);

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Destination Directory");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int result = fileChooser.showOpenDialog(shell.getView());
            if (result == JFileChooser.APPROVE_OPTION) {
                File destinationDir = fileChooser.getSelectedFile();
                File destinationFile = new File(destinationDir, selectedFileName);

                try {
                    Path sourcePath = sourceFile.toPath();
                    Path destinationPath = destinationFile.toPath();
                    Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    shell.updateView();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(shell.getView(), "Failed to move the file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public void handleCopy(Shell shell) {
        String selectedFileName = shell.getView().getSelectedFile();
        if (selectedFileName != null) {
            File sourceFile = new File(shell.getModel().getCurrentDirectory(), selectedFileName);

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Destination Directory");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int result = fileChooser.showOpenDialog(shell.getView());
            if (result == JFileChooser.APPROVE_OPTION) {
                File destinationDir = fileChooser.getSelectedFile();
                File destinationFile = new File(destinationDir, selectedFileName);

                try {
                    Path sourcePath = sourceFile.toPath();
                    Path destinationPath = destinationFile.toPath();
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    shell.updateView();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(shell.getView(), "Failed to copy the file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public void handleDelete(Shell shell) {
        String selectedFileName = shell.getView().getSelectedFile();
        if (selectedFileName != null) {
            File fileToDelete = new File(shell.getModel().getCurrentDirectory(), selectedFileName);
            if (fileToDelete.exists()) {
                int result = JOptionPane.showConfirmDialog(
                        shell.getView(),
                        "Ви точно хочете це видалити?",
                        "Видалено",
                        JOptionPane.YES_NO_OPTION
                );

                if (result == JOptionPane.YES_OPTION) {
                    boolean deleted = fileToDelete.delete();
                    if (deleted) {
                        shell.updateView();
                    } else {
                        JOptionPane.showMessageDialog(shell.getView(), "Failed to delete the file.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    @Override
    public void handleChooseDir(Shell shell) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(shell.getView());
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDir = fileChooser.getSelectedFile();
            shell.getModel().setCurrentDirectory(selectedDir);
            shell.updateView();
        }
    }
}
