package pl.migibud.employeeservice.employee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/employees")
@RequiredArgsConstructor
class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    ResponseEntity<EmployeeDto> saveDepartment(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

//    @GetMapping("/{code}")
//    ResponseEntity<DepartmentDto> getDepartment(@PathVariable String code){
//        DepartmentDto departmentDto = departmentService.getDepartmentByCode(code);
//        return new ResponseEntity<>(departmentDto,HttpStatus.OK);
//    }

}
