package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import frsf.isi.died.app.vista.material.GenericTableModel;
import frsf.isi.died.tp.modelo.productos.*;
import frsf.isi.died.app.controller.BuscarContController;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;


public class BuscarContPanel extends JPanel{
	
	private JScrollPane scrollPane;
	private JLabel lblTitulo;
	private JLabel lblMetadato;
	private JLabel lblAutor;
	private JLabel lblSeccion;
	private JLabel lblParrafo;
	private JLabel lblCapitulo;
	private JLabel lblEditorial;
	private JLabel lblResumen;
	private JLabel lblPalabraClave;
	private JTextField txtTitulo;
	private JTextField txtMetadato;
	private JTextField txtAutor;
	private JTextField txtSeccion;
	private JTextField txtParrafo;
	private JTextField txtCapitulo;
	private JTextField txtEditorial;
	private JTextField txtResumen;
	private JTextField txtPalabraClave;
	private JButton btnBuscar;
	private JTable tabla;
	
	private BuscarContController controller;
	private GenericTableModel tableModel;
	
	public BuscarContPanel() {
		this.setLayout(new GridBagLayout());
		this.tableModel = new GenericTableModel();
	}
	
	public void construir() {
		GridBagConstraints gridConst = new GridBagConstraints();
		
		lblTitulo = new JLabel("Titulo: ");
		gridConst.gridx=0;
		gridConst.gridy=0;
		this.add(lblTitulo, gridConst);
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(40);
		gridConst.gridx=1;
		this.add(txtTitulo, gridConst);
		
		lblMetadato = new JLabel("Metadato: ");
		gridConst.gridx=0;
		gridConst.gridy=1;
		this.add(lblMetadato, gridConst);
		
		txtMetadato = new JTextField();
		txtMetadato.setColumns(40);
		gridConst.gridx=1;
		this.add(txtMetadato, gridConst);
		
		lblAutor = new JLabel("Autor: ");
		gridConst.gridx=0;
		gridConst.gridy=2;
		this.add(lblAutor, gridConst);
		
		txtAutor = new JTextField();
		txtAutor.setColumns(40);
		gridConst.gridx=1;
		this.add(txtAutor,  gridConst);
		
		
		lblSeccion = new JLabel("Seccion: ");
		gridConst.gridx=0;
		gridConst.gridy=3;
		this.add(lblSeccion, gridConst);
		
		txtSeccion = new JTextField();
		txtSeccion.setColumns(40);
		gridConst.gridx=1;
		this.add(txtSeccion,  gridConst);
		
		lblParrafo = new JLabel("Parrafo: ");
		gridConst.gridx=0;
		gridConst.gridy=4;
		this.add(lblParrafo,  gridConst);
		
		txtParrafo = new JTextField();
		txtParrafo.setColumns(40);
		gridConst.gridx=1;
		this.add(txtParrafo,  gridConst);
		
		lblCapitulo = new JLabel("Capitulo: ");
		gridConst.gridx=0;
		gridConst.gridy=5;
		this.add(lblCapitulo,  gridConst);
		
		txtCapitulo = new JTextField();
		txtCapitulo.setColumns(5);
		gridConst.gridx=1;
		gridConst.anchor = GridBagConstraints.LINE_START;
		this.add(txtCapitulo,  gridConst);
		
		lblEditorial = new JLabel("Editorial: ");
		gridConst.gridx=0;
		gridConst.gridy=6;
		gridConst.anchor = GridBagConstraints.CENTER;
		this.add(lblEditorial,  gridConst);
		
		txtEditorial = new JTextField();
		txtEditorial.setColumns(40);
		gridConst.gridx=1;
		this.add(txtEditorial,  gridConst);
		
		lblResumen = new JLabel("Resumen: ");
		gridConst.gridx=0;
		gridConst.gridy=7;
		this.add(lblResumen,  gridConst);
		
		txtResumen = new JTextField();
		txtResumen.setColumns(40);
		gridConst.gridx=1;
		this.add(txtResumen, gridConst);
		
		lblPalabraClave = new JLabel("Palabra Clave: ");
		gridConst.gridx=0;
		gridConst.gridy=8;
		this.add(lblPalabraClave, gridConst);
		
		txtPalabraClave = new JTextField();
		txtPalabraClave.setColumns(20);
		gridConst.gridx=1;
		this.add(txtPalabraClave, gridConst);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(e ->{
			//try {
				List<MaterialCapacitacion> materiales = controller.buscarContenido(txtTitulo.getText(), txtMetadato.getText(), txtAutor.getText(), txtSeccion.getText(), txtParrafo.getText(), txtCapitulo.getText(), txtEditorial.getText(), txtResumen.getText(), txtPalabraClave.getText());
				this.setListaMateriales(materiales);
				
			/*}catch(Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
			}*/
		});
		gridConst.gridx=0;
		gridConst.gridy=9;
		this.add(btnBuscar, gridConst);
		
		tabla = new JTable(this.tableModel);
		tabla.setFillsViewportHeight(true);
		
		scrollPane = new JScrollPane(tabla);gridConst.gridx=0;
		gridConst.gridwidth=9;
		gridConst.gridy=10;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);
	}

	public BuscarContController getController() {
		return controller;
	}

	public void setController(BuscarContController controller) {
		this.controller = controller;
	}
	
	public void setListaMateriales(List<MaterialCapacitacion> materiales) {
		this.tableModel.setMateriales(materiales);
		this.tableModel.fireTableDataChanged();
	}
	
	
	

}
