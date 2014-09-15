/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.tylframework.data.mongo;

import it.tylframework.data.mongo.basics.Country;
import it.tylframework.data.mongo.basics.Language;
import it.tylframework.data.mongo.basics.Numerator;
import it.tylframework.data.mongo.common.LangKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SampleMongoApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;
    @Autowired
    private CountryRepository countryRep;
    @Autowired
    private LanguageRepository languageRep;
    @Autowired
    private NumeratorRepository numeratorRep;

	@Override
	public void run(String... args) throws Exception {

		repository.deleteAll();
        countryRep.deleteAll();
        languageRep.deleteAll();

		// save a couple of customers
		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));
        countryRep.save(new Country("it", "Italia",123));

        numeratorRep.save(new Numerator("meter");
        languageRep.save(new Language("it","italianflag","italian"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();


        // fetch all countries
        System.out.println("Countries found with findAll():");
        System.out.println("-------------------------------");
        for (Country country : countryRep.findAll()) {
            System.out.println(country);
        }
        System.out.println();

        // fetch all languages
        System.out.println("Languages found with findAll():");
        System.out.println("-------------------------------");
        for (Language language : languageRep.findAll()) {
            System.out.println(language);
        }
        System.out.println();

        // fetch all numerators
        System.out.println("Numerators found with findAll():");
        System.out.println("-------------------------------");
        for (Numerator numerator : numeratorRep.findAll()) {
            System.out.println(numerator);
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

        System.out.println("Country found with twoCharCode('it'):");
        System.out.println("--------------------------------");
        Country cn=countryRep.findByTwoCharCode("it");
        System.out.println(cn);
        System.out.println(cn.getCreated_by());
        System.out.println(cn.getCreated_on());

	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleMongoApplication.class, args);
	}
}
