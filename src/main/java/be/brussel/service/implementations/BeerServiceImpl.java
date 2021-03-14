package be.brussel.service.implementations;

import be.brussel.datapersistence.BeersDAO;
import be.brussel.model.Beer;
import be.brussel.service.BeerService;


import java.util.List;

public class BeerServiceImpl implements BeerService {

    private BeersDAO beersDAO = new BeersDAO();

    @Override
    public List<Beer> getBeers() {
        return beersDAO.getBeers();
    }

    @Override
    public List<Beer> getBeers(int alcoholConsumed) {
        return beersDAO.getBeers(alcoholConsumed);
    }
}
