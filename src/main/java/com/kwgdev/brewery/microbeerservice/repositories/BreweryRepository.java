package com.kwgdev.brewery.microbeerservice.repositories;

/**
 * created by kw on 1/14/2021 @ 5:59 PM
 */
import com.kwgdev.brewery.microbeerservice.domain.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by jt on 2019-01-26.
 */
public interface BreweryRepository extends JpaRepository<Brewery, UUID> {
}
