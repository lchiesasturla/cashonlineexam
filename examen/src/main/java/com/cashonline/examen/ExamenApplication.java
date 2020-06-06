package com.cashonline.examen;

import com.cashonline.models.entity.Loan;
import com.cashonline.models.entity.User;
import com.cashonline.repository.LoanRepository;
import com.cashonline.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.cashonline.controller"})
@EntityScan("com.cashonline.models.entity")
@EnableJpaRepositories("com.cashonline.repository")
@SpringBootApplication
public class ExamenApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamenApplication.class, args);
    }

    @Bean
    public CommandLineRunner insertSampleData(UserRepository userRepository, LoanRepository loanRepository) {
        return args -> {
            List<User> sampleUsers = new ArrayList();
            List<Loan> sampleLoans = new ArrayList();
            
            sampleUsers.add(new User("Lucas1@test.com", "Lucas1", "Lucas1"));
            sampleUsers.add(new User("Lucas2@test.com", "Lucas2", "Lucas2"));
            sampleUsers.add(new User("Lucas3@test.com", "Lucas3", "Lucas3"));
            sampleUsers.add(new User("Lucas4@test.com", "Lucas4", "Lucas4"));

            for (int i = 0; i < sampleUsers.size(); i++) {
                userRepository.save(sampleUsers.get(i));
                System.out.println("\nSe inserto el siguiente usuario:\n");
                System.out.println("ID del usuario: " + sampleUsers.get(i).getIdUser());
                System.out.println("Nombre: " + sampleUsers.get(i).getFirstName());
                System.out.println("Apellido: " + sampleUsers.get(i).getLastName());
                System.out.println("Email: " + sampleUsers.get(i).getEmail() + "\n");
            }

            sampleLoans.add(new Loan(16.5f, sampleUsers.get(0)));
            sampleLoans.add(new Loan(162.5f, sampleUsers.get(0)));
            sampleLoans.add(new Loan(1.3f, sampleUsers.get(1)));
            sampleLoans.add(new Loan(8.5f, sampleUsers.get(1)));
            sampleLoans.add(new Loan(1.3f, sampleUsers.get(2)));
            sampleLoans.add(new Loan(8.5f, sampleUsers.get(3)));
            sampleLoans.add(new Loan(2.9f, sampleUsers.get(3)));
            sampleLoans.add(new Loan(0.5f, sampleUsers.get(3)));
            
            for (int i = 0; i < sampleLoans.size(); i++) {
                loanRepository.save(sampleLoans.get(i));
                System.out.println("\nSe inserto el siguiente prestamo:\n");
                System.out.println("ID del prestamo: " + sampleLoans.get(i).getIdLoan());
                System.out.println("Total: " + sampleLoans.get(i).getTotal());
                System.out.println("ID del usuario: " + sampleLoans.get(i).getUser().getIdUser() + "\n");
            }
        };
    }
}
