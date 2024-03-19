package cz.vse.java.ploa00.bpbackend.service;

import cz.vse.java.ploa00.bpbackend.api.gen.model.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO addProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long productId);

    ProductDTO updateProduct(Long productId, ProductDTO productDTO);

    void deleteProduct(Long productId);
}
