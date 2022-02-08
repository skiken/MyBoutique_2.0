package com.training.MyBoutique_20.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.MyBoutique_20.dto.CategoryDto;
import com.training.MyBoutique_20.services.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/"+"/categories")
public class CategoryEndpoint {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/create")
    public CategoryDto create(@RequestBody CategoryDto categoryDto) {
    	return categoryService.create(categoryDto);
    }
	
	@GetMapping
	public List<CategoryDto> findAll(){
		return categoryService.findAll();
	}
	
	@GetMapping("/id/{id}")
	public CategoryDto findById(@PathVariable Long id) {
		return categoryService.findCategoryById(id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		this.categoryService.delete(id);
	}

}
