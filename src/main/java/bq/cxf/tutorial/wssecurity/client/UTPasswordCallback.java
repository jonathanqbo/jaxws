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

package bq.cxf.tutorial.wssecurity.client;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

/**
 * <b>  </b>
 *
 * <p> </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Feb 26, 2014 10:12:07 PM
 *
 */
public class UTPasswordCallback implements CallbackHandler {

	// set password at server side, and compare with the password setted at client side
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback pc : callbacks) {
            WSPasswordCallback wspc = (WSPasswordCallback)pc;

            //
            String action = null;
            switch(wspc.getUsage()){
            case WSPasswordCallback.USERNAME_TOKEN:
            	action = "username_token";
            	break;
            case WSPasswordCallback.DECRYPT:
            	action = "decrypt";
            	break;
            case WSPasswordCallback.SIGNATURE:
            	action = "signature";
            	break;
            default:
            	action = "unknow action:" + wspc.getUsage();
            }
            System.out.println("### action : " + action);
            
            if("bq".equals(wspc.getIdentifier()))
            	wspc.setPassword("bq_password");
            else if("server".equals(wspc.getIdentifier()))
            	wspc.setPassword("server_password");
            else if("client".equals(wspc.getIdentifier()))
            	wspc.setPassword("client_password");
            
            return;
        }
    }
}