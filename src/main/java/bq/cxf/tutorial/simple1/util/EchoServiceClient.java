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

package bq.cxf.tutorial.simple1.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import bq.cxf.tutorial.simple1.EchoException;
import bq.cxf.tutorial.simple1.EchoService;
import bq.cxf.tutorial.simple1.Message;

/**
 * <b>  </b>
 *
 * <p> </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Feb 20, 2014 4:03:53 PM
 *
 */
public class EchoServiceClient {

	private EchoService echoService = null;
	
	public EchoServiceClient(EchoService service){
		this.echoService = service;
	}
	
	public void test(){
		System.out.println("--- echo string msg ---");
		String msg = echoService.echoString("hello, world!");
		System.out.println("get echo : " + msg);
		
		System.out.println("--- echo object msg ---");
		Message msgObj = new Message();
		msgObj.setId("message1");
		msgObj.setContent("hello, world!");
		Message msgEchoObj = echoService.echoObject(msgObj);
		print(msgEchoObj);
		
		//
		System.out.println("--- echo list object msg ---");
		List<Message> listMsgs = new ArrayList<>();
		for(int i = 0; i < 5; i++){
			Message aMsg = new Message();
			aMsg.setId("message" + i);
			aMsg.setContent("say hello, world " + i);
			listMsgs.add(aMsg);
		}
		List<Message> echoListMsgs = echoService.echoList(listMsgs);
		print(echoListMsgs);
		
		//
		System.out.println("--- echo map object msg ---");
		Map<String, Message> mapMsgs = new HashMap<String, Message>();
		for(int i = 0; i < 5; i++){
			Message aMsg = new Message();
			aMsg.setId("message" + i);
			aMsg.setContent("say hello, world " + i);
			mapMsgs.put("type" + i, aMsg);
		}
		Map<String, Message> echoMapMsgs = echoService.echoMap(mapMsgs);
		print(echoMapMsgs);
		
		//
		System.out.println("--- echo exception ---");
		try {
			echoService.echoException("world eclapsed!");
		} catch (EchoException e1) {
			e1.printStackTrace();
		}
	}
	
	private void print(Map<String, Message> echoMapMsgs) {
		for(Entry<String, Message> msgEntry : echoMapMsgs.entrySet()){
			System.out.println(msgEntry.getKey());
			print(msgEntry.getValue());
		}
	}

	private void print(List<Message> echoListMsgs) {
		for(Message aMsg : echoListMsgs)
			print(aMsg);
	}

	private void print(Message msg) {
		System.out.println(msg.getId() + " | " + msg.getContent() + " | " + msg.getTs());
	}
	
}
