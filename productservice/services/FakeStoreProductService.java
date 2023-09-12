package dev.ashish.productservice.services;

import dev.ashish.productservice.Exceptions.NotFoundException;
import dev.ashish.productservice.dtos.GenericProductDto;
import dev.ashish.productservice.thirdpartyclients.producservice.fakestore.FakeStoreProductDto;
import dev.ashish.productservice.thirdpartyclients.producservice.fakestore.FakeStoreProductSerivceClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private FakeStoreProductSerivceClient fakeStoreProductSerivceClient;

    private GenericProductDto convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto){

        GenericProductDto product=new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());

        return  product;
    }
    public FakeStoreProductService (FakeStoreProductSerivceClient fakeStoreProductSerivceClient){
        this.fakeStoreProductSerivceClient=fakeStoreProductSerivceClient;

    }


    @Override
    public GenericProductDto createProduct(GenericProductDto product){

        return convertFakeStoreProductIntoGenericProduct( fakeStoreProductSerivceClient.createProduct(product));
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
       return convertFakeStoreProductIntoGenericProduct(fakeStoreProductSerivceClient.getProductById(id));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<GenericProductDto> genericProductDtos=new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductSerivceClient.getAllProducts()){
            genericProductDtos.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto));
        }

        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductSerivceClient.deleteProduct(id));
    }
}
