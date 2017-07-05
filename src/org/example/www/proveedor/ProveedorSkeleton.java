
package org.example.www.proveedor;

public class ProveedorSkeleton 
{

	public org.example.www.proveedor.ComprobarResponse comprobar(org.example.www.proveedor.Comprobar comprobar) 
	{
		org.example.www.proveedor.ComprobarResponse rs = new org.example.www.proveedor.ComprobarResponse();
		rs.setComprobado(true);
		return rs;
	}


	public org.example.www.proveedor.GenerarPresupuestoResponse generarPresupuesto(org.example.www.proveedor.GenerarPresupuesto generarPresupuesto) 
	{
		org.example.www.proveedor.GenerarPresupuestoResponse rs = new org.example.www.proveedor.GenerarPresupuestoResponse();
		float precioTotal = 0;
		for(int i=0;i<generarPresupuesto.getProductos().length;i++)
		{
			precioTotal = generarPresupuesto.getProductos()[i].getPrecio() * generarPresupuesto.getProductos()[i].getCantidad();			
		}
		precioTotal = (float) ( precioTotal * 1.3 );
		rs.setPresupuesto(precioTotal);
		return rs;
	}

}
