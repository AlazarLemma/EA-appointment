package edu.miu.group3.appointment.system.service;

import edu.miu.group3.appointment.system.domain.Category;
import edu.miu.group3.appointment.system.repository.CategoryRepository;
import edu.miu.group3.appointment.system.service.exception.CategoryNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    };

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    };

    public Category getCategory(Long categoryId){
        if(!categoryRepository.existsById(categoryId)){
            throw new CategoryNotFoundException("Category with Id " + categoryId + " does not exist");
        }
        return categoryRepository.findById(categoryId).get();
    };

    public Category updateCategory(Long categoryId, Category category){
        if(!categoryRepository.existsById(categoryId)){
            throw new CategoryNotFoundException("Category with Id " + categoryId + " does not exist");
        }
        Optional<Category> currentCategory = categoryRepository.findById(categoryId);
        Optional<Category> result = currentCategory.map(c -> {
            c.setTitle(category.getTitle());
            c.setStartTime(category.getStartTime());
            c.setEndTime(category.getEndTime());
            return categoryRepository.save(c);
        });

        return result.get();
    };

    public void deleteCategory(Long categoryId){
        if(!categoryRepository.existsById(categoryId)){
            throw new CategoryNotFoundException("Category with Id " + categoryId + " does not exist");
        }
        categoryRepository.deleteById(categoryId);
    };
}
