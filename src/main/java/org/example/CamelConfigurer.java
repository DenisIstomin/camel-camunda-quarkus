package org.example;

import org.apache.camel.quarkus.core.events.ComponentAddEvent;
import org.apache.camel.spi.CamelEvent;
import org.camunda.bpm.camel.component.CamundaBpmComponent;
import org.camunda.bpm.engine.ProcessEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class CamelConfigurer {
    @Inject
    ProcessEngine processEngine;

    private static final Logger LOGGER = LoggerFactory.getLogger(CamelConfigurer.class);

    public void onComponentAdd(@Observes ComponentAddEvent event) {
        if (event.getComponent() instanceof CamundaBpmComponent) {
            LOGGER.info("CamundaBpmComponent added!");
        }
    }

    public void onCamelContextInitializing(@Observes CamelEvent.CamelContextInitializingEvent event) {
        CamundaBpmComponent component = new CamundaBpmComponent(processEngine);
        event.getContext().addComponent("camunda-bpm", component);
    }
}