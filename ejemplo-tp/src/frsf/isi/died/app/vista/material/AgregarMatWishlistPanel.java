package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import frsf.isi.died.app.controller.AgregarMatWishlistController;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class AgregarMatWishlistPanel extends JPanel{
	private JScrollPane scrollPane;
	private JTable tabla;
	private JButton btnagregar;
	private JLabel lblTitulo;
	private JTextField txtTitulo;
	
	private GenericTableModel tableModel;
	
	private AgregarMatWishlistController controller;
	
	public AgregarMatWishlistPanel() {
		this.setLayout(new GridBagLayout());
		tableModel = new GenericTableModel();
	}
	
	public void construir() {
		GridBagConstraints gridConst = new GridBagConstraints();
		
		lblTitulo = new JLabel("Titulo: ");
		gridConst.gridx = 0;
		gridConst.gridy = 0;
		this.add(lblTitulo, gridConst);
		
		txtTitulo = new JTextField(40);
		gridConst.gridx = 1;
		gridConst.gridwidth = 7;
		this.add(txtTitulo, gridConst);
		
		btnagregar = new JButton("Agregar");
		btnagregar.addActionListener(e ->{
			String tit = txtTitulo.getText();
			txtTitulo.setText("");
			List<MaterialCapacitacion> materiales = controller.buscarMaterial(tit);
			if(materiales.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No se encontraron libros con ese nombre", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
			else {
				this.setListaMateriales(materiales);
				int seleccion = JOptionPane.showConfirmDialog(this, "Seguro que quiere agregar este libro a su wishlist?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				switch(seleccion) {
				case JOptionPane.YES_OPTION: 
					this.setListaMateriales(new ArrayList<MaterialCapacitacion>());
					this.controller.agregarMaterial(materiales);
					break;
				case JOptionPane.NO_OPTION:
					this.setListaMateriales(new ArrayList<MaterialCapacitacion>());
				}
			}
		});
		gridConst.gridx = 8;
		gridConst.gridwidth = 1;
		this.add(btnagregar, gridConst);
		
		tabla = new JTable(this.tableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=9;	
		gridConst.gridy=4;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);
	}
	
	public AgregarMatWishlistController getController() {
		return this.controller;
	}
	
	public void setController(AgregarMatWishlistController controller) {
		this.controller = controller;
	}
	
	public void setListaMateriales(List<MaterialCapacitacion> listaMateriales) {
		this.tableModel.setMateriales(listaMateriales);
		this.tableModel.fireTableDataChanged();
	}
	
}
