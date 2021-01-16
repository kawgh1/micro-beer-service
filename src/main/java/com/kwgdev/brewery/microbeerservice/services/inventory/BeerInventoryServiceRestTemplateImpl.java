package com.kwgdev.brewery.microbeerservice.services.inventory;

/**
 * created by kw on 1/14/2021 @ 6:00 PM
 */
import com.kwgdev.brewery.microbeerservice.services.inventory.model.BeerInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by jt on 2019-06-07.
 */
@Slf4j
@ConfigurationProperties(prefix = "com.kwgdev.brewery", ignoreUnknownFields = true)
@Component
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService {

    private final String INVENTORY_PATH = "/api/v1/beer/{beerId}/inventory";
    private final RestTemplate restTemplate;

    private String beerInventoryServiceHost;

    public void setBeerInventoryServiceHost(String beerInventoryServiceHost) {
        this.beerInventoryServiceHost = beerInventoryServiceHost;
    }

//    public BeerInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder.build();
//    }

    public BeerInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder,
                                        @Value("${com.kwgdev.brewery.inventory-user}") String inventoryUser,
                                        @Value("${com.kwgdev.brewery.inventory-password}")String inventoryPassword) {
        this.restTemplate = restTemplateBuilder
                .basicAuthentication(inventoryUser, inventoryPassword)
                .build();
    }

    @Override
    public Integer getOnhandInventory(UUID beerId) {

        log.debug("Calling Inventory Service");
        //System.out.println(beerInventoryServiceHost + INVENTORY_PATH);

        // this part binds the beer object into the URL {beerId} of "/api/v1/beer/{beerId}/inventory"
        ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate
                .exchange(beerInventoryServiceHost + INVENTORY_PATH + "?showInventoryOnHand=true", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<BeerInventoryDto>>(){}, (Object) beerId);

        //sum from inventory list
        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                // stream all the beer
                .stream()
                // convert to integer of quantity on hand
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                // add that all together
                .sum();

        return onHand;
    }
}
