package dev.ashish.productservice.Controllers;

import dev.ashish.productservice.Exceptions.ExceptionDto;
import dev.ashish.productservice.Exceptions.NotFoundException;
import dev.ashish.productservice.dtos.GenericProductDto;
import dev.ashish.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")

public class ProductController {
    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService=productService;
    }

    @GetMapping
    public List<GenericProductDto> getAllProducts(){
       return productService.getAllProducts();

    }
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws  NotFoundException{
        return  productService.getProductById(id);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto>  deleteProductById(@PathVariable("id") Long id){
        return  new ResponseEntity<>(
                productService.deleteProduct(id),
                HttpStatus.OK
        );
    }
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionDto> HandleNotFoundException(NotFoundException notFoundException){
        //System.out.println("Not found exception happened");
        return  new ResponseEntity(
                new ExceptionDto(HttpStatus.NOT_FOUND,notFoundException.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }


    @PostMapping
    @PutMapping
    public  GenericProductDto  createProduct(@RequestBody GenericProductDto product){


        //return "Created product with id: "+ product.getTitle();
      return   productService.createProduct(product);
    }
    public void updateProductById(){

    }
}
