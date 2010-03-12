package org.fgt.camel.learn.incidents;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name="ReportIncident", targetNamespace = "http://incidents.learn.camel.fgt.org")
public interface ReportIncidentEndpoint {

	@WebMethod
	public OutputReportIncident reportIncident(@WebParam(name="InputReportIncident")InputReportIncident parameters);
}
