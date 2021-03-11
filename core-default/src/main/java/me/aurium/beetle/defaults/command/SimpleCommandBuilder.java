package me.aurium.beetle.defaults.command;

import me.aurium.beetle.api.command.ContextHandler;
import me.aurium.beetle.api.command.ContextSource;
import me.aurium.beetle.api.command.TabContextHandler;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class SimpleCommandBuilder<T> {

    private final String name;
    private final ContextSource<T> contextSource;

    private ContextHandler<T> contextHandler;
    private TabContextHandler<T> tabContextHandler;
    private String permission;
    private Collection<String> aliases;
    private String description;
    private String usage;

    public SimpleCommandBuilder(String commandName, ContextSource<T> source) {
        this.name = commandName;
        this.contextSource = source;
        this.contextHandler = consumed -> {
            consumed.debug("Context missing for command " + consumed.getAlias() + "! Please define a context.");
            return true;
        };
        this.tabContextHandler = context -> Collections.emptySet();
        this.aliases = Collections.emptySet();
        this.description = "Default description for minecraft like platforms";
        this.usage = "Default usage for minecraft like platforms";
    }

    public SimpleCommandBuilder<T> setContextHandler(ContextHandler<T> context) {
        this.contextHandler = context;
        return this;
    }

    public SimpleCommandBuilder<T> setTabContextHandler(TabContextHandler<T> context) {
        this.tabContextHandler = context;
        return this;
    }

    public SimpleCommandBuilder<T> setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public SimpleCommandBuilder<T> setUsage(String usage) {
        this.usage = usage;
        return this;
    }

    public SimpleCommandBuilder<T> setDescription(String description) {
        this.permission = description;
        return this;
    }

    public SimpleCommandBuilder<T> setAliases(Collection<String> aliases) {
        this.aliases = aliases;
        return this;
    }

    public SimpleCommandBuilder<T> setAliases(String... aliases) {
        this.aliases = Arrays.asList(aliases);
        return this;
    }

    public SimpleCommand<T> build() {
        Objects.requireNonNull(contextHandler);
        Objects.requireNonNull(tabContextHandler);
        Objects.requireNonNull(permission);
        Objects.requireNonNull(aliases);
        Objects.requireNonNull(description);
        Objects.requireNonNull(usage);

        return new SimpleCommand<>(contextHandler,tabContextHandler,name,permission,aliases,description,usage,contextSource);
    }

    public static <T> SimpleCommandBuilder<T> of(String commandName, ContextSource<T> source) {
        return new SimpleCommandBuilder<>(commandName, source);
    }

}