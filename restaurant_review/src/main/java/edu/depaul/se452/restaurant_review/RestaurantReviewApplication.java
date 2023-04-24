package edu.depaul.se452.restaurant_review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import edu.depaul.se452.restaurant_review.review_lu.relational.ReviewRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
public class RestaurantReviewApplication {

	@Value("${environment}")
	private String env;
	
	public static void main(String[] args) {
		SpringApplication.run(RestaurantReviewApplication.class, args);
	}

	@Bean
	public CommandLineRunner showLogLevel() {
		return (args) -> {
			System.out.println(env);
			log.debug("Debug");
			log.info("Info");
			log.warn("Warning");
			log.error("Error");
		};
	}

	@Bean
	public CommandLineRunner showAllReviews(ReviewRepository repo) {
		return (args) -> {
			log.info("number of reviews: " + repo.count());
		};
	}
}
