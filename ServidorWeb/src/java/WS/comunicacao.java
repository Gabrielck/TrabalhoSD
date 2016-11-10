/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import bancoInterface.BancoDeDados;
import bancoInterface.Frase;
import java.util.Arrays;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Zelp
 */
@Path("comunicacaoWS")
public class comunicacao {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of comunicacao
     */
    public comunicacao() {
    }
    
    @GET
    @Path("/messages/type/{messagetype}")
    @Produces(MediaType.APPLICATION_JSON)
    public String queryJson(@PathParam("messagetype") int type) throws Exception {
        Frase aux[] = BancoDeDados.lista_tipo(type);
        
        JSONArray array = new JSONArray();
        
        for(Integer i=0;i<aux.length;i++)
        {
            JSONObject message = new JSONObject();
            message.put("description", aux[i].getFrase());
            message.put("type", aux[i].getTipo());
            message.put("_id", aux[i].getId());
            
            array.put(message);
        }
        
        return array.toString();
    }
    
    @GET
    @Path("/messages/radom/{messagetype}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRJson(@PathParam("messagetype") int type) throws Exception {
        Frase aux = BancoDeDados.mensagem(type);
      
        JSONObject message = new JSONObject();
        message.put("description", aux.getFrase());
        message.put("type", aux.getTipo());
        message.put("_id", aux.getId());
        
        return message.toString();
    }

    @GET
    @Path("/messages/{messageid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getIdJson(@PathParam("messageid") int id) throws Exception {
        Frase aux = BancoDeDados.consulta(id);
        JSONObject message = new JSONObject();
        message.put("description", aux.getFrase());
        message.put("type", aux.getTipo());
        message.put("_id", aux.getId());
        
        return message.toString();
    }

    @PUT
    @Path("/messages/{messageid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) throws Exception {
        JSONObject message = new JSONObject(content);
        String messageDescription = message.get("description").toString();
        String messageType = message.get("type").toString();
        String messageId = message.get("_id").toString();
        
        BancoDeDados.alterar(Integer.parseInt(messageId), messageDescription, Integer.parseInt(messageType));
    }
    
    @POST
    @Path("/messages")
    @Consumes(MediaType.APPLICATION_JSON)
    public void postJson(String content) throws Exception {
        JSONObject message = new JSONObject(content);
        String messageDescription = message.get("description").toString();
        String messageType = message.get("type").toString();
        
        BancoDeDados.inserir(messageDescription, Integer.parseInt(messageType));
    }
    
    @DELETE
    @Path("/messages/{messageid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteJson(@PathParam("messageid") int messageid) throws Exception {
        boolean res = BancoDeDados.exluir(messageid);
        if(res)
            return "Frase excluída com sucesso!";
        else
            return "Erro ao excluir frase no banco";
    }
    
}
