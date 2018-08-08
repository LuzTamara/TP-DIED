package frsf.isi.died.app.controller;

import frsf.isi.died.app.vista.material.AgregarContPanel;
import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.tp.modelo.productos.*;
import frsf.isi.died.tp.estructuras.*;
import java.util.List;
import java.util.ArrayList;

public class AgregarContController {
	
	private AgregarContPanel panelContenido;
	private MaterialCapacitacionDao materialDAO;
	
	public AgregarContController(AgregarContPanel panel) {
		this.panelContenido = panel;
		this.panelContenido.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}
	
	
	public void crearPanel() {
		this.panelContenido.construir();
	}

	public AgregarContPanel getPanelContenido() {
		return panelContenido;
	}

	public void setPanelContenido(AgregarContPanel panelContenido) {
		this.panelContenido = panelContenido;
	}
	
	public MaterialCapacitacion encontrarMaterial(String nombre) {
		List<Libro> libros = materialDAO.listaLibros();
		List<Video> videos = materialDAO.listaVideos();
		Libro padreL = new Libro();
		Video padreV = new Video();
		String auxiliar;
		
		if(nombre.charAt(0) != '"') {
			auxiliar = '"'+nombre+'"';
		}
		else {
			auxiliar = nombre;
		}
		
		for (Libro l: libros) {
			if (l.getTitulo().equals(auxiliar)) {
					padreL = l;
			}
		}
		//si no es un libro no va a coincidir con ninguno y padreL va a quedar solamente inicializado == es un video 
		if(padreL.getTitulo().equals("en desarrollo")) {
			for (Video v: videos) {
				if(v.getTitulo().equals(auxiliar))
					padreV = v;
			}
			return (MaterialCapacitacion)padreV;
		}
		else {
			return (MaterialCapacitacion)padreL;
		}
	}
	
	public NodoNoBinario agregarAPadre(MaterialCapacitacion material, String padre, String titulo, String tipo, String valor) {
		NodoNoBinario arbol = material.getContenido();
		NodoNoBinario arbolViejo = arbol;
		NodoNoBinario nuevo;
		if(arbol == null) {
			nuevo = new NodoNoBinario();
			nuevo.setValor(titulo);
			material.setContenido(nuevo);
			this.materialDAO.crearArbol(material.getTitulo(), nuevo); 
		}
		else {
			nuevo = new NodoNoBinario(valor, TipoNodo.valueOf(tipo));
			if(padre.equals("")) {
				arbol.setValor(titulo);
			}
			else {
				if (arbol.getValor().equals(padre)) {
					arbol.AgregarHijo(nuevo);
				}
				else {
					this.recursivaNodo(arbol.getHijos(), padre, nuevo);
				}
			}
			material.setContenido(arbol);
			this.materialDAO.actualizarArbol(material.getTitulo(), arbolViejo, arbol);
		}
		
		return nuevo;
	}
	
	public void recursivaNodo(ArrayList<NodoNoBinario> hijos, String padre, NodoNoBinario nuevo) {

		Boolean encontrado = false;
		int contador = 0;
		if(hijos.isEmpty()) {
			return;
		}
		while(!encontrado && contador<= hijos.size()-1) {
			if(hijos.get(contador).getValor().equalsIgnoreCase(padre)) {
				encontrado = true;
				ArrayList<NodoNoBinario> h = hijos.get(contador).getHijos();
				h.add(nuevo);
				hijos.get(contador).setHijos(h);
			}
			else {
				this.recursivaNodo(hijos.get(contador).getHijos(), padre, nuevo);
			}
			contador++;
		}
	}
	

}
