package pl.fastust.spring5webfluxrest.controllers;

import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.fastust.spring5webfluxrest.domain.Category;
import pl.fastust.spring5webfluxrest.repositories.CategoryRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Tom - 18.03.2021
 */
@RestController
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/api/v1/categories")
    Flux<Category> list(){
        return categoryRepository.findAll();
    }

    @GetMapping("/api/v1/categories/{id}")
    Mono<Category> getById(@PathVariable String id){
        return categoryRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/categories")
    Mono<Void> create(@RequestBody Publisher<Category> categoryStream){
        return categoryRepository.saveAll(categoryStream).then();
    }
}
