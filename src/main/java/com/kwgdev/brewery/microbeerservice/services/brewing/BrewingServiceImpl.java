package com.kwgdev.brewery.microbeerservice.services.brewing;

/**
 * created by kw on 1/14/2021 @ 6:00 PM
 */
import com.kwgdev.brewery.microbeerservice.config.JmsConfig;
import com.kwgdev.brewery.microbeerservice.web.mappers.BeerMapper;
import com.kwgdev.brewery.model.events.BrewBeerEvent;
import com.kwgdev.brewery.microbeerservice.domain.Beer;
import com.kwgdev.brewery.microbeerservice.repositories.BeerRepository;
import com.kwgdev.brewery.microbeerservice.services.inventory.BeerInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by jt on 2019-06-23.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingServiceImpl implements BrewingService {

    private final BeerInventoryService beerInventoryService;
    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Override
    @Transactional
    @Scheduled(fixedRate = 5000) //run every 5 seconds
    public void checkForLowInventory() {
        log.debug("Checking Beer Inventory");

        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {

            Integer invQoh = beerInventoryService.getOnhandInventory(beer.getId());

            if(beer.getMinOnHand() >= invQoh ) {
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE,
                        new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }
}
