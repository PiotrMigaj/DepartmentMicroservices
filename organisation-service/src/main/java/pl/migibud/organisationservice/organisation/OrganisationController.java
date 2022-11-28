package pl.migibud.organisationservice.organisation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/organisations")
@RequiredArgsConstructor
class OrganisationController {

    private final OrganisationService organisationService;

    @PostMapping
    ResponseEntity<OrganisationDto> saveOrganisation(@RequestBody OrganisationDto organisationDto){
        OrganisationDto savedOrganisation = organisationService.saveOrganisation(organisationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrganisation);
    }

    @GetMapping("/{code}")
    ResponseEntity<OrganisationDto> getOrganisation(@PathVariable String code){
        OrganisationDto organisationDto = organisationService.getOrganisationByCode(code);
        return ResponseEntity.ok(organisationDto);
    }
}
