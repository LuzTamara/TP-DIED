package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
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

import frsf.isi.died.app.controller.ActVideoController;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.Video;

public class ActVideoPanel extends JPanel{
	private JScrollPane scrollPane;
	private JTable tabla;
	private JLabel lblTitulo;
	private JLabel lblCosto;
	private JLabel lblDuracion;
	private JLabel lblTema;
	private JLabel lblCalificacion;
	private JLabel lblRelevancia;
	private JLabel lblInformacion;
	private JTextField txtTitulo;
	private JTextField txtCosto;
	private JTextField txtDuracion;
	private JTextField txtTema;
	private JTextField txtCalificacion;
	private JComboBox<String> listRelevancia;
	
	private JButton btnActualizar;

	private VideoTableModel tableModel;

	private ActVideoController controller;
	
	public ActVideoPanel() {
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
		

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener( e ->{
			String titulo = txtTitulo.getText();
			String costo = txtCosto.getText();
			String duracion = txtDuracion.getText();
			String tema = txtTema.getText();
			String calificacion = txtCalificacion.getText();
			String relevancia = (String) listRelevancia.getSelectedItem();
			
			Video buscado = controller.buscarVideo(titulo);
			if(buscado == null) {
				JOptionPane.showMessageDialog(this, "No se encontró un video con ese nombre", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
			else {
				List<Video> videos = new ArrayList<Video>();
				videos.add(buscado);
				this.setListaVideos(videos);
				int seleccion = JOptionPane.showConfirmDialog(this, "Seguro que quiere actualizar este video?", "Confirmacion", JOptionPane.YES_NO_OPTION);
				switch(seleccion) {
				case JOptionPane.YES_OPTION:
					controller.actualizarVideo(buscado, costo, duracion, tema, calificacion, relevancia);
					JOptionPane.showMessageDialog(this, "El video se ha modificado correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					this.setListaVideos(new ArrayList<Video>());
					break;
				case JOptionPane.NO_OPTION:
					this.setListaVideos(new ArrayList<Video>());
					break;
				}
			}
			txtTitulo.setText("");
			txtCosto.setText("");
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
		
		lblInformacion = new JLabel("Los campos que permanezcan vacios no se actualizaran");
		gridConst.gridx = 2;
		gridConst.gridwidth = 7;
		gridConst.weightx = 1.0;
		gridConst.anchor = GridBagConstraints.CENTER;
		this.add(lblInformacion, gridConst);
		
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
	
	public ActVideoController getController() {
		return this.controller;
	}
	
	public void setController(ActVideoController controller) {
		this.controller = controller;
	}
	
	public void setListaVideos(List<Video> videosLista) {
		this.tableModel.setVideos(videosLista);
		this.tableModel.fireTableDataChanged();
	}
	
}
