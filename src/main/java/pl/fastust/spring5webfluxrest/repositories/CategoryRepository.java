package pl.fastust.spring5webfluxrest.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pl.fastust.spring5webfluxrest.domain.Category;

/**
 * Created by Tom - 18.03.2021
 */
public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
}
