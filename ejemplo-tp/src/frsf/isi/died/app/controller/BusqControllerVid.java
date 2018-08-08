package frsf.isi.died.app.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.BusqPanelVid;
import frsf.isi.died.tp.modelo.productos.Video;
import java.time.LocalDate;
import frsf.isi.died.tp.modelo.BibliotecaABB;
import frsf.isi.died.tp.modelo.productos.Video;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

import java.util.ArrayList;
import java.util.List;

public class BusqControllerVid {

	private BusqPanelVid panelBusquedaVideo;
	private MaterialCapacitacionDao materialDAO;
	
	public BusqControllerVid(BusqPanelVid panel) {
		this.panelBusquedaVideo = panel;
		this.panelBusquedaVideo.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
		
	}
	
	public void crearPanel() {
		this.panelBusquedaVideo.setListaVideos(new ArrayList<Video>(), false);
		this.panelBusquedaVideo.construir();
	}

	public BusqPanelVid getPanelBusquedaVideo() {
		return panelBusquedaVideo;
	}

	public void setPanelBusquedaVideo(BusqPanelVid panelBusquedaVideo) {
		this.panelBusquedaVideo = panelBusquedaVideo;
	}
	
	public void buscarVideo(int criterio, Object buscado, Object buscado2, String ordenamiento) {
		List<Video> Videos = new ArrayList<>();
		List<MaterialCapacitacion> resultados= new ArrayList<>();
		Videos = materialDAO.listaVideos();
		switch(criterio) {
		//busqueda por titulo
		case 1:
			for(Video l: Videos) {
				if(l.getTitulo().equals(buscado))
					resultados.add(l);
		}
			break;
			//busqueda por calificacion
		case 2:
			for(Video l: Videos) {
				if(l.getCalificacion() == buscado)
					resultados.add(l);
			}
			break;
			//busqueda por tema	
		case 3:
			for(Video l: Videos) {
				if(l.getTema().equals(buscado))
					resultados.add(l);	
			}
			break;
			//busqueda por fecha
		case 4:
			for(Video l: Videos) {
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
		case "Calificacion":
			resultados = biblio.ordena(resultados, biblio.getComparaCalificacion());
		case "Precio":
			resultados = biblio.ordena(resultados, biblio.getComparaPrecio());
		case "Fecha de publicacion":
			resultados = biblio.ordena(resultados, biblio.getComparaFecha());
		case "Relevancia":
			resultados = biblio.ordena(resultados, biblio.getComparaRelevancia());
		default:
		}
		
		//para poder devolverlo como Video luego de usar el ordenamiento generico 
		List<Video> resultadoFinal = new ArrayList<Video>();
		for(MaterialCapacitacion l: resultados)
			resultadoFinal.add((Video)l);
		
		this.panelBusquedaVideo.setListaVideos(resultadoFinal, false);
		
	}
	
	public void mostrarBotones(String valor, int fila) {
		this.panelBusquedaVideo.construirBotones(valor, fila);
		
	}
}
