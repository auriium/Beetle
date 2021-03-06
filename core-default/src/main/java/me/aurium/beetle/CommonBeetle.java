package me.aurium.beetle;

import me.aurium.beetle.Beetle;
import me.aurium.beetle.datacore.DataCoreFactory;
import me.aurium.beetle.file.FileProvider;
import me.aurium.beetle.logger.SimpleLogger;
import me.aurium.beetle.service.ServiceRegistry;
import me.aurium.beetle.task.Tasker;

public class CommonBeetle implements Beetle {

    private final Tasker tasker;
    private final SimpleLogger logger;
    private final DataCoreFactory factory;
    private final ServiceRegistry serviceRegistry;
    private final FileProvider fileProvider;
    private final boolean isDebug;

    public CommonBeetle(Tasker tasker, SimpleLogger logger, DataCoreFactory factory, ServiceRegistry serviceRegistry, FileProvider fileProvider, boolean isDebug) {
        this.tasker = tasker;
        this.logger = logger;
        this.serviceRegistry = serviceRegistry;
        this.fileProvider = fileProvider;
        this.factory = factory;
        this.isDebug = isDebug;
    }


    @Override
    public Tasker getTasker() {
        return tasker;
    }

    @Override
    public SimpleLogger getLogger() {
        return logger;
    }

    @Override
    public DataCoreFactory getDataCoreFactory() {
        return factory;
    }

    @Override
    public ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }

    @Override
    public FileProvider getFileProvider() {
        return fileProvider;
    }

    @Override
    public boolean isDebug() {
        return isDebug;
    }
}