package com.kwgdev.brewery.microbeerservice.services.brewing;

/**
 * created by kw on 1/14/2021 @ 5:59 PM
 */
import com.kwgdev.brewery.microbeerservice.config.JmsConfig;
import com.kwgdev.brewery.model.BeerDto;
import com.kwgdev.brewery.model.events.BrewBeerEvent;
import com.kwgdev.brewery.model.events.NewInventoryEvent;
import com.kwgdev.brewery.microbeerservice.domain.Beer;
import com.kwgdev.brewery.microbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jt on 2019-06-24.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BrewBeerListener {

    private final JmsTemplate jmsTemplate;
    private final BeerRepository beerRepository;

    @Transactional
    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent brewBeerEvent){

        BeerDto dto = brewBeerEvent.getBeerDto();

        Beer beer = beerRepository.getOne(dto.getId());
        //Brewing some beer
        dto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(dto);

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }
}
