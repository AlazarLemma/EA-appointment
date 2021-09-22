package edu.miu.group3.appointment.system.service;
import edu.miu.group3.appointment.system.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category getCategory(Long categoryId);
    Category updateCategory(Long categoryId, Category category);
    void deleteCategory(Long categoryId);
}
