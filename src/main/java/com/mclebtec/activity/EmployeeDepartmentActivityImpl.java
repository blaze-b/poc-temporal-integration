package com.mclebtec.activity;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class EmployeeDepartmentActivityImpl implements EmployeeDepartmentActivity {
  @Override
  public Boolean checkEmployeeIsActiveInDepartment(String employeeId) {
    log.info("Starting::checkEmployeeIsActiveInDepartment::employeeId::{}", employeeId);
    try {
      Thread.sleep(Duration.ofSeconds(5).toMillis());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    log.info("Ending::checkEmployeeIsActiveInDepartment::employeeId::{}", employeeId);
    return true;

  }

  @Override
  public void addEmployeeToTheDepartment(String employeeId) {
    log.info("Starting::addEmployeeToTheDepartment::employeeId::{}", employeeId);
    try {
      Thread.sleep(Duration.ofSeconds(5).toMillis());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    log.info("Ending::addEmployeeToTheDepartment::employeeId::{}", employeeId);
  }

  @Override
  public Map<String, String> getEmployeeDepartmentDetails(String employeeId) {
    log.info("Starting::getEmployeeDepartmentDetails::employeeId::{}", employeeId);
    try {
      Thread.sleep(Duration.ofSeconds(5).toMillis());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    log.info("Ending::getEmployeeDepartmentDetails::employeeId::{}", employeeId);
    return new HashMap<>(Collections.singletonMap(employeeId, "DEPT_"+UUID.randomUUID()));
  }
}
