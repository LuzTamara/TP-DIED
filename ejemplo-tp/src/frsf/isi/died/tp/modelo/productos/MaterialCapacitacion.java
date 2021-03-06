/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.tp.modelo.productos;

import java.util.ArrayList;
import java.time.LocalDate;

import frsf.isi.died.app.dao.util.CsvRecord;
import frsf.isi.died.tp.util.*;
import frsf.isi.died.tp.estructuras.*;

/**
 * Representa de manera abstracta los materiales de capacitación
 * 
 * @author mdominguez
 */
public abstract class MaterialCapacitacion implements Ordenable,Comparable<MaterialCapacitacion>, CsvRecord{
	protected Integer id;
	/**
	 * Titulo del material
	 */
	protected String titulo;

	/**
	 * Costo básico que debe sumarse al precio por el mero hecho de publicarlo en el
	 * portal
	 */
	protected Double costo;
	

	/**
	 * Constructor por defecto
	 */
	
	protected String tema;
	
	protected Integer calificacion;
	
	protected TiposRelevancia relevancia;
	
	protected LocalDate fechaPublicacion;
	
	protected Double pageRank;
	
	protected NodoNoBinario contenido;
	
	public MaterialCapacitacion() {
		this(0,"en desarrollo",0.0);
	}

	/**
	 * Constructor que recibe como argumento un ID y un Titulo
	 * 
	 * @param id
	 * @param titulo
	 */
	public MaterialCapacitacion(Integer id, String titulo) {
		this(id,titulo,0.0);
	}

	/**
	 * Constructor que recibe como argumento un ID y un costo
	 * 
	 * @param id
	 * @param titulo
	 */
	public MaterialCapacitacion(Integer id,String titulo, Double costo) {
		this.id =id;
		this.titulo = titulo;
		this.costo = costo;
		//siempre creamos su arbol de contenidos pero vacio
		this.contenido = new NodoNoBinario();
	}
	
	public String getTitulo() {
		return titulo;
	}

	public Double getCosto() {
		return costo;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public Double getPageRank() {
		return pageRank;
	}

	public void setPageRank(Double pageRank) {
		this.pageRank = pageRank;
	}

	public TiposRelevancia getRelevancia() {
		return relevancia;
	}

	public void setRelevancia(TiposRelevancia relevancia) {
		this.relevancia = relevancia;
	}

	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}
	
	

	public NodoNoBinario getContenido() {
		return contenido;
	}

	public void setContenido(NodoNoBinario contenido) {
		this.contenido = contenido;
	}

	/**
	 * El precio de un material se define según el tipo del material y toma como
	 * base el costo del mismo
	 * 
	 * @return
	 */
	public abstract Double precio();
	
	/**
	 * Retorna verdadero si es una instancia de libro, falso en caso contrario
	 * @return
	 */
	public abstract Boolean esLibro();

	/**
	 * Retorna verdadero si es una instancia de video, falso en caso contrario
	 * @return
	 */
	public abstract Boolean esVideo();
	

	/**
	 * Retorna un valor numerico que será utilizado para ordenar los elementos
	 * @return
	 */
	@Override
	public final int valor() {
		return this.precio().intValue();
	}

	/**
	 * el método toString imprime el titulo, y el precio de un libro
	 * usando el formato : [Titulo: <titulo> ; Precio: <precio> ]
	 */
	@Override
	public String toString() {
		return "[Titulo " +this.titulo+"; PRECIO: "+this.precio()+"]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaterialCapacitacion other = (MaterialCapacitacion) obj;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Override
	public int compareTo(MaterialCapacitacion o) {
		int aux = this.titulo.compareTo(o.titulo);
		if(aux==0) {
			aux = this.precio().compareTo(o.precio());
		}
		return aux;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
}
