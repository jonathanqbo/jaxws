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

package bq.cxf.tutorial.wssecuritypolicy.usernametoken;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.systest.ws.common.SecurityTestUtil;
import org.apache.cxf.systest.ws.common.TestParam;
import org.apache.cxf.testutil.common.AbstractBusClientServerTestBase;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import bq.cxf.tutorial.wssecuritypolicy.gen.Echoservice;

/**
 * A set of tests for Username Tokens over the Transport Binding. 
 */
@RunWith(value = org.junit.runners.Parameterized.class)
public class UsernameTokenTest extends AbstractBusClientServerTestBase {
    static final String PORT = "8080";
    
    private static final String NAMESPACE = "http://bq.cxf.tutorial/echoservice";
    private static final QName SERVICE_QNAME = new QName(NAMESPACE, "echoservice");

    final TestParam test;
    
    public UsernameTokenTest(TestParam type) {
        this.test = type;
    }
    
    @BeforeClass
    public static void startServers() throws Exception {
        assertTrue(
            "Server failed to launch",
            // run the server in the same process
            // set this to false to fork
            launchServer(Server.class, true)
        );
    }
    
    @Parameters
    public static Collection<TestParam[]> data() {
       
        return Arrays.asList(new TestParam[][] {{new TestParam(PORT, false)},
                                                {new TestParam(PORT, true)},
        });
    }
    
    @org.junit.AfterClass
    public static void cleanup() throws Exception {
        SecurityTestUtil.cleanup();
        stopAllServers();
    }

    @Test
    public void testPlaintext() throws Exception {
        test("EchoPlaintextPort");
    }
    
    @Test
    public void testEchoPlaintextNoPasswordPort() throws Exception{
    	test("EchoPlaintextNoPasswordPort");
    }
    
    @Test
    public void testEchoDigestPort() throws Exception{
    	test("EchoDigestPort");
    }
    
    @Test
    public void testEchoAsymmetricSESupportingPort() throws Exception{
    	test("EchoAsymmetricSESupportingPort");
    }
    
    @Test
    public void testEchoSymmetricSESupportingPort() throws Exception{
    	test("EchoDigestPort");
    }
    
    @Test
    public void testEchoAsymmetricEncrSupportingPort() throws Exception{
    	test("EchoAsymmetricEncrSupportingPort");
    }
    
    public void test(String port) throws Exception {
        SpringBusFactory bf = new SpringBusFactory();
        
        URL busFile = UsernameTokenTest.class.getResource("/bq/cxf/tutorial/wssecuritypolicy/usernametoken/client.xml");
        
        Bus bus = bf.createBus(busFile.toString());
        SpringBusFactory.setDefaultBus(bus);
        SpringBusFactory.setThreadDefaultBus(bus);

        URL wsdl = UsernameTokenTest.class.getResource("/bq/cxf/tutorial/wssecuritypolicy/usernametoken/echoservice.wsdl").toURI().toURL();
        Service service = Service.create(wsdl, SERVICE_QNAME);
        QName portQName = new QName(NAMESPACE, port);
        Echoservice echoService = service.getPort(portQName, Echoservice.class);
        updateAddressPort(echoService, test.getPort());
        
        echoService.echo("hello world!");
        
        ((java.io.Closeable)echoService).close();
        bus.shutdown(true);
    }
    
//    @Test
//    public void testPasswordHashedReplay() throws Exception {
//        SpringBusFactory bf = new SpringBusFactory();
//        URL busFile = UsernameTokenTest.class.getResource("client.xml");
//
//        Bus bus = bf.createBus(busFile.toString());
//        SpringBusFactory.setDefaultBus(bus);
//        SpringBusFactory.setThreadDefaultBus(bus);
//
//        URL wsdl = UsernameTokenTest.class.getResource("DoubleItUt.wsdl");
//        Service service = Service.create(wsdl, SERVICE_QNAME);
//        
//        QName portQName = new QName(NAMESPACE, "DoubleItDigestPort");
//        DoubleItPortType utPort = 
//                service.getPort(portQName, DoubleItPortType.class);
//        updateAddressPort(utPort, test.getPort());
//        
//        if (!test.isStreaming()) {
//            Client cxfClient = ClientProxy.getClient(utPort);
//            SecurityHeaderCacheInterceptor cacheInterceptor = new SecurityHeaderCacheInterceptor();
//            cxfClient.getOutInterceptors().add(cacheInterceptor);
//            
//            // Make two invocations with the same UsernameToken
//            utPort.doubleIt(25);
//            try {
//                utPort.doubleIt(25);
//                fail("Failure expected on a replayed UsernameToken");
//            } catch (javax.xml.ws.soap.SOAPFaultException ex) {
//                String error = "A replay attack has been detected";
//                String error2 = "The security token could not be authenticated or authorized";
//                assertTrue(ex.getMessage().contains(error) || ex.getMessage().contains(error2));
//            }
//        }
//        
//        ((java.io.Closeable)utPort).close();
//        bus.shutdown(true);
//    }
}
