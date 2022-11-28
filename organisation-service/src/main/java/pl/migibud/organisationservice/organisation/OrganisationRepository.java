package pl.migibud.organisationservice.organisation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface OrganisationRepository extends JpaRepository<Organisation,Long> {
    Optional<Organisation> findByCode(String code);
}
