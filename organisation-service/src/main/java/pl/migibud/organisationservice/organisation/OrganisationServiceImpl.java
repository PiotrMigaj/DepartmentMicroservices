package pl.migibud.organisationservice.organisation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.migibud.organisationservice.exception.OrganisationError;
import pl.migibud.organisationservice.exception.OrganisationException;

@Slf4j
@Service
@RequiredArgsConstructor
class OrganisationServiceImpl implements OrganisationService {

    private final OrganisationRepository organisationRepository;

    @Override
    public OrganisationDto saveOrganisation(OrganisationDto organisationDto) {
        Organisation organisation = OrganisationMapper.mapToOrganisation(organisationDto);
        Organisation savedOrganization = organisationRepository.save(organisation);
        return OrganisationMapper.mapToDto(savedOrganization);
    }

    @Override
    public OrganisationDto getOrganisationByCode(String code) {
        return organisationRepository.findByCode(code)
                .map(OrganisationMapper::mapToDto)
                .orElseThrow(() -> new OrganisationException(OrganisationError.ORGANISATION_NOT_FOUND,
                        String.format("Organisation with code: %s not found.", code)
                ));
    }
}
