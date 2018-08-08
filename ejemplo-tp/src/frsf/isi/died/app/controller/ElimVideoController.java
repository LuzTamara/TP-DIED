package frsf.isi.died.app.controller;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.ElimVideoPanel;
import frsf.isi.died.tp.modelo.productos.Video;

import java.util.List;
import java.util.ArrayList;

public class ElimVideoController {
	private ElimVideoPanel panelElimVideo;
	private MaterialCapacitacionDao materialDAO;
	
	public ElimVideoController(ElimVideoPanel panelElimVideo) {
		this.panelElimVideo = panelElimVideo;
		this.panelElimVideo.setController(this);
		this.materialDAO = new MaterialCapacitacionDaoDefault();
	}
	
	public void crearPanel() {		
		this.panelElimVideo.setListaVideos(new ArrayList<Video>());
		this.panelElimVideo.construir();
	}
	
	public List<Video> buscarVideo(String titulo) {
		List<Video> videosBuscados = new ArrayList<>();
		List<Video> videos = this.materialDAO.listaVideos();
		String auxiliar;
		for(Video v : videos) {
			auxiliar = v.getTitulo();
			if(auxiliar.charAt(0) == '"') {
				auxiliar = auxiliar.substring(1, auxiliar.length()-1);
			}
			if(auxiliar.equals(titulo)) {
				videosBuscados.add(v);
			}
		}
		return videosBuscados;
	}
	
	public void eliminarVideo(List<Video> videos) {
		for(Video v : videos) {
			this.materialDAO.eliminarVideo(v);
		}
	}
	
	public ElimVideoPanel getPanelElimVideo() {
		return panelElimVideo;
	}

	public void setPanelElimLibro(ElimVideoPanel panelElimVideo) {
		this.panelElimVideo = panelElimVideo;
	}
	
}
