package exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exception.ErrorMessage;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {

  static Gson gson = new GsonBuilder().setPrettyPrinting().create();
  
  @Context
  ServletContext context;

  @Override
  public Response toResponse(EntityNotFoundException ex) {
    ErrorMessage em = new ErrorMessage(ex, Response.Status.NOT_FOUND.getStatusCode());
    return Response.status(Response.Status.NOT_FOUND).entity(gson.toJson(em)).type(MediaType.APPLICATION_JSON).build();
            
  }

}
