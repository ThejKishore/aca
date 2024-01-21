package com.kish.learn.employee.emp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeResource {

    private RestClient restClient = RestClient.create();

    @Value("${dapr.employee.svc.name:employee-data-svc}")
    private String daprEmployeeDataSvcName;

    @Value("${dapr.employee.svc.port:7100}")
    private String daprPort;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity fetchAll(){

        try{
            List<Employee> employeeList = restClient.get()
                    .uri("http://localhost:"+daprPort+"/employee")
                    .header("dapr-app-id",daprEmployeeDataSvcName)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<Employee>>() {});
            return ResponseEntity.ok(employeeList);
        }catch (Exception e){
            log.error(" Exception fetching the data ",e);
            return ResponseEntity.status(500).body("Exception in fetching data "+e.getLocalizedMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity fetchById(@PathVariable("id") Long id){
        try{
            Employee employee =  restClient.get()
                    .uri("http://localhost:"+daprPort+"/employee/"+id)
                    .header("dapr-app-id",daprEmployeeDataSvcName)
                    .retrieve()
                    .body(Employee.class);
            return ResponseEntity.ok(employee);
        }catch (Exception e){
            log.error(" Exception fetching the data ",e);
            return ResponseEntity.status(500).body("Exception in fetching data "+e.getLocalizedMessage());
        }
    }
}
