package dev.raphtalia.javamoduleapi;

import dev.raphtalia.javamoduleapi.objects.Command;
import dev.raphtalia.javamoduleapi.objects.Event;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

public class RaphtaliaModuleAPI {

    public static RaphtaliaModuleAPI Builder(String token) {
        return new RaphtaliaModuleAPI(token);
    }

    private String token;
    private String port = "4002";
    private Command[] commands;
    private Event[] events;

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

    public RaphtaliaModuleAPI registerEvents(Event... events) {
        this.events = events;
        return this;
    }

    public void build() {
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
    }
}
