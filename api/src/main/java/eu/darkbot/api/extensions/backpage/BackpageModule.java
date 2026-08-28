package eu.darkbot.api.extensions.backpage;

import eu.darkbot.api.extensions.Installable;
import eu.darkbot.api.extensions.Task;

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
