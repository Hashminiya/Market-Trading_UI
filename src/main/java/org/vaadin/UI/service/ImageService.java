package org.vaadin.UI.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ImageService {
    private static final String UNSPLASH_API_URL = "https://api.unsplash.com/search/photos";
    private static final String ACCESS_KEY = "Jx_Vupqm8X2tRzqrR9BMgSMc7NYR0PHr8QiBRMpHJ_A";

    public static String getImageUrl(String query) {
        // Remove spaces from the query
        query = query.replace(" ", "%20");
        String apiUrl = UNSPLASH_API_URL + "?query=" + query + "&client_id=" + ACCESS_KEY;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");

            InputStream responseStream = connection.getInputStream();
            String responseBody = new Scanner(responseStream, "UTF-8").useDelimiter("\\A").next();
            responseStream.close();

            JSONObject jsonResponse = new JSONObject(responseBody);
            JSONArray results = jsonResponse.getJSONArray("results");

            if (results.length() > 0) {
                JSONObject firstResult = results.getJSONObject(0);
                return firstResult.getJSONObject("urls").getString("regular");
            } else {
                // Fallback to a default image search if no results are found
                return getDefaultImageUrl();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Return a placeholder image if there's an error with the request
            return "https://via.placeholder.com/200";
        }
    }

    private static String getDefaultImageUrl() {
        String defaultQuery = "item";
        String defaultApiUrl = UNSPLASH_API_URL + "?query=" + defaultQuery + "&client_id=" + ACCESS_KEY;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(defaultApiUrl).openConnection();
            connection.setRequestMethod("GET");

            InputStream responseStream = connection.getInputStream();
            String responseBody = new Scanner(responseStream, "UTF-8").useDelimiter("\\A").next();
            responseStream.close();

            JSONObject jsonResponse = new JSONObject(responseBody);
            JSONArray results = jsonResponse.getJSONArray("results");

            if (results.length() > 0) {
                JSONObject firstResult = results.getJSONObject(0);
                return firstResult.getJSONObject("urls").getString("regular");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "https://via.placeholder.com/200";
    }
}
