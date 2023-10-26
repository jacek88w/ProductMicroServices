package pl.jacek.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.jacek.productservice.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
