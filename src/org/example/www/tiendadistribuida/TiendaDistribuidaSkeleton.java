package org.example.www.tiendadistribuida;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class TiendaDistribuidaSkeleton {

	private String URI = "http://apirestmtis-2017.herokuapp.com/api/tienda/";
	
	private boolean validarTienda(String string) 
	{
		// Crea un cliente para consumir servicios REST
		Client cliente = ClientBuilder.newClient();

		// Crea un objeto WebTarget para acceder al servicio
		// indicado en la URI
		WebTarget servicio = cliente.target(URI + string);

		try {
			// Realiza la petición GET, obteniendo el cuerpo de la respuesta
			// como un objeto de tipo String
			String texto = servicio.request().get(String.class);

			// saca por pantalla el contenido de la respuesta
			System.out.println(texto);
			return true;
		} catch (Exception e) {
			// Ha ocurrido algún error
			System.out.println("No se ha encontrado el recurso.");
			return false;
		}
	}
	public org.example.www.tiendadistribuida.ValidarResponse validar(org.example.www.tiendadistribuida.Validar validar) 
	{
		org.example.www.tiendadistribuida.ValidarResponse res = new org.example.www.tiendadistribuida.ValidarResponse();
		String cif = validar.getCif();
		boolean respuesta = validarTienda(cif);
		res.setValidada(respuesta);
		return res;
	}

}
