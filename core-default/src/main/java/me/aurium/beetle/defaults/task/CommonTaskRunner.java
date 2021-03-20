package me.aurium.beetle.defaults.task;

import me.aurium.beetle.api.task.TaskRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

//taskrunner for platforms with a main thread to use
public class CommonTaskRunner implements TaskRunner {

    private final Logger logger = LoggerFactory.getLogger(CommonTaskRunner.class);

    private final Executor asyncQueue;
    private final Executor syncQueue;

    public CommonTaskRunner(Executor syncQueue, Executor asyncQueue) {
        this.asyncQueue = asyncQueue;
        this.syncQueue = syncQueue;
    }

    @Override
    public void executeSync(Runnable runnable) {
        supplySync(() -> {
            runnable.run();

            return null;
        }).whenComplete((ignored,exception) -> {
            if (exception != null) {
                logger.error(exception.getLocalizedMessage());
            }
        });
    }

    @Override
    public void executeAsync(Runnable runnable) {
        supplyAsync(() -> {
            runnable.run();

            return null;
        }).whenComplete((ignored,exception) -> {
            if (exception != null) {
                logger.error(exception.getLocalizedMessage());
            }
        });
    }

    @Override
    public <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier,asyncQueue);
    }

    @Override
    public <T> CompletableFuture<T> supplySync(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier,syncQueue);
    }

}