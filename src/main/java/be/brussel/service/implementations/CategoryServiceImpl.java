package be.brussel.service.implementations;

import be.brussel.datapersistence.CategoryDAO;
import be.brussel.model.Category;
import be.brussel.service.CategoryService;
import be.brussel.service.exception.CategoryValidationException;
import be.brussel.service.exception.ValidationException;
import be.brussel.util.CategoryValidator;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    public List<Category> getCategories() {
        return categoryDAO.getCategories();
    }

    @Override
    public Category createCategory(Category category) throws ValidationException {
        List<Category> categories = getCategories();

        if(CategoryValidator.categoryNameEmpty(category)){
            throw new CategoryValidationException("Categoryname can not be empty");
        }
        if(CategoryValidator.categoryAlreadyInDB(category, categories)){
            throw new CategoryValidationException("This categoryname already exists in the database");
        }

        return categoryDAO.createCategory(category);
    }

    @Override
    public Category updateCategory(Category category) {

        return categoryDAO.updateCategory(category);
    }

    @Override
    public boolean deleteCategory(Category category) {

        return categoryDAO.deleteCategory(category);
    }


}
