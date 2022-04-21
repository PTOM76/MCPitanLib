package ml.pkom.mcpitanlib.api.gui;

import ml.pkom.mcpitanlib.api.register.Registries;
import ml.pkom.mcpitanlib.api.util.IdentifierExt;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerType;

public class GuiFactoryMngExt {

    public ScreenHandlerFactory<GuiScreenHandler> screenHandlerFactory;
    public ScreenFactory<GuiScreen> screenFactory;

    public GuiFactoryMngExt(ScreenHandlerFactory<GuiScreenHandler> screenHandlerFactory) {
        this.screenHandlerFactory = screenHandlerFactory;
    }

    public ScreenHandlerFactory<GuiScreenHandler> getScreenHandlerFactory() {
        return screenHandlerFactory;
    }

    public ScreenFactory<GuiScreen> getScreenFactory() {
        return screenFactory;
    }

    public ScreenHandlerType createScreenHandlerType(IdentifierExt id, ScreenHandlerRegistry.SimpleClientHandlerFactory factory) {
        return Registries.registerScreenHandler(id, factory);
    }

    public ScreenHandlerType createScreenHandlerType(IdentifierExt id, ScreenHandlerRegistry.ExtendedClientHandlerFactory factory) {
        return Registries.registerScreenHandler(id, factory);
    }

    public static interface ScreenHandlerFactory<T extends GuiScreenHandler> {
        public T create(ScreenHandlerType<?> type, int syncId);
        public T create(int syncId, PlayerInventory inventory);
    }

    public static interface ScreenFactory<T extends GuiScreen> {
        public T create(ScreenHandlerType<?> type, int syncId);
        public T create(int syncId, PlayerInventory inventory);
    }
}
