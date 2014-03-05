/*
Copyright (c) 2014 (Jonathan Q. Bo) 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package bq.cxf.tutorial.simple1.endpoint;

import javax.xml.ws.Endpoint;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;

import bq.cxf.tutorial.simple1.EchoServiceImpl;

/**
 * <b>  </b>
 *
 * <p> </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Feb 20, 2014 4:15:12 PM
 *
 */
public class Server {

	private Server() throws Exception {
        EchoServiceImpl service = new EchoServiceImpl();
        String address = "http://localhost:8081/jaxws/ws/echoservice";
        EndpointImpl jaxWsEndpoint = (EndpointImpl)Endpoint.publish(address, service);
        
        // force type cast just to used to add interceptors
        jaxWsEndpoint.getInInterceptors().add(new LoggingInInterceptor());
        jaxWsEndpoint.getOutInterceptors().add(new LoggingOutInterceptor());
    }

    public static void main(String args[]) {
        try {
			new Server();
		} catch (Exception e) {
			e.printStackTrace();
		}
        System.out.println("Server ready...");

        try {
			Thread.sleep(5 * 60 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println("Server exiting");
        System.exit(0);
    }
	
}
