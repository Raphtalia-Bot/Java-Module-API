package dev.raphtalia.javamoduleapi.objects;

import reactor.util.annotation.Nullable;

import java.util.Optional;

public abstract class Command {

    private final String name;
    private final Optional<String> description;

    public Command(String name, String description) {
        this.name = name;
        this.description = Optional.of(description);
    }

    public Command(String name) {
        this.name = name;
        this.description = Optional.empty();
    }

    public String getName() {
        return name;
    }

    public Optional<String> getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
