package myproject1.project1task3;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * Provides methods to fetch various pieces of information about US states,
 * including population data, state flags, maps, nicknames, mottos, and anthems,
 * mainly using web scraping techniques and API requests.
 */
public class StateInfoModel {

    /**
     * Fetches the population of a specified state using the US Census Bureau API.
     * With the help of ChatGPT in parts of the code generation
     *
     * @param state The name of the state for which to fetch the population.
     * @return The population of the state as a string, or a message if data is not available.
     * @throws IOException If there is an error making the HTTP request.
     */
    public String fetchPopulation(String state) throws IOException {
        // Use key and url to fetch state's data
        String apiKey = "9db615badf148d51c76590499fc726a634303ac8";
        String censusCode = StateMapping.getCensusCode(state);
        String apiUrl = "https://api.census.gov/data/2020/dec/pl?get=P1_001N&for=state:" + censusCode + "&key=" + apiKey; // Example URL
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        int responseCode = conn.getResponseCode();

        // Read lines
        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder jsonResponse = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonResponse.append(line);
                }
            }

            // Transform to jason array
            JsonElement jelement = JsonParser.parseString(jsonResponse.toString());
            JsonArray jsonArray = jelement.getAsJsonArray();
            if (!jsonArray.isEmpty() && jsonArray.size() > 1) {
                // The first element of the first array is the population.
                return jsonArray.get(1).getAsJsonArray().get(0).getAsString();
            }
        } else {
            System.out.println("API request failed with response code: " + responseCode);
        }
        return "Population data not available";
    }

    /**
     * Fetches the URL of the state flag for a specified state using web scraping.
     *
     * @param state The name of the state for which to fetch the flag.
     * @return The URL of the state flag, or a message if it is not found.
     * @throws IOException If there is an error fetching the web page.
     */
    public String fetchStateFlag(String state) throws IOException {
        try {
            String baseUrl = "https://en.wikipedia.org/wiki/";
            String completeUrl = baseUrl + state.replace(" ", "_"); // Replace spaces for states with multiple words

            // Fetch the page content
            Document doc = Jsoup.connect(completeUrl).get();
            // Find the infobox image, which usually contains the state flag
            Element infoboxImage = doc.select("table.infobox img").first();
            // Check if it fetches or not
            if (infoboxImage != null) {
                return "https:" + infoboxImage.attr("src");
            } else {
               return "State flag image not found.";
            }
        } catch (Exception e) {
            return "Failed to fetch page: " + e.getMessage();
        }
    }

    /**
     * Fetches the URL of the state map for a specified state using web scraping.
     *
     * @param state The name of the state for which to fetch the map.
     * @return The URL of the state map, or a message if it is not found.
     * @throws IOException If there is an error fetching the web page.
     */
    public String fetchStateMap(String state) throws IOException {
        try {
            String baseUrl = "https://en.wikipedia.org/wiki/";
            String completeUrl = baseUrl + state.replace(" ", "_"); // Replace spaces for states with multiple words

            // Fetch the page content
            Document doc = Jsoup.connect(completeUrl).get();
            // Find the infobox image, which usually contains the state flag
            Elements infoboxImage = doc.select("table.infobox img");
            Element image = infoboxImage.get(2);

            if (image != null) {
                return "https:" + image.attr("src");
            } else {
                return "State flag image not found.";
            }

        } catch (Exception e) {
            return "Failed to fetch state map: " + e.getMessage();
        }
    }

    /**
     * Fetches the nickname of a specified state using web scraping.
     *
     * @param state The name of the state for which to fetch the nickname.
     * @return The nickname of the state, or a message if it is not found.
     * @throws IOException If there is an error fetching the web page.
     */
    public String fetchNickname(String state) throws IOException {
        String baseUrl = "https://en.wikipedia.org/wiki/";
        String completeUrl = baseUrl + state.replace(" ", "_");
        try {
            // Fetch the HTML content from the Wikipedia page
            Document doc = Jsoup.connect(completeUrl).get();

            // Extract the state's nickname
            Element nicknameDiv = doc.selectFirst(".ib-settlement-nickname");
            if (nicknameDiv != null) {
                Elements supTags = nicknameDiv.select("sup");
                for (Element supTag : supTags) {
                    supTag.remove(); // Remove the <sup> tags
                }
            }
            return nicknameDiv != null ? nicknameDiv.text() : "Nickname not found";
        } catch (Exception e) {
            return "Failed to fetch nickname" + e.getMessage();
        }
    }

    /**
     * Fetches the motto of a specified state using web scraping.
     *
     * @param state The name of the state for which to fetch the motto.
     * @return The motto of the state, or a message if it is not found.
     * @throws IOException If there is an error fetching the web page.
     */
    public String fetchMotto(String state) throws IOException {
        StringBuilder outLink = new StringBuilder();
        String baseUrl = "https://en.wikipedia.org/wiki/";
        String completeUrl = baseUrl + state.replace(" ", "_");
        try {
            // Fetch the HTML content from the Wikipedia page
            Document doc = Jsoup.connect(completeUrl).get();

            // Extract the state's nickname
            Elements mottoLinks = doc.select(".mergedrow .ib-settlement-nickname a:not(sup[class=reference])");
            for (Element link : mottoLinks) {
                // Get and print the text of each link
                String text = link.text();
                if (text.equals("Latin")) text = "(Latin)";
                if (text.contains("[")) continue;
                outLink.append(text);
                outLink.append(" ");
            }
            String out = outLink.toString();

            return !out.isEmpty() ? out : "Motto not found";

        } catch (Exception e) {
            return "Failed to fetch motto" + e.getMessage();
        }
    }

    /**
     * Fetches the anthem of a specified state using web scraping.
     *
     * @param state The name of the state for which to fetch the anthem.
     * @return The anthem of the state, or a message if it is not found.
     * @throws IOException If there is an error fetching the web page.
     */
    public String fetchAnthem(String state) throws IOException {
        String baseUrl = "https://en.wikipedia.org/wiki/";
        String completeUrl = baseUrl + state.replace(" ", "_");
        try {
            // Fetch the HTML content from the Wikipedia page
            Document doc = Jsoup.connect(completeUrl).get();

            // Extract the state's nickname
            Elements anthemLinks = doc.select("tr.mergedrow:contains(Anthem:)");

            return anthemLinks != null ? anthemLinks.text() : "Anthem not found";
        } catch (Exception e) {
            return "Failed to fetch motto" + e.getMessage();
        }
    }


}
