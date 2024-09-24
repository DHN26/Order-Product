package io.vcti.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.vcti.product.entity.Product;
import io.vcti.product.repo.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProductService {

	@Autowired
	private ProductRepo repo;

	public void saveProduct(Product product) {
		repo.save(product);
	}

	public List<Product> fetchAll() {
		return repo.findAll();
	}

	public Product findById(long id) {
		return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found!"));
	}

	public Product updateProduct(Long id, String status) {
		Product p = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found!"));
		p.setStatus(status);
		repo.save(p);
		return p;
	}

	public void deleteById(long id) {
		Product p = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found!"));
		repo.deleteById(id);
	}

	public List<Product> sortByPrice() {
		return repo.sortByPrice();
	}

	@Transactional
	public int updateAll(int discount) {
		return repo.updateAll(discount);
	}

}
