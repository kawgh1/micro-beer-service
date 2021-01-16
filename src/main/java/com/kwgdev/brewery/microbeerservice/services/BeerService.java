package com.kwgdev.brewery.microbeerservice.services;

/**
 * created by kw on 1/14/2021 @ 5:59 PM
 */
import com.kwgdev.brewery.model.BeerDto;
import com.kwgdev.brewery.model.BeerPagedList;
import com.kwgdev.brewery.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    BeerDto findBeerById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto saveBeer(BeerDto beerDto);

    void updateBeer(UUID beerId, BeerDto beerDto);

    void deleteById(UUID beerId);

    BeerDto findBeerByUpc(String upc);
}
