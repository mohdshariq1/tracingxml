package org.mshariq.cxf.brave;

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

@Path("/sample")
@Api(value = "/sample", description = "Sample JAX-RS service with Swagger documentation")
public interface SampleInterface {

	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	@ApiOperation(value = "Get operation with Response and @Default value", notes = "Get operation with Response and @Default value", response = Item.class, responseContainer = "List")
	Response getItems(
	        @ApiParam(value = "Page to fetch", required = true) @QueryParam("page") @DefaultValue("1") int page);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mshariq.cxf.brave.SampleInterface#getItem(java.lang.String,
	 * java.lang.String)
	 */
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{name}")
	@GET
	@ApiOperation(value = "Get operation with type and headers", notes = "Get operation with type and headers", response = Item.class)
	Item getItem(
	        @ApiParam(value = "language", required = true) @HeaderParam("Accept-Language") final String language,
	        @ApiParam(value = "name", required = true) @PathParam("name") String name);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mshariq.cxf.brave.SampleInterface#createItem(javax.ws.rs.core.
	 * UriInfo, org.mshariq.cxf.brave.Item)
	 */
	@Consumes({ MediaType.APPLICATION_JSON })
	@POST
	@ApiOperation(value = "Post operation with entity in a body", notes = "Post operation with entity in a body", response = Item.class)
	Response createItem(
	        @Context final UriInfo uriInfo,
	        @ApiParam(value = "item", required = true) final Item item);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mshariq.cxf.brave.SampleInterface#updateItem(java.lang.String,
	 * java.lang.String)
	 */
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{name}")
	@PUT
	@ApiOperation(value = "Put operation with form parameter", notes = "Put operation with form parameter", response = Item.class)
	Item updateItem(@ApiParam(value = "name", required = true) @PathParam("name") String name,
	        @ApiParam(value = "value", required = true) @FormParam("value") String value) ;
	        

	@Path("/{name}")
	@DELETE
	@ApiOperation(value = "Delete operation with implicit header", notes = "Delete operation with implicit header")
	@ApiImplicitParams(@ApiImplicitParam(name = "Accept-Language", value = "language", required = true, dataType = "String", paramType = "header"))
	Response delete(@ApiParam(value = "name", required = true) @PathParam("name") String name);

}