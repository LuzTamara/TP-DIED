package frsf.isi.died.app.controller;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import frsf.isi.died.app.vista.grafo.ControlPanel;
import frsf.isi.died.app.vista.grafo.ControlPanelCaminos;
import frsf.isi.died.app.vista.grafo.GrafoPanel;
import frsf.isi.died.app.vista.material.LibroPanel;
import frsf.isi.died.app.vista.material.VideoPanel;
import frsf.isi.died.app.vista.material.AgregarMatWishlistPanel;
import frsf.isi.died.app.vista.material.WishlistPanel;
import frsf.isi.died.app.vista.material.PageRankPanel;
import frsf.isi.died.app.vista.material.AgregarContPanel;
import frsf.isi.died.app.vista.material.BuscarContPanel;
import frsf.isi.died.app.vista.material.BusqPanelLib;
import frsf.isi.died.app.vista.material.BusqPanelVid;
import frsf.isi.died.app.vista.material.ActLibroPanel;
import frsf.isi.died.app.vista.material.ActVideoPanel;
import frsf.isi.died.app.vista.material.ElimLibroPanel;
import frsf.isi.died.app.vista.material.ElimVideoPanel;

public class MenuController {

	private JFrame framePrincipal;
	
	public MenuController(JFrame f) {
		this.framePrincipal = f;
	}
	
	public void showView(TiposAcciones accion) {
		switch (accion) {
		case ABM_LIBROS:
			LibroPanel panelLibros = new LibroPanel();
			LibroController controllerl = new LibroController(panelLibros);
			controllerl.crearPanel();
			framePrincipal.setContentPane(controllerl.getPanelLibro());
			break;
		case ABM_VIDEOS:
			VideoPanel panelVideos = new VideoPanel();
			VideoController controllerv = new VideoController(panelVideos);
			controllerv.crearPanel();
			framePrincipal.setContentPane(controllerv.getPanelVideo());
			break;
		case AGREGAR_WISHLIST:
			AgregarMatWishlistPanel panelAgregarWishlist = new AgregarMatWishlistPanel();
			AgregarMatWishlistController controlleraw = new AgregarMatWishlistController(panelAgregarWishlist);
			controlleraw.crearPanel();
			framePrincipal.setContentPane(controlleraw.getPanelAgregarWishlist());
			break;
		case VER_WISHLIST:
			WishlistPanel panelWishlist = new WishlistPanel();
			WishlistController controllerw = new WishlistController(panelWishlist);
			controllerw.crearPanel();
			framePrincipal.setContentPane(controllerw.getPanelWishlist());
			break;
		case BUSQ_LIB:
			BusqPanelLib panelBusquedaLibro = new BusqPanelLib();
			BusqControllerLib blcontroller = new BusqControllerLib(panelBusquedaLibro);
			blcontroller.crearPanel();
			framePrincipal.setContentPane(blcontroller.getpanelBusquedaLibro());
			break;
		case BUSQ_VID:
			BusqPanelVid panelBusquedaVideo = new BusqPanelVid();
			BusqControllerVid bvcontroller = new BusqControllerVid(panelBusquedaVideo);
			bvcontroller.crearPanel();
			framePrincipal.setContentPane(bvcontroller.getPanelBusquedaVideo());
			break;
		case AGREGAR_CONT:
			AgregarContPanel panelAgregarContenido = new AgregarContPanel();
			AgregarContController accontroller = new AgregarContController(panelAgregarContenido);
			accontroller.crearPanel();
			framePrincipal.setContentPane(accontroller.getPanelContenido());
			break;
		case BUSCAR_CONT:
			BuscarContPanel panelBuscarContenido = new BuscarContPanel();
			BuscarContController bccontroller = new BuscarContController(panelBuscarContenido);
			bccontroller.crearPanel();
			framePrincipal.setContentPane(bccontroller.getPanelBuscarCont());
			break;
		case VER_GRAFO:
			JPanel panel = new JPanel(new BorderLayout());
			ControlPanel controlPanel = new ControlPanel();
			GrafoPanel grafoPanel = new GrafoPanel();
			GrafoController grfController = new GrafoController(grafoPanel,controlPanel, null, this, null);
			panel.add(controlPanel , BorderLayout.PAGE_START);
			panel.add(grafoPanel , BorderLayout.CENTER);
			framePrincipal.setContentPane(panel);
			break;
		case VER_GRAFO_CAMINOS:
			JPanel panelC = new JPanel(new BorderLayout());
			ControlPanelCaminos controlPanelC = new ControlPanelCaminos();
			GrafoPanel grafoPanelC = new GrafoPanel();
			GrafoController grfControllerC = new GrafoController(grafoPanelC, null, controlPanelC, this, null);
			panelC.add(controlPanelC , BorderLayout.PAGE_START);
			panelC.add(grafoPanelC , BorderLayout.CENTER);
			framePrincipal.setContentPane(panelC);
			break;
		case VER_PAGE_RANK:
			PageRankPanel panelPageRank = new PageRankPanel();
			PageRankController controllerpr = new PageRankController(panelPageRank);
			controllerpr.crearPanel();
			framePrincipal.setContentPane(controllerpr.getPanelPageRank());
			break;
		case ACT_LIBRO:
			ActLibroPanel panelActLibro = new ActLibroPanel();
			ActLibroController controlleral = new ActLibroController(panelActLibro);
			controlleral.crearPanel();
			framePrincipal.setContentPane(controlleral.getPanelActLibro());
			break;
		case ACT_VIDEO:
			ActVideoPanel panelActVideo = new ActVideoPanel();
			ActVideoController controllerav = new ActVideoController(panelActVideo);
			controllerav.crearPanel();
			framePrincipal.setContentPane(controllerav.getPanelActVideo());
			break;
		case ELIM_LIBRO:
			ElimLibroPanel panelElimLibro = new ElimLibroPanel();
			ElimLibroController controllerel = new ElimLibroController(panelElimLibro);
			controllerel.crearPanel();
			framePrincipal.setContentPane(controllerel.getPanelElimLibro());
			break;
		case ELIM_VIDEO:
			ElimVideoPanel panelElimVideo = new ElimVideoPanel();
			ElimVideoController controllerev = new ElimVideoController(panelElimVideo);
			controllerev.crearPanel();
			framePrincipal.setContentPane(controllerev.getPanelElimVideo());
			break;
		default:
			break;
		}
		framePrincipal.pack();

	}
	
	public void actualizarPanelControl(String tema) {
		JPanel panel = new JPanel(new BorderLayout());
		ControlPanel controlPanel = new ControlPanel();
		GrafoPanel grafoPanel = new GrafoPanel();
		GrafoController grfController = new GrafoController(grafoPanel, controlPanel, null, this, tema);
		panel.add(controlPanel , BorderLayout.PAGE_START);
		panel.add(grafoPanel , BorderLayout.CENTER);
		framePrincipal.getContentPane().removeAll();
		framePrincipal.setContentPane(panel);
		framePrincipal.pack();
	}
	
	public void actualizarPanelControlCaminos(String tema) {
		JPanel panelC = new JPanel(new BorderLayout());
		ControlPanelCaminos controlPanelC = new ControlPanelCaminos();
		GrafoPanel grafoPanelC = new GrafoPanel();
		GrafoController grfControllerC = new GrafoController(grafoPanelC, null, controlPanelC, this, tema);
		panelC.add(controlPanelC , BorderLayout.PAGE_START);
		panelC.add(grafoPanelC , BorderLayout.CENTER);
		framePrincipal.getContentPane().removeAll();
		framePrincipal.setContentPane(panelC);
		framePrincipal.pack();
	}
	
}
