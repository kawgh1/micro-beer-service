package com.kwgdev.brewery.microbeerservice.services.order;

/**
 * created by kw on 1/14/2021 @ 6:15 PM
 */
import com.kwgdev.brewery.microbeerservice.config.JmsConfig;
import com.kwgdev.brewery.model.events.BeerOrderValidationResult;
import com.kwgdev.brewery.model.events.ValidateBeerOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 2019-09-08.
 */
@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private final BeerOrderValidator beerOrderValidator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateBeerOrderRequest event){

        Boolean orderIsValid = beerOrderValidator.validateOrder(event.getBeerOrder());

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESULT_QUEUE, BeerOrderValidationResult.builder()
                .beerOrderId(event.getBeerOrder().getId())
                .isValid(orderIsValid)
                .build());
    }
}
