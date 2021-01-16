package com.kwgdev.brewery.microbeerservice.services.inventory;

/**
 * created by kw on 1/14/2021 @ 6:06 PM
 */
import com.kwgdev.brewery.microbeerservice.bootstrap.DefaultBreweryLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getOnhandInventory() {
        Integer qoh = beerInventoryService.getOnhandInventory(DefaultBreweryLoader.BEER_1_UUID);

        System.out.println(qoh);

    }
}
