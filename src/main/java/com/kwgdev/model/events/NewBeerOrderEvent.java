package com.kwgdev.model.events;

/**
 * created by kw on 1/14/2021 @ 6:04 PM
 */
import com.kwgdev.model.BeerOrderDto;
import org.springframework.context.ApplicationEvent;

public class NewBeerOrderEvent extends ApplicationEvent {

    public NewBeerOrderEvent(BeerOrderDto source) {
        super(source);
    }

    public BeerOrderDto getBeerOrder(){
        return (BeerOrderDto) this.source;
    }
}
