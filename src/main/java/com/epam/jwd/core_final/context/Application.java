package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.MainMenu;
import com.epam.jwd.core_final.context.impl.MenuHandler;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.function.Supplier;

public interface Application {
    static void start() throws InvalidStateException {
        // todo
        final NassaContext nassaContext = NassaContext.getInstance();
        nassaContext.init();
        MenuHandler.getInstance().setActiveMenu(new MainMenu());
        MenuHandler.getInstance().start();
    }
}
