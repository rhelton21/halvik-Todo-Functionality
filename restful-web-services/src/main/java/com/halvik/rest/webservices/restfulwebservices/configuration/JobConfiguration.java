package com.halvik.rest.webservices.restfulwebservices.configuration;


import com.halvik.rest.webservices.restfulwebservices.model.Todo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.orm.JpaNativeQueryProvider;
import org.springframework.batch.item.database.orm.JpaQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;


@Configuration
@EnableBatchProcessing
public class JobConfiguration {

    @Autowired
    EntityManagerFactory emf;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @Bean
    public JpaPagingItemReader<Todo> pagingItemReader() {
        JpaPagingItemReader<Todo> reader = new JpaPagingItemReader();
        reader.setEntityManagerFactory(emf);

        JpaQueryProvider jpaQueryProvider = new JpaNativeQueryProvider<>();
//        jpaQueryProvider..setEntityClass(Todo.class);
        String sqlQuery = "select * from todos";
        ((JpaNativeQueryProvider) jpaQueryProvider).setSqlQuery(sqlQuery);

        reader.setQueryProvider(jpaQueryProvider);
        return reader;
    }


    @Bean
    public ItemWriter<Todo> customerItemWriter() {
        return items -> {
            for (Todo item : items) {
                System.out.println(item.toString());
            }
        };
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Todo, Todo>chunk(10)
                .reader(pagingItemReader())
                .writer(customerItemWriter())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(step1())
                .build();
    }
}
