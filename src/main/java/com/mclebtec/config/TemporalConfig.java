package com.mclebtec.config;

import com.mclebtec.activity.EmployeeAuditActivityImpl;
import com.mclebtec.activity.EmployeeDepartmentActivityImpl;
import com.mclebtec.worker.EmployeeWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TemporalConfig {

  @Value("${temporal_server}")
  private String temporalServer;

  @Value("${temporal_url}")
  private String temporalUrl;

  @Value("${temporal_namespace}")
  private String temporalNamespace;

  @Value("${temporal_queue}")
  private String queueName;

  @Bean(name = "temporalQueueName")
  public String temporalQueueName() {
    return queueName;
  }

  @Bean
  public WorkflowServiceStubs workflowServiceStubs() {
    WorkflowServiceStubs workflowServiceStubs = (temporalServer.equalsIgnoreCase("local"))
        ? WorkflowServiceStubs.newLocalServiceStubs()
        : WorkflowServiceStubs.newServiceStubs(WorkflowServiceStubsOptions.newBuilder().setTarget(temporalUrl).build());
    log.info("workflowServiceStubs::{}", workflowServiceStubs.toString());
    return workflowServiceStubs;
  }

  @Bean
  public WorkflowClient workflowClient(WorkflowServiceStubs workflowServiceStubs) {
    return WorkflowClient.newInstance(workflowServiceStubs,
        WorkflowClientOptions.newBuilder().setNamespace(temporalNamespace).build());
  }

  @Bean
  public WorkerFactory workerFactory(WorkflowClient workflowClient) {
    return WorkerFactory.newInstance(workflowClient);
  }

  @Bean
  public Worker employeeWorkerRegister(WorkerFactory workerFactory) {
    log.info("worker-factory::{}, queue-name::{}", workerFactory, queueName);
    Worker worker = workerFactory.newWorker(queueName);
    worker.registerWorkflowImplementationTypes(EmployeeWorkflowImpl.class);
    worker.registerActivitiesImplementations(new EmployeeDepartmentActivityImpl(), new EmployeeAuditActivityImpl());
    log.info("final-worker-details::{}", worker.toString());
    try {
      workerFactory.start();
    } catch (Exception e) {
      log.error("factory worker not started::error-details::{}", e.getLocalizedMessage(), e.getCause());
    }
    return worker;
  }

  //  @Bean
  //  public DataConverter mainDataConverter() {
  //    return DataConverter.getDefaultInstance();
  //  }

}
