package com.moviedatabase.movielibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("")
    /**
     * Get all movies
     * @return List of movies
     */
    public List<Movie> getAll() {
        return movieRepository.getAll();
    }

    @GetMapping("/{id}")
    /**
     * Get movie by id
     * @param id Movie id
     * @return Movie
     */
    public Movie getById(@PathVariable("id") int id) {
        return movieRepository.getById(id);
    }

    /**
     * Add movies
     * @param movies
     */
    @PostMapping("")
    public int add(@RequestBody List<Movie> movies) {
        return movieRepository.save(movies);
    }

    /**
     * Update movie
     * @param movie
     */
    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Movie updatedMovie) {
        Movie movie = movieRepository.getById(id);
        if(movie!=null){
            movie.setName(updatedMovie.getName());
            movie.setRating(updatedMovie.getRating());
            movieRepository.update(movie);
            return 1;
        }
        else{
            return -1;
        }
    }
    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Movie updatedMovie) {
        Movie movie = movieRepository.getById(id);
        if(movie!=null){
            if(updatedMovie.getName()!=null){
                movie.setName(updatedMovie.getName());
            }
            if(updatedMovie.getRating()>0){
                movie.setRating(updatedMovie.getRating());
            }
            movieRepository.update(movie);
            return 1;
        }
        else{
            return -1;
        }
    }
    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return movieRepository.delete(id);
    }
}
