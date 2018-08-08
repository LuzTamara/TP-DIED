package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import frsf.isi.died.app.controller.VideoController;
import frsf.isi.died.tp.modelo.productos.Video;
import frsf.isi.died.tp.util.TiposRelevancia;

public class VideoPanel extends JPanel{
	private JScrollPane scrollPane;
	private JTable tabla;
	private JLabel lblTitulo;
	private JLabel lblCosto;
	private JLabel lblDuracion;
	private JLabel lblTema;
	private JLabel lblCalificacion;
	private JLabel lblRelevancia;
	private JLabel lblDD;
	private JLabel lblMM;
	private JLabel lblAAAA;
	private JTextField txtTitulo;
	private JTextField txtCosto;
	private JTextField txtDuracion;
	private JTextField txtTema;
	private JTextField txtCalificacion;
	private JTextField txtDD;
	private JTextField txtMM;
	private JTextField txtAAAA;
	private JComboBox<String> listRelevancia;
	
	private JButton btnAgregar;
	private JButton btnCancelar;

	private VideoTableModel tableModel;

	private VideoController controller;
	
	public VideoPanel() {
		this.setLayout(new GridBagLayout());
		tableModel = new VideoTableModel();
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
				Integer duracion = Integer.valueOf(txtDuracion.getText());
				String tema = txtTema.getText();
				Integer calificacion = Integer.valueOf(txtCalificacion.getText());
				String relevancia = (String) listRelevancia.getSelectedItem();
				LocalDate fechaPublicacion = LocalDate.of(Integer.valueOf(txtAAAA.getText()), Integer.valueOf(txtMM.getText()), Integer.valueOf(txtDD.getText()));
				controller.agregarVideo(txtTitulo.getText(), costo, duracion, tema, calificacion, relevancia, fechaPublicacion);
				txtTitulo.setText("");
				txtCosto.setText("");
				txtDuracion.setText("");
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
		gridConst.gridy=1;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		this.add(btnCancelar, gridConst);
		
		lblCosto= new JLabel("Costo: ");		
		gridConst.gridx=0;
		gridConst.gridy=1;
		gridConst.weightx=0.0;
		gridConst.fill = GridBagConstraints.BOTH;
		gridConst.anchor = GridBagConstraints.LINE_END;
		this.add(lblCosto, gridConst);
		
		txtCosto = new JTextField();
		txtCosto.setColumns(5);
		gridConst.gridx=1;
		this.add(txtCosto, gridConst);
		
		lblDuracion= new JLabel("Duracion: ");
		gridConst.gridx=2;
		this.add(lblDuracion, gridConst);
		
		txtDuracion = new JTextField();
		txtDuracion.setColumns(5);
		gridConst.gridx=3;
		this.add(txtDuracion, gridConst);
		
		lblTema = new JLabel("Tema: ");
		gridConst.gridx = 4;
		this.add(lblTema, gridConst);
		
		txtTema = new JTextField();
		txtTema.setColumns(5);
		gridConst.gridx = 5;
		this.add(txtTema, gridConst);
		
		lblCalificacion = new JLabel("Calificacion: ");
		gridConst.gridx = 6;
		this.add(lblCalificacion, gridConst);
		
		txtCalificacion = new JTextField();
		txtCalificacion.setColumns(5);
		gridConst.gridx = 7;
		gridConst.anchor = GridBagConstraints.LINE_START;
		gridConst.fill = GridBagConstraints.VERTICAL;
		this.add(txtCalificacion, gridConst);
		
		lblRelevancia = new JLabel("Relevancia: ");
		gridConst.fill = GridBagConstraints.BOTH;
		gridConst.gridx = 0;
		gridConst.gridy = 2;
		this.add(lblRelevancia, gridConst);
		
		listRelevancia = new JComboBox<String>();
		listRelevancia.addItem("Alta");
		listRelevancia.addItem("Media");
		listRelevancia.addItem("Baja");
		gridConst.gridx = 1;
		gridConst.fill = GridBagConstraints.NONE;
		this.add(listRelevancia, gridConst);
		
		lblDD = new JLabel("Fecha Publicacion: DD");
		gridConst.gridx = 2;
		gridConst.fill = GridBagConstraints.VERTICAL;
		gridConst.anchor = GridBagConstraints.LINE_END;
		this.add(lblDD, gridConst);
		
		txtDD = new JTextField();
		txtDD.setColumns(5);
		gridConst.gridx = 3;
		this.add(txtDD, gridConst);
		
		lblMM = new JLabel("MM");
		gridConst.gridx = 4;
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
		
		tabla = new JTable(this.tableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=9;	
		gridConst.gridy=3;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);
	}

	public VideoController getController() {
		return controller;
	}
	
	public void setController(VideoController controller) {
		this.controller = controller;
	}
	
	public void setListaVideos(List<Video> videosLista,boolean actualizar) {
		this.tableModel.setVideos(videosLista);
		if(actualizar) this.tableModel.fireTableDataChanged();
	}
	
}
