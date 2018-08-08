package frsf.isi.died.app.controller;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.WishlistPanel;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.util.OrdenamientoMonticulo;
import java.util.List;

public class WishlistController {
	private WishlistPanel panelWishlist;
	private MaterialCapacitacionDao materialDAO;
	
	public WishlistController(WishlistPanel panel) {
		this.panelWishlist = panel;
		this.panelWishlist.setController(this);
		this.materialDAO = new MaterialCapacitacionDaoDefault();
	}
	
	public void crearPanel() {
		List<MaterialCapacitacion> materiales = materialDAO.listaWishlist();
		OrdenamientoMonticulo mont = new OrdenamientoMonticulo(true);
		for(MaterialCapacitacion mat : materiales) {
			mont.Insertar(mat);
		}
		materiales.clear();
		while(!mont.esVacio()) {
			materiales.add(mont.Eliminar());
		}
		this.panelWishlist.setListaMateriales(materiales);
		this.panelWishlist.construir();
	}
	
	public WishlistPanel getPanelWishlist() {
		return panelWishlist;
	}
	
	public void setPanelWishlist(WishlistPanel panelWishlist) {
		this.panelWishlist = panelWishlist;
	}
	
	public void cambiarOrden(boolean orden) {
		List<MaterialCapacitacion> materiales = materialDAO.listaWishlist();
		if(orden) {
			OrdenamientoMonticulo mont = new OrdenamientoMonticulo(orden);
			for(MaterialCapacitacion mat : materiales) {
				mont.Insertar(mat);
			}
			materiales.clear();
			while(!mont.esVacio()) {
				materiales.add(mont.Eliminar());
			}
		}
		else {
			OrdenamientoMonticulo mont = new OrdenamientoMonticulo(orden);
			for(MaterialCapacitacion mat : materiales) {
				mont.Insertar(mat);
			}
			materiales.clear();
			while(!mont.esVacio()) {
				materiales.add(mont.Eliminar());
			}
		}
		this.panelWishlist.setListaMateriales(materiales);
		this.panelWishlist.actualizarTabla();
	}
	
}
