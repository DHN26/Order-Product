package io.vcti.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import io.vcti.product.entity.Product;
import io.vcti.product.repo.ProductRepo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class KafkaConsumerService {
	
//	@Autowired
//	private KafkaTemplate<String, Product> kafkaTemplate;

	@Autowired
	private ProductRepo repo;
	
//	@KafkaListener(topics = "product-order-topic", groupId = "group-1")
//	public void printMsgFromTopic(String message) {
//		System.out.println("Message from order service : " + message);
//	}
	
	
	@KafkaListener(topics = "product-order-topic", groupId = "group-1")
	public void decrementProductCount(Long productId)
	{
		Product product=repo.findById(productId).orElseThrow(()->new EntityNotFoundException("Product not found!!"));
		if(product.getRemainingProducts()>0)
		{
			product.setRemainingProducts(product.getRemainingProducts()-1);
			repo.save(product); 
		}
	
		//sending updated product to previously called producer i.e order
		//kafkaTemplate.send("product-order-topic", product);
		
		System.out.println("Product updated successfully after order being placed.");
	}

}
