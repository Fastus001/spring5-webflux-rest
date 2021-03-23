package pl.fastust.spring5webfluxrest.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import pl.fastust.spring5webfluxrest.domain.Vendor;
import pl.fastust.spring5webfluxrest.repositories.VendorRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

class VendorControllerTest {

    public static final String VENDOR_ONE = "VendorOne";
    public static final String API_V_1_VENDORS = "/api/v1/vendors";
    public static final String LAST_NAME = "LastName";
    public static final String SOME_ID = "someId";
    public static final String VENDOR_TWO = "VendorTwo";

    WebTestClient webTestClient;
    VendorRepository vendorRepository;
    VendorController vendorController;

    @BeforeEach
    void setUp() {
        vendorRepository = Mockito.mock(VendorRepository.class);
        vendorController = new VendorController(vendorRepository);
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    void list() {
        given(vendorRepository.findAll())
                .willReturn(Flux.just(Vendor.builder().firstName(VENDOR_ONE).lastName(LAST_NAME).build(),
                        Vendor.builder().firstName(VENDOR_TWO).lastName(LAST_NAME).build()));

        webTestClient.get().uri(API_V_1_VENDORS)
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    void getById() {
        given(vendorRepository.findById(SOME_ID))
                .willReturn(Mono.just(Vendor.builder().firstName(VENDOR_ONE).lastName(LAST_NAME).build()));

        webTestClient.get().uri(API_V_1_VENDORS+"/"+SOME_ID)
                .exchange()
                .expectBody(Vendor.class);
    }

    @Test
    void create(){
        given(vendorRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(Vendor.builder().build()));

        final Mono<Vendor> vendorToSaveMono = Mono.just(Vendor.builder()
                .firstName(VENDOR_ONE).lastName(LAST_NAME).build());

        webTestClient.post().uri("/api/v1/vendors")
                .body(vendorToSaveMono,Vendor.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    void update(){
        given(vendorRepository.save(any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().build()));

        final Mono<Vendor> vendorMonoToUpdate = Mono.just(Vendor.builder()
                .firstName(VENDOR_ONE)
                .lastName(LAST_NAME).build());

        webTestClient.put()
                .uri("/api/v1/vendors/someId")
                .body(vendorMonoToUpdate, Vendor.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void patch(){
        given(vendorRepository.findById(anyString()))
                .willReturn(Mono.just(Vendor.builder().firstName(VENDOR_ONE).lastName(LAST_NAME).build()));

        given(vendorRepository.save(any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().build()));

        final Mono<Vendor> vendorMonoToUpdate = Mono.just(Vendor.builder()
                .firstName("new vendor name")
                .lastName("new vendor last name").build());

        webTestClient.patch()
                .uri("/api/v1/vendors/someId")
                .body(vendorMonoToUpdate, Vendor.class)
                .exchange()
                .expectStatus().isOk();

        verify(vendorRepository).save(any());
    }

    @Test
    void patchWithNoChanges(){
        given(vendorRepository.findById(anyString()))
                .willReturn(Mono.just(Vendor.builder().firstName(VENDOR_ONE).lastName(LAST_NAME).build()));


        final Mono<Vendor> vendorMonoToUpdate = Mono.just(Vendor.builder()
                .firstName(VENDOR_ONE)
                .lastName(LAST_NAME).build());

        webTestClient.patch()
                .uri("/api/v1/vendors/someId")
                .body(vendorMonoToUpdate, Vendor.class)
                .exchange()
                .expectStatus().isOk();

        verify(vendorRepository, never()).save(any());
    }
}