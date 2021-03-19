package pl.fastust.spring5webfluxrest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.fastust.spring5webfluxrest.domain.Vendor;
import pl.fastust.spring5webfluxrest.repositories.VendorRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Tom - 19.03.2021
 */
@RestController
public class VendorController {

    private final VendorRepository vendorRepository;

    public VendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @GetMapping("/api/v1/vendors")
    Flux<Vendor> list(){
        return vendorRepository.findAll();
    }

    @GetMapping("/api/v1/vendors/{id}")
    Mono<Vendor> getById(@PathVariable String id){
        return vendorRepository.findById(id);
    }
}
