package me.aurium.beetle.generic;

import me.aurium.beetle.core.Beetle;
import me.aurium.beetle.core.BeetleFactory;
import me.aurium.beetle.core.DefaultBeetle;
import me.aurium.beetle.core.datacore.CommonDatacoreFactory;
import me.aurium.beetle.core.datacore.DataCoreFactory;
import me.aurium.beetle.core.logger.SLFLoggerHelper;
import me.aurium.beetle.core.logger.SimpleLogger;
import me.aurium.beetle.core.registry.SimpleRegistry;
import me.aurium.beetle.core.runner.TaskRunner;
import me.aurium.beetle.core.service.ServiceRegistry;

public class GenericBeetleFactory implements BeetleFactory {

    private final Boolean isDebug;

    public GenericBeetleFactory(boolean isDebug) {
        this.isDebug = isDebug;
    }

    @Override
    public Beetle build() {
        SimpleLogger logger = SLFLoggerHelper.buildLogger(isDebug);
        TaskRunner runner = new GenericTaskRunner();

        return build(runner,logger);
    }

    @Override
    public Beetle build(TaskRunner runner) {
        SimpleLogger logger = SLFLoggerHelper.buildLogger(isDebug);

        return build(runner,logger);
    }

    @Override
    public Beetle build(SimpleLogger logger) {
        TaskRunner runner = new GenericTaskRunner();

        return build(runner,logger);
    }

    @Override
    public Beetle build(TaskRunner runner, SimpleLogger logger) {

        DataCoreFactory dataCoreFactory = new CommonDatacoreFactory(runner);
        ServiceRegistry serviceRegistry = new SimpleRegistry();


        return new DefaultBeetle(runner,logger,dataCoreFactory,serviceRegistry,isDebug);
    }
}