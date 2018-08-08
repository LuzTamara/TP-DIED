package frsf.isi.died.app.dao;

import java.util.List;
import java.util.ArrayList;

import frsf.isi.died.tp.estructuras.NodoNoBinario;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Video;

public interface MaterialCapacitacionDao {

	public void agregarLibro(Libro mat);
	public void agregarVideo(Video mat);
	public void actualizarLibro(Libro viejo, Libro nuevo);
	public void actualizarVideo(Video viejo, Video nuevo);
	public void eliminarLibro(Libro mat);
	public void eliminarVideo(Video mat);
	public List<Libro> listaLibros();
	public List<Video> listaVideos();
	public List<MaterialCapacitacion> listaMateriales();
	public List<MaterialCapacitacion> listaWishlist();
	public MaterialCapacitacion findById(Integer id);
	public void crearCamino(Integer idOrigen, Integer idDestino);
	public List<MaterialCapacitacion> buscarCamino(Integer idOrigen, Integer idDestino, Integer saltos);
	public ArrayList<ArrayList<MaterialCapacitacion>> caminosDisponibles(Integer idOrigen, Integer idDestino);
	public void agregarAWishlist(MaterialCapacitacion mat);
	public void calcularPageRank(List<MaterialCapacitacion> materiales);
	public void crearArbol(String titulo, NodoNoBinario arbol);
	public void actualizarArbol(String titulo, NodoNoBinario arbolViejo, NodoNoBinario arbolNuevo);
}
