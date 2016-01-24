package website.automate.jwebrobot.executor.action;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import website.automate.jwebrobot.context.GlobalExecutionContext;
import website.automate.jwebrobot.context.ScenarioExecutionContext;
import website.automate.jwebrobot.executor.ScenarioExecutor;
import website.automate.jwebrobot.models.scenario.Scenario;
import website.automate.jwebrobot.models.scenario.actions.IncludeAction;
import website.automate.jwebrobot.models.scenario.actions.criteria.ScenarioCriterion;

@RunWith(MockitoJUnitRunner.class)
public class IncludeActionExecutorTest {

    private static final String SCENARIO_TITLE = "awesome scenario";
    
    @Mock private ScenarioExecutor scenarioExecutor;
    @Mock private IncludeAction action;
    @Mock private ScenarioExecutionContext scenarioContext;
    @Mock private ScenarioExecutionContext childScenarioContext;
    @Mock private GlobalExecutionContext globalContext;
    @Mock private Scenario childScenario;
    @Mock private ScenarioCriterion scenarioCriterion;
    
    private IncludeActionExecutor executor;
    
    @Test
    public void includedScenarioShouldBeExecuted(){
        when(action.getScenario()).thenReturn(scenarioCriterion);
        when(scenarioCriterion.getValue()).thenReturn(SCENARIO_TITLE);
        when(scenarioContext.getGlobalContext()).thenReturn(globalContext);
        when(globalContext.getScenario(SCENARIO_TITLE)).thenReturn(childScenario);
        when(scenarioContext.createChildContext(childScenario)).thenReturn(childScenarioContext);
        executor = new IncludeActionExecutor(scenarioExecutor);
        
        executor.execute(action, scenarioContext);
        
        verify(scenarioExecutor).runScenario(childScenario, childScenarioContext);
    }
}