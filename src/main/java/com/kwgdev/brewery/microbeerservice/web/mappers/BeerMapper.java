package com.kwgdev.brewery.microbeerservice.web.mappers;

/**
 * created by kw on 1/14/2021 @ 6:02 PM
 */
import com.kwgdev.brewery.microbeerservice.domain.Beer;
import com.kwgdev.brewery.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    BeerDto beerToBeerDtoWithInventory(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
