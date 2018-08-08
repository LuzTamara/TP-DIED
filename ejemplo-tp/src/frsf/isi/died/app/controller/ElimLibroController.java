package frsf.isi.died.app.controller;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.ElimLibroPanel;
import frsf.isi.died.tp.modelo.productos.Libro;

import java.util.List;
import java.util.ArrayList;

public class ElimLibroController {
	private ElimLibroPanel panelElimLibro;
	private MaterialCapacitacionDao materialDAO;
	
	public ElimLibroController(ElimLibroPanel panelElimLibro) {
		this.panelElimLibro = panelElimLibro;
		this.panelElimLibro.setController(this);
		this.materialDAO = new MaterialCapacitacionDaoDefault();
	}
	
	public void crearPanel() {		
		this.panelElimLibro.setListaLibros(new ArrayList<Libro>());
		this.panelElimLibro.construir();
	}
	
	public List<Libro> buscarLibro(String titulo) {
		List<Libro> librosBuscados = new ArrayList<>();
		List<Libro> libros = this.materialDAO.listaLibros();
		String auxiliar;
		for(Libro l : libros) {
			auxiliar = l.getTitulo();
			if(auxiliar.charAt(0) == '"') {
				auxiliar = auxiliar.substring(1, auxiliar.length()-1);
			}
			if(auxiliar.equals(titulo)) {
				librosBuscados.add(l);
			}
		}
		return librosBuscados;
	}
	
	public void eliminarLibro(List<Libro> libros) {
		for(Libro l : libros) {
			this.materialDAO.eliminarLibro(l);
		}
	}
	
	public ElimLibroPanel getPanelElimLibro() {
		return panelElimLibro;
	}

	public void setPanelElimLibro(ElimLibroPanel panelElimLibro) {
		this.panelElimLibro = panelElimLibro;
	}
	
}
