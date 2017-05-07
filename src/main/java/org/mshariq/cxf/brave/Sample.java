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

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

public class Sample implements SampleInterface {
	private Map<String, Item> items;

	public Sample() {
		items = Collections.synchronizedMap(new TreeMap<String, Item>(String.CASE_INSENSITIVE_ORDER));
		items.put("Item 1", new Item("Item 1", "Value 1"));
		items.put("Item 2", new Item("Item 2", "Value 2"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mshariq.cxf.brave.SampleInterface#getItems(int)
	 */
	public Response getItems(int page) {

		return Response.ok(items.values()).build();
	}

	public Item getItem(final String language, String name) {
		return items.get(name);
	}

	public Response createItem(final UriInfo uriInfo, final Item item) {
		items.put(item.getName(), item);
		return Response.created(uriInfo.getBaseUriBuilder().path(item.getName()).build()).entity(item).build();
	}

	public Item updateItem(String name, String value) {
		Item item = new Item(name, value);
		items.put(name, item);
		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mshariq.cxf.brave.SampleInterface#delete(java.lang.String)
	 */
	public Response delete(String name) {
		items.remove(name);
		return Response.ok().build();
	}
}
