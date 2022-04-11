package ml.pkom.mcpitanlib.api.util;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class RecipeManageHelper {
    private static Map<IdentifierExt, JsonObject> recipes = new HashMap();

    public static Map<IdentifierExt, JsonObject> getRecipes() {
        return recipes;
    }

    public static void addRecipe(IdentifierExt id, JsonObject recipe) {
        getRecipes().put(id, recipe);
    }
}
