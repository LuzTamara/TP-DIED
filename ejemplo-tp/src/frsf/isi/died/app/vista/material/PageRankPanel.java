package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.*;

import frsf.isi.died.app.controller.PageRankController;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class PageRankPanel extends JPanel{
	private JLabel lblTitulo;
	private JLabel lblTema;
	private JComboBox<String> cmbTema;
	private JScrollPane scrollPane;
	private JTable tabla;
	
	private PageRankController controller;
	
	private GenericTableModel tableModel;
	
	public PageRankPanel() {
		this.setLayout(new GridBagLayout());
		this.tableModel = new GenericTableModel();
	}
	
	public void construir() {
		GridBagConstraints gridConst = new GridBagConstraints();
		
		lblTitulo = new JLabel("Page Rank");
		gridConst.gridx = 0;
		gridConst.gridy = 0;
		gridConst.gridwidth = 6;
		gridConst.weightx = 1.0;
		gridConst.anchor = GridBagConstraints.CENTER;
		this.add(lblTitulo, gridConst);
		
		lblTema = new JLabel("Tema: ");
		gridConst.gridx = 7;
		gridConst.gridwidth = 1;
		gridConst.weightx = 0.0;
		gridConst.anchor = GridBagConstraints.LINE_END;
		this.add(lblTema, gridConst);
		
		cmbTema = new JComboBox(controller.listaTemas(controller.listaVertices()).toArray());
		cmbTema.addActionListener(e -> {
			String seleccion = (String) cmbTema.getSelectedItem();
			controller.cambiarTema(seleccion);
		});
		gridConst.gridx = 8;
		gridConst.anchor = GridBagConstraints.CENTER;
		this.add(cmbTema, gridConst);
		
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
	
	public PageRankController getController() {
		return this.controller;
	}
	
	public void setController(PageRankController controller) {
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
