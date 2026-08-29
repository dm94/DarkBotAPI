package eu.darkbot.api.extensions.backpage;

import eu.darkbot.api.API;

import java.util.Collection;
import java.util.Optional;

/**
 * Registry of extensible functional tasks for internal game/account features.
 * Implementations can use backpage HTTP in Flash mode or packet APIs in Unity mode.
 */
public interface BackpageModuleRegistry extends API.Singleton {

    /** Registers a feature task; duplicate ids are rejected. */
    boolean register(BackpageModule module);

    boolean unregister(BackpageModule module);

    Collection<BackpageModule> getModules();

    Optional<BackpageModule> getModule(String id);
}
