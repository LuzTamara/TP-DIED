/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.tp.modelo.productos;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import frsf.isi.died.tp.util.TiposRelevancia;

/**
 *
 * @author mdominguez
 */
public class Video extends MaterialCapacitacion {

    private Integer duracionEnSegundos;
    private static final Double _VALOR_SEGUNDO = 0.15;

    public Video() {
        super();
    }

    public Video(Integer id,String titulo){
        super(id,titulo);
        this.duracionEnSegundos=0;
    }
    
    public Video(Integer id,String titulo, Double costo) {
        super(id,titulo, costo);
        this.duracionEnSegundos = 0;
    }

    public Video(Integer id,String titulo, Double costo, Integer duracion) {
        super(id,titulo, costo);
        this.duracionEnSegundos = duracion;
    }
    
    public Video(Integer id,String titulo, Double costo, Integer duracion, String tema, Integer calificacion, String relevancia, LocalDate fechaPublicacion) {
    	this.id = id;
		this.titulo = titulo;
		this.costo = costo;
		this.duracionEnSegundos = duracion;
		this.tema = tema;
		this.calificacion = calificacion;
		this.pageRank = 0.0;
		switch(relevancia) {
		case "Alta": this.relevancia = TiposRelevancia.ALTA;
		case "Media": this.relevancia = TiposRelevancia.MEDIA;
		case "Baja": this.relevancia = TiposRelevancia.BAJA;
		default: this.relevancia = TiposRelevancia.MEDIA;
		}
		this.fechaPublicacion = fechaPublicacion;
		this.contenido = null;
    }

    @Override
    public Double precio() {
        return costo + (duracionEnSegundos * _VALOR_SEGUNDO);
    }

    public Integer getDuracionEnSegundos() {
        return duracionEnSegundos;
    }

    public void setDuracionEnSegundos(Integer duracionEnSegundos) {
        this.duracionEnSegundos = duracionEnSegundos;
    }

	@Override
	public Boolean esLibro() {
		return false;
	}

	@Override
	public Boolean esVideo() {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Video && super.equals(obj) ;
	}
	
	@Override
	public List<String> asCsvRow() {
		List<String> lista = new ArrayList<String>();
		lista.add(this.id+"");
		lista.add("\""+this.titulo.toString()+"\"");
		lista.add(this.costo.toString());
		lista.add(this.duracionEnSegundos.toString());
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
		this.duracionEnSegundos =Integer.valueOf(datos.get(3));
		this.tema = datos.get(4);
		if(datos.get(5).equals(TiposRelevancia.ALTA.toString())) {
			this.relevancia = TiposRelevancia.ALTA;
		}
		else if(datos.get(5).equals(TiposRelevancia.MEDIA.toString())) {
			this.relevancia = TiposRelevancia.MEDIA;
		}
		else {
			this.relevancia = TiposRelevancia.BAJA;
		}
		this.calificacion = Integer.valueOf(datos.get(6));
		char[] fechaDatos = datos.get(7).toCharArray();
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