package com.mclebtec.worker;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface EmployeeWorkFlow {

  @WorkflowMethod
  void refreshEmployeeDetails(String employeeId);

}
