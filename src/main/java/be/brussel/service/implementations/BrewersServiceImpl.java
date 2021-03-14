package be.brussel.service.implementations;

import be.brussel.datapersistence.BrewerDAO;
import be.brussel.model.Brewer;
import be.brussel.service.BrewersService;
import be.brussel.service.data.Valuta;
import be.brussel.service.exception.BrewerValidationException;
import be.brussel.service.exception.ValidationException;
import be.brussel.util.BrewerValidator;

import java.util.List;

public class BrewersServiceImpl implements BrewersService {

    private BrewerDAO brewerDAO = new BrewerDAO();

    @Override
    public List<Brewer> getBrewers() {

        return brewerDAO.getBrewers();
    }

    @Override
    public List<Brewer> getBrewers(Valuta valuta) {

        return brewerDAO.getBrewers(valuta);
    }

    @Override
    public List<Brewer> getBrewers(String nameFilter) {

        return brewerDAO.getBrewers(nameFilter);
    }

    @Override
    public List<Brewer> getBrewers(String nameFilter, Valuta valuta) {

        return brewerDAO.getBrewers(nameFilter, valuta);
    }

    @Override
    public Brewer createBrewer(Brewer brewer) throws ValidationException {

        List<Brewer> brewers = getBrewers();

        if(brewer.getName().equals("")){
            throw new BrewerValidationException("Name can not be empty");
        }
        if(BrewerValidator.brewerAlreadyInDB(brewer, brewers)){
            throw new BrewerValidationException("This brewer already exist in the database");
        }

        return brewerDAO.createBrewer(brewer);
    }

    @Override
    public Brewer updateBrewer(Brewer brewer) throws ValidationException {

        List<Brewer> brewers = getBrewers();

        if(BrewerValidator.brewerNameEmpty(brewer)){
            throw new BrewerValidationException("Name can not be empty");
        }
        if(BrewerValidator.brewerAlreadyInDB(brewer, brewers)){
            throw new BrewerValidationException("This brewername already exist in the database");
        }

        return brewerDAO.updateBrewer(brewer);
    }

    @Override
    public boolean deleteBrewerById(Integer id) {
        return brewerDAO.deleteBrewerById(id);
    }
}
