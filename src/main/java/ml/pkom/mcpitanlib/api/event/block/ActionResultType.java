package ml.pkom.mcpitanlib.api.event.block;

import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;

public class ActionResultType {

    private ActionResult actionResult;

    public ActionResultType(ActionResult actionResult) {
        this.actionResult = actionResult;
    }
    public static ActionResultType SUCCESS = new ActionResultType(ActionResult.SUCCESS);
    public static ActionResultType CONSUME = new ActionResultType(ActionResult.CONSUME);
    public static ActionResultType CONSUME_PARTIAL = new ActionResultType(ActionResult.CONSUME_PARTIAL);
    public static ActionResultType PASS = new ActionResultType(ActionResult.PASS);
    public static ActionResultType FAIL = new ActionResultType(ActionResult.FAIL);

    public void setActionResult(ActionResult actionResult) {
        this.actionResult = actionResult;
    }

    public ActionResult getActionResult() {
        return actionResult;
    }

    public static ActionResultType of(ActionResult actionResult) {
        if (actionResult == ActionResult.SUCCESS) return SUCCESS;
        if (actionResult == ActionResult.CONSUME) return CONSUME;
        if (actionResult == ActionResult.CONSUME_PARTIAL) return CONSUME_PARTIAL;
        if (actionResult == ActionResult.PASS) return PASS;
        if (actionResult == ActionResult.FAIL) return FAIL;
        return PASS;
    }

    public <T> TypedActionResult<T> getTypedActionResult(T data) {
        if (this.equals(SUCCESS)) return TypedActionResult.success(data);
        if (this.equals(CONSUME)) return TypedActionResult.consume(data);
        if (this.equals(CONSUME_PARTIAL)) return TypedActionResult.consume(data);
        if (this.equals(PASS)) return TypedActionResult.pass(data);
        if (this.equals(FAIL)) return TypedActionResult.fail(data);
        return TypedActionResult.pass(data);
    }
}
