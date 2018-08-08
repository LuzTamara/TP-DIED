package frsf.isi.died.app.controller;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.ActVideoPanel;
import frsf.isi.died.tp.modelo.productos.Video;

import java.util.ArrayList;
import java.util.List;

public class ActVideoController {
	private ActVideoPanel panelActVideo;
	private MaterialCapacitacionDao materialDAO;
	
	public ActVideoController(ActVideoPanel panelActVideo) {
		this.panelActVideo = panelActVideo;
		this.panelActVideo.setController(this);
		this.materialDAO = new MaterialCapacitacionDaoDefault();
	}
	
	public void crearPanel() {		
		this.panelActVideo.setListaVideos(new ArrayList<Video>());
		this.panelActVideo.construir();
	}
	
	public Video buscarVideo(String titulo) {
		List<Video> videos = this.materialDAO.listaVideos();
		String auxiliar;
		for(Video v : videos) {
			auxiliar = v.getTitulo();
			if(auxiliar.charAt(0) == '"') {
				auxiliar = auxiliar.substring(1, auxiliar.length()-1);
			}
			if(auxiliar.equals(titulo)) {
				return v;
			}
		}
		return null;
	}
	
	public void actualizarVideo(Video vid, String costo, String duracion, String tema, String calificacion, String relevancia) {
		Double costoAux;
		String temaAux;
		Integer calificacionAux;
		Integer duracionAux;
		
		if(costo.equals("")) {
			costoAux = vid.getCosto();
		}
		else {
			costoAux = Double.valueOf(costo);
		}
		if(duracion.equals("")) {
			duracionAux = vid.getDuracionEnSegundos();
		}
		else {
			duracionAux = Integer.valueOf(duracion);
		}
		if(tema.equals("")) {
			temaAux = vid.getTema();
		}
		else {
			temaAux = tema;
		}
		if(calificacion.equals("")) {
			calificacionAux = vid.getCalificacion();
		}
		else {
			calificacionAux = Integer.valueOf(calificacion);
		}
		
		Video nuevo = new Video(vid.getId(), vid.getTitulo(), costoAux, duracionAux, temaAux, calificacionAux, relevancia, vid.getFechaPublicacion());
		
		this.materialDAO.actualizarVideo(vid, nuevo);
	}
	
	public ActVideoPanel getPanelActVideo() {
		return this.panelActVideo;
	}
	
	public void setPanelActVideo(ActVideoPanel panelActVideo) {
		this.panelActVideo = panelActVideo;
	}

	
}
