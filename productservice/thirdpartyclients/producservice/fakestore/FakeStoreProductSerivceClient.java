package dev.ashish.productservice.thirdpartyclients.producservice.fakestore;

import dev.ashish.productservice.Exceptions.NotFoundException;
import dev.ashish.productservice.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//Wrapper over FakeStore API

@Service
public class FakeStoreProductSerivceClient {
    private RestTemplateBuilder restTemplateBuilder;
    private String specificProductrequestURL ="https://fakestoreapi.com/products/{id}";
    private  String productRequestsBaseURL ="https://fakestoreapi.com/products";
    public FakeStoreProductSerivceClient (RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;

    }


    public FakeStoreProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate=new RestTemplateBuilder().build();
        ResponseEntity<FakeStoreProductDto> response=
                restTemplate.postForEntity(productRequestsBaseURL,product,FakeStoreProductDto.class);

        return  response.getBody();
    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        // FakeStoreProductService fakeStoreProductService=new FakeStoreProductService();
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response= restTemplate.getForEntity(specificProductrequestURL, FakeStoreProductDto.class,id);

        FakeStoreProductDto fakeStoreProductDto=response.getBody();

        if(fakeStoreProductDto==null){
            throw  new NotFoundException("Product with id: "+id+"doesn't exists");
        }

        // response.getStatusCode();
        return  fakeStoreProductDto;

        // return  new Product();

    }


    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> response=
                restTemplate.getForEntity(productRequestsBaseURL,FakeStoreProductDto[].class);

        List<GenericProductDto> answer=new ArrayList<>();



        return  Arrays.stream(response.getBody()).toList();
        //return null;
    }


    public FakeStoreProductDto deleteProduct(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response= restTemplate.execute(specificProductrequestURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);


        //restTemplate.delete(specificProductrequestURL);
        return response.getBody();
    }
}
