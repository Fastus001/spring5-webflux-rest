package pl.fastust.spring5webfluxrest.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Tom - 18.03.2021
 */
@Data
@Document
public class Category {

    @Id
    private String id;

    private String description;
}
