package pl.migibud.employeeservice.employee;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.migibud.employeeservice.department.DepartmentClient;
import pl.migibud.employeeservice.department.DepartmentDto;
import pl.migibud.employeeservice.exception.EmployeeError;
import pl.migibud.employeeservice.exception.EmployeeException;
import pl.migibud.employeeservice.organisation.OrganisationClient;
import pl.migibud.employeeservice.organisation.OrganisationDto;

@Slf4j
@Service
@RequiredArgsConstructor
class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentClient departmentClient;
    private final OrganisationClient organisationClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        employeeRepository.findByEmail(employeeDto.getEmail())
                .ifPresent(employee -> {
                    throw new EmployeeException(EmployeeError.EMPLOYEE_WITH_EMAIL_ALREADY_EXISTS,
                            String.format("Employee with email: '%s' already exists.", employee.getEmail()));
                });


        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }


//    @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Override
    public ApiResponseDto getEmployeeById(Long id) {
        log.info("inside getEmployeeById() method");
        EmployeeDto employeeDto = employeeRepository.findById(id)
                .map(EmployeeMapper::mapToEmployeeDto)
                .orElseThrow(() -> new EmployeeException(EmployeeError.EMPLOYEE_NOT_FOUND, String.format("Employee with id: %s not found.", id)));

        DepartmentDto departmentDto = departmentClient.getDepartment(employeeDto.getDepartmentCode());
        OrganisationDto organisationDto = organisationClient.getOrganisation(employeeDto.getOrganisationCode());

        return new ApiResponseDto(employeeDto,departmentDto,organisationDto);
    }

    public ApiResponseDto getDefaultDepartment(Long id, Exception exception){
        log.info("inside getDefaultDepartment() method");
        EmployeeDto employeeDto = employeeRepository.findById(id)
                .map(EmployeeMapper::mapToEmployeeDto)
                .orElseThrow(() -> new EmployeeException(EmployeeError.EMPLOYEE_NOT_FOUND, String.format("Employee with id: %s not found.", id)));

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("R&D Department");
        departmentDto.setCode("RD001");
        departmentDto.setDescription("Research and Development Department");

        OrganisationDto organisationDto = new OrganisationDto();
        organisationDto.setName("def");
        organisationDto.setDescription("def");
        organisationDto.setCode("def");

        return new ApiResponseDto(employeeDto,departmentDto,organisationDto);
    }
}
