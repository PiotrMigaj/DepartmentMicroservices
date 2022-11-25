package pl.migibud.departmentservice.department;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface DepartmentRepository extends JpaRepository<Department,Long> {
    Optional<Department> findByCode(String code);
}
