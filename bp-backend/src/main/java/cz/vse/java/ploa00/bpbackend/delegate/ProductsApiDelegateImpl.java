package cz.vse.java.ploa00.bpbackend.delegate;

import cz.vse.java.ploa00.bpbackend.api.gen.model.ProductDTO;
import cz.vse.java.ploa00.bpbackend.api.gen.rest.ProductsApiDelegate;
import cz.vse.java.ploa00.bpbackend.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductsApiDelegateImpl implements ProductsApiDelegate {

    private ProductService productService;
    @Override
    public ResponseEntity<ProductDTO> addProduct(ProductDTO productDTO) {

        ProductDTO savedProduct = productService.addProduct(productDTO);

        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long productId) {

        productService.deleteProduct(productId);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getAllProducts() {

        List<ProductDTO> allProducts = productService.getAllProducts();

        return ResponseEntity.ok(allProducts);
    }

    @Override
    public ResponseEntity<ProductDTO> getProductById(Long productId) {

        ProductDTO productDTO = productService.getProductById(productId);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDTO> updateProduct(Long productId, ProductDTO productDTO) {

        ProductDTO updatedProduct = productService.updateProduct(productId, productDTO);

        return ResponseEntity.ok(updatedProduct);
    }
}
