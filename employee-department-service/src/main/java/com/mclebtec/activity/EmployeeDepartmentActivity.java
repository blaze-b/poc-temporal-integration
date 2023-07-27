package com.mclebtec.activity;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

import java.util.Map;

@ActivityInterface
public interface EmployeeDepartmentActivity {

  @ActivityMethod
  Boolean checkEmployeeIsActiveInDepartment(String employeeId);

  @ActivityMethod
  void addEmployeeToDepartment(String employeeId);

  @ActivityMethod
  Map<String, String> getEmployeeDepartmentDetails(String employeeId);

}
