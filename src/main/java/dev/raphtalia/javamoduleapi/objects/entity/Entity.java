package dev.raphtalia.javamoduleapi.objects.entity;

import dev.raphtalia.javamoduleapi.ModuleClient;

public interface Entity {

    ModuleClient getClient();

    String getId();
}
