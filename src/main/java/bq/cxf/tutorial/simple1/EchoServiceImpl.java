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

package bq.cxf.tutorial.simple1;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * <b>  </b>
 *
 * <p> </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Feb 19, 2014 10:27:41 PM
 *
 */
@WebService(endpointInterface="bq.cxf.tutorial.simple1.EchoService")
public class EchoServiceImpl implements EchoService{

	@Override
	public String echoString(String msg) {
		System.out.println("echo msg : " + msg);
		return msg;
	}

	@Override
	public Message echoObject(Message msg) {
		msg.setContent("echo : " + msg.getContent());
		msg.setTs(new Date(System.currentTimeMillis()));
		return msg;
	}

	@Override
	public List<Message> echoList(List<Message> msgs) {
		for(Message msg : msgs){
			msg.setContent("echo : " + msg.getContent());
			msg.setTs(new Date(System.currentTimeMillis()));
		}
		return msgs;
	}

	@Override
	public Map<String, Message> echoMap(Map<String, Message> msgs) {
		Message msg = null;
		for(Entry<String, Message> entry : msgs.entrySet()){
			msg = entry.getValue();
			msg.setContent("echo : " + msg.getContent());
			msg.setTs(new Date(System.currentTimeMillis()));
		}
		return msgs;
	}

	@Override
	public void echoException(String msg) throws EchoException {
		EchoException echoE = new EchoException("echo:" + msg);
		throw echoE;
	}

}
