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

package bq.cxf.tutorial.wssecuritypolicy.x509;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.systest.wssec.examples.common.SecurityTestUtil;
import org.apache.cxf.systest.wssec.examples.common.TestParam;
import org.apache.cxf.testutil.common.AbstractBusClientServerTestBase;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import bq.cxf.tutorial.wssecuritypolicy.gen.Echoservice;

/**
 * A set of tests for X509 Tokens using policies defined in the OASIS spec:
 * "WS-SecurityPolicy Examples Version 1.0".
 */
@RunWith(value = org.junit.runners.Parameterized.class)
public class X509TokenTest extends AbstractBusClientServerTestBase {
    static final String PORT = "8080";
    
    private static final String NAMESPACE = "http://bq.cxf.tutorial/echoservice";
    private static final QName SERVICE_QNAME = new QName(NAMESPACE, "echoservice");
    
    final TestParam test;
    
    public X509TokenTest(TestParam type) {
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

    /**
     * 2.2.1 (WSS1.0) X.509 Certificates, Sign, Encrypt
     */
    @org.junit.Test
    public void testAsymmetricSignEncrypt() throws Exception {
    	test("EchoAsymmetricSignEncryptPort");
    }
    
    /**
     * 2.2.2 (WSS1.0) Mutual Authentication with X.509 Certificates, Sign, Encrypt
     */
    @org.junit.Test
    public void testAsymmetricProtectTokens() throws Exception {
    	test("EchoAsymmetricProtectTokensPort");
    }
    
    /**
     * 2.2.3 (WSS1.1) Anonymous with X.509 Certificate, Sign, Encrypt
     */
    @org.junit.Test
    public void testSymmetricSignEncrypt() throws Exception {
    	test("EchoSymmetricSignEncryptPort");
    }
    
    /**
     * 2.2.4 (WSS1.1) Mutual Authentication with X.509 Certificates, Sign, Encrypt
     */
    @org.junit.Test
    public void testSymmetricEndorsing() throws Exception {
        test("EchoSymmetricEndorsingPort");
    }

	private void test(String port) throws MalformedURLException, IOException {
		SpringBusFactory bf = new SpringBusFactory();
        URL busFile = X509TokenTest.class.getResource("/bq/cxf/tutorial/wssecuritypolicy/x509/client.xml");

        Bus bus = bf.createBus(busFile.toString());
        SpringBusFactory.setDefaultBus(bus);
        SpringBusFactory.setThreadDefaultBus(bus);

        URL wsdl = X509TokenTest.class.getResource("/bq/cxf/tutorial/wssecuritypolicy/x509/echoservice.wsdl");
        Service service = Service.create(wsdl, SERVICE_QNAME);
        QName portQName = new QName(NAMESPACE, port);
        Echoservice echoService = 
                service.getPort(portQName, Echoservice.class);
        updateAddressPort(echoService, test.getPort());
      
        echoService.echo("hello, world!");
        
        ((java.io.Closeable)echoService).close();
        bus.shutdown(true);
	}
    
    
    
}
