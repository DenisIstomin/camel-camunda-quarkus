package org.example;

import org.apache.camel.Exchange;
import org.apache.camel.FluentProducerTemplate;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/")
public class HttpProcessService {

    @Inject
    FluentProducerTemplate producer;

    @GET
    @Path("/start-process")
    @Produces(MediaType.TEXT_PLAIN)
    public String startProcessInstance() {
        Exchange exchange = producer.to("direct:start").send();
        return exchange.getMessage().getBody(String.class);
    }

    @GET
    @Path("/sync-in-out")
    @Produces(MediaType.TEXT_PLAIN)
    public String syncInOut(@QueryParam("param") String paramValue) {
        Exchange exchange = producer.to("direct:sync-in-out").withBody(paramValue).send();
        return exchange.getMessage().getBody(String.class);
    }
}
