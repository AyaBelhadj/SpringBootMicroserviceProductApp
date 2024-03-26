package com.example.productApp.productAPI;

import com.example.productApp.productAPI.model.Product;
import com.example.productApp.productAPI.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository repository){
        return  args -> {
            try{
                Product Product1=new Product("1","eye_creme",
                        "efficient for dark cercles",
                        50.0F);
                Product Product2=new Product("2","night_creme",
                        "efficient for hidrated face",
                        80.0F);
                Product Product3=new Product("3","cleanser",
                        "efficient for face cleaning",
                        50.0F);
                Product Product4=new Product("4","sunblock",
                        "efficient for hidrated face ",
                        80.0F);
                repository.save(Product1);
                repository.save(Product2);
                repository.save(Product3);
                repository.save(Product4);
            }
            catch (Exception e){
                System.err.println("Une exception s'est produite : " + e.getMessage());

            }

        };
    }

}
