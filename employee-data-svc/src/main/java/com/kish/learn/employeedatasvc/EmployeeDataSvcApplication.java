package com.kish.learn.employeedatasvc;

import com.kish.learn.employeedatasvc.emp.Employee;
import com.kish.learn.employeedatasvc.emp.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeeDataSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeDataSvcApplication.class, args);
    }


    @Bean
    public CommandLineRunner cmdlRnr(EmployeeService employeeService){

        return args-> {
                employeeService.save(Employee.builder().firstName("David").lastName("Amalraj").deptName("wealth").build());
                employeeService.save(Employee.builder().firstName("Pradeep").lastName("Sadhu").deptName("wealth").build());
                employeeService.save(Employee.builder().firstName("Venkat").lastName("Srinivasan").deptName("wealth").build());
                employeeService.save(Employee.builder().firstName("Ramkumar").lastName("Rajamanikam").deptName("wealth").build());
                employeeService.save(Employee.builder().firstName("Nehal").lastName("Vaidhya").deptName("private").build());
                employeeService.save(Employee.builder().firstName("Kaveh").lastName("Maleja").deptName("private").build());
        };

    }
}
