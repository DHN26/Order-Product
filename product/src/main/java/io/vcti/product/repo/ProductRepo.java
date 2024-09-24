package io.vcti.product.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import io.vcti.product.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

	@Query(value = "select *  from product_tbl order by price", nativeQuery = true)
	List<Product> sortByPrice();

	@Modifying
    @Query(value = "update product_tbl p set p.price = (p.price - (p.price * :discount / 100)) where p.price >= 50000", nativeQuery = true)
    int updateAll(@Param("discount") int discount);

}
