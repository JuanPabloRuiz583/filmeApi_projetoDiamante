package br.com.fiap.FilmeApi.services;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class TmdbService {

    private final WebClient webClient;
    private final String apiKey;
    private final String imageBase;

    public TmdbService(@Value("${tmdb.api-key}") String apiKey,
                       @Value("${tmdb.image-base}") String imageBase,
                       WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.themoviedb.org/3").build();
        this.apiKey = apiKey;
        this.imageBase = imageBase;
    }

    public String buscarCapa(String titulo, Integer ano) {
        try {
            String query = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
            String url = "/search/movie?api_key=" + apiKey + "&query=" + query;
            // busca so pelo nome

            Mono<Map> respMono = webClient.get().uri(url).retrieve().bodyToMono(Map.class);
            Map resp = respMono.block();
            if (resp == null) return null;

            Object resultsObj = resp.get("results");
            if (!(resultsObj instanceof List)) return null;

            List results = (List) resultsObj;
            if (results.isEmpty()) return null;

            Map first = (Map) results.get(0);
            String posterPath = (String) first.get("poster_path");
            if (posterPath == null) return null;

            return imageBase + posterPath;

        } catch (Exception e) {
            System.out.println("Erro ao buscar capa TMDB: " + e.getMessage());
            return null;
        }
    }
}

