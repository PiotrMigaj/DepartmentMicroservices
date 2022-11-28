package pl.migibud.organisationservice.organisation;

class OrganisationMapper {

    static OrganisationDto mapToDto(Organisation organisation){
        return new OrganisationDto(
                organisation.getId(),
                organisation.getName(),
                organisation.getDescription(),
                organisation.getCode(),
                organisation.getCreatedAt()
        );
    }

    static Organisation mapToOrganisation(OrganisationDto organisationDto){
        return new Organisation(
                organisationDto.getId(),
                organisationDto.getName(),
                organisationDto.getDescription(),
                organisationDto.getCode(),
                organisationDto.getCreatedAt()
        );
    }

}
