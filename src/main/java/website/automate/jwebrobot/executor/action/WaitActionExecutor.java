package website.automate.jwebrobot.executor.action;

import com.google.inject.Inject;

import website.automate.jwebrobot.context.ScenarioExecutionContext;
import website.automate.jwebrobot.expression.ExpressionEvaluator;
import website.automate.jwebrobot.models.scenario.actions.WaitAction;

public class WaitActionExecutor extends IfUnlessActionExecutor<WaitAction> {

    @Inject
    public WaitActionExecutor(ExpressionEvaluator expressionEvaluator) {
        super(expressionEvaluator);
    }

    @Override
    public Class<WaitAction> getActionType() {
        return WaitAction.class;
    }

    @Override
    public void safeExecute(final WaitAction action, ScenarioExecutionContext context) {
        long time = Long.parseLong(action.getTime().getValue());
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }

    }

}
