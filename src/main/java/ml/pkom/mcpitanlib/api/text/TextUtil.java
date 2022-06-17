package ml.pkom.mcpitanlib.api.text;

import ml.pkom.mcpitanlib.api.util.math.CText;
import net.minecraft.text.Text;

public class TextUtil {

    public static Text create(String str) {
        return literal(str);
    }

    public static Text literal(String str) {
        return Text.literal(str);
    }

    public static Text translatable(String key) {
        return Text.translatable(key);
    }

    public static Text translatable(String key, Object... args) {
        return Text.translatable(key, args);
    }

    public static Text empty() {
        return Text.empty();
    }

    public static String getString(Text text) {
        return text.getString();
    }

    public static CText cText(Text text) {
        return new CText(text);
    }

    public static Text text(CText cText) {
        return cText.getText();
    }
}
