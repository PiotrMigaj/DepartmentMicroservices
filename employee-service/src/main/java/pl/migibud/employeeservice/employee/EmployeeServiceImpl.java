package pl.migibud.employeeservice.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.migibud.employeeservice.exception.EmployeeError;
import pl.migibud.employeeservice.exception.EmployeeException;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        employeeRepository.findByEmail(employeeDto.getEmail())
                .ifPresent(employee -> {
                    throw new EmployeeException(EmployeeError.EMPLOYEE_WITH_EMAIL_ALREADY_EXISTS,
                            String.format("Employee with email: '%s' already exists.",employee.getEmail()));
                });


        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail()
        );
        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(employee -> new EmployeeDto(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getEmail())
                )
                .orElseThrow(()->new EmployeeException(EmployeeError.EMPLOYEE_NOT_FOUND,String.format("Employee with id: %s not found.",id)));
    }
}
