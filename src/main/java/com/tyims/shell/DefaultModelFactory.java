package com.tyims.shell;

public class DefaultModelFactory implements ModelFactory{

    @Override
    public Model createModel() {
        return new Model();
    }
}
