package com.training.MyBoutique_20.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.MyBoutique_20.dto.CategoryDto;
import com.training.MyBoutique_20.persistence.Category;
import com.training.MyBoutique_20.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CategoryService {
	
	@Resource
	private CategoryRepository categoryRepository;
	
	public List<CategoryDto> findAll() {
		log.debug("request to find all categories");
		return this.categoryRepository.findAll().stream().map(CategoryService::mapToDto).collect(Collectors.toList());
		
	}
	
	@Transactional(readOnly = true)
	public CategoryDto findCategoryById(Long id) {
		log.debug("request to find category:,{}",id);
		return this.categoryRepository.findById(id).map(CategoryService::mapToDto).
		orElseThrow(()->new IllegalStateException("cathegorie not found"));
		
		
	}
	
	public void delete (Long id) {
		log.debug("request to delete category: {}",id);
		this.categoryRepository.findById(id).orElseThrow(()->new IllegalStateException("category don't exist"));
		this.categoryRepository.deleteById(id);
	}
	
	public CategoryDto create (CategoryDto categoryDto) {
		log.debug("request to create category:{}",categoryDto);
		return mapToDto(
				this.categoryRepository.save(
				new Category(
				categoryDto.getName(),
				categoryDto.getDescription(),
				Collections.emptySet()
				))
				);
	}
	
	public static CategoryDto mapToDto(Category category){
		if(category!=null) {
			return new CategoryDto(category.getId(),
					category.getName(),
					category.getDescription(),
					category.getSetProduct().stream().map(ProductService::mapToDto).collect(Collectors.toSet())
			);
					
		} return null;
		
	}

}
