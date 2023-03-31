package online.javafun.wholesale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import online.javafun.wholesale.model.Equipment;
import online.javafun.wholesale.model.Product;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.Set;

@SpringBootApplication
public class HurtowniaDynamicLimitsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HurtowniaDynamicLimitsApplication.class, args);
        Validator validator = context.getBean(Validator.class);
        Product product1 = new Product("Dell XPS 15", "Laptop 15 calowy z 2021 roku", "PL13243");
        Set<ConstraintViolation<Product>> productConstraintViolations = validator.validate(product1);
        if (!productConstraintViolations.isEmpty()) {
            System.out.println("Nieprawidłowy produkt, złamane ograniczenia:");
            productConstraintViolations.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        }

        Equipment equipment1 = new Equipment("iPhone 13", "KARNOW123", "EQ7986");
        Set<ConstraintViolation<Equipment>> equipmentConstraintViolations = validator.validate(equipment1);
        if (!equipmentConstraintViolations.isEmpty()) {
            System.out.println("Nieprawidłowe wyposażenie, złamane ograniczenia:");
            equipmentConstraintViolations.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        }
    }
}