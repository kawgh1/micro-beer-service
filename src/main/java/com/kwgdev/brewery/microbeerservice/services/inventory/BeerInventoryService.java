package com.kwgdev.brewery.microbeerservice.services.inventory;

import java.util.UUID;

/**
 * created by kw on 1/14/2021 @ 6:00 PM
 */
public interface BeerInventoryService {

    Integer getOnhandInventory(UUID beerId);
}
