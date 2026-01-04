import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Rest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            // Runtime input
            System.out.print("Enter Latitude  : ");
            String latitude = sc.nextLine();

            System.out.print("Enter Longitude : ");
            String longitude = sc.nextLine();

            // REST API URL
            String apiUrl =
                "https://api.open-meteo.com/v1/forecast?latitude=" + latitude +
                "&longitude=" + longitude +
                "&current_weather=true";

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            // Manual JSON parsing (no library)
            String temp = json.split("\"temperature\":")[1].split(",")[0];
            String wind = json.split("\"windspeed\":")[1].split(",")[0];
            String time = json.split("\"time\":\"")[1].split("\"")[0];

            // Structured Output
            System.out.println("\n----- WEATHER REPORT -----");
            System.out.println("Latitude    : " + latitude);
            System.out.println("Longitude   : " + longitude);
            System.out.println("Temperature : " + temp + " °C");
            System.out.println("Wind Speed  : " + wind + " km/h");
            System.out.println("Time        : " + time);
            System.out.println("--------------------------");

        } catch (java.lang.Exception e) {
            System.out.println("Error occurred while fetching weather data.");
        }

        sc.close();
    }
}
