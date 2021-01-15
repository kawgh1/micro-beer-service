package com.kwgdev.model.events;

/**
 * created by kw on 1/14/2021 @ 6:04 PM
 */
import com.kwgdev.model.BeerDto;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent implements Serializable {

    static final long serialVersionUID = 5294557463904704401L;

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }

}
