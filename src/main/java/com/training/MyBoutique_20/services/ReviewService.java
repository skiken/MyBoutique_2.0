package com.training.MyBoutique_20.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.MyBoutique_20.dto.ReviewDto;
import com.training.MyBoutique_20.persistence.Review;
import com.training.MyBoutique_20.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReviewService {

	@Resource
	private ReviewRepository reviewRepository;

	public List<ReviewDto> findAll() {
		log.debug("Request to get all Reviews");
		return this.reviewRepository.findAll().stream().map(ReviewService::mapToDto).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ReviewDto findById(Long id) {
		log.debug("Request to get Review : {}", id);
		return this.reviewRepository.findById(id).map(ReviewService::mapToDto).orElse(null);
	}

	public ReviewDto create(ReviewDto reviewDto) {
		log.debug("Request to create Review : {}", reviewDto);
		return mapToDto(this.reviewRepository
				.save(new Review(
						reviewDto.getRating(),
						reviewDto.getText(),
						null
						)));
	}

	public void delete(Long id) {
		log.debug("Request to delete Review : {}", id);
	}

	public static ReviewDto mapToDto(Review review) {
		if (review != null) {
			return new ReviewDto(review.getId(), review.getRating(), review.getText());
		}
		return null;
	}

}
