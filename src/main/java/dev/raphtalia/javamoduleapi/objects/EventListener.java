package dev.raphtalia.javamoduleapi.objects;

import dev.raphtalia.javamoduleapi.objects.events.Event;
import reactor.core.publisher.Mono;

public interface EventListener<T extends Event> {

    Class<T> getEventType();

    Mono<Void> execute(T event);
}
