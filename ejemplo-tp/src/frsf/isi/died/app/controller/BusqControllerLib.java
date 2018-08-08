package frsf.isi.died.app.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.BusqPanelLib;
import frsf.isi.died.tp.modelo.productos.Libro;
import java.time.LocalDate;
import frsf.isi.died.tp.modelo.BibliotecaABB;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

import java.util.ArrayList;
import java.util.List;

public class BusqControllerLib {

	private BusqPanelLib panelBusquedaLibro;
	private MaterialCapacitacionDao materialDAO;
		
	public BusqControllerLib(BusqPanelLib panel) {
		this.panelBusquedaLibro = panel;
		this.panelBusquedaLibro.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}
	
	public void crearPanel() {		
		this.panelBusquedaLibro.setListaLibros(new ArrayList<Libro>(), false);
		this.panelBusquedaLibro.construir();
	}

	public BusqPanelLib getpanelBusquedaLibro() {
		return panelBusquedaLibro;
	}

	public void setpanelBusquedaLibro(BusqPanelLib panelBusquedaLibro) {
		this.panelBusquedaLibro = panelBusquedaLibro;
	}
	
	public void buscarLibro(int criterio, Object buscado, Object buscado2, String ordenamiento) {
		List<Libro> libros = new ArrayList<>();
		List<MaterialCapacitacion> resultados= new ArrayList<>();
		libros = materialDAO.listaLibros();
		switch(criterio) {
		//busqueda por titulo
		case 1:
			for(Libro l: libros) {
				String aux = l.getTitulo();
				if(aux.charAt(0) == '"') {
					aux = aux.substring(1, aux.length()-1);
					if(aux.equals(buscado)) {
						resultados.add(l);
					}
				}
				else {
					if(aux.equals(buscado)) {
						resultados.add(l);
					}
				}
			}
			break;
			//busqueda por calificacion
		case 2:
			for(Libro l: libros) {
				System.out.println("Calificacion: "+l.getCalificacion());
				if(l.getCalificacion() == Integer.valueOf((String)buscado))
					resultados.add(l);
			}
			break;
			//busqueda por tema	
		case 3:
			for(Libro l: libros) {
				String aux = l.getTema();
				if(aux.charAt(0) == '"') {
					aux = aux.substring(1, aux.length()-1);
					if(aux.equals(buscado)) {
						resultados.add(l);
					}
				}
				else {
					if(aux.equals(buscado)) {
						resultados.add(l);
					}
				}	
			}
			break;
			//busqueda por fecha
		case 4:
			for(Libro l: libros) {
				if(l.getFechaPublicacion().compareTo((LocalDate)buscado) >=0 && l.getFechaPublicacion().compareTo((LocalDate)buscado2)<=0)
					resultados.add(l);
			}
			break;
		default:
		
		}
		
		//ordenar
		BibliotecaABB biblio = new BibliotecaABB();
		
		switch(ordenamiento) {
		case "Titulo":
			resultados = biblio.ordena(resultados, biblio.getComparaTitulo());
			break;
		case "Calificacion":
			resultados = biblio.ordena(resultados, biblio.getComparaCalificacion());
			break;
		case "Precio":
			resultados = biblio.ordena(resultados, biblio.getComparaPrecio());
			break;
		case "Fecha de publicacion":
			resultados = biblio.ordena(resultados, biblio.getComparaFecha());
			break;
		case "Relevancia":
			resultados = biblio.ordena(resultados, biblio.getComparaRelevancia());
			break;
		default:
		}
		
		//para poder devolverlo como libro luego de usar el ordenamiento generico 
		List<Libro> resultadoFinal = new ArrayList<Libro>();
		for(MaterialCapacitacion l: resultados) {
			resultadoFinal.add((Libro)l);//TODO ver porque el libro esta como null
		}
		
		this.panelBusquedaLibro.setListaLibros(resultadoFinal, true);
		
	}
	
	public void mostrarBotones(String valor, int fila) {
		this.panelBusquedaLibro.construirBotones(valor, fila);
	}
	
}
