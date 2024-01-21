package com.kish.learn.employee;

import com.kish.learn.employee.emp.Employee;
import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import io.dapr.client.domain.HttpExtension;
import io.dapr.utils.TypeRef;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;


@Slf4j
@SpringBootApplication
public class EmployeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }

    @Bean
    public CommandLineRunner cmdlRnr(){
        return args -> {
            DaprClient daprClient = new DaprClientBuilder().build();
            List<Employee> resp = daprClient.invokeMethod("employee-data-svc" ,"employee",HttpExtension.GET , Collections.emptyMap(), new TypeRef<List<Employee>>() {}).block();
            log.info("resp {}",resp);
        };
    }
}
