package com.example;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanAccessor;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mongocontroller {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	Tracer tracer;

	@Autowired
	private SpanAccessor accessor;

	static Logger log = Logger.getLogger(Demo2RedisMongoApplication.class.getName());
	Span newSpan1;

	@RequestMapping("/mongo")
	public String mongohello() throws Exception {

		newSpan1 = this.tracer.createSpan("mongo", new AlwaysSampler());
		try {
			this.tracer.addTag("name", "mongo-tag");
			log.info("mongo service started ");
			repository.deleteAll();

			// save a couple of customers
			repository.save(new Customer("Alice", "Smith"));
			repository.save(new Customer("Bob", "Smith"));

			// fetch all customers
			System.out.println("Customers found with findAll():");
			System.out.println("-------------------------------");
			for (Customer customer : repository.findAll()) {
				System.out.println(customer);
			}
			System.out.println();

			// fetch an individual customer
			System.out.println("Customer found with findByFirstName('Alice'):");
			System.out.println("--------------------------------");
			System.out.println(repository.findByFirstName("Alice"));

			System.out.println("Customers found with findByLastName('Smith'):");
			System.out.println("--------------------------------");
			for (Customer customer : repository.findByLastName("Smith")) {
				System.out.println(customer);

			}
			return "success!!!! for mongo";
		}

		finally {
			
			newSpan1.logEvent("mongo service closed ");
			this.tracer.close(newSpan1);

		}
	}
}
