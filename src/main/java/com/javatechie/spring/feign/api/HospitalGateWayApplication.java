package com.javatechie.spring.feign.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HospitalGateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalGateWayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("doctor-service", r -> r.path("/doctor-api/**")
						.filters(f -> f.stripPrefix(1))
						.uri("http://localhost:8081"))
				.route("diagnosis-service", r -> r.path("/diagnosis-api/**")
						.filters(f -> f.stripPrefix(1))
						.uri("http://localhost:8082"))
				.build();
	}
}
