package pl.migibud.employeeservice.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findById(Long id);
    Optional<Employee> findByEmail(String email);
}
