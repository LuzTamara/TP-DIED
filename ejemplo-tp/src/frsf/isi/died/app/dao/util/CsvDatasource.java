package frsf.isi.died.app.dao.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import frsf.isi.died.tp.estructuras.NodoNoBinario;

public class CsvDatasource {

	private char separador = ',';
	private char delimitadorString = '"';
	private char delimitadorNodoHijo = ';';

	public List<List<String>> readFile(String fileName) {
		List<List<String>> filas = new ArrayList<>();
		Scanner scanner;
		try {
			scanner = new Scanner(new File(fileName));

			while (scanner.hasNext()) {
				List<String> line = parseLine(scanner.nextLine());
				filas.add(line);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return filas;
	}

	private List<String> parseLine(String cvsLine) {
		List<String> result = new ArrayList<>();
		// if empty, return!
		if (cvsLine == null && cvsLine.isEmpty()) {
			return result;
		}
		StringBuffer curVal = new StringBuffer();
		boolean inQuotes = false;
		boolean startCollectChar = false;
		boolean doubleQuotesInColumn = false;

		char[] chars = cvsLine.toCharArray();

		for (char ch : chars) {

			if (inQuotes) {
				startCollectChar = true;
				if (ch == delimitadorString) {
					inQuotes = false;
					doubleQuotesInColumn = false;
				} else {

					// Fixed : allow "" in custom quote enclosed
					if (ch == '\"') {
						if (!doubleQuotesInColumn) {
							curVal.append(ch);
							doubleQuotesInColumn = true;
						}
					} else {
						curVal.append(ch);
					}

				}
			} else {
				if (ch == delimitadorString) {

					inQuotes = true;

					// Fixed : allow "" in empty quote enclosed
					if (chars[0] != '"' && delimitadorString == '\"') {
						curVal.append('"');
					}

					// double quotes in column will hit this!
					if (startCollectChar) {
						curVal.append('"');
					}

				} else if (ch == separador) {

					result.add(curVal.toString());

					curVal = new StringBuffer();
					startCollectChar = false;

				} else if (ch == '\r') {
					// ignore LF characters
					continue;
				} else if (ch == '\n') {
					// the end, break!
					break;
				} else {
					curVal.append(ch);
				}
			}

		}

		result.add(curVal.toString());

		return result;
	}

	public void guardarColeccion(String archivoCsv, List<CsvRecord> datos) throws IOException {
		FileWriter writer;
		writer = new FileWriter(archivoCsv);
		for (CsvRecord fila : datos) {
			this.writeLine(writer, fila.asCsvRow());
		}
		writer.flush();
		writer.close();
	}
	
	public void agregarFilaAlFinal(String archivoCsv, List<String> fila) throws IOException {
		FileWriter writer = new FileWriter(archivoCsv,true); 
		this.writeLine(writer, fila);
		writer.flush();
		writer.close();

	}
	
	public void agregarFilaAlFinal(String archivoCsv, CsvRecord fila) throws IOException {
		FileWriter writer = new FileWriter(archivoCsv,true); 
		this.writeLine(writer, fila.asCsvRow());
		writer.flush();
		writer.close();

	}
	
	public void eliminarFila(String archivoCsv, List<String> fila, boolean opcion) throws IOException {
		File nuevo = new File(archivoCsv);
		Scanner scanner;
		List<List<String>> filasAGuardar = new ArrayList<>();
		
		
		if(opcion) {
			if(fila.get(1).charAt(1) == '"') {
				String aux = fila.get(1);
				aux = aux.substring(1, aux.length()-1);
				fila.set(1, aux);
			}
		}
		
		try {
			scanner = new Scanner(new File(archivoCsv));
			
			while (scanner.hasNext()) {
				List<String> line = parseLine(scanner.nextLine());
				if(opcion) {
					if(!line.get(1).equals(fila.get(1))) {
						filasAGuardar.add(line);
					}
				}
				else {
					if(!line.get(0).equals(fila.get(0))) {
						filasAGuardar.add(line);
					}
				}
			}
			scanner.close();
			
			nuevo.delete();
			FileWriter writer = new FileWriter(archivoCsv,true);
			for(List<String> fil : filasAGuardar) {
				this.writeLine(writer, fil);
			}
			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// https://tools.ietf.org/html/rfc4180
	private static String followCVSformat(String value) {

		String result = value;
		if (result.contains("\"")) {
			result = result.replace("\"", "\"\"");
		}
		return result;

	}

	private void writeLine(Writer w, List<String> values) throws IOException {

		boolean first = true;
		
		if(values.get(1).charAt(1) == '"') {
			String aux = values.get(1);
			aux = aux.substring(1, aux.length()-1);
			values.set(1, aux);
		}

		// default customQuote is empty

		StringBuilder sb = new StringBuilder();
		for (String value : values) {
			if (!first) {
				sb.append(this.separador);
			}
			if (this.delimitadorString == ' ') {
				sb.append(followCVSformat(value));
			} else {
				sb.append(this.delimitadorString).append(followCVSformat(value)).append(this.delimitadorString);
			}

			first = false;
		}
		sb.append("\n");
		w.append(sb.toString());

	}

}
