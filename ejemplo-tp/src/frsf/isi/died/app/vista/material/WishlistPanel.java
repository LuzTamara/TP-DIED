package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.*;

import frsf.isi.died.app.controller.WishlistController;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class WishlistPanel extends JPanel{
	private JLabel lblTitulo;
	private JLabel lblOrdenar;
	private JComboBox<String> listOrden;
	private JScrollPane scrollPane;
	private JTable tabla;
	
	private WishlistController controller;
	
	private GenericTableModel tableModel;
	
	public WishlistPanel() {
		this.setLayout(new GridBagLayout());
		tableModel = new GenericTableModel();
	}
	
	public void construir() {
		GridBagConstraints gridConst = new GridBagConstraints();
		
		lblTitulo = new JLabel("Wishlist");
		gridConst.gridx = 0;
		gridConst.gridy = 0;
		gridConst.gridwidth = 6;
		gridConst.weightx = 1.0;
		gridConst.anchor = GridBagConstraints.CENTER;
		this.add(lblTitulo, gridConst);
		
		lblOrdenar = new JLabel("Orden: ");
		gridConst.gridx = 7;
		gridConst.gridwidth = 1;
		gridConst.weightx = 0.0;
		gridConst.anchor = GridBagConstraints.LINE_END;
		this.add(lblOrdenar, gridConst);
		
		listOrden = new JComboBox<String>();
		listOrden.addItem("Ascendente");
		listOrden.addItem("Descendente");
		listOrden.addActionListener(e -> {
			if(((String) listOrden.getSelectedItem()).equals("Ascendente")){
				controller.cambiarOrden(true);
			}
			else {
				controller.cambiarOrden(false);
			}
		});
		gridConst.gridx = 8;
		gridConst.anchor = GridBagConstraints.CENTER;
		this.add(listOrden, gridConst);
		
		tabla = new JTable(this.tableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=9;	
		gridConst.gridy=1;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);
		
	}
	
	public WishlistController getController() {
		return controller;
	}
	
	public void setController(WishlistController controller) {
		this.controller = controller;
	}
	
	public void setListaMateriales(List<MaterialCapacitacion> materiales) {
		this.tableModel.setMateriales(materiales);
	}
	
	public void actualizarTabla() {
		tabla.setModel(this.tableModel);
		tabla.setFillsViewportHeight(true);
	}
	
}
