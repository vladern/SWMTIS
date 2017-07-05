
/**
 * FacturaSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package org.example.www.factura;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientResponse;
import org.json.*;

public class FacturaSkeleton {

	private static String URI = "http://apirestmtis-2017.herokuapp.com/api/factura";

	private String getFactura(int i) {
		// Crea un cliente para consumir servicios REST
		Client cliente = ClientBuilder.newClient();

		// Crea un objeto WebTarget para acceder al servicio
		// indicado en la URI
		WebTarget servicio = cliente.target(URI + "/" + i);

		try {
			// Realiza la petición GET, obteniendo el cuerpo de la respuesta
			// como un objeto de tipo String
			String texto = servicio.request().get(String.class);

			// Imprime el contenido de la respuesta
			System.out.println(texto);
			return texto;
		} catch (Exception e) {
			// Ha ocurrido algún error
			System.out.println("No se ha encontrado el recurso.");
		}
		return null;
	}
	private int getNumFacturas()
	{
        // Crea un cliente para consumir servicios REST
        Client cliente = ClientBuilder.newClient();
        // Crea un objeto WebTarget para acceder al servicio
        // indicado en la URI
        WebTarget servicio = cliente.target(URI+"s");
        // Realiza la petición GET, obteniendo el cuerpo de la respuesta
        // como un objeto de tipo String
        String texto = servicio.request().get(String.class);
        JSONObject obj = new JSONObject(texto);
        
		return obj.getJSONArray("factura").length();
	}
	/**
	 * Auto generated method signature
	 * 
	 * @param generar
	 * @return generarResponse
	 */

	public org.example.www.factura.GenerarResponse generar(org.example.www.factura.Generar generar) 
	{
		org.example.www.factura.GenerarResponse res = new org.example.www.factura.GenerarResponse();
		int id = getNumFacturas() + 1;
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
            form.param("id",""+id);
            form.param("cliente", generar.getCliente());
            form.param("precioTotal", ""+generar.getPrecioTotal());
            //genero un string para cada producto 
            for(int i=0;i<generar.getProductos().length;i++)
            	form.param("productos",
            				"{nombre:"+generar.getProductos()[i].getNombre()
            				+",id:"+generar.getProductos()[i].getId()
            				+",cantidad:"+generar.getProductos()[i].getCantidad()
            				+",precio:"+generar.getProductos()[i].getPrecio()+"}");
            
            // Realiza la petición POST adjuntando el formulario
            Response respuesta = servicio.request()
                    .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
            System.out.println(respuesta.readEntity(String.class));
        }catch(Exception ex)
        {
        	System.out.println(ex.getMessage());
        }
        res.setCif(id);
		return res;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param obtener
	 * @return obtenerResponse
	 */

	public org.example.www.factura.ObtenerResponse obtener(org.example.www.factura.Obtener obtener) {
		// el objeto esperado para la salida
		org.example.www.factura.ObtenerResponse res = new org.example.www.factura.ObtenerResponse();
		// la respuesta en forma de string
		String respuesta = this.getFactura(obtener.getId());
		if (respuesta != null) 
		{
			// el objeto json que obtenemos de la respuesta del rest
			JSONObject obj = new JSONObject(respuesta);
			//obtenemos el id
			int id = obj.getJSONObject("factura").getInt("id");
			//obtenemos el precio total
			int precioTotal = obj.getJSONObject("factura").getInt("precioTotal");
			//inicializo la factura
			Factura factura = new Factura();
			//inicializo el producto
			Producto producto = new Producto();
			try
			{
				factura.setId(id);
				factura.setPrecioTotal(precioTotal);
				//array de los productos
				JSONArray arr = obj.getJSONObject("factura").getJSONArray("productos");
				Producto[] productos = new Producto[arr.length()];
				for (int i = 0; i < arr.length(); i++)
				{
					String pr = arr.getString(i);
					JSONObject objPr = new JSONObject(pr);
					
					//inicializo las variables
				    int idP = objPr.getInt("id");
				    String nombre = objPr.getString("nombre");
				    int cant = objPr.getInt("cantidad");
				    int precio = objPr.getInt("precio");
				    // hago el set de las variables
				    producto.setId(idP);
				    producto.setNombre(nombre);
				    producto.setCantidad(cant);
				    producto.setPrecio(precio);
				    // y por ultimo meto el producto obtenido dentro del array de productos
				    productos[i]=producto;
				}
				factura.setProductos(productos);
				res.setFactura(factura);
				return res;
			}catch(Exception ex)
			{
				System.out.println(ex);
			}


		}
		return res;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param validar
	 * @return validarResponse
	 */

	public org.example.www.factura.ValidarResponse validar(org.example.www.factura.Validar validar) 
	{
		org.example.www.factura.ValidarResponse res = new org.example.www.factura.ValidarResponse();
		//de momento lo dejo así
		res.setValidada(true);
		return res;
	}

}
