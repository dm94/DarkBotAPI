package eu.darkbot.api.managers;

import eu.darkbot.api.API;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("PMD.ExcessivePublicCount")
public interface SkylabAPI extends API.Singleton {

    default boolean viewModule(String moduleName) {
        return false;
    }

    default boolean purchaseRobot(String construction, int robotType) {
        return false;
    }

    default boolean sendAction(Action action) {
        return false;
    }

    default Collection<OreStorage> getOreStorages() {
        return Collections.emptyList();
    }

    default Collection<SkylabModule> getModules() {
        return Collections.emptyList();
    }

    default Optional<SkylabModule> getModule(String moduleType) {
        for (SkylabModule module : getModules()) {
            if (module.getType().equals(moduleType)) return Optional.of(module);
        }
        return Optional.empty();
    }

    default Optional<String> getSelectedModuleName() {
        return Optional.empty();
    }

    default int getStorageEfficiency() {
        return 0;
    }

    default Optional<Productivity> getProductivity() {
        return Optional.empty();
    }

    default Optional<XenoInfo> getXenoInfo() {
        return Optional.empty();
    }

    default Optional<CollectorInfo> getCollectorInfo() {
        return Optional.empty();
    }

    default Optional<UpgradeCost> getUpgradeCost() {
        return Optional.empty();
    }

    default Optional<Receipt> getLastReceipt() {
        return Optional.empty();
    }

    @SuppressWarnings({"PMD.DataClass", "PMD.ExcessiveParameterList"})
    final class Action {
        private final String construction;
        private final String transportMode;
        private final String subaction;
        private final int countPrometium;
        private final int countEndurium;
        private final int countTerbium;
        private final int countPrometid;
        private final int countPromerium;
        private final int countDuranium;
        private final int countXenomit;
        private final int countSeprom;

        public Action(String construction, String transportMode, String subaction,
                      int countPrometium, int countEndurium, int countTerbium,
                      int countPrometid, int countPromerium, int countDuranium,
                      int countXenomit, int countSeprom) {
            this.construction = construction == null ? "" : construction;
            this.transportMode = transportMode == null ? "" : transportMode;
            this.subaction = subaction == null ? "" : subaction;
            this.countPrometium = countPrometium;
            this.countEndurium = countEndurium;
            this.countTerbium = countTerbium;
            this.countPrometid = countPrometid;
            this.countPromerium = countPromerium;
            this.countDuranium = countDuranium;
            this.countXenomit = countXenomit;
            this.countSeprom = countSeprom;
        }

        public String getConstruction() { return construction; }
        public String getTransportMode() { return transportMode; }
        public String getSubaction() { return subaction; }
        public int getCountPrometium() { return countPrometium; }
        public int getCountEndurium() { return countEndurium; }
        public int getCountTerbium() { return countTerbium; }
        public int getCountPrometid() { return countPrometid; }
        public int getCountPromerium() { return countPromerium; }
        public int getCountDuranium() { return countDuranium; }
        public int getCountXenomit() { return countXenomit; }
        public int getCountSeprom() { return countSeprom; }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Action)) return false;
            Action that = (Action) other;
            return countPrometium == that.countPrometium && countEndurium == that.countEndurium
                    && countTerbium == that.countTerbium && countPrometid == that.countPrometid
                    && countPromerium == that.countPromerium && countDuranium == that.countDuranium
                    && countXenomit == that.countXenomit && countSeprom == that.countSeprom
                    && Objects.equals(construction, that.construction)
                    && Objects.equals(transportMode, that.transportMode)
                    && Objects.equals(subaction, that.subaction);
        }

        @Override
        public int hashCode() {
            return Objects.hash(construction, transportMode, subaction, countPrometium,
                    countEndurium, countTerbium, countPrometid, countPromerium,
                    countDuranium, countXenomit, countSeprom);
        }

        @Override
        public String toString() {
            return "Action(" + construction + ", " + transportMode + ", " + subaction + ")";
        }
    }

    @SuppressWarnings("PMD.DataClass")
    final class OreStorage {
        private final int oreId;
        private final String oreName;
        private final int current;
        private final int capacity;

        public OreStorage(int oreId, String oreName, int current, int capacity) {
            this.oreId = oreId;
            this.oreName = oreName == null ? "" : oreName;
            this.current = current;
            this.capacity = capacity;
        }

        public int getOreId() { return oreId; }
        public String getOreName() { return oreName; }
        public int getCurrent() { return current; }
        public int getCapacity() { return capacity; }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof OreStorage)) return false;
            OreStorage that = (OreStorage) other;
            return oreId == that.oreId && current == that.current
                    && capacity == that.capacity && Objects.equals(oreName, that.oreName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(oreId, oreName, current, capacity);
        }

        @Override
        public String toString() {
            return "OreStorage(" + oreName + ", " + current + "/" + capacity + ")";
        }
    }

    @SuppressWarnings("PMD.DataClass")
    final class SkylabModule {
        private final String type;
        private final String state;
        private final int level;
        private final int maxLevel;
        private final int efficiency;
        private final int currentPower;
        private final int maxPower;

        public SkylabModule(String type, String state, int level, int maxLevel,
                            int efficiency, int currentPower, int maxPower) {
            this.type = type == null ? "" : type;
            this.state = state == null ? "" : state;
            this.level = level;
            this.maxLevel = maxLevel;
            this.efficiency = efficiency;
            this.currentPower = currentPower;
            this.maxPower = maxPower;
        }

        public String getType() { return type; }
        public String getState() { return state; }
        public int getLevel() { return level; }
        public int getMaxLevel() { return maxLevel; }
        public int getEfficiency() { return efficiency; }
        public int getCurrentPower() { return currentPower; }
        public int getMaxPower() { return maxPower; }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof SkylabModule)) return false;
            SkylabModule that = (SkylabModule) other;
            return level == that.level && maxLevel == that.maxLevel
                    && efficiency == that.efficiency && currentPower == that.currentPower
                    && maxPower == that.maxPower && Objects.equals(type, that.type)
                    && Objects.equals(state, that.state);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, state, level, maxLevel, efficiency, currentPower, maxPower);
        }

        @Override
        public String toString() {
            return "SkylabModule(" + type + ", level=" + level + "/" + maxLevel
                    + ", state=" + state + ")";
        }
    }

    final class RobotPrice {
        private final String priceType;
        private final int value;

        public RobotPrice(String priceType, int value) {
            this.priceType = priceType == null ? "" : priceType;
            this.value = value;
        }

        public String getPriceType() { return priceType; }
        public int getValue() { return value; }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof RobotPrice)) return false;
            RobotPrice that = (RobotPrice) other;
            return value == that.value && Objects.equals(priceType, that.priceType);
        }

        @Override
        public int hashCode() {
            return Objects.hash(priceType, value);
        }

        @Override
        public String toString() {
            return "RobotPrice(" + priceType + "=" + value + ")";
        }
    }

    final class RobotProduction {
        private final int uridium;
        private final long leftTime;

        public RobotProduction(int uridium, long leftTime) {
            this.uridium = uridium;
            this.leftTime = leftTime;
        }

        public int getUridium() { return uridium; }
        public long getLeftTime() { return leftTime; }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof RobotProduction)) return false;
            RobotProduction that = (RobotProduction) other;
            return uridium == that.uridium && leftTime == that.leftTime;
        }

        @Override
        public int hashCode() {
            return Objects.hash(uridium, leftTime);
        }
    }

    @SuppressWarnings("PMD.DataClass")
    final class Productivity {
        private final int efficiency;
        private final int bonus;
        private final int active;
        private final int maxActive;
        private final int inStorage;
        private final Optional<RobotPrice> type1Price;
        private final Optional<RobotPrice> type2Price;
        private final List<RobotProduction> robots;

        public Productivity(int efficiency, int bonus, int active, int maxActive, int inStorage,
                            RobotPrice type1Price, RobotPrice type2Price,
                            List<RobotProduction> robots) {
            this.efficiency = efficiency;
            this.bonus = bonus;
            this.active = active;
            this.maxActive = maxActive;
            this.inStorage = inStorage;
            this.type1Price = Optional.ofNullable(type1Price);
            this.type2Price = Optional.ofNullable(type2Price);
            this.robots = robots == null
                    ? Collections.emptyList()
                    : Collections.unmodifiableList(new ArrayList<>(robots));
        }

        public int getEfficiency() { return efficiency; }
        public int getBonus() { return bonus; }
        public int getActive() { return active; }
        public int getMaxActive() { return maxActive; }
        public int getInStorage() { return inStorage; }
        public Optional<RobotPrice> getType1Price() { return type1Price; }
        public Optional<RobotPrice> getType2Price() { return type2Price; }
        public List<RobotProduction> getRobots() { return robots; }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Productivity)) return false;
            Productivity that = (Productivity) other;
            return efficiency == that.efficiency && bonus == that.bonus
                    && active == that.active && maxActive == that.maxActive
                    && inStorage == that.inStorage && Objects.equals(robots, that.robots)
                    && Objects.equals(type1Price, that.type1Price)
                    && Objects.equals(type2Price, that.type2Price);
        }

        @Override
        public int hashCode() {
            return Objects.hash(efficiency, bonus, active, maxActive, inStorage,
                    type1Price, type2Price, robots);
        }
    }

    final class XenoInfo {
        private final int activeEfficiency;
        private final int activeConsumption;
        private final int activeEnergy;
        private final int inactiveConsumption;

        public XenoInfo(int activeEfficiency, int activeConsumption,
                        int activeEnergy, int inactiveConsumption) {
            this.activeEfficiency = activeEfficiency;
            this.activeConsumption = activeConsumption;
            this.activeEnergy = activeEnergy;
            this.inactiveConsumption = inactiveConsumption;
        }

        public int getActiveEfficiency() { return activeEfficiency; }
        public int getActiveConsumption() { return activeConsumption; }
        public int getActiveEnergy() { return activeEnergy; }
        public int getInactiveConsumption() { return inactiveConsumption; }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof XenoInfo)) return false;
            XenoInfo that = (XenoInfo) other;
            return activeEfficiency == that.activeEfficiency
                    && activeConsumption == that.activeConsumption
                    && activeEnergy == that.activeEnergy
                    && inactiveConsumption == that.inactiveConsumption;
        }

        @Override
        public int hashCode() {
            return Objects.hash(activeEfficiency, activeConsumption, activeEnergy, inactiveConsumption);
        }

        @Override
        public String toString() {
            return "XenoInfo(efficiency=" + activeEfficiency + ", energy=" + activeEnergy + ")";
        }
    }

    final class CollectorInfo {
        private final int production;
        private final long leftTime;
        private final long maxTime;

        public CollectorInfo(int production, long leftTime, long maxTime) {
            this.production = production;
            this.leftTime = leftTime;
            this.maxTime = maxTime;
        }

        public int getProduction() { return production; }
        public long getLeftTime() { return leftTime; }
        public long getMaxTime() { return maxTime; }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof CollectorInfo)) return false;
            CollectorInfo that = (CollectorInfo) other;
            return production == that.production && leftTime == that.leftTime
                    && maxTime == that.maxTime;
        }

        @Override
        public int hashCode() {
            return Objects.hash(production, leftTime, maxTime);
        }
    }

    @SuppressWarnings("PMD.DataClass")
    final class UpgradeCost {
        private final int prometium;
        private final int endurium;
        private final int terbium;
        private final int credits;
        private final int uridiumInstant;
        private final long timeNormal;
        private final long timeLeft;

        public UpgradeCost(int prometium, int endurium, int terbium, int credits,
                           int uridiumInstant, long timeNormal, long timeLeft) {
            this.prometium = prometium;
            this.endurium = endurium;
            this.terbium = terbium;
            this.credits = credits;
            this.uridiumInstant = uridiumInstant;
            this.timeNormal = timeNormal;
            this.timeLeft = timeLeft;
        }

        public int getPrometium() { return prometium; }
        public int getEndurium() { return endurium; }
        public int getTerbium() { return terbium; }
        public int getCredits() { return credits; }
        public int getUridiumInstant() { return uridiumInstant; }
        public long getTimeNormal() { return timeNormal; }
        public long getTimeLeft() { return timeLeft; }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof UpgradeCost)) return false;
            UpgradeCost that = (UpgradeCost) other;
            return prometium == that.prometium && endurium == that.endurium
                    && terbium == that.terbium && credits == that.credits
                    && uridiumInstant == that.uridiumInstant
                    && timeNormal == that.timeNormal && timeLeft == that.timeLeft;
        }

        @Override
        public int hashCode() {
            return Objects.hash(prometium, endurium, terbium, credits,
                    uridiumInstant, timeNormal, timeLeft);
        }
    }

    final class MessageKey {
        private final int enumKey;
        private final String stringKey;

        public MessageKey(int enumKey, String stringKey) {
            this.enumKey = enumKey;
            this.stringKey = stringKey == null ? "" : stringKey;
        }

        public int getEnumKey() { return enumKey; }
        public String getStringKey() { return stringKey; }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof MessageKey)) return false;
            MessageKey that = (MessageKey) other;
            return enumKey == that.enumKey && Objects.equals(stringKey, that.stringKey);
        }

        @Override
        public int hashCode() {
            return Objects.hash(enumKey, stringKey);
        }

        @Override
        public String toString() {
            return "MessageKey(" + enumKey + ", " + stringKey + ")";
        }
    }

    @SuppressWarnings("PMD.DataClass")
    final class Receipt {
        private final int resultId;
        private final int transportPrice;
        private final String construction;
        private final String subaction;
        private final List<MessageKey> successKeys;
        private final List<MessageKey> errorKeys;

        public Receipt(int resultId, int transportPrice, String construction, String subaction,
                       List<MessageKey> successKeys, List<MessageKey> errorKeys) {
            this.resultId = resultId;
            this.transportPrice = transportPrice;
            this.construction = construction == null ? "" : construction;
            this.subaction = subaction == null ? "" : subaction;
            this.successKeys = successKeys == null
                    ? Collections.emptyList()
                    : Collections.unmodifiableList(new ArrayList<>(successKeys));
            this.errorKeys = errorKeys == null
                    ? Collections.emptyList()
                    : Collections.unmodifiableList(new ArrayList<>(errorKeys));
        }

        public int getResultId() { return resultId; }
        public int getTransportPrice() { return transportPrice; }
        public String getConstruction() { return construction; }
        public String getSubaction() { return subaction; }
        public List<MessageKey> getSuccessKeys() { return successKeys; }
        public List<MessageKey> getErrorKeys() { return errorKeys; }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Receipt)) return false;
            Receipt that = (Receipt) other;
            return resultId == that.resultId && transportPrice == that.transportPrice
                    && Objects.equals(construction, that.construction)
                    && Objects.equals(subaction, that.subaction)
                    && Objects.equals(successKeys, that.successKeys)
                    && Objects.equals(errorKeys, that.errorKeys);
        }

        @Override
        public int hashCode() {
            return Objects.hash(resultId, transportPrice, construction, subaction,
                    successKeys, errorKeys);
        }

        @Override
        public String toString() {
            return "Receipt(resultId=" + resultId + ", construction=" + construction
                    + ", errors=" + errorKeys.size() + ")";
        }
    }
}
