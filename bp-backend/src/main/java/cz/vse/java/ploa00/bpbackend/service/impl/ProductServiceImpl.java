package cz.vse.java.ploa00.bpbackend.service.impl;

import cz.vse.java.ploa00.bpbackend.api.gen.model.ProductDTO;
import cz.vse.java.ploa00.bpbackend.entity.product.Product;
import cz.vse.java.ploa00.bpbackend.exception.ResourceNotFoundException;
import cz.vse.java.ploa00.bpbackend.repository.ProductRepository;
import cz.vse.java.ploa00.bpbackend.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private ModelMapper modelMapper;
    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {

        Product product = modelMapper.map(productDTO, Product.class);

        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAllProducts() {

        List<Product> allProducts = productRepository.findAll();

        return allProducts.stream().map((product -> modelMapper.map(product, ProductDTO.class)))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with given id: " + productId));

        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {

        productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with given id: " + productId));

        Product product = modelMapper.map(productDTO, Product.class);

        product.setId(productId);

        Product updatedProduct = productRepository.save(product);

        return modelMapper.map(updatedProduct, ProductDTO.class);
    }

    @Override
    public void deleteProduct(Long productId) {

        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        } else {
         throw new ResourceNotFoundException("Product not found with given ID" + productId);
        }
    }
}
