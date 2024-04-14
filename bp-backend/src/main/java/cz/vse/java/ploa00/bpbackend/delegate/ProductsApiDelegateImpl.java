package cz.vse.java.ploa00.bpbackend.delegate;

import cz.vse.java.ploa00.bpbackend.api.gen.model.ProductDTO;
import cz.vse.java.ploa00.bpbackend.api.gen.rest.ProductsApiDelegate;
import cz.vse.java.ploa00.bpbackend.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductsApiDelegateImpl extends AbstractDelegate implements ProductsApiDelegate {

    private ProductService productService;
    @Override
    public ResponseEntity<ProductDTO> addProduct(ProductDTO productDTO) {
        checkManagerRole();

        ProductDTO savedProduct = productService.addProduct(productDTO);

        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long productId) {
        checkManagerRole();

        productService.deleteProduct(productId);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        checkEmployeeRole();

        List<ProductDTO> allProducts = productService.getAllProducts();

        return ResponseEntity.ok(allProducts);
    }

    @Override
    public ResponseEntity<ProductDTO> getProductById(Long productId) {
        checkEmployeeRole();

        ProductDTO productDTO = productService.getProductById(productId);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDTO> updateProduct(Long productId, ProductDTO productDTO) {
        checkManagerRole();

        ProductDTO updatedProduct = productService.updateProduct(productId, productDTO);

        return ResponseEntity.ok(updatedProduct);
    }
}
