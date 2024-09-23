package tibo.spring.commandmodule.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient<T> {
    private final String urlApi;
    private final RestTemplate template;
    private final HttpHeaders headers;

    public RestClient(String urlApi) {
        this.urlApi = urlApi;
        template = new RestTemplate();
        headers = new HttpHeaders();
        headers.add("accept", "*/*");
        headers.add("content-type", "application/json");
    }

    public T getRequest(Class<T> clazz) {
        HttpEntity<String> request = new HttpEntity<>("", headers);
        ResponseEntity<T> response = template.exchange(urlApi, HttpMethod.GET, request, clazz);
        if (response.hasBody()) {
            return response.getBody();
        }
        return null;
    }
}
