package pl.migibud.employeeservice.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import pl.migibud.employeeservice.department.DepartmentClient;
import pl.migibud.employeeservice.department.DepartmentDto;
import pl.migibud.employeeservice.exception.EmployeeError;
import pl.migibud.employeeservice.exception.EmployeeException;

@Service
@RequiredArgsConstructor
class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentClient departmentClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        employeeRepository.findByEmail(employeeDto.getEmail())
                .ifPresent(employee -> {
                    throw new EmployeeException(EmployeeError.EMPLOYEE_WITH_EMAIL_ALREADY_EXISTS,
                            String.format("Employee with email: '%s' already exists.", employee.getEmail()));
                });


        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail(),
                savedEmployee.getDepartmentCode()
        );
        return savedEmployeeDto;
    }

    @Override
    public ApiResponseDto getEmployeeById(Long id) {

        EmployeeDto employeeDto = employeeRepository.findById(id)
                .map(employee -> new EmployeeDto(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getEmail(),
                        employee.getDepartmentCode())
                )
                .orElseThrow(() -> new EmployeeException(EmployeeError.EMPLOYEE_NOT_FOUND, String.format("Employee with id: %s not found.", id)));

        DepartmentDto departmentDto = departmentClient.getDepartment(employeeDto.getDepartmentCode());

        return new ApiResponseDto(employeeDto,departmentDto);
    }
}
