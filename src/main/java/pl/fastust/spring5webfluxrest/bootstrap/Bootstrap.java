package pl.fastust.spring5webfluxrest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.fastust.spring5webfluxrest.domain.Category;
import pl.fastust.spring5webfluxrest.domain.Vendor;
import pl.fastust.spring5webfluxrest.repositories.CategoryRepository;
import pl.fastust.spring5webfluxrest.repositories.VendorRepository;

/**
 * Created by Tom - 18.03.2021
 */
@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (categoryRepository.count().block() == 0) {
            loadCategories();
        }
        if (vendorRepository.count().block() == 0) {
            loadVendors();
        }
    }

    private void loadVendors() {
        Vendor vendor = new Vendor();
        vendor.setFirstName("Mark");
        vendor.setLastName("Jackson");

        Vendor vendor1 = new Vendor();
        vendor1.setFirstName("Johny");
        vendor1.setLastName("Brown");

        Vendor vendor2 = new Vendor();
        vendor2.setFirstName("Rebeca");
        vendor2.setLastName("Smith");

        vendorRepository.save(vendor).block();
        vendorRepository.save(vendor1).block();
        vendorRepository.save(vendor2).block();

        System.out.println("Data Loaded Vendors category = " + vendorRepository.count().block());
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setDescription("Fruits");

        Category dried = new Category();
        dried.setDescription("Dried");

        Category fresh = new Category();
        fresh.setDescription("Fresh");

        Category exotic = new Category();
        exotic.setDescription("Exotic");

        Category nuts = new Category();
        nuts.setDescription("Nuts");

        categoryRepository.save(fruits).block();
        categoryRepository.save(dried).block();
        categoryRepository.save(fresh).block();
        categoryRepository.save(exotic).block();
        categoryRepository.save(nuts).block();

        System.out.println("Data loaded Categories = " + categoryRepository.count().block());
    }
}
