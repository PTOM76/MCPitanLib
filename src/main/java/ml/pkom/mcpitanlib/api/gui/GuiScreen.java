package ml.pkom.mcpitanlib.api.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import ml.pkom.mcpitanlib.api.event.render.ScreenRenderEvent;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class GuiScreen extends HandledScreen<ScreenHandler> {

    public GuiScreenSettings settings = new GuiScreenSettings();


    public Identifier TEXTURE = new Identifier("minecraft", "textures/gui/container/generic_54.png");

    public GuiScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    public void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        renderTexture(new ScreenRenderEvent(matrices, delta, mouseX, mouseY));
    }

    @Override
    public void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        renderTextureFore(new ScreenRenderEvent(matrices, 0, mouseX, mouseY));
    }

    /**
     *
     * cannot use event.delta
     */
    public void renderTextureFore(ScreenRenderEvent event) {
        super.drawForeground(event.getMatrices(), event.getMouseX(), event.getMouseY());
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        render(new ScreenRenderEvent(matrices, delta, mouseX, mouseY));
    }

    public void render(ScreenRenderEvent event) {
        if (settings.darkBackground)
            renderBackground(event);
        superRender(event);
        if (settings.showMouseOverTooltip)
            renderItemTooltip(event);
    }

    /**
     * Darken the background
     */
    public void renderBackground(ScreenRenderEvent event) {
        this.renderBackground(event.getMatrices());
    }

    /**
     * Draw item hover tooltip
     */
    public void renderItemTooltip(ScreenRenderEvent event) {
        this.drawMouseoverTooltip(event.getMatrices(), event.getMouseX(), event.getMouseY());
    }

    @Override
    public void init() {
        superInit();
    }

    public void superInit() {
        super.init();
    }

    public void superRender(ScreenRenderEvent event) {
        super.render(event.getMatrices(), event.getMouseX(), event.getMouseY(), event.getDelta());
    }

    public void renderTexture(ScreenRenderEvent event) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, getTexture());
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(event.getMatrices(), x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    public void setTexture(Identifier TEXTURE) {
        this.TEXTURE = TEXTURE;
    }

    public Identifier getTexture() {
        return TEXTURE;
    }
}
