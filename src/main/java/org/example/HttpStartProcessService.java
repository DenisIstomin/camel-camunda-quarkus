package org.example;

import org.apache.camel.Exchange;
import org.apache.camel.FluentProducerTemplate;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/start-process")
public class HttpStartProcessService {

    @Inject
    FluentProducerTemplate producer;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String startProcessInstance() {
        Exchange exchange = producer.to("direct:start").send();
        return exchange.getMessage().getBody(String.class);
    }
}
