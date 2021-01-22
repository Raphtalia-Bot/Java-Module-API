package dev.raphtalia.javamoduleapi.objects.entity.channel;

import dev.raphtalia.javamoduleapi.objects.entity.Entity;
import reactor.core.publisher.Mono;
import reactor.util.annotation.Nullable;

public interface Channel extends Entity {

    Type getType();

    default Mono<Void> delete() {
        return delete(null);
    }

    Mono<Void> delete(@Nullable String reason);

    default String getMention() {
        return "<#" + getId() + '>';
    }

    enum Type {
        UNKNOWN(-1),
        GUILD_TEXT(0),
        DM(1),
        GUILD_VOICE(2),
        GROUP_DM(3),
        GUILD_CATEGORY(4),
        GUILD_NEWS(5),
        GUILD_STORE(6);

        private final int value;

        Type(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Type of(final int value) {
            switch (value) {
                case 0: return GUILD_TEXT;
                case 1: return DM;
                case 2: return GUILD_VOICE;
                case 3: return GROUP_DM;
                case 4: return GUILD_CATEGORY;
                case 5: return GUILD_NEWS;
                case 6: return GUILD_STORE;
                default: return UNKNOWN;
            }
        }
    }
}
