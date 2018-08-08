package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import frsf.isi.died.app.controller.LibroController;
import frsf.isi.died.tp.modelo.productos.Libro;

public class LibroPanel extends JPanel{
	
	private JScrollPane scrollPane;
	private JTable tabla;
	private JLabel lblTitulo;
	private JLabel lblCosto;
	private JLabel lblPrecioCompra;
	private JLabel lblPaginas;
	private JLabel lblTema;
	private JLabel lblCalificacion;
	private JLabel lblRelevancia;
	private JLabel lblDD;
	private JLabel lblMM;
	private JLabel lblAAAA;
	private JTextField txtTitulo;
	private JTextField txtCosto;
	private JTextField txtPrecioCompra;
	private JTextField txtPaginas;
	private JTextField txtTema;
	private JTextField txtCalificacion;
	private JComboBox<String> listRelevancia;
	private JTextField txtDD;
	private JTextField txtMM;
	private JTextField txtAAAA;
	private JButton btnAgregar;
	private JButton btnCancelar;

	private LibroTableModel tableModel;

	private LibroController controller;
	
	public LibroPanel() {
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
		

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener( e ->{
			try {
				Double costo = Double.valueOf(txtCosto.getText());
				Double precio = Double.valueOf(txtPrecioCompra.getText());
				Integer paginas = Integer.valueOf(txtPaginas.getText());
				String tema = txtTema.getText();
				Integer calificacion = Integer.valueOf(txtCalificacion.getText());
				String relevancia = (String) listRelevancia.getSelectedItem();
				LocalDate fechaPublicacion = LocalDate.of(Integer.valueOf(txtAAAA.getText()), Integer.valueOf(txtMM.getText()), Integer.valueOf(txtDD.getText()));
				controller.agregarLibro(txtTitulo.getText(), costo, precio, paginas, tema, calificacion, relevancia, fechaPublicacion); 
				txtTitulo.setText("");
				txtCosto.setText("");
				txtPrecioCompra.setText("");
				txtPaginas.setText("");
				txtTema.setText("");
				txtCalificacion.setText("");
				txtAAAA.setText("");
				txtMM.setText("");
				txtDD.setText("");
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
			}
		});
		gridConst.gridwidth=1;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		gridConst.fill = GridBagConstraints.NONE;
		gridConst.gridx=8;
		this.add(btnAgregar, gridConst);
		
		btnCancelar= new JButton("Cancelar");
		gridConst.gridy = 1;
		gridConst.anchor = GridBagConstraints.LINE_START;
		this.add(btnCancelar, gridConst);
		
		
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
		
		lblDD = new JLabel("Fecha Publicacion: DD");
		gridConst.gridx = 2;
		this.add(lblDD, gridConst);
		
		txtDD = new JTextField();
		txtDD.setColumns(5);
		gridConst.gridx = 3;
		this.add(txtDD, gridConst);
		
		lblMM = new JLabel("MM");
		gridConst.gridx = 4;
		gridConst.anchor = GridBagConstraints.LINE_END;
		this.add(lblMM, gridConst);
		
		txtMM = new JTextField();
		txtMM.setColumns(5);
		gridConst.gridx = 5;
		this.add(txtMM, gridConst);
		
		lblAAAA = new JLabel("AAAA");
		gridConst.gridx = 6;
		this.add(lblAAAA, gridConst);
		
		txtAAAA = new JTextField();
		txtAAAA.setColumns(5);
		gridConst.gridx = 7;
		this.add(txtAAAA, gridConst);
		
		lblRelevancia = new JLabel("Relevancia: ");
		gridConst.gridx = 0;
		gridConst.gridy = 3;
		gridConst.anchor = GridBagConstraints.LINE_START;
		this.add(lblRelevancia, gridConst);
		
		listRelevancia = new JComboBox<String>();
		listRelevancia.addItem("Alta");
		listRelevancia.addItem("Media");
		listRelevancia.addItem("Baja");
		gridConst.gridx = 1;
		gridConst.fill = GridBagConstraints.NONE;
		this.add(listRelevancia, gridConst);
		
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

	public LibroController getController() {
		return controller;
	}

	public void setController(LibroController controller) {
		this.controller = controller;
	}
	
	public void setListaLibros(List<Libro> librosLista,boolean actualizar) {
		this.tableModel.setLibros(librosLista);
		if(actualizar) this.tableModel.fireTableDataChanged();
	}

}