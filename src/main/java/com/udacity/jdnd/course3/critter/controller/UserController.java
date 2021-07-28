package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.user.*;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final CustomerService customerService;

    private final EmployeeService employeeService;

    public UserController(CustomerService customerService, EmployeeService employeeService) {
        this.customerService = customerService;
        this.employeeService = employeeService;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = convertCustomerDtoToEntity(customerDTO);
        Customer newCustomer = customerService.saveCustomer(customer);
        CustomerDTO customerDTOCreated = convertCustomerEntityToDto(newCustomer);
        return customerDTOCreated;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomer();
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(c->convertCustomerEntityToDto(c))
                .collect(Collectors.toList());
         return customerDTOS;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer = customerService.getOwnerByPet(petId);
        CustomerDTO customerDTO = convertCustomerEntityToDto(customer);
        return customerDTO;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = convertEmployeeDtoToEntity(employeeDTO);
        Employee newEmployee = employeeService.saveEmployee(employee);
        EmployeeDTO employeeDTOCreated = convertEmployeeEntityToDto(newEmployee);
        return employeeDTOCreated;

    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        return convertEmployeeEntityToDto(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {

        List<Employee> employees = employeeService.findEmployeesForService(employeeDTO.getSkills(),
                employeeDTO.getDate());

        List<EmployeeDTO> employeeDTOS = employees.stream()
                .map(employee -> convertEmployeeEntityToDto(employee))
                .collect(Collectors.toList());

        return employeeDTOS;
    }

    private CustomerDTO convertCustomerEntityToDto(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        if(customer.getPets() != null) {
            customerDTO.setPetIds(customer.getPets()
                    .stream()
                    .map(pet -> pet.getId())
                    .collect(Collectors.toList()));
        }
        return customerDTO;
    }

    private Customer convertCustomerDtoToEntity(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return customer;
    }

    private EmployeeDTO convertEmployeeEntityToDto(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee,employeeDTO);
        return employeeDTO;
    }

    private Employee convertEmployeeDtoToEntity(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);
        return employee;
    }
}
