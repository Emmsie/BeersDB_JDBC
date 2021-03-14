package be.brussel.util;

import be.brussel.model.Category;

import java.util.List;

public class CategoryValidator {

    public static boolean categoryAlreadyInDB(Category category, List<Category> categories){
        for ( Category cat : categories) {
            if(cat.getCategoryName().equals(category.getCategoryName())){
                return true;
            }
        }
        return false;
    }

    public static boolean categoryNameEmpty(Category category){
        if(category.getCategoryName().equals("")){
                return true;
        }
        return false;
    }


}
