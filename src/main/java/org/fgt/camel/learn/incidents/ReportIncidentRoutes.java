package org.fgt.camel.learn.incidents;

import org.apache.camel.builder.RouteBuilder;

public class ReportIncidentRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:start")
			.to("velocity:MailBody.vm")
			.to("log:org.fgt.log")
			.to("file://target/subfolder");
		
		from("file://target/subfolder")
			.setHeader("subject",constant("new incident reported"))
//			.to("smtp://fabricio.tuosto@gmail.com?password=xxxx&to=incident@mycompany.com")
			.to("log:org.fgt.log");
	}

}
