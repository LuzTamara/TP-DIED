package frsf.isi.died.app.controller;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.PageRankPanel;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class PageRankController {
	private PageRankPanel panelPageRank;
	private MaterialCapacitacionDao materialDAO;
	
	public PageRankController(PageRankPanel panel) {
		this.panelPageRank = panel;
		this.panelPageRank.setController(this);
		this.materialDAO = new MaterialCapacitacionDaoDefault();
	}
	
	public void crearPanel() {
		List<MaterialCapacitacion> materiales = this.materialDAO.listaMateriales();
		List<String> temas = this.listaTemas(materiales);
		List<MaterialCapacitacion> matPorTema = this.materialesPorTema(materiales, temas.get(0));
		List<MaterialCapacitacion> matPorPageRank = this.ordenarPorPageRank(matPorTema); 
		this.panelPageRank.setListaMateriales(matPorPageRank);
		this.panelPageRank.construir();
	}
	
	public PageRankPanel getPanelPageRank() {
		return this.panelPageRank;
	}
	
	public void setPanelPageRank(PageRankPanel panel) {
		this.panelPageRank = panel;
	}
	
	public List<MaterialCapacitacion> ordenarPorPageRank(List<MaterialCapacitacion> materiales){
		Comparator<MaterialCapacitacion> comparaPR = new Comparator<MaterialCapacitacion>() {
			@Override
			public int compare(MaterialCapacitacion mc1, MaterialCapacitacion mc2) {
				if(mc1.getPageRank() > mc2.getPageRank()) {
					return -1;
				}
				else if(mc1.getPageRank() < mc2.getPageRank()) {
					return 1;
				}
				else {
					return 0;
				}
			}
		};
		
		for(MaterialCapacitacion mat : materiales) {
			mat.setPageRank(1.0);
		}
		
		for(int h = 0; h < 100; h++) {
			this.materialDAO.calcularPageRank(materiales);
		}
		materiales.sort(comparaPR);
		return materiales;
	}
	
	public List<MaterialCapacitacion> listaVertices() {
		return this.materialDAO.listaMateriales();
	}
	
	public List<String> listaTemas(List<MaterialCapacitacion> materiales){
		List<String> temas = new ArrayList<String>();
		for(MaterialCapacitacion mat : materiales) {
			if(!temas.contains(mat.getTema())) {
				temas.add(mat.getTema());
			}
		}
		return temas;
	}
	
	public List<MaterialCapacitacion> materialesPorTema(List<MaterialCapacitacion> materiales, String tema){
		List<MaterialCapacitacion> auxiliar = new ArrayList<MaterialCapacitacion>();
		for(MaterialCapacitacion mat : materiales) {
			if(mat.getTema().equals(tema)) {
				auxiliar.add(mat);
			}
		}
		return auxiliar;
	}
	
	public void cambiarTema(String tema) {
		List<MaterialCapacitacion> matPorTema = this.materialesPorTema(this.materialDAO.listaMateriales(), tema);
		this.panelPageRank.setListaMateriales(matPorTema);
		this.panelPageRank.actualizarTabla();
	}
}
