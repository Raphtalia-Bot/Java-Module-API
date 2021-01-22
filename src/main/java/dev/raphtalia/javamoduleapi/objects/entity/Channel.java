package dev.raphtalia.javamoduleapi.objects.entity;

import dev.raphtalia.javamoduleapi.ModuleClient;
import dev.raphtalia.javamoduleapi.requests.data.ChannelData;

public class Channel implements Entity {

    private final ModuleClient client;
    private final ChannelData data;

    public Channel(final ModuleClient client, final ChannelData data) {
        this.client = client;
        this.data = data;
    }

    @Override
    public ModuleClient getClient() {
        return this.client;
    }

    @Override
    public String getId() {
        return this.data.getId();
    }
}
