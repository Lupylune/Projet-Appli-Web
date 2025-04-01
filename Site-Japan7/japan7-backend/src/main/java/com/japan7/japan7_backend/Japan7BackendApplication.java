package com.japan7.japan7_backend;

import com.japan7.japan7_backend.model.EvenementTest;
import com.japan7.japan7_backend.repository.EvenementTestRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.japan7.japan7_backend")
@EntityScan("com.japan7.japan7_backend.model")
@EnableJpaRepositories("com.japan7.japan7_backend.repository")
public class Japan7BackendApplication {

	@Bean
	public CommandLineRunner testEvenement(EvenementTestRepository repo) {
		return args -> {
			repo.save(new EvenementTest(
					"Projo Anime",
					"Projection de Re:Zero",
					LocalDateTime.now().plusDays(3),
					"Projection"
			));
			System.out.println("Événement test inséré !");
		};
	}

	@RestController
	@RequestMapping("/")
	public class HomeController {

		@GetMapping("/")
		public RedirectView redirectToHome() {
			return new RedirectView("/index.html");
		}
	}


	public static void main(String[] args) {
		SpringApplication.run(Japan7BackendApplication.class, args);
	}

}
