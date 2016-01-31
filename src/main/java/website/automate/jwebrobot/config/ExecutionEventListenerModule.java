package website.automate.jwebrobot.config;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;

import website.automate.jwebrobot.listener.ExecutionEventListener;
import website.automate.jwebrobot.report.YamlReporter;

public class ExecutionEventListenerModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<ExecutionEventListener> listenerBinder = Multibinder.newSetBinder(binder(), ExecutionEventListener.class);
        
        listenerBinder.addBinding().to(YamlReporter.class).in(Singleton.class);
    }
}
