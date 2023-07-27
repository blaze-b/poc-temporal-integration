package com.mclebtec.service;

import com.mclebtec.worker.EmployeeWorkFlow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

public interface EmployeeService {
  String onboard(String employeeId);

}


@Service
class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  @Qualifier(value = "temporalQueueName")
  private String temporalQueueName;

  @Autowired
  private WorkflowClient workflowClient;


  @Override
  public String onboard(String employeeId) {
    WorkflowOptions workflowOptions =
        WorkflowOptions.newBuilder().setTaskQueue(temporalQueueName).setWorkflowId(employeeId).build();
    EmployeeWorkFlow workflow = workflowClient.newWorkflowStub(EmployeeWorkFlow.class, workflowOptions);
    WorkflowClient.start(workflow::refreshEmployeeDetails, employeeId);
    return "SUCCESS";

  }
}
