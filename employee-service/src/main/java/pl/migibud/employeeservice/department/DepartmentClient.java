package pl.migibud.employeeservice.department;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080",value = "DEPARTMENT-SERVICE")
public interface DepartmentClient {
    @GetMapping("api/departments/{code}")
    DepartmentDto getDepartment(@PathVariable("code") String code);
}
