package pl.migibud.employeeservice.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.migibud.employeeservice.department.DepartmentDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
class ApiResponseDto {
    private EmployeeDto employeeDto;
    private DepartmentDto departmentDto;
}
