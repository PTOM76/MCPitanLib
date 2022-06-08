package ml.pkom.mcpitanlib.api.text;

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
}
