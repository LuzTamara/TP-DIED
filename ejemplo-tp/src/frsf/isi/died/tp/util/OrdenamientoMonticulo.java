package frsf.isi.died.tp.util;

import java.util.*;
import frsf.isi.died.tp.modelo.productos.*;

public class OrdenamientoMonticulo {
	PriorityQueue<MaterialCapacitacion> mont;
	
	public OrdenamientoMonticulo(boolean orden) {
		Comparator<MaterialCapacitacion> comp = new Comparator<MaterialCapacitacion>() {
			@Override
			public int compare(MaterialCapacitacion mc1, MaterialCapacitacion mc2) {
				if(!orden) {
					if((mc1.getRelevancia() == TiposRelevancia.ALTA) && (mc2.getRelevancia() == TiposRelevancia.MEDIA)) {
						return 1;
					}
					else if((mc1.getRelevancia() == TiposRelevancia.MEDIA) && (mc2.getRelevancia() == TiposRelevancia.BAJA)) {
						return 1;
					}
					else if((mc1.getRelevancia() == TiposRelevancia.ALTA) && (mc2.getRelevancia() == TiposRelevancia.BAJA)) {
						return 1;
					}
					else if((mc1.getRelevancia() == TiposRelevancia.MEDIA) && (mc2.getRelevancia() == TiposRelevancia.ALTA)) {
						return -1;
					}
					else if((mc1.getRelevancia() == TiposRelevancia.BAJA) && (mc2.getRelevancia() == TiposRelevancia.MEDIA)) {
						return -1;
					}
					else if((mc1.getRelevancia() == TiposRelevancia.BAJA) && (mc2.getRelevancia() == TiposRelevancia.ALTA)) {
						return -1;
					}
					else {
						if(mc1.getCalificacion() > mc2.getCalificacion()) {
							return 1;
						}
						else if(mc1.getCalificacion() < mc2.getCalificacion()) {
							return -1;
						}
						else {
							if(mc1.getCosto() > mc2.getCosto()) {
								return 1;
							}
							else if(mc1.getCosto() < mc2.getCosto()) {
								return -1;
							}
							else {
								return 0;
							}
						}
					}
				}
				else {
					if((mc1.getRelevancia() == TiposRelevancia.ALTA) && (mc2.getRelevancia() == TiposRelevancia.MEDIA)) {
						return -1;
					}
					else if((mc1.getRelevancia() == TiposRelevancia.MEDIA) && (mc2.getRelevancia() == TiposRelevancia.BAJA)) {
						return -1;
					}
					else if((mc1.getRelevancia() == TiposRelevancia.ALTA) && (mc2.getRelevancia() == TiposRelevancia.BAJA)) {
						return -1;
					}
					else if((mc1.getRelevancia() == TiposRelevancia.MEDIA) && (mc2.getRelevancia() == TiposRelevancia.ALTA)) {
						return 1;
					}
					else if((mc1.getRelevancia() == TiposRelevancia.BAJA) && (mc2.getRelevancia() == TiposRelevancia.MEDIA)) {
						return 1;
					}
					else if((mc1.getRelevancia() == TiposRelevancia.BAJA) && (mc2.getRelevancia() == TiposRelevancia.ALTA)) {
						return 1;
					}
					else {
						if(mc1.getCalificacion() > mc2.getCalificacion()) {
							return -1;
						}
						else if(mc1.getCalificacion() < mc2.getCalificacion()) {
							return 1;
						}
						else {
							if(mc1.getCosto() > mc2.getCosto()) {
								return -1;
							}
							else if(mc1.getCosto() < mc2.getCosto()) {
								return 1;
							}
							else {
								return 0;
							}
						}
					}
				}
			}
		};
		mont = new PriorityQueue<MaterialCapacitacion>(comp);
	}
	
	public void Insertar(MaterialCapacitacion m) {
		this.mont.add(m);
	}
	
	public MaterialCapacitacion Eliminar() {
		return this.mont.remove();
	}
	
	public boolean esVacio() {
		return mont.isEmpty();
	}
	
}
