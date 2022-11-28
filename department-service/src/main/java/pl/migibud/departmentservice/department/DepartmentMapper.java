package pl.migibud.departmentservice.department;

class DepartmentMapper {

    private DepartmentMapper() {
    }

    static DepartmentDto mapToDepartmentDto(Department department){
        return new DepartmentDto(
                department.getId(),
                department.getName(),
                department.getDescription(),
                department.getCode()
        );
    }

    static Department mapToDepartment(DepartmentDto departmentDto){
        return new Department(
                departmentDto.getId(),
                departmentDto.getName(),
                departmentDto.getDescription(),
                departmentDto.getCode()
        );
    }
}
