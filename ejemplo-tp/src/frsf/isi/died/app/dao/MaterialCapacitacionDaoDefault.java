package frsf.isi.died.app.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.app.dao.util.CsvDatasource;
import frsf.isi.died.tp.estructuras.Grafo;
import frsf.isi.died.tp.modelo.Biblioteca;
import frsf.isi.died.tp.modelo.BibliotecaABB;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Video;
import frsf.isi.died.tp.estructuras.NodoNoBinario;
import frsf.isi.died.tp.estructuras.TipoNodo;


public class MaterialCapacitacionDaoDefault implements MaterialCapacitacionDao{

	private static Grafo<MaterialCapacitacion> GRAFO_MATERIAL  = new Grafo<MaterialCapacitacion>();
	
	private static Integer SECUENCIA_ID=0;
	private static Biblioteca biblioteca = new BibliotecaABB();
	private static List<MaterialCapacitacion> wishlist = new ArrayList<MaterialCapacitacion>();
	private static List<NodoNoBinario> arbolContenido = new ArrayList<NodoNoBinario>();
	private CsvDatasource dataSource;
 
	
	public MaterialCapacitacionDaoDefault() {
		dataSource = new CsvDatasource();
		if(GRAFO_MATERIAL.esVacio()) {
			cargarGrafo();
		}
		if(arbolContenido.isEmpty()) {
			cargarArbol();
		}
		if(wishlist.isEmpty()) {
			cargarWishlist();
		}
	}

	private void cargarGrafo() {
		List<List<String>> libros = dataSource.readFile("libros.csv");
		for(List<String> filaLibro : libros) {
			Libro aux = new Libro();
			aux.loadFromStringRow(filaLibro);
			GRAFO_MATERIAL.addNodo(aux);
		}
		List<List<String>> videos= dataSource.readFile("videos.csv");
		for(List<String> filaVideo: videos) {
			Video aux = new Video();
			aux.loadFromStringRow(filaVideo);
			GRAFO_MATERIAL.addNodo(aux);
		}
		List<List<String>> aristas= dataSource.readFile("aristas.csv");
		for(List<String> filaArista: aristas) {
			MaterialCapacitacion n1 = this.findById(Integer.valueOf(filaArista.get(0)));
			MaterialCapacitacion n2 = this.findById(Integer.valueOf(filaArista.get(2)));
			GRAFO_MATERIAL.conectar(n1, n2);
		}
 	}
	
	private void cargarArbol() {
		List<List<String>> arboles = dataSource.readFile("contenidos.csv");
		List<String> aux;
		String auxiliar;
		for(List<String> arbol : arboles) {
			MaterialCapacitacion mat = this.encontrarMaterial(arbol.get(0));
			auxiliar = arbol.get(2);
			auxiliar = auxiliar.substring(1, auxiliar.length()-1);
			NodoNoBinario raiz = new NodoNoBinario(arbol.get(1).substring(1, arbol.get(1).length()-1), TipoNodo.valueOf(auxiliar));
			List<NodoNoBinario> hijos = new ArrayList<NodoNoBinario>();
			aux = arbol.subList(4, arbol.size()-1);
			hijos = this.getHijos(arbol.subList(4, arbol.size()-1), raiz);
			for(NodoNoBinario hijo : hijos) {
				raiz.AgregarHijo(hijo);
			}
			mat.setContenido(raiz);
			this.arbolContenido.add(raiz);
		}
	}
	
	private void cargarWishlist() {
		List<List<String>> materiales = dataSource.readFile("wishlist.csv");
		for(List<String> material : materiales) {
			if(material.size() == 9) { //cantidad de elementos de un libro
				Libro aux = new Libro();
				aux.loadFromStringRow(material);
				wishlist.add(aux);
			}
			else { //elementos de un video
				Video aux = new Video();
				aux.loadFromStringRow(material);
				wishlist.add(aux);
			}
		}
	}
	
	@Override
	public void agregarLibro(Libro mat) {
		mat.setId(++SECUENCIA_ID);
		GRAFO_MATERIAL.addNodo(mat);	
		biblioteca.agregar(mat);
		try {
			dataSource.agregarFilaAlFinal("libros.csv", mat);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void agregarVideo(Video mat) {
		mat.setId(++SECUENCIA_ID);
		GRAFO_MATERIAL.addNodo(mat);				
		biblioteca.agregar(mat);
		try {
			dataSource.agregarFilaAlFinal("videos.csv", mat);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void actualizarLibro(Libro viejo, Libro nuevo) {
		try {
			dataSource.eliminarFila("libros.csv", viejo.asCsvRow(), true);
			dataSource.agregarFilaAlFinal("libros.csv", nuevo.asCsvRow());
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	
	@Override
	public void actualizarVideo(Video viejo, Video nuevo) {
		try {
			dataSource.eliminarFila("videos.csv", viejo.asCsvRow(), true);
			dataSource.agregarFilaAlFinal("videos.csv", nuevo.asCsvRow());
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	
	@Override
	public void eliminarLibro(Libro mat) {
		try {
			dataSource.eliminarFila("libros.csv", mat.asCsvRow(), true);
		} catch (IOException e){
			// TODO Auto-generated catch block
		}
	}
	
	@Override
	public void eliminarVideo(Video mat) {
		try {
			dataSource.eliminarFila("videos.csv", mat.asCsvRow(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		
	}
	
	@Override
	public void agregarAWishlist(MaterialCapacitacion mat) {
		wishlist.add(mat);
		try{
			dataSource.agregarFilaAlFinal("wishlist.csv", mat.asCsvRow());
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		
	}
	
	@Override
	public void crearArbol(String titulo, NodoNoBinario arbol) {
		List<String> fila = new ArrayList<String>();
		fila.add(titulo);
		fila = arbol.asCsvRow(fila);
		arbolContenido.add(arbol);
		try {
			dataSource.agregarFilaAlFinal("contenidos.csv", fila);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
		} 
	}
	
	@Override
	public void actualizarArbol(String titulo, NodoNoBinario arbolViejo, NodoNoBinario arbolNuevo) {
		List<String> filaNueva = new ArrayList<String>();
		List<String> filaVieja = new ArrayList<String>();
		filaVieja.add(titulo);
		filaVieja = arbolViejo.asCsvRow(filaVieja);
		filaNueva.add(titulo);
		filaNueva = arbolNuevo.asCsvRow(filaNueva);
		System.out.println("Fila nueva: "+filaNueva.toString());
		try {
			dataSource.eliminarFila("contenidos.csv", filaVieja, false);
			dataSource.agregarFilaAlFinal("contenidos.csv", filaNueva);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

	@Override
	public List<Libro> listaLibros() {
		List<Libro> libros = new ArrayList<>();
		for(MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if(mat.esLibro()) libros.add((Libro)mat); 
		}
		return libros;
	}

	@Override
	public List<Video> listaVideos() {
		List<Video> vids = new ArrayList<>();
		for(MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if(mat.esVideo()) vids.add((Video)mat); 
		}
		return vids;
	}

	@Override
	public List<MaterialCapacitacion> listaMateriales() {
		// TODO Auto-generated method stub
		return GRAFO_MATERIAL.listaVertices();
	}
	
	@Override
	public List<MaterialCapacitacion> listaWishlist(){
		return wishlist;
	}

	@Override
	public MaterialCapacitacion findById(Integer id) {
		// TODO Auto-generated method stub
		for(MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if(mat.getId().equals(id)) return mat;
		}
		return null;
	}

	@Override
	public List<MaterialCapacitacion> buscarCamino(Integer idOrigen, Integer idDestino, Integer saltos) {
		MaterialCapacitacion n1 = this.findById(idOrigen);
		MaterialCapacitacion n2 = this.findById(idDestino);
		return GRAFO_MATERIAL.buscarCaminoNSaltos(n1, n2, saltos);
	}
	
	@Override
	public ArrayList<ArrayList<MaterialCapacitacion>> caminosDisponibles(Integer idOrigen, Integer idDestino){
		MaterialCapacitacion n1 = this.findById(idOrigen);
		MaterialCapacitacion n2 = this.findById(idDestino);
		List<MaterialCapacitacion> caminosAux = GRAFO_MATERIAL.caminosPosibles(n1, n2);	
		ArrayList<MaterialCapacitacion> caminoAux = new ArrayList<MaterialCapacitacion>();
		ArrayList<ArrayList<MaterialCapacitacion>> caminos = new ArrayList<ArrayList<MaterialCapacitacion>>();
		caminoAux.add(n1);
		for(int i = 1; i < caminosAux.size(); i++) {
			if(n1.equals(caminosAux.get(i))) {
				caminos.add((ArrayList<MaterialCapacitacion>)caminoAux.clone());
				caminoAux.clear();
				caminoAux.add(n1);
			}
			else {
				caminoAux.add(caminosAux.get(i));
			}
		}
		caminos.add(caminoAux);
		return caminos;
	}

	@Override
	public void crearCamino(Integer idOrigen, Integer idDestino) {
		MaterialCapacitacion n1 = this.findById(idOrigen);
		MaterialCapacitacion n2 = this.findById(idDestino);
		GRAFO_MATERIAL.conectar(n1, n2);
		List<String> fila = new ArrayList<>();
		fila.add(n1.getId()+"");
		fila.add(n1.getTitulo());
		fila.add(n2.getId()+"");
		fila.add(n2.getTitulo());
		try {
			dataSource.agregarFilaAlFinal("aristas.csv", fila);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void calcularPageRank(List<MaterialCapacitacion> materiales) {
		final Double d = 0.85;
		List<MaterialCapacitacion> nodosEntrada = new ArrayList<MaterialCapacitacion>();
		List<Double> pr = new ArrayList<Double>();
		Double aux = 0.0;
		for(MaterialCapacitacion material : materiales) {
			if((this.GRAFO_MATERIAL.gradoEntrada(material) == 0) && (this.GRAFO_MATERIAL.gradoSalida(material) == 0)) {
				aux = 0.0;
			}
			else {
				nodosEntrada = this.GRAFO_MATERIAL.encontrarNodosEntrada(material);
				for(MaterialCapacitacion mat : nodosEntrada) {
					if(mat != null) {
						aux = aux + (mat.getPageRank() / this.GRAFO_MATERIAL.gradoSalida(mat)); 
					}
				}
				aux = (1 - d) + d * aux;
			}
			pr.add(aux);
		}
		for(int i = 0; i < pr.size(); i++) {
			materiales.get(i).setPageRank(pr.get(i));
		}
	}
	
	private MaterialCapacitacion encontrarMaterial(String nombre) {
		List<Libro> libros = this.listaLibros();
		List<Video> videos = this.listaVideos();
		Libro padreL = new Libro();
		Video padreV = new Video();
		
		for (Libro l: libros) {
			if (l.getTitulo().equals(nombre))
					padreL = l;
		}
		//si no es un libro no va a coincidir con ninguno y padreL va a quedar solamente inicializado == es un video 
		if(padreL.getTitulo().equals("en desarrollo")) {
			for (Video v: videos) {
				if(v.getTitulo().equals(nombre))
					padreV = v;
			}
			return (MaterialCapacitacion)padreV;
		}
		else {
			return (MaterialCapacitacion)padreL;
		}
	}
	
	private List<NodoNoBinario> getHijos(List<String> fila, NodoNoBinario padre){
		List<NodoNoBinario> hijos = new ArrayList<NodoNoBinario>();
		NodoNoBinario nodo = new NodoNoBinario(fila.get(0).substring(1, fila.get(0).length()-1), TipoNodo.valueOf(fila.get(1).substring(1, fila.get(1).length()-1)));
		if(!fila.get(3).equals(")")) {
			this.getHijos(fila.subList(3, fila.size()-1), nodo);
		} 
		else {
			
			this.getHermanos(fila, padre);
		}
		
		hijos.add(nodo);
		
		return hijos;
	}
	
	private void getHermanos(List<String> fila, NodoNoBinario padre){
		if(!fila.isEmpty() && !fila.get(0).equals(")")) {
			String auxiliar = fila.get(1);
			if(auxiliar.length() > 1) {
				auxiliar = auxiliar.substring(1, auxiliar.length()-1);
			}
			NodoNoBinario nodo = new NodoNoBinario(fila.get(0).substring(1, fila.get(0).length()-1), TipoNodo.valueOf(auxiliar));
			padre.AgregarHijo(nodo);
			this.getHermanos(fila.subList(4, fila.size()), padre);
		}
	}
	
}
