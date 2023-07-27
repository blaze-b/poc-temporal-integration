package com.mclebtec.activity;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.UUID;

@Slf4j
public class EmployeeAuditActivityImpl implements EmployeeAuditActivity {
  @Override
  public void computeEmployeeAuditDetails(String employeeId) {
    log.info("Starting::computeEmployeeAuditDetails::employeeId::{}", employeeId);
    try {
      Thread.sleep(Duration.ofSeconds(5).toMillis());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    log.info("Ending::computeEmployeeAuditDetails::employeeId::{}", employeeId);
  }

  @Override
  public String getEmployeeAuditDetails(String employeeId) {
    log.info("Starting::getEmployeeDetails::employeeId::{}", employeeId);
    try {
      Thread.sleep(Duration.ofSeconds(5).toMillis());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    log.info("Ending::getEmployeeDetails::employeeId::{}", employeeId);
    return "AUDIT_" + UUID.randomUUID();
  }
}
