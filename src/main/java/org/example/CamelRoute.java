package org.example;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;

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
    }
}
