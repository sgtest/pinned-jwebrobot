package website.automate.jwebrobot.config;

import com.google.inject.AbstractModule;
import website.automate.jwebrobot.executor.ScenarioExecutor;
import website.automate.jwebrobot.executor.impl.ScenarioExecutorImpl;


public class ScenarioExecutorModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ScenarioExecutor.class).to(ScenarioExecutorImpl.class);
    }
}
