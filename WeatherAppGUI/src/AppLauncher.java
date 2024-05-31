import java.util.Scanner;
import org.json.simple.JSONObject;

public class AppLauncher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Weather App");
        System.out.println("-----------");

        while (true) {
            System.out.print("Enter a location (or type 'exit' to quit): ");
            String location = scanner.nextLine();

            if (location.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the weather app.");
                break;
            }

            // Retrieve weather data for the given location
            JSONObject weatherData = WeatherApp.getWeatherData(location);

            if (weatherData != null) {
                // Display weather data
                double temperature = (double) weatherData.get("temperature");
                String weatherCondition = (String) weatherData.get("weather_condition");
                long humidity = (long) weatherData.get("humidity");
                double windspeed = (double) weatherData.get("windspeed");

                System.out.println("Weather data for " + location + ":");
                System.out.println("Temperature: " + temperature + " C");
                System.out.println("Condition: " + weatherCondition);
                System.out.println("Humidity: " + humidity + "%");
                System.out.println("Windspeed: " + windspeed + " km/h");
            } else {
                System.out.println("Error: Could not retrieve weather data for " + location);
            }
        }

        scanner.close();
    }
}
