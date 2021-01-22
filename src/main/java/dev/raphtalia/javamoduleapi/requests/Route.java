package dev.raphtalia.javamoduleapi.requests;

import dev.raphtalia.javamoduleapi.ModuleClient;
import dev.raphtalia.javamoduleapi.objects.entity.Channel;
import io.netty.handler.codec.http.HttpMethod;

public class Route {

    private final HttpMethod method;
    private final String urlTemplete;

    private Route(HttpMethod method, String urlTemplete) {
        this.method = method;
        this.urlTemplete = urlTemplete;
    }

    public static Route get(String url) {
        return new Route(HttpMethod.GET, url);
    }

    public static Route post(String url) {
        return new Route(HttpMethod.POST, url);
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getUrlTemplete() {
        return urlTemplete;
    }
}
