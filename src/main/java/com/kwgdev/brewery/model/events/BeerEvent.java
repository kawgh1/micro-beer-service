package com.kwgdev.brewery.model.events;

/**
 * created by kw on 1/14/2021 @ 6:03 PM
 */
import com.kwgdev.brewery.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jt on 2019-06-24.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerEvent {

    private BeerDto beerDto;
}
