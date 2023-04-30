package org.example;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class ServiceDelegateBean implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        Logger.getLogger(this.getClass())
                .infov("\n\nService Task called. Hurray!!");
    }

}
