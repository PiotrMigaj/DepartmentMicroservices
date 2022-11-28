package pl.migibud.organisationservice.organisation;


interface OrganisationService {

    OrganisationDto saveOrganisation(OrganisationDto organisationDto);
    OrganisationDto getOrganisationByCode(String code);

}
