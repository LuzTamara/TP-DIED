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

import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.app.controller.ElimLibroController;

public class ElimLibroPanel extends JPanel{
	private JScrollPane scrollPane;
	private JTable tabla;
	private JButton btneliminar;
	private JLabel lblTitulo;
	private JTextField txtTitulo;
	
	private LibroTableModel tableModel;
	
	private ElimLibroController controller;
	
	public ElimLibroPanel() {
		this.setLayout(new GridBagLayout());
		tableModel = new LibroTableModel();
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
		
		btneliminar = new JButton("Eliminar");
		btneliminar.addActionListener(e ->{
			String tit = txtTitulo.getText();
			txtTitulo.setText("");
			List<Libro> libros = controller.buscarLibro(tit);
			if(libros.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No se encontraron libros con ese nombre", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
			else {
				this.setListaLibros(libros);
				int seleccion = JOptionPane.showConfirmDialog(this, "Seguro que quiere eliminar este libro?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				switch(seleccion) {
				case JOptionPane.YES_OPTION: 
					this.setListaLibros(new ArrayList<Libro>());
					controller.eliminarLibro(libros);
					break;
				case JOptionPane.NO_OPTION:
					this.setListaLibros(new ArrayList<Libro>());
				}
			}
		});
		gridConst.gridx = 8;
		gridConst.gridwidth = 1;
		this.add(btneliminar, gridConst);
		
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
	
	public ElimLibroController getController() {
		return this.controller;
	}
	
	public void setController(ElimLibroController controller) {
		this.controller = controller;
	}
	
	public void setListaLibros(List<Libro> librosLista) {
		this.tableModel.setLibros(librosLista);
		this.tableModel.fireTableDataChanged();
	}
	
}
