package com.mclebtec.activity;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface EmployeeAuditActivity {

  @ActivityMethod
  void computeEmployeeAuditDetails(String employeeId);

  @ActivityMethod
  String getEmployeeAuditDetails(String employeeId);

}
