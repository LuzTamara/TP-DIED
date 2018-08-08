package frsf.isi.died.app.controller;

import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.grafo.AristaView;
import frsf.isi.died.app.vista.grafo.ControlPanel;
import frsf.isi.died.app.vista.grafo.GrafoPanel;
import frsf.isi.died.app.vista.grafo.ControlPanelCaminos;
import frsf.isi.died.app.vista.grafo.VerticeView;
import frsf.isi.died.tp.estructuras.Arista;
import frsf.isi.died.tp.estructuras.Grafo;
import frsf.isi.died.tp.estructuras.Vertice;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class GrafoController {

	private GrafoPanel vistaGrafo;
	private ControlPanel vistaControl;
	private ControlPanelCaminos vistaControlCaminos;
	private MaterialCapacitacionDao materialDao;
	private MenuController controllerMenu;

	public GrafoController(GrafoPanel panelGrf, ControlPanel panelCtrl, ControlPanelCaminos panelCtrlCaminos, MenuController controllerMenu, String tema) {
		this.vistaGrafo = panelGrf;
		this.vistaGrafo.setController(this);
		this.materialDao = new MaterialCapacitacionDaoDefault();
		this.controllerMenu = controllerMenu;
		List<MaterialCapacitacion> materiales = this.materialDao.listaMateriales();
		List<String> temas = new ArrayList<String>();
		if(tema != null) {
			temas = this.listaTemas(materiales, tema);
		}
		else {
			temas = this.listaTemas(materiales);
		}
		List<MaterialCapacitacion> matPorTema = this.materialesPorTema(materiales, temas.get(0));
		if(panelCtrl != null) {
			this.vistaControl = panelCtrl;
			this.vistaControl.setController(this);
			this.vistaControl.armarPanel(matPorTema, temas);
		}
		if(panelCtrlCaminos != null) {
			this.vistaControlCaminos = panelCtrlCaminos;
			this.vistaControlCaminos.setController(this);
			this.vistaControlCaminos.armarPanel(matPorTema, temas);
		}
	}

	public void crearVertice(Integer coordenadaX, Integer coordenadaY, Color color, MaterialCapacitacion mc) {
		VerticeView v = new VerticeView(coordenadaX, coordenadaY, color);
		v.setId(mc.getId());
		v.setNombre(mc.getTitulo());
		this.vistaGrafo.agregar(v);
		this.vistaGrafo.repaint();
	}

	public void crearArista(AristaView arista) {
		this.materialDao.crearCamino(arista.getOrigen().getId(), arista.getDestino().getId());
		this.vistaGrafo.agregar(arista);
		this.vistaGrafo.repaint();
	}

	public void buscarCamino(Integer nodo1, Integer nodo2, Integer saltos) {
		List<MaterialCapacitacion> camino = this.materialDao.buscarCamino(nodo1, nodo2, saltos);
		this.vistaGrafo.caminoPintar(camino);
		this.vistaGrafo.repaint();
	}
	
	public void caminosPosibles(Integer nodo1, Integer nodo2) {
		ArrayList<ArrayList<MaterialCapacitacion>> caminos = this.materialDao.caminosDisponibles(nodo1, nodo2);
		for(ArrayList<MaterialCapacitacion> camino: caminos) {
			this.vistaGrafo.caminoPintar(camino);			
		}
		this.vistaGrafo.repaint();
	}

	public List<MaterialCapacitacion> listaVertices() {
		return materialDao.listaMateriales();
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
	
	public List<String> listaTemas(List<MaterialCapacitacion> materiales, String tema){
		List<String> temas = new ArrayList<String>();
		temas.add(tema);
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
	
	public void cambiarMaterialesPorTema(String tema) {
		List<MaterialCapacitacion> materiales = this.materialDao.listaMateriales();
		List<MaterialCapacitacion> matPorTema = this.materialesPorTema(materiales, tema);
		this.controllerMenu.actualizarPanelControl(tema);
	}
	
	public void cambiarMaterialesPorTemaCaminos(String tema) {
		List<MaterialCapacitacion> materiales = this.materialDao.listaMateriales();
		List<MaterialCapacitacion> matPorTema = this.materialesPorTema(materiales, tema);
		this.controllerMenu.actualizarPanelControlCaminos(tema);
	}
	
	public String getSeleccionBarraDesplegable() {
		if(vistaControl != null) {
			return vistaControl.getSeleccion();
		}
		else {
			return vistaControlCaminos.getSeleccion();
		}
	}
	
	public ControlPanel getPanelControl() {
		return this.vistaControl;
	}
	
	public ControlPanelCaminos getPanelControlCaminos() {
		return this.vistaControlCaminos;
	}
}
