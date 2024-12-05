package com.murad.ecommerce.product;


import com.murad.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Integer createProduct(
            ProductRequest request
    ) {
        var product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    public ProductResponse findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID:: " + id));
    }

    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }


    @Transactional(rollbackFor = ProductPurchaseException.class)
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) {
        var productIds = requests
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        // Retrieve and validate the products
        var productsFromDB = repository.findAllByIdInOrderById(productIds);
        if (productIds.size() != productsFromDB.size()) {
            var missingProductIds = productIds.stream()
                    .filter(id -> productsFromDB.stream().noneMatch(product -> product.getId().equals(id)))
                    .toList();
            throw new ProductPurchaseException("Products not found for IDs: " + missingProductIds);
        }

        // Sort the requests to match the products from the database
        var sortedRequests = requests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        List<Product> productsToUpdate = new ArrayList<>();
        List<ProductPurchaseResponse> purchaseResponses = new ArrayList<>();

        for (int i = 0; i < productsFromDB.size(); i++) {
            var product = productsFromDB.get(i);
            var request = sortedRequests.get(i);

            if (product.getAvailableQuantity() < request.quantity()) {
                throw new ProductPurchaseException("Insufficient stock for product ID: " + request.productId());
            }

            // Update product quantity
            product.setAvailableQuantity(product.getAvailableQuantity() - request.quantity());
            productsToUpdate.add(product);

            // Add response for the purchased product
            purchaseResponses.add(mapper.toproductPurchaseResponse(product, request.quantity()));
        }

        // Batch save updated products
        repository.saveAll(productsToUpdate);

        return purchaseResponses;
    }
}
