package pl.migibud.employeeservice.organisation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganisationDto {
    private Long id;
    private String name;
    private String description;
    private String code;
    private LocalDateTime createdAt;
}
