package frsf.isi.died.app.vista;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import frsf.isi.died.app.controller.TiposAcciones;
import frsf.isi.died.app.controller.MenuController;

public class Principal {
	public static void main(String[] args) {
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	          createAndShowGUI();
	        }
	    });
	}

	 private static void createAndShowGUI() {
		 JFrame f = new JFrame();
		 MenuController controller = new MenuController(f);
				    
	        JMenuBar menuBar;
	        JMenu menu;
	        JMenuItem menuItem;

	        menuBar = new JMenuBar();

	        menu = new JMenu("Sistema");
	        menuBar.add(menu);
	        
	        menuItem = new JMenuItem("Nuevo Libro");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.ABM_LIBROS));
	        menu.add(menuItem);

	        menuItem = new JMenuItem("Nuevo Video");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.ABM_VIDEOS));
	        menu.add(menuItem);
	        menu.addSeparator();
	        
	        menuItem = new JMenuItem("Buscar Libro");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.BUSQ_LIB));
	        menu.add(menuItem);
	        
	        menuItem = new JMenuItem("Buscar Video");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.BUSQ_VID));
	        menu.add(menuItem);
	        menu.addSeparator();
	        
	        menuItem = new JMenuItem("Actualizar Libro");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.ACT_LIBRO));
	        menu.add(menuItem);
	        
	        menuItem = new JMenuItem("Actualizar Video");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.ACT_VIDEO));
	        menu.add(menuItem);
	        menu.addSeparator();
	        
	        menuItem = new JMenuItem("Eliminar Libro");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.ELIM_LIBRO));
	        menu.add(menuItem);
	        
	        menuItem = new JMenuItem("Eliminar Video");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.ELIM_VIDEO));
	        menu.add(menuItem);
	        menu.addSeparator(); 
	        
	        menuItem = new JMenuItem("Salir");
	        menuItem.addActionListener(e->System.exit(99));
	        menu.add(menuItem);

	        menuBar.add(menu);
	        
	        menu = new JMenu("Perfil");
	        menuBar.add(menu);
	        
	        menuItem = new JMenuItem("Agregar Material A Mi Wishlist");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.AGREGAR_WISHLIST));
	        menu.add(menuItem);
	        menu.addSeparator();
	        
	        menuItem = new JMenuItem("Mi Wishlist");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.VER_WISHLIST));
	        menu.add(menuItem);
	        
	        menu = new JMenu("Contenidos");
	        menuBar.add(menu);
	        
	        menuItem = new JMenuItem("Agregar Contenidos");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.AGREGAR_CONT));
	        menu.add(menuItem);
	        menu.addSeparator();
	        
	        menuItem = new JMenuItem("Buscar Contenidos");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.BUSCAR_CONT));
	        menu.add(menuItem);
	        
	        menu = new JMenu("Opciones");
	        menuBar.add(menu);
	        
	        JMenu menuAux = new JMenu("Buscar Caminos");
	        
	        menuItem = new JMenuItem("Con N Saltos");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.VER_GRAFO));
	        menuAux.add(menuItem);
	        
	        menuItem = new JMenuItem("Todos");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.VER_GRAFO_CAMINOS));
	        menuAux.add(menuItem);
	        
	        menu.add(menuAux);
	        menu.addSeparator();
	        
	        menuItem = new JMenuItem("Page Rank");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.VER_PAGE_RANK));
	        menu.add(menuItem);
	        
	        f.setJMenuBar(menuBar);
	        
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        f.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	        f.pack();
	        f.setVisible(true);
	    }

}
