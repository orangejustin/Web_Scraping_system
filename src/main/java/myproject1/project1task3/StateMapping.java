package myproject1.project1task3;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides a mapping from US state names to their corresponding census codes.
 * This utility class facilitates the retrieval of census codes for states, which
 * are used in various API requests to fetch state-specific data.
 */
public class StateMapping {
    // A static HashMap to hold the mapping between state names and their census codes.
    private static final Map<String, String> stateToFullName = new HashMap<>();

    static {
        stateToFullName.put("Alabama", "01");
        stateToFullName.put("Alaska", "02");
        stateToFullName.put("Arizona", "04");
        stateToFullName.put("Arkansas", "05");
        stateToFullName.put("California", "06");
        stateToFullName.put("Colorado", "08");
        stateToFullName.put("Connecticut", "09");
        stateToFullName.put("Delaware", "10");
        stateToFullName.put("Florida", "12");
        stateToFullName.put("Georgia", "13");
        stateToFullName.put("Hawaii", "15");
        stateToFullName.put("Idaho", "16");
        stateToFullName.put("Illinois", "17");
        stateToFullName.put("Indiana", "18");
        stateToFullName.put("Iowa", "19");
        stateToFullName.put("Kansas", "20");
        stateToFullName.put("Kentucky", "21");
        stateToFullName.put("Louisiana", "22");
        stateToFullName.put("Maine", "23");
        stateToFullName.put("Maryland", "24");
        stateToFullName.put("Massachusetts", "25");
        stateToFullName.put("Michigan", "26");
        stateToFullName.put("Minnesota", "27");
        stateToFullName.put("Mississippi", "28");
        stateToFullName.put("Missouri", "29");
        stateToFullName.put("Montana", "30");
        stateToFullName.put("Nebraska", "31");
        stateToFullName.put("Nevada", "32");
        stateToFullName.put("New Hampshire", "33");
        stateToFullName.put("New Jersey", "34");
        stateToFullName.put("New Mexico", "35");
        stateToFullName.put("New York", "36");
        stateToFullName.put("North Carolina", "37");
        stateToFullName.put("North Dakota", "38");
        stateToFullName.put("Ohio", "39");
        stateToFullName.put("Oklahoma", "40");
        stateToFullName.put("Oregon", "41");
        stateToFullName.put("Pennsylvania", "42");
        stateToFullName.put("Rhode Island", "44");
        stateToFullName.put("South Carolina", "45");
        stateToFullName.put("South Dakota", "46");
        stateToFullName.put("Tennessee", "47");
        stateToFullName.put("Texas", "48");
        stateToFullName.put("Utah", "49");
        stateToFullName.put("Vermont", "50");
        stateToFullName.put("Virginia", "51");
        stateToFullName.put("Washington", "53");
        stateToFullName.put("West Virginia", "54");
        stateToFullName.put("Wisconsin", "55");
        stateToFullName.put("Wyoming", "56");
    }

    /**
     * Retrieves the census code for a given state name.
     *
     * @param stateFullName The full name of the state for which the census code is to be retrieved.
     * @return The census code corresponding to the state name provided, or "Unknown" if the state is not found.
     */
    public static String getCensusCode(String stateFullName) {
        return stateToFullName.getOrDefault(stateFullName, "Unknown");
    }
}
