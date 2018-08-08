package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import frsf.isi.died.tp.modelo.productos.*;
import frsf.isi.died.tp.estructuras.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import frsf.isi.died.app.controller.AgregarContController;

public class AgregarContPanel extends JPanel{
	 
	String[] tipos = { "TITULO", "METADATO", "AUTOR", "SECCION", "PARRAFO", "CAPITULO", "EDITORIAL", "RESUMEN", "PALABRA_CLAVE"};

	private JScrollPane scrollPane;
	private JLabel lblMaterial;
	private JLabel lblTitulo;
	private JLabel lblPadre;
	private JLabel lblTipo;
	private JLabel lblValor;
	private JTextField txtMaterial;
	private JTextField txtPadre;
	private JTextField txtTitulo;
	private JTextField txtValor;
	private JComboBox<String> boxTipo;
	private JButton btnCrear;
	private JTextArea txtResultado;
	
	private String resultado;
	
	private AgregarContController controller;
	
	public AgregarContPanel() {
		this.setLayout(new GridBagLayout());
	}
	
	public void construir() {
		GridBagConstraints gridConst = new GridBagConstraints();
		
		lblMaterial = new JLabel("Material :");
		gridConst.gridx=0;
		gridConst.gridy=0;
		this.add(lblMaterial, gridConst);
		//TODO cambiar esto por un combobox? minimizar errores
		txtMaterial = new JTextField();
		txtMaterial.setColumns(40);
		gridConst.gridx =1;
		this.add(txtMaterial, gridConst);
		
		lblTitulo = new JLabel("Titulo: ");
		gridConst.gridx=0;
		gridConst.gridy=1;
		this.add(lblTitulo, gridConst);
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(30);
		gridConst.gridx=1;
		this.add(txtTitulo, gridConst);
		
		lblPadre = new JLabel("Padre: ");
		gridConst.gridx=0;
		gridConst.gridy=2;
		this.add(lblPadre, gridConst);
		
		txtPadre = new JTextField();
		txtPadre.setColumns(20);
		gridConst.gridx=1;
		this.add(txtPadre, gridConst);
		
		lblTipo = new JLabel("Tipo: ");
		gridConst.gridx=0;
		gridConst.gridy=3;
		this.add(lblTipo, gridConst);
		
		boxTipo = new JComboBox<>(tipos);
		gridConst.gridx = 1;
		this.add(boxTipo, gridConst);
		
		lblValor = new JLabel("Valor: ");
		gridConst.gridx=0;
		gridConst.gridy=4;
		this.add(lblValor, gridConst);
		
		txtValor = new JTextField();
		txtValor.setColumns(5);
		gridConst.gridx=1;
		this.add(txtValor, gridConst);
		
		btnCrear = new JButton("Crear"); //(si ya tiene arbol y quiso agregar titulo siempre sobreescribe)
		btnCrear.addActionListener(e ->{
			//try {
				//obtener material correcto 
				MaterialCapacitacion material = controller.encontrarMaterial(txtMaterial.getText());
				//asociar al material
				controller.agregarAPadre(material, txtPadre.getText(), txtTitulo.getText(), (String)boxTipo.getSelectedItem(), txtValor.getText());
			/*}catch(Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Hubo un error a la hora de agregar", JOptionPane.ERROR_MESSAGE);
			}*/
			
			txtMaterial.setText("");
			txtTitulo.setText("");
			txtPadre.setText("");
			txtValor.setText("");	
		});
		gridConst.gridx=0;
		gridConst.gridy=5;
		this.add(btnCrear, gridConst);	
		
		txtResultado = new JTextArea(resultado);
		gridConst.gridx=0;
		gridConst.gridy=5;
		this.add(txtResultado, gridConst);
		
	}

	public AgregarContController getController() {
		return controller;
	}

	public void setController(AgregarContController controller) {
		this.controller = controller;
	}
	
	
}
