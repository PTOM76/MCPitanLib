package ml.pkom.mcpitanlib.api.base.util;

import net.minecraft.util.Identifier;

public class IdentifierExt extends Identifier {
    public IdentifierExt(String[] id) {
        super(id);
    }

    public IdentifierExt(String id) {
        super(id);
    }

    public IdentifierExt(String namespace, String path) {
        super(namespace, path);
    }

    public Identifier asIdentifier() {
        return this;
    }
}
