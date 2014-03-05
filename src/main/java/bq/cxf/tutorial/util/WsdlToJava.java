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

package bq.cxf.tutorial.util;

import org.apache.cxf.tools.wsdlto.WSDLToJava;
import org.junit.Test;

/**
 * <b>  </b>
 *
 * <p> </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Feb 23, 2014 10:01:58 AM
 *
 */
public class WsdlToJava {

//	@Test
	public void testWsdlFirst(){
//		generateServer("src/main/resource/bq/cxf/tutorial/wsdlfirst/echoservice.wsdl", "bq.cxf.tutorial.wsdlfirst.server.gen");
		generateClient("http://localhost:8080/jaxws/gen/echoservice?wsdl", "bq.cxf.tutorial.wsdlfirst.client.gen");
	}
	
	@Test
	public void testSoapHeader(){
		String wsdl = "src/main/resource/bq/cxf/tutorial/soapheader/echoservicewithheader.wsdl";
		String packagepath = "bq.cxf.tutorial.soapheader.server.gen";
		generateServer(wsdl, packagepath);
	}
	
	public static void generateServer(String wsdlfilepath, String packagepath){
		String[] cmd = new String[]{"-impl", "-server", "-p", packagepath, "-d", "src/main/java", wsdlfilepath};
		WSDLToJava.main(cmd);
	}
	
	public static void generateClient(String wsdlfilepath, String packagepath){
		String[] cmd = new String[]{"-client", "-p", packagepath, "-d", "src/main/java", wsdlfilepath};
		WSDLToJava.main(cmd);
	}
	
	public static void generateClientWithBind(String wsdlfilepath, String packagepath, String bindingfilepath){
		String[] cmd = new String[]{"-client", "-p", packagepath, "-b", bindingfilepath ,"-d", "src/main/java", wsdlfilepath};
		WSDLToJava.main(cmd);
	}
	
}
