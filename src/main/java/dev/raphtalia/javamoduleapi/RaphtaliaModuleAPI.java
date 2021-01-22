package dev.raphtalia.javamoduleapi;

import dev.raphtalia.javamoduleapi.objects.Command;
import dev.raphtalia.javamoduleapi.objects.EventListener;
import dev.raphtalia.javamoduleapi.objects.events.Event;
import dev.raphtalia.javamoduleapi.util.exceptions.DuplicateRegistryException;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RaphtaliaModuleAPI {

    public static RaphtaliaModuleAPI Builder(String token) {
        return new RaphtaliaModuleAPI(token);
    }

    private String token;
    private String port = "4002";
    private Command[] commands;
    private EventListener<?>[] events;

    private RaphtaliaModuleAPI(String token) {
        this.token = token;
    }

    public RaphtaliaModuleAPI port(String port) {
        this.port = port;
        return this;
    }

    public RaphtaliaModuleAPI registerCommands(Command... commands) {
        this.commands = commands;
        return this;
    }

    public RaphtaliaModuleAPI registerEvents(EventListener<?>... events) {
        this.events = events;
        return this;
    }

    public void build() throws DuplicateRegistryException {
        StringBuilder duplicateString = new StringBuilder();
        Set<String> allCommands = new HashSet<>();
        Set<Command> duplicateCommands = Arrays.stream(this.commands).filter(n -> !allCommands.add(n.getName())).collect(Collectors.toSet());
        for (Command duplicate : duplicateCommands) {
            duplicateString.append(duplicate.getName()).append(" ");
            System.out.println(duplicate.getName() + " has already been registered!");
        }
        Set<Class<?>> allEvents = new HashSet<>();
        Set<EventListener<?>> duplicateEvents = Arrays.stream(this.events).filter(n -> !allEvents.add(n.getEventType())).collect(Collectors.toSet());
        for (EventListener<?> duplicate : duplicateEvents) {
            duplicateString.append(duplicate.getClass().getName().toLowerCase()).append(" ");
            System.out.println(duplicate.getClass().getName().toLowerCase() + " has already been registered!");
        }
        if(duplicateEvents.isEmpty() && duplicateCommands.isEmpty()) {
            HttpServer.create().port(Integer.parseInt(port))
                .route(routes -> {
                    routes.get("/", (request, response) -> {
                        System.out.println("TEST");
                        return response.sendString(Mono.just("TESTS COMPLETE"));
                    });
                    routes.get("/commands", (request, response) -> {
                        System.out.println("COMMAND");
                        return response.sendString(Mono.just("COMMANDS COMPLETE"));
                    });
                    routes.post("/commands/{id}", (request, response) -> {
                        System.out.println("COMMAND");
                        return response.sendString(Mono.just("COMMANDS " + request.param("id") + " COMPLETE"));
                    });
                    routes.get("/events", (request, response) -> {
                        System.out.println("EVENT");
                        return response.sendString(Mono.just("EVENTS COMPLETE"));
                    });
                    routes.post("/events/{id}", (request, response) -> {
                        System.out.println("EVENT");
                        return response.sendString(Mono.just("EVENTS " + request.param("id") + " COMPLETE"));
                    });
                })
                .bindNow().onDispose().block();
        } else {
            throw new DuplicateRegistryException(duplicateString.toString() + "are all already registered!");
        }
    }
}
