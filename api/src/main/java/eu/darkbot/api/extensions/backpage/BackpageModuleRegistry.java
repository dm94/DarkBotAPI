package eu.darkbot.api.extensions.backpage;

import eu.darkbot.api.API;

import java.util.Collection;
import java.util.Optional;

public interface BackpageModuleRegistry extends API.Singleton {

    boolean register(BackpageModule module);

    boolean unregister(BackpageModule module);

    Collection<BackpageModule> getModules();

    Optional<BackpageModule> getModule(String id);
}
