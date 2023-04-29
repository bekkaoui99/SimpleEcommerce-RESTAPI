package ma.web.productbackend;

import lombok.AllArgsConstructor;
import ma.web.productbackend.entities.Product;



import ma.web.productbackend.serviceP.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication

public class ProductBackEndApplication {




    public static void main(String[] args) {
        SpringApplication.run(ProductBackEndApplication.class, args);
    }
    @Bean
    CommandLineRunner start(service service){
        return args -> {

            Stream.of("computer","screan","phone","printer").forEach(name->{

                Product product = new Product();
                product.setName(name);
                product.setPrice(Math.random()*10000);
                product.setQuantity(Math.floor(Math.random()*201));
                product.setAvailble(Math.random()>0.5?true:false);
                product.setSelected(Math.random()>0.5?true:false);
                service.saveProduct(product);


            });


            service.getAllProducts().forEach(data->{
                System.out.println(data.toString());
            });



        };
    }







}
