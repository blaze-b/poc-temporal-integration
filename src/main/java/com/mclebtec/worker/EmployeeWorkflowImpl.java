package com.mclebtec.worker;

import com.mclebtec.activity.EmployeeAuditActivity;
import com.mclebtec.activity.EmployeeDepartmentActivity;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Map;

@Slf4j
public class EmployeeWorkflowImpl implements EmployeeWorkFlow {

  private final RetryOptions retryOptions =
      RetryOptions.newBuilder().setInitialInterval(Duration.ofSeconds(1)).setMaximumInterval(Duration.ofSeconds(100))
          .setBackoffCoefficient(2).setMaximumAttempts(50000).build();

  ActivityOptions EmployeeDepartmentOptions =
      ActivityOptions.newBuilder().setScheduleToCloseTimeout(Duration.ofSeconds(30)).setRetryOptions(retryOptions)
          .build();
  private final EmployeeDepartmentActivity employeeDepartmentActivity =
      Workflow.newActivityStub(EmployeeDepartmentActivity.class, EmployeeDepartmentOptions);

  ActivityOptions employeeAuditOptions =
      ActivityOptions.newBuilder().setScheduleToCloseTimeout(Duration.ofSeconds(30)).setRetryOptions(retryOptions)
          .build();
  private final EmployeeAuditActivity employeeAuditActivity =
      Workflow.newActivityStub(EmployeeAuditActivity.class, employeeAuditOptions);

  @Override
  public void refreshEmployeeDetails(String employeeId) {
    log.info("Initiating employee refresh details, employee Id::{}", employeeId);
    if (employeeDepartmentActivity.checkEmployeeIsActiveInDepartment(employeeId)) {
      employeeDepartmentActivity.addEmployeeToTheDepartment(employeeId);
      Map<String, String> employeeDepartmentDetails =
          employeeDepartmentActivity.getEmployeeDepartmentDetails(employeeId);
      log.info("employee_department_details::{}", employeeDepartmentDetails.get(employeeId));
    }
    Workflow.sleep(Duration.ofSeconds(30));
    employeeAuditActivity.computeEmployeeAuditDetails(employeeId);
    String employeeAuditId = employeeAuditActivity.getEmployeeAuditDetails(employeeId);
    log.info("employee_audit_id::{}", employeeAuditId);
  }

}
