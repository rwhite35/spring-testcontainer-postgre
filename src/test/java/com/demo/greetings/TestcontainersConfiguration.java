package com.demo.greetings;

// these imports are specific to Kafka and LocalStack.
// Kafka may be broken for gradle 8 and Spring Boot 3.3.x 
// but left for reference. See compatibility matrix https://spring.io/projects/spring-kafka
//
// import static org.testcontainers.utility.DockerImageName.parse;
// import org.springframework.boot.ApplicationRunner;
// import org.springframework.context.annotation.DependsOn;
// import org.springframework.test.context.DynamicPropertyRegistry;
// import org.testcontainers.containers.KafkaContainer;
//
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
//
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.containers.PostgreSQLContainer;
import io.github.microcks.testcontainers.MicrocksContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {

	// Postgres container instance, requires Docker daemon running
	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		return new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
	}

	// Mock Greeting Service OpenAPI service.
	// registering the Microcks provided mock endpoint as
	// `application.greeting-service-url`.
	// call `greeting-service` from application routes to the Microcks endpoint.
	@Bean
	MicrocksContainer microcksContainer(DynamicPropertyRegistry registry) {
		MicrocksContainer microcks = new MicrocksContainer("quay.io/microcks/microcks-uber:1.8.1")
				.withMainArtifacts("greeting-openapi.yaml")
				.withAccessToHost(true);

		registry.add("application.greeting-service-url", () -> microcks.getRestMockEndpoint("Greeting Service", "1.0"));
		return microcks;
	}

	/**
	 * doesnt import correctly and not necessary for PostgreSQLContainer
	 * 
	 * @Bean
	 * @ServiceConnection
	 *                    KafkaContainer kafkaContainer() {
	 *                    return new
	 *                    KafkaContainer(parse("confluentinc/cp-kafka:7.5.0"));
	 *                    }
	 */

	/**
	 * not using AWS for this project and not necessary for PostgreSQLContainer
	 * 
	 * @Bean("localstackContainer")
	 * LocalStackContainer localstackContainer(DynamicPropertyRegistry registry) {
	 * LocalStackContainer localStack = new
	 * LocalStackContainer(parse("localstack/localstack:2.3"));
	 * registry.add("spring.cloud.aws.credentials.access-key",
	 * localStack::getAccessKey);
	 * registry.add("spring.cloud.aws.credentials.secret-key",
	 * localStack::getSecretKey);
	 * registry.add("spring.cloud.aws.region.static", localStack::getRegion);
	 * registry.add("spring.cloud.aws.endpoint", localStack::getEndpoint);
	 * return localStack;
	 * }
	 * 
	 * @Bean
	 *       @DependsOn("localstackContainer")
	 *       ApplicationRunner awsInitializer(ApplicationProperties properties,
	 *       FileStorageService fileStorageService) {
	 *       return args ->
	 *       fileStorageService.createBucket(properties.productImagesBucketName());
	 *       }
	 */
}
