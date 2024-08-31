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
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
//
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		return new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
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
