package com.example.demo2;


import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/movies")
public class MovieController {
	private final MovieRepository movieRepository;
	public MovieController(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	//method implementations with walkthroughs below
	
	@PutMapping
	List<MovieEntity> createOrUpdateMovie(@RequestBody MovieEntity newMovie) {
		return (List<MovieEntity>) movieRepository.save(newMovie);
	}
	
	@GetMapping(value = { "", "/" }, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	List<MovieEntity> getMovies() {
		return (List<MovieEntity>) movieRepository.findAll();
	}
	
	@GetMapping("/by-title")
	List<MovieEntity> byTitle(@RequestParam String title) {
		return movieRepository.findOneByTitle(title);
	}
	
	@DeleteMapping("/{id}")
	List<Void> delete(@PathVariable String id) {
		return (List<Void>) movieRepository.deleteById(id);
	}
}