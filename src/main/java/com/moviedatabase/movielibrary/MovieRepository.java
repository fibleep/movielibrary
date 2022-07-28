package com.moviedatabase.movielibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Get all movies
     * @return List of movies
     */
    public List<Movie> getAll(){
       return jdbcTemplate.query("select id, name, rating from movie", BeanPropertyRowMapper.newInstance(Movie.class));
    }

    /**
     * Get movie by id
     * @param id Movie id
     * @return Movie
     */
    public Movie getById(int id){
        return jdbcTemplate.queryForObject("select id, name, rating from movie where id=?",BeanPropertyRowMapper.newInstance(Movie.class),id);
    }

    /**
     * Add movies
     * @param movies
     * @return
     */
    public int save(List<Movie> movies) {
        movies.forEach(movie -> {
            jdbcTemplate.update("insert into movie (name, rating) values (?, ?)", movie.getName(), movie.getRating());
        });
        return 1;
    }

    /**
     * Update movie
     * @param movie
     * @return
     */
    public int update(Movie movie){
        return jdbcTemplate.update("update movie set name=?, rating=? where id=?", movie.getName(), movie.getRating(), movie.getId());
    }
    public int delete(int id){
        return jdbcTemplate.update("delete from movie where id=?", id);
    }
}
