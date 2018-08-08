package frsf.isi.died.tp.modelo;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

import frsf.isi.died.tp.estructuras.Arbol;
import frsf.isi.died.tp.estructuras.ArbolBinarioBusqueda;
import frsf.isi.died.tp.estructuras.ArbolVacio;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.util.TiposRelevancia;

public class BibliotecaABB implements Biblioteca {

	private Arbol materiales;
	private Boolean flagOrdenarPorPrecio;
	private Comparator<MaterialCapacitacion> comparaTitulo= (mc1,mc2)-> mc1.getTitulo().compareTo(mc2.getTitulo());
	private Comparator<MaterialCapacitacion> comparaPrecio= (mc1,mc2)-> mc1.precio().intValue()- mc2.precio().intValue();
	private Comparator<MaterialCapacitacion> comparaFecha=(mc1,mc2)-> mc1.getFechaPublicacion().compareTo(mc2.getFechaPublicacion());
	private Comparator<MaterialCapacitacion> comparaCalificacion= (mc1,mc2)-> mc1.getCalificacion().intValue()- mc2.getCalificacion().intValue();
		
	Comparator<MaterialCapacitacion> comparaRelevancia = new Comparator<MaterialCapacitacion>() {
		@Override
		public int compare(MaterialCapacitacion mc1, MaterialCapacitacion mc2) {
			TiposRelevancia r1 = mc1.getRelevancia();
			TiposRelevancia r2 = mc2.getRelevancia();
			if(r1 == TiposRelevancia.ALTA && r2 == TiposRelevancia.BAJA)
				return 1;
			else if(r1 == TiposRelevancia.ALTA && r2 == TiposRelevancia.MEDIA)
				return 1;
			else if(r1 == TiposRelevancia.MEDIA && r2 == TiposRelevancia.BAJA)
				return 1;
			else if(r1 == TiposRelevancia.BAJA && r2 == TiposRelevancia.ALTA)
				return -1;
			else if(r1 == TiposRelevancia.MEDIA && r2 == TiposRelevancia.ALTA)
				return -1;
			else if(r1 == TiposRelevancia.BAJA && r2 == TiposRelevancia.MEDIA)
				return -1;
			else
				return 0;
		}
	};
	
	
	public BibliotecaABB() {
		this.materiales = new ArbolVacio();
		flagOrdenarPorPrecio= false;
	}
	
	@Override
	public void agregar(MaterialCapacitacion material) {
		if(this.materiales.esVacio()) this.materiales = new ArbolBinarioBusqueda(material, comparaTitulo);
		else{
			if(materiales.tamanio()<10)this.materiales.add(material);
		}
	}

	@Override
	public Integer cantidadMateriales() {
		// TODO Auto-generated method stub
		return materiales.tamanio();
	}

	@Override
	public Integer cantidadLibros() {
		// TODO Auto-generated method stub
		return materiales.tamanioLibros();
	}

	@Override
	public Integer cantidadVideos() {
		// TODO Auto-generated method stub
		return materiales.tamanioVideos();
	}

	@Override
	public Collection<MaterialCapacitacion> materiales() {
		// TODO RETORNAR LA LISTA DEL ARBOL ORDENADA ASCENDENTEMENTE		
		return this.materiales.inOrden();		
	}

	@Override
	public void imprimir() {
		// TODO IMPRIMIR LA LISTA DEL ARBOL ORDENADA ASCENDENTEMENTE
		materiales.imprimir();
	}

	@Override
	public void ordenarPorPrecio(Boolean b) {
		if((flagOrdenarPorPrecio && b) || (!flagOrdenarPorPrecio && !b ) ) {
			// no hago nada porque ya estaba ordenando por precio
			// y me pide que ordene por precio por lo tanto retorno
			return;
		}

		if(flagOrdenarPorPrecio && !b) {
			this.flagOrdenarPorPrecio = false;
			this.ordenarPorTitulo();
		}
		if(!flagOrdenarPorPrecio && b) {
			this.flagOrdenarPorPrecio= true;
			this.ordenarPorPrecio();
		}		
	}

	@Override
	public MaterialCapacitacion buscar(Integer precio) {
		// TODO Auto-generated method stub
		if(!flagOrdenarPorPrecio) this.ordenarPorPrecio(true);
		return this.materiales.buscar(precio);		
	}
	
	public Collection<MaterialCapacitacion> rango(Double costoMinimo,Double costoMax){
		if(!flagOrdenarPorPrecio) this.ordenarPorPrecio(true); 				
		return this.materiales.rango(costoMinimo, costoMax);
	}
	
	private void ordenarPorPrecio() {
		// Creo un nuevo arbol que ordena comparando por PRECIO.
		// Obtengo la lista del �rbol acutal.
		// Paso cada elemento de la lista al nuevo �rbol.
		// ahora el nuevo �rbol cuando lo recorra ordenado, mostrar� los 
		// datos ordenados por PRECIO la pr�xima vez que se invoque en 
		// BibliotecaABB el m�todo imprimir() o materiales()
		ArbolBinarioBusqueda abb = new ArbolBinarioBusqueda(this.comparaPrecio);
		this.materiales.inOrden().stream().forEach(mat -> abb.add(mat));
		this.materiales = abb;
	}
	
	private void ordenarPorTitulo() {
		// Creo un nuevo arbol que ordena comparando por titulo.
		// Obtengo la lista del �rbol acutal.
		// Paso cada elemento de la lista al nuevo �rbol.
		// ahora el nuevo �rbol cuando lo recorra ordenado, mostrar� los 
		// datos ordenados por titulo la pr�xima vez que se invoque en 
		// BibliotecaABB el m�todo imprimir() o materiales()
		ArbolBinarioBusqueda abb = new ArbolBinarioBusqueda(this.comparaTitulo);
		this.materiales.inOrden().stream().forEach(mat -> abb.add(mat));
		this.materiales = abb;
	};
	
	//metodos para ordenar la busqueda tp final
	
	public List<MaterialCapacitacion> ordena(List<MaterialCapacitacion> lista, Comparator<MaterialCapacitacion> comparador) {
		List<MaterialCapacitacion> resultado = new ArrayList<MaterialCapacitacion>();
		ArbolBinarioBusqueda abb = new ArbolBinarioBusqueda(comparador);
		for(MaterialCapacitacion mat: lista)
			abb.add(mat);
		resultado = abb.inOrden();	
		return resultado;
	}

	public Comparator<MaterialCapacitacion> getComparaTitulo() {
		return comparaTitulo;
	}

	public void setComparaTitulo(Comparator<MaterialCapacitacion> comparaTitulo) {
		this.comparaTitulo = comparaTitulo;
	}

	public Comparator<MaterialCapacitacion> getComparaPrecio() {
		return comparaPrecio;
	}

	public void setComparaPrecio(Comparator<MaterialCapacitacion> comparaPrecio) {
		this.comparaPrecio = comparaPrecio;
	}

	public Comparator<MaterialCapacitacion> getComparaFecha() {
		return comparaFecha;
	}

	public void setComparaFecha(Comparator<MaterialCapacitacion> comparaFecha) {
		this.comparaFecha = comparaFecha;
	}

	public Comparator<MaterialCapacitacion> getComparaCalificacion() {
		return comparaCalificacion;
	}

	public void setComparaCalificacion(Comparator<MaterialCapacitacion> comparaCalificacion) {
		this.comparaCalificacion = comparaCalificacion;
	}

	public Comparator<MaterialCapacitacion> getComparaRelevancia() {
		return comparaRelevancia;
	}

	public void setComparaRelevancia(Comparator<MaterialCapacitacion> comparaRelevancia) {
		this.comparaRelevancia = comparaRelevancia;
	}

	
}
