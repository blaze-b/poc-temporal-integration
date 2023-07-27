package com.mclebtec.controller;

import com.mclebtec.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  private final EmployeeService solutionService;

  public EmployeeController(EmployeeService solutionService) {this.solutionService = solutionService;}

  @GetMapping("/")
  public String ping() {
    return "Hello";
  }

  @GetMapping("/employee/onboard")
  public String onboardEmployee(@RequestParam("employee_id") String employeeId) {
    return solutionService.onboard(employeeId);
  }

}
