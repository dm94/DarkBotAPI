package eu.darkbot.api.managers;

import eu.darkbot.api.API;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Provides Inventory Data
 */

public interface InventoryAPI extends API.Singleton {
    /**
     * @return list of items in inventory
     */
    List<? extends Item> getItems(int minWaitMs);


    interface Item {
        /**
         * @return item loot id
         */
        String getLootId();

        /**
         * @return a readable anme
         */
        String getName();

        /**
         * @return amount of item
         */
        double getAmount();

        /**
         * @return decoded packet attributes, or an empty map when the item has none
         */
        default Map<Integer, String> getAttributes() {
            return Collections.emptyMap();
        }

        /**
         * @param key numeric attribute key from the inventory packet
         * @return the raw attribute value, or {@code null} when it was not announced
         */
        default String getAttribute(int key) {
            return getAttributes().get(key);
        }
    }
}
