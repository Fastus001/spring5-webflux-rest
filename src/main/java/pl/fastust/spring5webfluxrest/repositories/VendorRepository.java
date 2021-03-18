package pl.fastust.spring5webfluxrest.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pl.fastust.spring5webfluxrest.domain.Vendor;

/**
 * Created by Tom - 18.03.2021
 */
public interface VendorRepository extends ReactiveCrudRepository<Vendor, String > {
}
