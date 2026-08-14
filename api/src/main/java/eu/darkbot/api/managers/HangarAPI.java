package eu.darkbot.api.managers;

import eu.darkbot.api.API;
import eu.darkbot.api.extensions.Task;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Allows different hangar-related actions to be performed.
 * Should only be called from background thread, used in {@link Task}s.
 *
 * @see Task
 * @since 0.7.1
 */
public interface HangarAPI extends API.Singleton {

    /**
     * Update the current hangar
     * Use {@link #requestUpdateCurrentHangar(long)} if you need it to update
     * continuously.
     */
    void updateCurrentHangar();

    /**
     * Force hangar list update.
     * Use {@link #requestUpdateHangarList(long)} if you need it to update
     * continuously.
     */
    void updateHangarList();

    /**
     * Returns the ID of the current hangar.
     * A value of 0 indicates that the currentHangar has not been initialized.
     */
    int getCurrentHangarId();

    /**
     * Request hangar list to be updated within a certain timeframe.
     * This method should be repeatedly called to request updates
     *
     * @param millis The maximum time to wait
     */
    void requestUpdateCurrentHangar(long millis);

    /**
     * Request current hangar to be updated within a certain timeframe.
     * This method should be repeatedly called to request updates
     *
     * @param millis The maximum time to wait
     */
    void requestUpdateHangarList(long millis);

    /**
     * Request to change the current hangar.
     * The ship must be in a base or disconnected.
     *
     * @param hangarId Id of the hangar to which the change is to be made
     * @return true if the request was successful
     */
    boolean changeHangar(int hangarId);

    /**
     * Returns the ship id associated with the current packet-backed hangar snapshot.
     * Implementations that do not receive Unity hangar packets return {@code 0}.
     */
    default long getCurrentShipId() {
        return 0L;
    }

    /** Returns the active configuration id announced by the server, or {@code 0} if unknown. */
    default int getCurrentConfigId() {
        return 0;
    }

    /** Returns the known configurations for the current ship as a read-only snapshot. */
    default Map<Integer, Configuration> getConfigurations() {
        return Collections.emptyMap();
    }

    /** Returns the ships announced by the current hangar snapshot. */
    default Collection<? extends Ship> getShips() {
        return Collections.emptyList();
    }

    /** Returns the drones announced by the current hangar snapshot. */
    default Collection<? extends Drone> getDrones() {
        return Collections.emptyList();
    }

    /** Requests repair of one known drone; the server remains authoritative for the result. */
    default boolean repairDrone(long droneId) {
        return false;
    }

    /** Packet-backed drone summary from {@code HangarShipDroneView}. */
    @SuppressWarnings("PMD.DataClass")
    final class Drone {
        private final long id;
        private final int typeId;
        private final String lootId;
        private final int points;
        private final int level;
        private final int position;
        private final double damage;
        private final int nextLevel;

        public Drone(long id, int typeId, String lootId, int points, int level,
                     int position, double damage, int nextLevel) {
            this.id = id;
            this.typeId = typeId;
            this.lootId = lootId == null ? "" : lootId;
            this.points = points;
            this.level = level;
            this.position = position;
            this.damage = damage;
            this.nextLevel = nextLevel;
        }

        public long getId() { return id; }
        public int getTypeId() { return typeId; }
        public String getLootId() { return lootId; }
        public int getPoints() { return points; }
        public int getLevel() { return level; }
        public int getPosition() { return position; }
        public double getDamage() { return damage; }
        public int getNextLevel() { return nextLevel; }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Drone)) return false;
            Drone that = (Drone) other;
            return id == that.id && typeId == that.typeId && points == that.points
                    && level == that.level && position == that.position
                    && Double.compare(damage, that.damage) == 0 && nextLevel == that.nextLevel
                    && Objects.equals(lootId, that.lootId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, typeId, lootId, points, level, position, damage, nextLevel);
        }
    }

    /** Packet-backed ship summary from {@code HangarShipBriefCommand}. */
    final class Ship {
        private final long id;
        private final String lootId;
        private final boolean favorite;
        private final boolean active;

        public Ship(long id, String lootId, boolean favorite, boolean active) {
            this.id = id;
            this.lootId = lootId == null ? "" : lootId;
            this.favorite = favorite;
            this.active = active;
        }

        public long getId() {
            return id;
        }

        public String getLootId() {
            return lootId;
        }

        public boolean isFavorite() {
            return favorite;
        }

        public boolean isActive() {
            return active;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Ship)) return false;
            Ship that = (Ship) other;
            return id == that.id && favorite == that.favorite && active == that.active
                    && Objects.equals(lootId, that.lootId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, lootId, favorite, active);
        }

        @Override
        public String toString() {
            return "Ship(" + id + ", " + lootId + ", active=" + active + ")";
        }
    }

    /** Packet-backed slot configuration for one ship preset. */
    final class Configuration {
        private final int id;
        private final boolean active;
        private final int setId;
        private final Map<String, List<Integer>> slots;

        public Configuration(int id, boolean active, int setId, Map<String, List<Integer>> slots) {
            this.id = id;
            this.active = active;
            this.setId = setId;
            this.slots = Collections.unmodifiableMap(new java.util.LinkedHashMap<>(slots));
        }

        public int getId() {
            return id;
        }

        public boolean isActive() {
            return active;
        }

        public int getSetId() {
            return setId;
        }

        public Map<String, List<Integer>> getSlots() {
            return slots;
        }

        public List<Integer> getSlots(String slotSet) {
            List<Integer> values = slots.get(slotSet);
            return values == null ? Collections.emptyList() : values;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Configuration)) return false;
            Configuration that = (Configuration) other;
            return id == that.id && active == that.active && setId == that.setId
                    && Objects.equals(slots, that.slots);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, active, setId, slots);
        }
    }
}
