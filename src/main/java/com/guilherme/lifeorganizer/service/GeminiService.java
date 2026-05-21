package com.guilherme.lifeorganizer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private static final String GEMINI_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=";

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public String analyze(String prompt) {
        try {
            String requestBody = """
                    {
                        "contents": [
                            {
                                "parts": [
                                    {
                                        "text": "%s"
                                    }
                                ]
                            }
                        ],
                        "generationConfig": {
                            "temperature": 0.7,
                            "maxOutputTokens": 2048
                        }
                    }
                    """.formatted(prompt.replace("\"", "'").replace("\n", " "));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(GEMINI_URL + apiKey))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return extractText(response.body());

        } catch (Exception e) {
            System.out.println("=== GEMINI ERROR ===");
            e.printStackTrace();
            return "Sorry, I was unable to process your request at this time.";
        }
    }

    private String extractText(String responseBody) {
        try {
            if (responseBody.contains("\"error\"")) {
                return "AI service temporarily unavailable. Please try again later.";
            }

            int start = responseBody.indexOf("\"text\": \"") + 9;
            if (start <= 8) {
                return "Could not extract response from Gemini.";
            }

            // Encontra o fechamento correto da string de texto
            int end = start;
            while (end < responseBody.length()) {
                if (responseBody.charAt(end) == '"' &&
                        responseBody.charAt(end - 1) != '\\') {
                    break;
                }
                end++;
            }

            return responseBody.substring(start, end)
                    .replace("\\n", "\n")
                    .replace("\\\"", "\"")
                    .replace("\\\\", "\\");

        } catch (Exception e) {
            return "Error processing Gemini response.";
        }
    }
}