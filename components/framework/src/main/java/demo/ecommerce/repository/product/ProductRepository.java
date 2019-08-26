package demo.ecommerce.repository.product;

import demo.ecommerce.product.model.Product;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {

    @Query("Select * from product order by updated_on desc, id desc LIMIT $1 OFFSET $2")
    Flux<Product> findAllPageable(int limit, long offset);

    @Query("Select * from product where inventory_count > 0 order by updated_on desc, id desc LIMIT $1 OFFSET $2")
    Flux<Product> availableProducts(int limit, long offset);

    @Query("Select * from product where inventory_count = 0 order by updated_on desc, id desc LIMIT $1 OFFSET $2")
    Flux<Product> notAvailableProducts(int limit, long offset);

    @Query("Select * from product where user_id=$1 order by updated_on desc, id desc LIMIT $2 OFFSET $3")
    Flux<Product> userProducts(Long userId, int limit, long offset);

}
