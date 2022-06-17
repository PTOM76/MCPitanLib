package ml.pkom.mcpitanlib.api.util.math;

import ml.pkom.mcpitanlib.api.text.TextUtil;
import net.minecraft.text.Text;

public class CText {
    private Text text;

    @Override
    public String toString() {
        return TextUtil.getString(getText());
    }

    public CText() {
    }

    public CText(Text text) {
        setText(text);
    }

    public static CText literal(String string) {
        return new CText(TextUtil.literal(string));
    }

    public static CText empty() {
        return new CText(TextUtil.empty());
    }

    public static CText translatable(String key, Object... args) {
        return new CText(TextUtil.translatable(key, args));
    }

    public static CText translatable(String key) {
        return new CText(TextUtil.translatable(key));
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}
