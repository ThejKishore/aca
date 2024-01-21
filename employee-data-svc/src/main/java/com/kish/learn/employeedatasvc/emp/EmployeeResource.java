package com.kish.learn.employeedatasvc.emp;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeResource {

    private final EmployeeService employeeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity fetchAll(){
            try{
                List<Employee> employeeList = employeeService.getAll();
                return ResponseEntity.ok(employeeList);
            }catch (Exception e){
                log.error(" Exception fetching the data ",e);
                return ResponseEntity.status(500).body("Exception in fetching data "+e.getLocalizedMessage());
            }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity fetchById(@PathVariable("id") Long id){
        try{
            Optional<Employee> employee = employeeService.getEmpById(id);
            return employee.map(d -> ResponseEntity.ok(employee)).orElse(ResponseEntity.notFound().build());
        }catch (Exception e){
            log.error(" Exception fetching the data ",e);
            return ResponseEntity.status(500).body("Exception in fetching data "+e.getLocalizedMessage());
        }
    }

}
