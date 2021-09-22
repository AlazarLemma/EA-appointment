package edu.miu.group3.appointment.system.service;

import edu.miu.group3.appointment.system.domain.Category;
import edu.miu.group3.appointment.system.service.dto.CategoriesDto;
import edu.miu.group3.appointment.system.service.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryAdapterService {
    public CategoryDto fromCategory(Category category) {
        return new CategoryDto(category.getId(), category.getTitle(), category.getDefaultSessionLength());
    }

    public Category fromDto(CategoryDto dto) {
        return new Category(dto.getTitle(), dto.getDefaultSessionLength());
    }

    public CategoriesDto fromCategoriesToDto(List<Category> categories) {
        List<CategoryDto> dtos = categories.stream().map(category -> fromCategory(category)).collect(Collectors.toList());
        return new CategoriesDto(dtos);
    }

}