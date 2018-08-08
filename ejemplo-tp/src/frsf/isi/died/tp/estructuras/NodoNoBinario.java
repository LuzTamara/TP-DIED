package frsf.isi.died.tp.estructuras;

import java.util.ArrayList;
import java.util.List;
import frsf.isi.died.tp.estructuras.*;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.tp.modelo.productos.*;

public class NodoNoBinario {
	private String valor;
	private TipoNodo tipo;
	private ArrayList<NodoNoBinario> hijos;

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public TipoNodo getTipo() {
		return tipo;
	}

	public void setTipo(TipoNodo tipo) {
		this.tipo = tipo;
	}

	public ArrayList<NodoNoBinario> getHijos() {
		return hijos;
	}

	public void setHijos(ArrayList<NodoNoBinario> hijos) {
		this.hijos = hijos;
	}
	
	public NodoNoBinario(String valor, TipoNodo tipo) {
		super();
		this.valor = valor;
		this.tipo = tipo;
		this.hijos = new ArrayList<NodoNoBinario>();
	}

	public NodoNoBinario() {
		this.valor = "";
		this.tipo = TipoNodo.TITULO;
		this.hijos = new ArrayList<NodoNoBinario>();
	}
	
	public void AgregarHijo(NodoNoBinario hijo) {
		this.hijos.add(hijo);
	}

	public List<NodoNoBinario> buscarNodoCoincidente(TipoNodo tipoN){
		List<NodoNoBinario> resultado = new ArrayList<NodoNoBinario>();
		List<NodoNoBinario> aux = new ArrayList<NodoNoBinario>();
		if(this.tipo.equals(tipoN))
			resultado.add(this);
		resultado.addAll(this.recursivaCoincidente(this.hijos, tipoN, aux));
		return resultado;
	}

	public List<NodoNoBinario> recursivaCoincidente(List<NodoNoBinario> hijos, TipoNodo tipoN, List<NodoNoBinario> aux){
		if(!(hijos.isEmpty())) {
			for(NodoNoBinario h: hijos) {
				h.recursivaCoincidente(h.getHijos(), tipoN, aux);
				if(h.getTipo().equals(tipoN))
					aux.add(h);
			}
		}
		return aux;
	}
	
	public List<String> asCsvRow(List<String> fila){
		fila.add('"'+this.valor+'"');
		fila.add('"'+this.tipo.toString()+'"');
		fila.add("(");
		if(this.hijos.isEmpty()) {
			fila.add(")");
			return fila;
		}
		else {
			for(NodoNoBinario hijo : this.hijos) {
				hijo.asCsvRow(fila);
			}
			fila.add(")");
		}
		
		return fila;
	}
	
}
