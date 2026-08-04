package com.avinash.kumar.module2.service;

import com.avinash.kumar.module2.dto.EmployeeDTO;
import com.avinash.kumar.module2.entity.EmployeeEntity;
import com.avinash.kumar.module2.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository,ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public Optional<EmployeeDTO> findById(Long id) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        return employeeEntity.map( e-> mapper.map(employeeEntity,EmployeeDTO.class));
    }

    public List<EmployeeDTO> findAll() {
        List<EmployeeEntity> list =  employeeRepository.findAll();
        return list.stream().map(e -> mapper.map(e,EmployeeDTO.class)).toList();
    }

    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        EmployeeEntity employee = mapper.map(employeeDTO,EmployeeEntity.class);
        EmployeeEntity savedEmployee =  employeeRepository.save(employee);
        return mapper.map(savedEmployee,EmployeeDTO.class);
    }


    public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO, Long empId) {

        EmployeeEntity employeeEntity = mapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setId(empId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return mapper.map(savedEmployeeEntity,EmployeeDTO.class);
    }

    public void deleteEmployeeById(Long empId) {
        employeeRepository.deleteById(empId);
    }

    public EmployeeDTO updatePartialEmployee(Long empId, Map<String, Object> updates) {
        boolean exists = isExistsById(empId);
        if(!exists) return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(empId).get();
        updates.forEach((key,value)->{
           Field toUpdate =  ReflectionUtils.findField(EmployeeEntity.class,key);
            assert toUpdate != null;
            toUpdate.setAccessible(true);
           ReflectionUtils.setField(toUpdate,employeeEntity,value);
        });
        return mapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }

    public boolean isExistsById(Long id){
        return employeeRepository.existsById(id);
    }
}
