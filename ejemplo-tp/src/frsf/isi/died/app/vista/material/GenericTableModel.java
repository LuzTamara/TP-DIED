package frsf.isi.died.app.vista.material;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class GenericTableModel extends AbstractTableModel{
	private List<MaterialCapacitacion> materiales;
	private String[] columnas = {"ID", "Titulo", "Costo", "Tema", "Relevancia", "Calificacion", "Fecha Apertura"};
	
	@Override
	public String getColumnName(int indice) {
		return this.columnas[indice];
	}
	
	public List<MaterialCapacitacion> getMateriales() {
		return materiales;
	}

	public void setMateriales(List<MaterialCapacitacion> materiales) {
		this.materiales = materiales;
	}

	@Override
	public int getRowCount() {
		return materiales.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object valor = null;
		switch (columnIndex) {
		case 0:
			valor = this.materiales.get(rowIndex).getId();
			break;
		case 1:
			valor = this.materiales.get(rowIndex).getTitulo();
			break;
		case 2:
			valor = this.materiales.get(rowIndex).getCosto();
			break;
		case 3:
			valor = this.materiales.get(rowIndex).getTema();
			break;
		case 4:
			valor = this.materiales.get(rowIndex).getRelevancia();
			break;
		case 5:
			valor = this.materiales.get(rowIndex).getCalificacion();
			break;
		case 6:
			valor = this.materiales.get(rowIndex).getFechaPublicacion();
			break;
		default:
			System.out.println("Indice fuera de rango");
			valor = "S/D";
			break;
		}
		return valor;
	}
	
}
