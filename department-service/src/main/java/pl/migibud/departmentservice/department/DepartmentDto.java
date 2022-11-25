package pl.migibud.departmentservice.department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
class DepartmentDto {
    private Long id;
    private String name;
    private String description;
    private String code;
}
