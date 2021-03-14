package be.brussel.util;

import be.brussel.model.Brewer;

import java.util.List;

public class BrewerValidator {

    public static boolean brewerAlreadyInDB(Brewer brewer, List<Brewer> brewers){
        for ( Brewer brew : brewers) {
            if(brew.getName().equals(brewer.getName())){
                return true;
            }
        }
        return false;
    }

    public static boolean brewerNameEmpty(Brewer brewer){
        if(brewer.getName().equals("")){
            return true;
        }
        return false;
    }
}
