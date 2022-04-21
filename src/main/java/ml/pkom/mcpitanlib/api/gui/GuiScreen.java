package ml.pkom.mcpitanlib.api.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import ml.pkom.mcpitanlib.api.event.render.DrawBackgroundEvent;
import ml.pkom.mcpitanlib.api.util.IdentifierExt;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;

public class GuiScreen extends HandledScreen<ScreenHandler> {

    public IdentifierExt TEXTURE = new IdentifierExt("minecraft", "textures/gui/container/generic_54.png");

    public GuiScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    public void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        renderTexture(new DrawBackgroundEvent(matrices, delta, mouseX, mouseY));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        render(new DrawBackgroundEvent(matrices, delta, mouseX, mouseY));
    }

    public void render(DrawBackgroundEvent event) {
        superRender(event);
    }

    @Override
    public void init() {
        superInit();
    }

    public void superInit() {
        super.init();
    }

    public void superRender(DrawBackgroundEvent event) {
        super.render(event.getMatrices(), event.getMouseX(), event.getMouseY(), event.getDelta());
    }

    public void renderTexture(DrawBackgroundEvent event) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, getTexture());
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(event.getMatrices(), x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    public void setTexture(IdentifierExt TEXTURE) {
        this.TEXTURE = TEXTURE;
    }

    public IdentifierExt getTexture() {
        return TEXTURE;
    }
}
