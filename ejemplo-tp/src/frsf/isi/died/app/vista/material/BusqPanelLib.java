package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.time.LocalDate;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import frsf.isi.died.app.controller.BusqControllerLib;
import frsf.isi.died.tp.modelo.productos.Libro;

public class BusqPanelLib extends JPanel{
	
	String[] ordenamiento = {"Titulo", "Calificacion", "Precio", "Fecha de publicacion", "Relevancia"};
	
	private JScrollPane scrollPane;
	private JTable tabla;
	private JLabel lblBusqTitulo;
	private JLabel lblBusqCalific;
	private JLabel lblBusqTema;
	private JLabel lblBusqRangoDesdeDD;
	private JLabel lblBusqRangoDesdeMM;
	private JLabel lblBusqRangoDesdeAAAA;
	private JLabel lblBusqRangoHastaDD;
	private JLabel lblBusqRangoHastaMM;
	private JLabel lblBusqRangoHastaAAAA;
	private JLabel lblOrdenamiento1;
	private JLabel lblOrdenamiento2;
	private JLabel lblOrdenamiento3;
	private JLabel lblOrdenamiento4;
	private JTextField txtBusqTitulo;
	private JTextField txtBusqCalific;
	private JTextField txtBusqTema;
	private JTextField txtBusqRangoDesdeDD;
	private JTextField txtBusqRangoDesdeMM;
	private JTextField txtBusqRangoDesdeAAAA;
	private JTextField txtBusqRangoHastaDD;
	private JTextField txtBusqRangoHastaMM;
	private JTextField txtBusqRangoHastaAAAA;
	private JButton btnBusqTitulo;
	private JButton btnBusqCalific;
	private JButton btnBusqTema;
	private JButton btnBusqRango;
	private JButton btnWishlist;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JComboBox<String> ordenamientoTitulo;
	private JComboBox<String> ordenamientoCalific;
	private JComboBox<String> ordenamientoTema;
	private JComboBox<String> ordenamientoRango;
	
	
	private LibroTableModel tableModel;
	
	private BusqControllerLib controller;
	
	public BusqPanelLib() {
		this.setLayout(new GridBagLayout());
		tableModel = new LibroTableModel();
	}
	
	public void construir() {
		GridBagConstraints gridConst = new GridBagConstraints();
		
		//busqueda por titulo
		lblBusqTitulo = new JLabel("Titulo: ");
		gridConst.gridx=0;
		gridConst.gridy=0;
		gridConst.anchor= GridBagConstraints.LINE_START;
		this.add(lblBusqTitulo, gridConst);
		
		txtBusqTitulo = new JTextField();
		txtBusqTitulo.setColumns(20);
		gridConst.gridx=1;
		gridConst.gridwidth=5;
		
		
		this.add(txtBusqTitulo, gridConst);
		
		lblOrdenamiento1 = new JLabel("Ordenar por: ");
		gridConst.gridx=6;
		gridConst.gridwidth=1;
		this.add(lblOrdenamiento1, gridConst);
		
		ordenamientoTitulo = new JComboBox<>(ordenamiento);
		gridConst.gridx=7;
		this.add(ordenamientoTitulo, gridConst);
		
		btnBusqTitulo = new JButton("Buscar");
		btnBusqTitulo.addActionListener(e ->{
			controller.buscarLibro(1, txtBusqTitulo.getText(), null, (String) ordenamientoTitulo.getSelectedItem());
			txtBusqTitulo.setText("");
		}
		);
		gridConst.gridx=8;
		this.add(btnBusqTitulo, gridConst);
		
		
		//busqueda por calificacion
		lblBusqCalific = new JLabel("Calificacion: ");
		gridConst.gridx= 0;
		gridConst.gridy=1;
		this.add(lblBusqCalific, gridConst);
		
		txtBusqCalific = new JTextField();
		txtBusqCalific.setColumns(5);
		gridConst.gridx = 1;
		gridConst.gridwidth=5;
		this.add(txtBusqCalific, gridConst);
		
		lblOrdenamiento2 = new JLabel("Ordenar por: ");
		gridConst.gridx=6;
		gridConst.gridwidth=1;
		this.add(lblOrdenamiento2, gridConst);
		
		ordenamientoCalific = new JComboBox<>(ordenamiento);
		gridConst.gridx=7;
		this.add(ordenamientoCalific, gridConst);
		
		btnBusqCalific = new JButton("Buscar");
		btnBusqCalific.addActionListener(e -> {
			controller.buscarLibro(2, txtBusqCalific.getText(), null, (String) ordenamientoCalific.getSelectedItem());
			txtBusqCalific.setText("");
		}
		);
		gridConst.gridx=8;
		this.add(btnBusqCalific, gridConst);
		
		
		//busqueda por tema
		lblBusqTema = new JLabel("Tema: ");
		gridConst.gridx=0;
		gridConst.gridy = 2;
		this.add(lblBusqTema, gridConst);
		
		txtBusqTema = new JTextField();
		txtBusqTema.setColumns(10);
		gridConst.gridx=1;
		gridConst.gridwidth=5;
		
		
		this.add(txtBusqTema, gridConst);
		
		lblOrdenamiento3 = new JLabel("Ordenar por: ");
		gridConst.gridx=6;
		gridConst.gridwidth=1;
		this.add(lblOrdenamiento3, gridConst);
		
		ordenamientoTema = new JComboBox<>(ordenamiento);
		gridConst.gridx=7;
		this.add(ordenamientoTema, gridConst);
		
		btnBusqTema = new JButton("Buscar");
		btnBusqTema.addActionListener(e ->{
			controller.buscarLibro(3, txtBusqTema.getText(), null, (String) ordenamientoTema.getSelectedItem());
			txtBusqTema.setText("");
		}
		);
		gridConst.gridx=8;
		this.add(btnBusqTema, gridConst);
		
		//busqueda por rango de fecha
		lblBusqRangoDesdeDD = new JLabel("Fecha Desde: DD");
		gridConst.gridx=0;
		gridConst.gridy=3;
		gridConst.anchor = GridBagConstraints.WEST;
		this.add(lblBusqRangoDesdeDD, gridConst);
		
		txtBusqRangoDesdeDD = new JTextField();
		txtBusqRangoDesdeDD.setColumns(2);
		gridConst.gridx=1;
		this.add(txtBusqRangoDesdeDD, gridConst);
		
		lblBusqRangoDesdeMM = new JLabel("MM");
		gridConst.gridx=2;
		this.add(lblBusqRangoDesdeMM, gridConst);
		
		txtBusqRangoDesdeMM = new JTextField();
		txtBusqRangoDesdeMM.setColumns(2);
		gridConst.gridx=3;
		gridConst.anchor = GridBagConstraints.WEST;
		this.add(txtBusqRangoDesdeMM, gridConst);
		
		lblBusqRangoDesdeAAAA = new JLabel("AAAA");
		gridConst.gridx=4;
		this.add(lblBusqRangoDesdeAAAA, gridConst);
		
		txtBusqRangoDesdeAAAA = new JTextField();
		txtBusqRangoDesdeAAAA.setColumns(4);
		gridConst.gridx=5;
		this.add(txtBusqRangoDesdeAAAA, gridConst);
		
		//
		
		lblBusqRangoHastaDD = new JLabel("Fecha Hasta: DD");
		gridConst.gridx=0;
		gridConst.gridy=4;
		this.add(lblBusqRangoHastaDD, gridConst);
		
		txtBusqRangoHastaDD = new JTextField();
		txtBusqRangoHastaDD.setColumns(2);
		gridConst.gridx=1;
		this.add(txtBusqRangoHastaDD, gridConst);
		
		lblBusqRangoHastaMM = new JLabel("MM");
		gridConst.gridx=2;
		this.add(lblBusqRangoHastaMM, gridConst);
		
		txtBusqRangoHastaMM = new JTextField();
		txtBusqRangoHastaMM.setColumns(2);
		gridConst.gridx=3;
		this.add(txtBusqRangoHastaMM, gridConst);
		
		lblBusqRangoHastaAAAA = new JLabel("AAAA");
		gridConst.gridx=4;
		this.add(lblBusqRangoHastaAAAA, gridConst);
		
		txtBusqRangoHastaAAAA = new JTextField();
		txtBusqRangoHastaAAAA.setColumns(4);
		gridConst.gridx=5;
		this.add(txtBusqRangoHastaAAAA, gridConst);
		
		
		lblOrdenamiento4 = new JLabel("Ordenar por: ");
		gridConst.gridx=6;
		this.add(lblOrdenamiento4, gridConst);
		
		ordenamientoRango = new JComboBox<>(ordenamiento);
		gridConst.gridx=7;
		this.add(ordenamientoRango, gridConst);
		
		btnBusqRango = new JButton("Buscar");
		btnBusqRango.addActionListener(e ->{
			 LocalDate desde = LocalDate.of(Integer.valueOf(txtBusqRangoDesdeAAAA.getText()),
					Integer.valueOf(txtBusqRangoDesdeMM.getText()),
					Integer.valueOf(txtBusqRangoDesdeDD.getText()));
			 LocalDate hasta = LocalDate.of(Integer.valueOf(txtBusqRangoHastaAAAA.getText()),
						Integer.valueOf(txtBusqRangoHastaMM.getText()),
						Integer.valueOf(txtBusqRangoHastaDD.getText()));
			controller.buscarLibro(4, desde, hasta, (String) ordenamientoTema.getSelectedItem());
			txtBusqRangoDesdeDD.setText("");
			txtBusqRangoDesdeMM.setText("");
			txtBusqRangoDesdeAAAA.setText("");
			txtBusqRangoHastaDD.setText("");
			txtBusqRangoHastaMM.setText("");
			txtBusqRangoHastaAAAA.setText("");
		}
		);
		gridConst.gridx=8;
		this.add(btnBusqRango, gridConst);
		
		tabla = new JTable(this.tableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=9;
		gridConst.gridy=9;
		gridConst.fill=GridBagConstraints.BOTH;
		tabla.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseClicked(java.awt.event.MouseEvent e){
				int fila = tabla.rowAtPoint(e.getPoint());
				controller.mostrarBotones((String) tabla.getValueAt(fila, 1), fila);
				}	
			}
			);
		this.add(scrollPane, gridConst);
	
	}
	
	public void construirBotones(String valor, int fila) {
		GridBagConstraints gridConst = new GridBagConstraints();
		
		btnWishlist = new JButton("Agregar a wishlist");
		btnWishlist.addActionListener(e->{
			//agregar a wishlist
		});
		gridConst.gridx=0;
		gridConst.gridy=fila;
		this.add(btnWishlist, gridConst);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(e ->{
			//modificar libro
		});
		gridConst.gridx=1;
		this.add(btnModificar, gridConst);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(e ->{
			//eliminar libro
		});
		gridConst.gridx=2;
		this.add(btnEliminar, gridConst);
		
	}

	public BusqControllerLib getController() {
		return controller;
	}

	public void setController(BusqControllerLib controller) {
		this.controller = controller;
	}
	
	public void setListaLibros(List<Libro> librosLista,boolean actualizar) {
		this.tableModel.setLibros(librosLista);
		if(actualizar) this.tableModel.fireTableDataChanged();
	}
	
}
