package pl.migibud.employeeservice.organisation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANISATION-SERVICE")
public interface OrganisationClient {
    @GetMapping("api/organisations/{code}")
    OrganisationDto getOrganisation(@PathVariable("code") String code);
}
