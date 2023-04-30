package org.example;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.camunda.bpm.camel.component.CamundaBpmComponent;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.history.HistoricVariableInstance;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CamelRoute extends EndpointRouteBuilder {
    @Override
    public void configure() {
        // Enable autoStartup for debug purposes
        from(timer("foo").fixedRate(true).period(1000))
                .to(direct("start")).autoStartup(false);

        from(direct("start"))
                .to("camunda-bpm://start?processDefinitionKey=process")
                .log(LoggingLevel.INFO, "Process instance with id ${body} started!");

        from(direct("sync-in-out"))
                .to("camunda-bpm://start?processDefinitionKey=process&copyBodyAsVariable=in")
                .process(x -> {
                    String instanceId = x.getMessage().getBody(String.class);
                    final HistoricVariableInstance variable = queryVariableFromInstance(
                            instanceId, ServiceDelegateBean.VARIABLE_RESULT);
                    x.getMessage().setBody(variable.getValue());
                })
                .log(LoggingLevel.INFO, "Process result: ${body}!");
    }

    private HistoricVariableInstance queryVariableFromInstance(final String instanceId, final String variableName) {
        CamundaBpmComponent camundaBpmComponent = this.getContext().getComponent("camunda-bpm", CamundaBpmComponent.class);
        ProcessEngine processEngine = camundaBpmComponent.getProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        return historyService.createHistoricVariableInstanceQuery()
                .processInstanceIdIn(instanceId)
                .variableName(variableName)
                .singleResult();
    }
}
