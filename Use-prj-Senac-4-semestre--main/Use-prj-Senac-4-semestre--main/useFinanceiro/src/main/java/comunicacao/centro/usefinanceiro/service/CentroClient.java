package comunicacao.centro.usefinanceiro.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CentroClient {
    private final RestClient client;

    public CentroClient(@Value("${use.cadastros.url}") String url) {
        var factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(2000);
        factory.setReadTimeout(3000);
        this.client = RestClient.builder()
                .baseUrl(url)
                .requestFactory(factory)
                .build();
    }

    public void validarCentro(String centroId) {
        try {
            client.get()
                    .uri("/centros/{idCentro}", centroId)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.NotFound erro) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Centro não encontrado.", erro);
        } catch (RestClientException erro) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                    "Serviço de cadastros indisponível.", erro);
        }
    }
}
