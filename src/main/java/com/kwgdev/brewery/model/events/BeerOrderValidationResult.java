package com.kwgdev.brewery.model.events;

/**
 * created by kw on 1/14/2021 @ 6:03 PM
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by jt on 2019-09-08.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerOrderValidationResult {
    private Boolean isValid;
    private UUID beerOrderId;
}
