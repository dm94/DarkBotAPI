package eu.darkbot.api.managers;

import eu.darkbot.api.API;

public interface SessionAPI extends API.Singleton {

    boolean isSessionValid();

    boolean requestRelogin();
}
