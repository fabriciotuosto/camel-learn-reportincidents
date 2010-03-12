package org.fgt.camel.learn.incidents;

import org.apache.camel.CamelContext;
import org.apache.camel.Consumer;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultCamelContext;


public class ReportIncidentEndpointImpl implements ReportIncidentEndpoint {

	private CamelContext camel;
//	private ProducerTemplate template;

	public ReportIncidentEndpointImpl() throws Exception{
		camel = new DefaultCamelContext();
		camel.addRoutes(new ReportIncidentRoutes());
//		template = camel.createProducerTemplate();
		addMailSenderConsumer();
		camel.start();
	}
	
	private void addMailSenderConsumer() throws Exception{
		Endpoint endpoint = camel.getEndpoint("file://target/subfolder?consumer.initialDelay=2000");
		Consumer consumer = endpoint.createConsumer(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				System.out.println("Sending email... "+ exchange.getIn().getBody(String.class));
				
			}
		});
		consumer.start();
	}

	@Override
	public OutputReportIncident reportIncident(InputReportIncident parameters) {
//		System.out.println("Hello ReportIncidentEndpointImpl is called from "
//				+ parameters.getGivenName());
//		sendToCamelLog(parameters.getGivenName());
//		template.sendBody("log:com.mycompany.part2.easy", parameters.getGivenName());
//		sendToCamelFile(parameters.getGivenName(),parameters.getIncidentId());
//		String filename = "easy-incident-" + parameters.getIncidentId() + ".txt";
//		template.sendBodyAndHeader("file://target/subfolder", parameters.getGivenName(),
//				FileComponent.DEFAULT_LOCK_FILE_POSTFIX, filename);

		
//		generateEmailBodyAndStoreAsFile(parameters);
		
		camel.createProducerTemplate().sendBody("direct:start",parameters);
		OutputReportIncident out = new OutputReportIncident();
		out.setCode("OK");
		return out;
	}
	
//	private void generateEmailBodyAndStoreAsFile(InputReportIncident parameters){
//		Object mailBody = template.sendBody("velocity:MailBody.vm",ExchangePattern.InOut,parameters);
//		String filename = "mail-incident"+parameters.getIncidentId()+".txt";
//		template.sendBodyAndHeader("file://target/subfolder", mailBody,FileComponent.FILE_EXCHANGE_FILE,filename);	
//	}

//	private void sendToCamelLog(String name) {
//		try{
//			Component component = camel.getComponent("log");
//			Endpoint endpoint   = component.createEndpoint("log:com.mycompany.part2");
//			Exchange exchange   = endpoint.createExchange();
//			exchange.getIn().setBody(name);
//			Producer producer = endpoint.createProducer();
//			producer.start();
//			producer.process(exchange);
//			producer.stop();
//		}catch (Exception e) {
//			throw new Error(e);
//		}
//	}
//
//	
//	private void sendToCamelFile(String name,String incidentid){
//		try{
//			Component component = camel.getComponent("file");
//			Endpoint endpoint   = component.createEndpoint("file://target");
//			Exchange exchange   = endpoint.createExchange();
//			exchange.getIn().setBody(name);
//			exchange.getIn().setHeader(FileComponent.FILE_EXCHANGE_FILE, "incident-"+incidentid+".txt");
//			Producer producer = endpoint.createProducer();
//			producer.start();
//			producer.process(exchange);
//			producer.stop();
//		}catch (Exception e) {
//			throw new Error(e);
//		}		
//	}
}
