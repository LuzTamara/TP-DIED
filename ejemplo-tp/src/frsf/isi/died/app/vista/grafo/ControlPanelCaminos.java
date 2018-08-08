package frsf.isi.died.app.vista.grafo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import frsf.isi.died.app.controller.GrafoController;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

/**
 *
 * @author mdominguez
 */
public class ControlPanelCaminos extends JPanel {
    
    private JComboBox<MaterialCapacitacion> cmbVertice1; 
    private JComboBox<MaterialCapacitacion> cmbVertice2;
    private JComboBox<String> cmbTema;
    private JButton btnBuscarCaminos;
    private GrafoController controller;
    private List<MaterialCapacitacion> listaVertices;
    
    public void armarPanel( List<MaterialCapacitacion> listaVertices, List<String> temas){
    	this.listaVertices = listaVertices;
    	this.cmbVertice1 = new JComboBox(listaVertices.toArray()); 
        this.cmbVertice2 = new JComboBox(listaVertices.toArray());
        this.cmbTema = new JComboBox<String>();
        for(String tem : temas) {
        	this.cmbTema.addItem(tem);
        }
        this.cmbTema.addActionListener(
        		e -> {
        			String seleccion = (String) this.cmbTema.getSelectedItem();
        			controller.cambiarMaterialesPorTemaCaminos(seleccion);
        		}
        );
        this.btnBuscarCaminos = new JButton("Buscar Caminos");
        this.btnBuscarCaminos.addActionListener(
                e -> { 
                    Integer idOrigen = this.listaVertices.get(cmbVertice1.getSelectedIndex()).getId();
                    Integer idDestino= this.listaVertices.get(cmbVertice2.getSelectedIndex()).getId();
                    controller.caminosPosibles(idOrigen, idDestino); 
                }
        );
        this.add(new JLabel("Vertice Origen"));        
    	this.add(cmbVertice1);
    	this.add(new JLabel("Vertice Destino"));
    	this.add(cmbVertice2);
    	this.add(new JLabel("Tema"));
    	this.add(cmbTema);
    	this.add(btnBuscarCaminos);
    }
    
    public String getSeleccion() {
    	return (String) cmbTema.getSelectedItem();
    }
    
    public GrafoController getController() {
        return controller;
    }

    public void setController(GrafoController controller) {
        this.controller = controller;
    }
    
}
