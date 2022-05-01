package ml.pkom.mcpitanlib.api.gui;

public class GuiScreenSettings {
    public boolean darkBackground;
    public boolean showMouseOverTooltip;

    public GuiScreenSettings(boolean darkBackground, boolean showMouseOverTooltip) {
        setDarkBackground(darkBackground);
        setShowMouseOverTooltip(showMouseOverTooltip);
    }

    public GuiScreenSettings() {
        this(true, true);
    }

    public void setDarkBackground(boolean darkBackground) {
        this.darkBackground = darkBackground;
    }

    public void setShowMouseOverTooltip(boolean showMouseOverTooltip) {
        this.showMouseOverTooltip = showMouseOverTooltip;
    }

    public boolean isDarkBackground() {
        return darkBackground;
    }

    public boolean isShowMouseOverTooltip() {
        return showMouseOverTooltip;
    }
}
