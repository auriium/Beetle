package me.aurium.beetle.defaults.task;

import me.aurium.beetle.api.task.SyncQueue;
import me.aurium.beetle.api.task.TaskRunner;
import me.aurium.beetle.api.task.Tasker;

import java.util.concurrent.*;

//tasker for platforms with a main thread
public abstract class AbstractTasker implements Tasker {

    private final SyncQueue syncQueue;
    private final Executor asyncQueue;

    private final TaskRunner taskRunner;

    protected AbstractTasker(Executor asyncQueue) {
        this.syncQueue = new CommonSyncQueue();
        this.asyncQueue = asyncQueue;

        this.taskRunner = new CommonTaskRunner(syncQueue,asyncQueue);
    }

    @Override
    public Executor getSyncExecutor() {
        return this.syncQueue;
    }

    @Override
    public Executor getAsyncExecutor() {
        return asyncQueue;
    }

    @Override
    public TaskRunner getRunner() {
        return this.taskRunner;
    }

    protected SyncQueue asQueue() {
        return this.syncQueue;
    }


}
