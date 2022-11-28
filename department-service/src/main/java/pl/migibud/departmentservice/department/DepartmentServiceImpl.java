package pl.migibud.departmentservice.department;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.migibud.departmentservice.exception.DepartmentError;
import pl.migibud.departmentservice.exception.DepartmentException;

@Slf4j
@Service
@RequiredArgsConstructor
class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        departmentRepository.findByCode(departmentDto.getCode())
                .ifPresent(
                        department -> {
                            throw new DepartmentException(DepartmentError.DEPARTMENT_WITH_CODE_ALREADY_EXISTS,
                                    String.format("Department with code: '%s' already exists.",department.getCode()));
                        }
                );

        Department department = DepartmentMapper.mapToDepartment(departmentDto);

        Department saveDepartment = departmentRepository.save(department);

        return DepartmentMapper.mapToDepartmentDto(saveDepartment);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {

        Department department = departmentRepository.findByCode(code)
                .orElseThrow(()->new DepartmentException(DepartmentError.DEPARTMENT_NOT_FOUND,String.format("Department with code: %s not found.",code)));

        return DepartmentMapper.mapToDepartmentDto(department);
    }
}
