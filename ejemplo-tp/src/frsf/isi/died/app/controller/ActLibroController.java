package frsf.isi.died.app.controller;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.ActLibroPanel;
import frsf.isi.died.tp.modelo.productos.Libro;

import java.util.ArrayList;
import java.util.List;

public class ActLibroController {
	private ActLibroPanel panelActLibro;
	private MaterialCapacitacionDao materialDAO;
	
	public ActLibroController(ActLibroPanel panelActLibro) {
		this.panelActLibro = panelActLibro;
		this.panelActLibro.setController(this);
		this.materialDAO = new MaterialCapacitacionDaoDefault();
	}
	
	public void crearPanel() {		
		this.panelActLibro.setListaLibros(new ArrayList<Libro>());
		this.panelActLibro.construir();
	}
	
	public Libro buscarLibro(String titulo) {
		List<Libro> libros = this.materialDAO.listaLibros();
		String auxiliar;
		for(Libro l : libros) {
			auxiliar = l.getTitulo();
			if(auxiliar.charAt(0) == '"') {
				auxiliar = auxiliar.substring(1, auxiliar.length()-1);
			}
			if(auxiliar.equals(titulo)) {
				return l;
			}
		}
		return null;
	}
	
	public void actualizarLibro(Libro lib, String costo, String precio, String paginas, String tema, String calificacion, String relevancia) {
		Double costoAux;
		Double precioAux;
		Integer paginasAux;
		String temaAux;
		Integer calificacionAux;
		
		if(costo.equals("")) {
			costoAux = lib.getCosto();
		}
		else {
			costoAux = Double.valueOf(costo);
		}
		if(precio.equals("")) {
			precioAux = lib.getPrecioCompra();
		}
		else {
			precioAux = Double.valueOf(precio);
		}
		if(paginas.equals("")) {
			paginasAux = lib.getPaginas();
		}
		else {
			paginasAux = Integer.valueOf(paginas);
		}
		if(tema.equals("")) {
			temaAux = lib.getTema();
		}
		else {
			temaAux = tema;
		}
		if(calificacion.equals("")) {
			calificacionAux = lib.getCalificacion();
		}
		else {
			calificacionAux = Integer.valueOf(calificacion);
		}
		
		Libro nuevo = new Libro(lib.getId(), lib.getTitulo(), costoAux, precioAux, paginasAux, temaAux, calificacionAux, relevancia, lib.getFechaPublicacion());
		
		this.materialDAO.actualizarLibro(lib, nuevo);
	}
	
	public ActLibroPanel getPanelActLibro() {
		return this.panelActLibro;
	}
	
	public void setPanelActLibro(ActLibroPanel panelActLibro) {
		this.panelActLibro = panelActLibro;
	}
	
}
