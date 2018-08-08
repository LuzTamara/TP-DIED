package frsf.isi.died.app.controller;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.AgregarMatWishlistPanel;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

import java.util.List;
import java.util.ArrayList;

public class AgregarMatWishlistController {
	private AgregarMatWishlistPanel panelAgregarWishlist;
	private MaterialCapacitacionDao materialDao;
	
	public AgregarMatWishlistController(AgregarMatWishlistPanel panelAgregarWishlist) {
		this.panelAgregarWishlist = panelAgregarWishlist;
		this.panelAgregarWishlist.setController(this);
		this.materialDao = new MaterialCapacitacionDaoDefault();
	}
	
	public void crearPanel() {
		this.panelAgregarWishlist.setListaMateriales(new ArrayList<MaterialCapacitacion>());
		this.panelAgregarWishlist.construir();
	}
	
	public List<MaterialCapacitacion> buscarMaterial(String titulo){
		List<MaterialCapacitacion> buscado = new ArrayList<MaterialCapacitacion>();
		List<MaterialCapacitacion> materiales = this.materialDao.listaMateriales();
		String auxiliar;
		for(MaterialCapacitacion mat : materiales) {
			auxiliar = mat.getTitulo();
			if(auxiliar.charAt(0) == '"') {
				auxiliar = auxiliar.substring(1, auxiliar.length()-1);
			}
			if(auxiliar.equals(titulo)) {
				buscado.add(mat);
			}
		}
		return buscado;
	}
	
	public void agregarMaterial(List<MaterialCapacitacion> material) {
		for(MaterialCapacitacion mat : material) {
			this.materialDao.agregarAWishlist(mat);
		}
	}
	
	public AgregarMatWishlistPanel getPanelAgregarWishlist() {
		return this.panelAgregarWishlist;
	}
	
	public void setPanelAgregarWishlist(AgregarMatWishlistPanel panelAgregarWishlist) {
		this.panelAgregarWishlist = panelAgregarWishlist;
	}
	
}
