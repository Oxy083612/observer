package edu.io.logic;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;


public class HttpDataSource implements DataSource {

    String endpoint = "https://www.goldapi.io/api/XPT/USD";
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public HttpDataSource(){}

    @Override
    public DataPack getData(){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .header("x-access-token", "goldapi-104q5usmh84wd7p-io")
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> httpResponse =  httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(httpResponse.body());

            double price = ((Number) data.get("price")).doubleValue();
            long timestamp = ((Number) data.get("timestamp")).longValue();

            return new DataPack(price, timestamp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
