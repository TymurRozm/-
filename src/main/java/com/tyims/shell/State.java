package com.tyims.shell;

public interface State {
    void handleSearch(Shell shell, String searchText);
    void handleMove(Shell shell);
    void handleCopy(Shell shell);
    void handleDelete(Shell shell);
    void handleChooseDir(Shell shell);
}
