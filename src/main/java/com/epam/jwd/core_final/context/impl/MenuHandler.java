package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationMenu;

public class MenuHandler {
    private boolean isRunning = true;
    private ApplicationMenu activeMenu;
    private static MenuHandler instance;

    public void stop() {
        isRunning = false;
    }

    public static MenuHandler getInstance() {
        if (instance == null) {
            instance = new MenuHandler();
        }
        return instance;
    }

    private MenuHandler() {

    }

    public void setActiveMenu(ApplicationMenu menu) {
        activeMenu = menu;
    }

    public void start() {
        while (isRunning) {
            activeMenu.printAvailableOptions();
            activeMenu.handleUserInput();
        }
    }
}
