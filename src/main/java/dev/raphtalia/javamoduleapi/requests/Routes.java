package dev.raphtalia.javamoduleapi.requests;

public class Routes {

    public static final String BASE_URL = "https://raphtalia.dev/api/module/v1";

    public static final Route CHANNEL_GET = Route.get("discord/channels/{channel.id}");
}
