package guru.sfg.beer.order.service.services.MSSCbeerService;

import guru.sfg.beer.order.service.services.MSSCbeerService.model.BeerDto;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.Optional;
import java.util.UUID;


@Slf4j
@Component
public class MSSCBeerServiceRestTemplateImpl implements MSSCBeerServiceService {

    private final String BEER_PATH = "/api/v1/";
    private final RestTemplate restTemplate; //per fare chimate HTTP ad altro microservizio
    @Value("${sfg.brewery.beer-service-host}")
    @Setter
    private String MSSCBeerServiceHost; // = http://localhost:8080


    public MSSCBeerServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder, @Value("${sfg.brewery.beer-service-host}") String MSSCBeerServiceHost) {
        this.restTemplate = restTemplateBuilder.build();
        this.MSSCBeerServiceHost = MSSCBeerServiceHost;
    }

    @Override
    public Optional<BeerDto> getBeerById(UUID beerId) {
        return Optional.ofNullable(restTemplate.getForObject(MSSCBeerServiceHost + BEER_PATH + "beer/" + beerId.toString(), BeerDto.class));
    }

    @Override
    public Optional<BeerDto> getBeerByUpc(String upc){
        log.debug("Calling MSSCBeerService");

        //Chiamata http get
        return Optional.ofNullable(restTemplate.
                getForObject(MSSCBeerServiceHost + BEER_PATH + "beerUpc/" + upc, BeerDto.class));
    }
}
