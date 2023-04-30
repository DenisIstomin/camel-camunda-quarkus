package org.example;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class ServiceDelegateBean implements JavaDelegate {

    public static final String VARIABLE_RESULT = "result";

    @Override
    public void execute(DelegateExecution execution) {
        Object in = execution.getVariable("in");
        execution.setVariable(VARIABLE_RESULT, in + " done");
        Logger.getLogger(this.getClass())
                .infov("\n\nService Task called. Hurray!!");
    }

}
