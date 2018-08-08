/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.tp.modelo.productos;

import frsf.isi.died.tp.util.TiposRelevancia;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mdominguez
 */
public class Libro extends MaterialCapacitacion {
	private Double precioCompra;
	private Integer paginas;

	public Libro() {
	}

	public Libro(Integer id, String titulo) {
		super(id, titulo);
		this.precioCompra = 0.0;
		this.paginas = 0;
	}

	public Libro(Integer id, String titulo, Double costo, Double precioCompra, Integer paginas) {
		super(id, titulo, costo);
		this.precioCompra = precioCompra;
		this.paginas = paginas;
	}
	
	public Libro(Integer id, String titulo, Double costo, Double precioCompra, Integer paginas, String tema, Integer calificacion, String relevancia, LocalDate fechaPublicacion) {
		this.id = id;
		this.titulo = titulo;
		this.costo = costo;
		this.precioCompra = precioCompra;
		this.paginas = paginas;
		this.tema = tema;
		this.calificacion = calificacion;
		this.pageRank = 0.0;
		if(relevancia.equals("Alta")) {
			this.relevancia = TiposRelevancia.ALTA;
		}
		else if(relevancia.equals("Baja")) {
			this.relevancia = TiposRelevancia.BAJA;
		}
		else {
			this.relevancia = TiposRelevancia.MEDIA;
		}
		this.fechaPublicacion = fechaPublicacion;
		this.contenido = null;
	}

	public Double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	private Double factorPagina() {
		return 1.0 + (0.03 * (this.paginas / 150));
	}

	@Override
	public Double precio() {
		return this.costo + (this.precioCompra * this.factorPagina());
	}

	@Override
	public Boolean esLibro() {
		return true;
	}

	@Override
	public Boolean esVideo() {
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Libro && super.equals(obj) ;
	}

	@Override
	public List<String> asCsvRow() {
		List<String> lista = new ArrayList<String>();
		lista.add(this.id+"");
		lista.add("\""+this.titulo.toString()+"\"");
		lista.add(this.costo.toString());
		lista.add(this.paginas.toString());
		lista.add(this.precioCompra.toString());
		lista.add(this.tema.toString());
		lista.add(this.relevancia.toString());
		lista.add(this.calificacion.toString());
		lista.add(this.fechaPublicacion.toString()+'"');
		return lista;
	}

	@Override
	public void loadFromStringRow(List<String> datos) {
		this.id =Integer.valueOf(datos.get(0));
		this.titulo = datos.get(1);
		this.costo =Double.valueOf(datos.get(2));
		this.paginas =Integer.valueOf(datos.get(3));
		this.precioCompra =Double.valueOf(datos.get(4));
		this.tema = datos.get(5);
		if(datos.get(6).equals(TiposRelevancia.ALTA.toString())) {
			this.relevancia = TiposRelevancia.ALTA;
		}
		else if(datos.get(6).equals(TiposRelevancia.MEDIA.toString())) {
			this.relevancia = TiposRelevancia.MEDIA;
		}
		else {
			this.relevancia = TiposRelevancia.BAJA;
		}
		this.calificacion = Integer.valueOf(datos.get(7));
		char[] fechaDatos = datos.get(8).toCharArray();
		StringBuffer fecha = new StringBuffer();
		boolean aux = false;
		Integer dd = 0;
		Integer mm = 0;
		Integer aaaa = 0;
		for(char c : fechaDatos) {
			if(c != '-') {
				if(c != '"') {
					fecha.append(c);
				}
				else {
					dd = Integer.valueOf(fecha.toString());
				}
			}
			else {
				if(!aux) {
					aaaa = Integer.valueOf(fecha.toString());
					fecha.delete(0, fecha.length());
					aux = true;
				}
				else {
					mm = Integer.valueOf(fecha.toString());
					fecha.delete(0, fecha.length());
				}
			}
		}
		this.fechaPublicacion = LocalDate.of(aaaa, mm, dd);
		this.contenido = null;
	}

	
}