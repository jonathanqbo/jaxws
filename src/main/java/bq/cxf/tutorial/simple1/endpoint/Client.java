/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package bq.cxf.tutorial.simple1.endpoint;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import bq.cxf.tutorial.simple1.EchoService;
import bq.cxf.tutorial.simple1.util.EchoServiceClient;

public final class Client {

    public static void main(String args[]) {
    	QName SERVICE_NAME = new QName("http://simple1.tutorial.cxf.bq/", "EchoService");
    	QName PORT_NAME = new QName("http://simple1.tutorial.cxf.bq/", "EchoServicePort");
    	String ENDPOINT = "http://localhost:8081/jaxws/ws/echoservice";
//    	String ENDPOINT = "http://localhost:9999/jaxws/ws/echoservice";
    	
        Service service = Service.create(SERVICE_NAME);
        service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, ENDPOINT);
        EchoService echoService = service.getPort(EchoService.class);

        EchoServiceClient client = new EchoServiceClient(echoService);
        client.test();
    }

}
