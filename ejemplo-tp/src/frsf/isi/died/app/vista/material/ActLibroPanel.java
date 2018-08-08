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
import javax.swing.JComboBox;

import frsf.isi.died.app.controller.ActLibroController;
import frsf.isi.died.tp.modelo.productos.Libro;

public class ActLibroPanel extends JPanel{
	private JScrollPane scrollPane;
	private JTable tabla;
	private JLabel lblTitulo;
	private JLabel lblCosto;
	private JLabel lblPrecioCompra;
	private JLabel lblPaginas;
	private JLabel lblTema;
	private JLabel lblCalificacion;
	private JLabel lblRelevancia;
	private JLabel lblInformacion;
	private JTextField txtTitulo;
	private JTextField txtCosto;
	private JTextField txtPrecioCompra;
	private JTextField txtPaginas;
	private JTextField txtTema;
	private JTextField txtCalificacion;
	private JComboBox<String> listRelevancia;
	private JButton btnActualizar;

	private LibroTableModel tableModel;

	private ActLibroController controller;
	
	public ActLibroPanel() {
		this.setLayout(new GridBagLayout());
		tableModel = new LibroTableModel();
	}
	
	public void construir() {
		GridBagConstraints gridConst= new GridBagConstraints();
		
		lblTitulo = new JLabel("Titulo: ");
		gridConst.gridx=0;
		gridConst.gridy=0;
		gridConst.fill = GridBagConstraints.BOTH;
		gridConst.anchor = GridBagConstraints.LINE_END;
		this.add(lblTitulo, gridConst); 
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(40);
		gridConst.gridx=1;
		gridConst.gridwidth=7;
		this.add(txtTitulo, gridConst);
		

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener( e ->{
			String titulo = txtTitulo.getText();
			String costo = txtCosto.getText();
			String precio = txtPrecioCompra.getText();
			String paginas = txtPaginas.getText();
			String tema = txtTema.getText();
			String calificacion = txtCalificacion.getText();
			String relevancia = (String) listRelevancia.getSelectedItem();
			
			Libro buscado = controller.buscarLibro(titulo);
			if(buscado == null) {
				JOptionPane.showMessageDialog(this, "No se encontró un libro con ese nombre", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
			else {
				List<Libro> libros = new ArrayList<Libro>();
				libros.add(buscado);
				this.setListaLibros(libros);
				int seleccion = JOptionPane.showConfirmDialog(this, "Seguro que quiere actualizar este libro?", "Confirmacion", JOptionPane.YES_NO_OPTION);
				switch(seleccion) {
				case JOptionPane.YES_OPTION:
					controller.actualizarLibro(buscado, costo, precio, paginas, tema, calificacion, relevancia);
					JOptionPane.showMessageDialog(this, "El libro se ha modificado correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					this.setListaLibros(new ArrayList<Libro>());
					break;
				case JOptionPane.NO_OPTION:
					this.setListaLibros(new ArrayList<Libro>());
					break;
				}
			}
			txtTitulo.setText("");
			txtCosto.setText("");
			txtPrecioCompra.setText("");
			txtPaginas.setText("");
			txtTema.setText("");
			txtCalificacion.setText("");
		});
		gridConst.gridwidth=1;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		gridConst.fill = GridBagConstraints.NONE;
		gridConst.gridx=8;
		this.add(btnActualizar, gridConst);
		
		lblCosto= new JLabel("Costo: ");		
		gridConst.gridx=0;
		gridConst.gridy=1;
		gridConst.weightx=0.0;
		gridConst.anchor = GridBagConstraints.LINE_END;
		gridConst.fill = GridBagConstraints.BOTH;
		this.add(lblCosto, gridConst);
		
		txtCosto = new JTextField();
		txtCosto.setColumns(5);
		gridConst.gridx=1;
		this.add(txtCosto, gridConst);
		
		lblPrecioCompra= new JLabel("Precio Compra: ");
		gridConst.gridx=2;
		this.add(lblPrecioCompra, gridConst);
		
		txtPrecioCompra = new JTextField();
		txtPrecioCompra.setColumns(5);
		gridConst.gridx=3;
		this.add(txtPrecioCompra, gridConst);
		
		lblPaginas= new JLabel("Paginas: ");		
		gridConst.gridx=4;
		this.add(lblPaginas, gridConst);
		
		txtPaginas = new JTextField();
		txtPaginas.setColumns(5);
		gridConst.gridx=5;
		this.add(txtPaginas, gridConst);
		
		lblTema = new JLabel("Tema: ");
		gridConst.gridx = 6;
		this.add(lblTema, gridConst);
		
		txtTema = new JTextField();
		txtTema.setColumns(5);
		gridConst.gridx = 7;
		this.add(txtTema, gridConst);
		
		lblCalificacion = new JLabel("Calificacion: ");
		gridConst.gridx = 0;
		gridConst.gridy = 2;
		this.add(lblCalificacion, gridConst);
		
		txtCalificacion = new JTextField();
		txtCalificacion.setColumns(5);
		gridConst.gridx = 1;
		this.add(txtCalificacion, gridConst);
		
		lblRelevancia = new JLabel("Relevancia: ");
		gridConst.gridx = 2;
		gridConst.anchor = GridBagConstraints.LINE_START;
		this.add(lblRelevancia, gridConst);
		
		listRelevancia = new JComboBox<String>();
		listRelevancia.addItem("Alta");
		listRelevancia.addItem("Media");
		listRelevancia.addItem("Baja");
		gridConst.gridx = 3;
		gridConst.fill = GridBagConstraints.NONE;
		this.add(listRelevancia, gridConst);
		
		lblInformacion = new JLabel("Los campos que permanezcan vacios no se actualizaran");
		gridConst.gridx = 0;
		gridConst.gridy = 3;
		gridConst.gridwidth = 7;
		gridConst.weightx = 1.0;
		gridConst.anchor = GridBagConstraints.CENTER;
		this.add(lblInformacion, gridConst);
		
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
	
	public ActLibroController getController() {
		return controller;
	}

	public void setController(ActLibroController controller) {
		this.controller = controller;
	}
	
	public void setListaLibros(List<Libro> librosLista) {
		this.tableModel.setLibros(librosLista);
		this.tableModel.fireTableDataChanged();
	}
	
}
