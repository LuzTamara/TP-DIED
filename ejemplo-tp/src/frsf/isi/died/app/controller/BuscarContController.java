package frsf.isi.died.app.controller;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.BuscarContPanel;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.estructuras.TipoNodo;
import frsf.isi.died.tp.estructuras.NodoNoBinario;

import java.util.List;
import java.util.ArrayList;

public class BuscarContController {
	
	private BuscarContPanel panelBuscarCont;
	private MaterialCapacitacionDao materialDAO;
	
	public BuscarContController(BuscarContPanel panel) {
		this.panelBuscarCont = panel;
		this.panelBuscarCont.setController(this);
		this.materialDAO = new MaterialCapacitacionDaoDefault();
	}
	
	public void crearPanel() {
		this.panelBuscarCont.setListaMateriales(new ArrayList<MaterialCapacitacion>());
		this.panelBuscarCont.construir();
	}

	public BuscarContPanel getPanelBuscarCont() {
		return panelBuscarCont;
	}

	public void setPanelBuscarCont(BuscarContPanel panelBuscarCont) {
		this.panelBuscarCont = panelBuscarCont;
	}
	
	
	public List<MaterialCapacitacion> buscarContenido(String titulo, String metadato, String autor, String seccion, 
			String parrafo, String capitulo, String editorial, String resumen, String palabraClave){
		
		List<MaterialCapacitacion> resultado = new ArrayList<MaterialCapacitacion>();
		List<MaterialCapacitacion> total = this.materialDAO.listaMateriales();
		
		for(MaterialCapacitacion mat: total) {
			Boolean entra = true;
			//buscar titulo
			if(!(titulo.equals(""))) {
				if(mat.getContenido() != null) {
					if(!mat.getContenido().getValor().equals(titulo)) {
						entra = false;
					}
				}
				else {
					entra = false;
				}
			}
			//buscar metadato
			if(!(metadato.equals(""))) {
				List<NodoNoBinario> posibles = new ArrayList<NodoNoBinario>();
				if(mat.getContenido() != null) {
					posibles = mat.getContenido().buscarNodoCoincidente(TipoNodo.METADATO);
				}
				else {
					entra = false;
				}
				if(!posibles.isEmpty()) {
					Integer c = 0;
					Boolean encontrado = false;
					while(encontrado == false && c<= posibles.size()-1) {
						if(posibles.get(c).getValor().equals(metadato))
							encontrado = true;
						c++;
					}
					if(!encontrado)
						entra = false;
				}
			}
			//buscar autor 
			if(!(autor.equals(""))) {
				List<NodoNoBinario> posibles = new ArrayList<NodoNoBinario>();
				if(mat.getContenido() != null) {
					posibles = mat.getContenido().buscarNodoCoincidente(TipoNodo.AUTOR);
				}
				else {
					entra = false;
				}
				if(!posibles.isEmpty()) {
					Integer c = 0;
					Boolean encontrado = false;
					while(encontrado == false && c<= posibles.size()-1) {
						if(posibles.get(c).getValor().equals(autor))
							encontrado = true;
						c++;
					}
					if(!encontrado)
						entra = false;
				}
			}
			//buscar seccion 
			if(!(seccion.equals(""))) {
				List<NodoNoBinario> posibles = new ArrayList<NodoNoBinario>();
				if(mat.getContenido() != null) {
					posibles = mat.getContenido().buscarNodoCoincidente(TipoNodo.SECCION);
				}
				else {
					entra = false;
				}
				if(!posibles.isEmpty()) {
					Integer c = 0;
					Boolean encontrado = false;
					while(encontrado == false && c<= posibles.size()-1) {
						if(posibles.get(c).getValor().equals(seccion))
							encontrado = true;
						c++;
					}
					if(!encontrado)
						entra = false;
				}
			}
			//buscar parrafo 
			if(!(parrafo.equals(""))) {
				List<NodoNoBinario> posibles = new ArrayList<NodoNoBinario>();
				if(mat.getContenido() != null) {
					posibles = mat.getContenido().buscarNodoCoincidente(TipoNodo.PARRAFO);
				}
				else {
					entra = false;
				}
				if(!posibles.isEmpty()) {
					Integer c = 0;
					Boolean encontrado = false;
					while(encontrado == false && c<= posibles.size()-1) {
						if(posibles.get(c).getValor().equals(parrafo))
							encontrado = true;
						c++;
					}
					if(!encontrado)
						entra = false;
				}
			}
			//buscar capitulo 
			if(!(capitulo.equals(""))) {
				List<NodoNoBinario> posibles = new ArrayList<NodoNoBinario>();
				if(mat.getContenido() != null) {
					posibles = mat.getContenido().buscarNodoCoincidente(TipoNodo.CAPITULO);
				}
				else {
					entra = false;
				}
				if(!posibles.isEmpty()) {
					Integer c = 0;
					Boolean encontrado = false;
					while(encontrado == false && c<= posibles.size()-1) {
						if(posibles.get(c).getValor().equals(capitulo)) {
							encontrado = true;
						}
						c++;
					}
					if(!encontrado)
						entra = false;
				}
			}
			//buscar editorial
			if(!(editorial.equals(""))) {
				List<NodoNoBinario> posibles = new ArrayList<NodoNoBinario>();
				if(mat.getContenido() != null) {
					posibles = mat.getContenido().buscarNodoCoincidente(TipoNodo.EDITORIAL);
				}
				else {
					entra = false;
				}
				if(!posibles.isEmpty()) {
					Integer c = 0;
					Boolean encontrado = false;
					while(encontrado == false && c<= posibles.size()-1) {
						if(posibles.get(c).getValor().equals(editorial))
							encontrado = true;
						c++;
					}
					if(!encontrado == false)
						entra = false;
				}
			}
			//buscar resumen
			if(!(resumen.equals(""))) {
				List<NodoNoBinario> posibles = new ArrayList<NodoNoBinario>();
				if(mat.getContenido() != null) {
					posibles = mat.getContenido().buscarNodoCoincidente(TipoNodo.RESUMEN);
				}
				else {
					entra = false;
				}
				if(!posibles.isEmpty()) {
					Integer c = 0;
					Boolean encontrado = false;
					while(encontrado == false && c<= posibles.size()-1) {
						if(posibles.get(c).getValor().equals(resumen))
							encontrado = true;
						c++;
					}
					if(!encontrado)
						entra = false;
				}
			}
			//buscar palabra clave
			if(!(palabraClave.equals(""))) {
				List<NodoNoBinario> posibles = new ArrayList<NodoNoBinario>();
				if(mat.getContenido() != null) {
					posibles = mat.getContenido().buscarNodoCoincidente(TipoNodo.PALABRA_CLAVE);
				}
				else {
					entra = false;
				}
				if(!posibles.isEmpty()) {
					Integer c = 0;
					Boolean encontrado = false;
					while(encontrado == false && c<= posibles.size()-1) {
						if(posibles.get(c).getValor().equals(palabraClave))
							encontrado = true;
						c++;
					}
					if(!encontrado)
						entra = false;
				}
			}
			
			if(entra) {
				resultado.add(mat);
			}
		}
		
		return resultado;
	}
	
}
