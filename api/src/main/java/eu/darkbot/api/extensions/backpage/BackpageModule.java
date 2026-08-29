package eu.darkbot.api.extensions.backpage;

import eu.darkbot.api.extensions.Installable;
import eu.darkbot.api.extensions.Task;

/**
 * Extensible functional task for account/client features such as Skylab, auctions
 * and other internal screens. Despite the historical name, implementations may be
 * packet-backed and must not assume that an HTTP backpage exists.
 */
public interface BackpageModule extends Task, Installable {

    enum State {
        IDLE,
        RUNNING,
        COOLDOWN,
        CAPTCHA,
        ERROR
    }

    String getId();

    default String getName() {
        return getClass().getSimpleName();
    }

    State getState();

    void setState(State state);

    default long getReadyAtMs() {
        return 0;
    }

    default String getStatusMessage() {
        return "";
    }

    default boolean cancel() {
        return false;
    }
}
