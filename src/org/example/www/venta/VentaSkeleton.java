package org.example.www.venta;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class VentaSkeleton {

	private static String URI = "http://apirestmtis-2017.herokuapp.com/api/venta";

	public org.example.www.venta.ValidarResponse validar(org.example.www.venta.Validar validar) 
	{
		org.example.www.venta.ValidarResponse rs = new org.example.www.venta.ValidarResponse();
		rs.setValidado(true);
		return rs ;
	}

	public org.example.www.venta.InsertarResponse insertar(org.example.www.venta.Insertar insertar) 
	{
		org.example.www.venta.InsertarResponse rs = new org.example.www.venta.InsertarResponse();
        try
        {
            // Crea un cliente para consumir servicios REST
            Client cliente = ClientBuilder.newClient();
         // Crea un objeto WebTarget para acceder al servicio
            // indicado en la URI
            WebTarget servicio = cliente.target(URI);
            // Crea un formulario y añade los campos
            // con la información necesaria para crear un mensaje
            Form form = new Form();
            form.param("pagado", insertar.getPagado()+"");
            form.param("precioTotal", ""+insertar.getPrecioTotal());
            //genero un string para cada producto 
            for(int i=0;i<insertar.getProductos().length;i++)
            	form.param("productos",
            				"{nombre:"+insertar.getProductos()[i].getNombre()
            				+",id:"+insertar.getProductos()[i].getId()
            				+",cantidad:"+insertar.getProductos()[i].getCantidad()
            				+",precio:"+insertar.getProductos()[i].getPrecio()+"}");
            
            // Realiza la petición POST adjuntando el formulario
            Response respuesta = servicio.request()
                    .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
            System.out.println(respuesta.readEntity(String.class));
        }catch(Exception ex)
        {
        	System.out.println(ex.getMessage());
        }
		return rs;
	}

}
