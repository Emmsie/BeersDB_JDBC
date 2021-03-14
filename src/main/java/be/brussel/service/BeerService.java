package be.brussel.service;

import be.brussel.model.Beer;

import java.util.List;

public interface BeerService {
    List<Beer> getBeers();
    List<Beer> getBeers(int alcoholConsumed);
}
