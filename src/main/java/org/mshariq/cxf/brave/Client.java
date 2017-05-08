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

package org.mshariq.cxf.brave;

import java.util.Arrays;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.tracing.brave.jaxrs.BraveClientProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.kristofa.brave.Brave;

import zipkin.Span;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.okhttp3.OkHttpSender;

public final class Client {
    private Client() {
    }

    public static void main(String args[]) throws Exception {

    	  OkHttpSender sender = OkHttpSender.create("http://127.0.0.1:9411/api/v1/spans");
          AsyncReporter<Span> reporter = AsyncReporter.builder(sender).build();
          Brave brave = new Brave.Builder("clientNew").reporter(reporter).build();
          final BraveClientProvider provider = new BraveClientProvider(brave);

          final Response response = WebClient
              .create("http://localhost:9000/catalog/2", Arrays.asList(provider))
              .accept(MediaType.APPLICATION_JSON)
              .get();

          
    	ApplicationContext appctxt =
            new ClassPathXmlApplicationContext(Client.class.getResource("/context-client.xml").toString());

        System.out.println("Client ready...");
        SampleInterface client = (SampleInterface) appctxt.getBean("sampleClient");
        System.out.println(client.getItems(0) );
    response.close();
    reporter.close();
    sender.close();
    
      //  Thread.sleep(5 * 6000 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}
