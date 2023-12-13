package com.tyims.shell;

import java.io.File;

public class Model implements Cloneable {
    private File currentDirectory;

    public Model() {
        setCurrentDirectory(new File("."));
    }

    public File getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(File directory) {
        this.currentDirectory = directory;
    }

    public File[] getFiles() {
        return currentDirectory.listFiles();
    }
    
    @Override
    public Model clone() {
        try {
            return (Model) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
