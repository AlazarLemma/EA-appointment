package edu.miu.group3.appointment.system.controller;

import edu.miu.group3.appointment.system.domain.Category;
import edu.miu.group3.appointment.system.service.CategoryAdapterService;
import edu.miu.group3.appointment.system.service.CategoryService;

import edu.miu.group3.appointment.system.service.dto.CategoriesDto;
import edu.miu.group3.appointment.system.service.dto.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path= "api/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    private CategoryAdapterService adapterService;

    @GetMapping
    public ResponseEntity<CategoriesDto> getAllCategories(){
        List<Category> result = categoryService.getAllCategories();
        CategoriesDto dto = adapterService.fromCategoriesToDto(result);

        return ResponseEntity.ok(dto);
    }

    @GetMapping(path = "{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable Long categoryId){
        Category result = categoryService.getCategory(categoryId);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto dto){
        Category category = adapterService.fromDto(dto);
        Category result = categoryService.addCategory(category);


        return new ResponseEntity<CategoryDto>(adapterService.fromCategory(result), HttpStatus.CREATED);
    }

    @PutMapping(path = "{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Long categoryId, @Valid @RequestBody Category category){
        Category result = categoryService.updateCategory(categoryId, category);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
    }
}
