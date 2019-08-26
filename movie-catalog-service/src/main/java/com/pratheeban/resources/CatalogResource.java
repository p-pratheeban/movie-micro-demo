package com.pratheeban.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratheeban.models.CatalogItem;
import com.pratheeban.models.UserRating;
import com.pratheeban.services.MovieInfo;
import com.pratheeban.services.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

	@Autowired
	private MovieInfo movieInfo;

	@Autowired
	private UserRatingInfo userRatingInfo;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		UserRating userRating = userRatingInfo.getUserRating(userId);

		return userRating.getRatings().stream().map(rating -> {
			return movieInfo.getCatalog(rating);
		}).collect(Collectors.toList());

	}

}
/*
 * @Autowired WebClient.Builder webClientBuilder;
 * 
 * Alternative WebClient way Movie movie =
 * webClientBuilder.build().get().uri("http://localhost:8082/movies/"+
 * rating.getMovieId()) .retrieve().bodyToMono(Movie.class).block();
 */
