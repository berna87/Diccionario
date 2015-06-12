package reto1;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Detector {

	private JFrame frame;
	private JTextField textFieldEntrada;
	private Diccionario dicEsp;
	private Diccionario dicEng;
	private Diccionario dicIta;
	private Diccionario dicFrn;
	private List<String> ngramEsp;
	private List<String> ngramEng;
	private List<String> ngramIta;
	private List<String> ngramFrn;
	private List<String> misNgramas;
	private JLabel lblTiempo,lblEsp,lblEng,lblIta,lblFrn;
	private JLabel lblTextoEscritoEn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Detector window = new Detector();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Detector() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cargarDiccionarios();
		
		JLabel lblTexto = new JLabel("TEXTO");
		
		textFieldEntrada = new JTextField();
		textFieldEntrada.setColumns(10);
		
		JButton btnComprobar = new JButton("Comprobar con Diccionarios");
		
		
		lblEsp = new JLabel("Esp :");
		
		lblEng = new JLabel("Eng :");
		
		lblIta = new JLabel("Ita :");
		
		lblFrn = new JLabel("Frn :");
		
		lblTextoEscritoEn = new JLabel("Texto escrito en :");
		
		lblTiempo = new JLabel("Tiempo : ");
		
		JButton btnNewButton = new JButton("Comprobar con NGramas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ngramEng != null || ngramEsp != null || ngramIta != null || ngramFrn != null  ){
					long inicio =0 ;
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileFilter(new FiltroTXT());
					int resultado = fileChooser.showOpenDialog(frame);
					if (resultado == JFileChooser.APPROVE_OPTION){
						try {
							inicio = System.nanoTime();
							Scanner in = new Scanner(fileChooser.getSelectedFile());
							String texto = "";
							while (in.hasNextLine()){
								texto += in.nextLine();
							}
							ConjuntoNgramas c = new ConjuntoNgramas(texto);
							misNgramas = c.nGramasMasUsados();
							compararNgramas();
							
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
					}
					long fin = System.nanoTime();
					lblTiempo.setText("Tiempo : "+(fin-inicio)/1000000+"ms");
					
				} else { System.out.println("Cargue primero uno de los nGramas");}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTexto)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldEntrada, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
						.addComponent(lblEsp)
						.addComponent(lblEng)
						.addComponent(lblIta)
						.addComponent(lblFrn)
						.addComponent(lblTextoEscritoEn)
						.addComponent(lblTiempo)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnComprobar, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTexto)
						.addComponent(textFieldEntrada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnComprobar)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEsp)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEng)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblIta)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblFrn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTextoEscritoEn)
					.addPreferredGap(ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
					.addComponent(lblTiempo)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNgramas = new JMenu("NGramas");
		menuBar.add(mnNgramas);
		
		JMenuItem mntmEsp = new JMenuItem("Esp");
		mntmEsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarNGramaEsp();
			}
		});
		mnNgramas.add(mntmEsp);
		
		JMenuItem mntmEng = new JMenuItem("Eng");
		mntmEng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarNGramaEng();
			}
		});
		mnNgramas.add(mntmEng);
		
		JMenuItem mntmIta = new JMenuItem("Ita");
		mntmIta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarNGramaIta();
			}
		});
		mnNgramas.add(mntmIta);
		
		JMenuItem mntmFrn = new JMenuItem("Frn");
		mntmFrn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarNGramaFrn();
			}
		});
		mnNgramas.add(mntmFrn);
		
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textFieldEntrada.getText().trim().equals("")){
					long inicio =0 ;
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileFilter(new FiltroTXT());
					int resultado = fileChooser.showOpenDialog(frame);
					if (resultado == JFileChooser.APPROVE_OPTION){
						try {
							inicio = System.nanoTime();
							Scanner in = new Scanner(fileChooser.getSelectedFile());
							String texto = "";
							while (in.hasNextLine()){
								texto += in.nextLine();
							}
							int valES = dicEsp.similitud(texto);
							int valEN = dicEng.similitud(texto);
							int valIT = dicIta.similitud(texto);
							int valFR = dicFrn.similitud(texto);
							
							lblEsp.setText("Esp : "+valES+"%");
							lblEng.setText("Eng : "+valEN+"%");
							lblIta.setText("Ita : "+valIT+"%");
							lblFrn.setText("Frn : "+valFR+"%");
							
							if (valES > valEN && valES > valIT && valES > valFR ) lblTextoEscritoEn.setText("Texto escrito en : Español");
							else if (valEN > valES && valEN > valIT && valEN > valFR ) lblTextoEscritoEn.setText("Texto escrito en : Ingles");
								else if (valIT > valEN && valIT > valES && valIT > valFR ) lblTextoEscritoEn.setText("Texto escrito en : Italiano");
									else if (valFR > valEN && valFR > valIT && valFR > valES ) lblTextoEscritoEn.setText("Texto escrito en : Frances");
									else lblTextoEscritoEn.setText("Texto escrito en : Varias posibilades");
							
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
					}
					long fin = System.nanoTime();
					lblTiempo.setText("Tiempo : "+(fin-inicio)/1000000+"ms");
				
				}else{
					long inicio = System.nanoTime();
					int valES = dicEsp.similitud(textFieldEntrada.getText());
					int valEN = dicEng.similitud(textFieldEntrada.getText());
					int valIT = dicIta.similitud(textFieldEntrada.getText());
					int valFR = dicFrn.similitud(textFieldEntrada.getText());
						
					lblEsp.setText("Esp : "+valES+"%");
					lblEng.setText("Eng : "+valEN+"%");
					lblIta.setText("Ita : "+valIT+"%");
					lblFrn.setText("Frn : "+valFR+"%");
					
					if (valES > valEN && valES > valIT && valES > valFR ) lblTextoEscritoEn.setText("Texto escrito en : Español");
					else if (valEN > valES && valEN > valIT && valEN > valFR ) lblTextoEscritoEn.setText("Texto escrito en : Ingles");
						else if (valIT > valEN && valIT > valES && valIT > valFR ) lblTextoEscritoEn.setText("Texto escrito en : Italiano");
							else if (valFR > valEN && valFR > valIT && valFR > valES ) lblTextoEscritoEn.setText("Texto escrito en : Frances");
							else lblTextoEscritoEn.setText("Texto escrito en : Varias posibilades");
					
					long fin = System.nanoTime();
					lblTiempo.setText("Tiempo : "+(fin-inicio)/1000000+"ms");
				}	
			}
		});
		
	}
	
	private void cargarDiccionarios(){
		File esp = new File (".\\diccionarioES.txt");
		File eng = new File (".\\diccionarioEN.txt");
		File ita = new File (".\\diccionarioIT.txt");
		File frn = new File (".\\diccionarioFR.txt");
		
		ArrayList<String> listaES = new ArrayList<String>();
		ArrayList<String> listaEN = new ArrayList<String>();
		ArrayList<String> listaIT = new ArrayList<String>();
		ArrayList<String> listaFR = new ArrayList<String>();
		
		try (Scanner in1 = new Scanner(esp,"UTF-8");
				Scanner in2 = new Scanner(eng,"UTF-8");
				Scanner in3 = new Scanner(ita,"UTF-8");
				Scanner in4 = new Scanner(frn,"UTF-8");
				){
			while (in1.hasNext()){
				listaES.add(in1.next());
			}
			while (in2.hasNext()){
				listaEN.add(in2.next());
			}
			while (in3.hasNext()){
				listaIT.add(in3.next());
			}
			while (in4.hasNext()){
				listaFR.add(in4.next());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dicEsp = new Diccionario(listaES);
		System.out.println("Diccionario español cargado");
		
		dicEng = new Diccionario(listaEN);
		System.out.println("Diccionario ingles cargado");
		
		dicIta = new Diccionario(listaIT);
		System.out.println("Diccionario italiano cargado");
		
		dicFrn = new Diccionario(listaFR);
		System.out.println("Diccionario frances cargado");
	}
	
	public void cargarNGramaEsp(){
		long inicio = System.nanoTime();
		File esp = new File (".\\ConsES.txt");
		String textoES = "";
		try (Scanner in1 = new Scanner(esp)){
			while (in1.hasNext()){
				textoES += in1.next();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConjuntoNgramas cnES = new ConjuntoNgramas(textoES);
		ngramEsp = cnES.nGramasMasUsados();
		System.out.println("Cargardos nGramas españoles");
		System.out.println(ngramEsp);
		long fin = System.nanoTime();
		lblTiempo.setText("Tiempo : "+(fin-inicio)/1000000+"ms");
	}
	
	public void cargarNGramaEng(){
		long inicio = System.nanoTime();
		File eng = new File (".\\ConsEN.txt");
		String textoEN = "";
		try (Scanner in1 = new Scanner(eng)){
			while (in1.hasNext()){
				textoEN += in1.next();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConjuntoNgramas cnEN = new ConjuntoNgramas(textoEN);
		ngramEng = cnEN.nGramasMasUsados();
		System.out.println("Cargardos nGramas ingleses");
		System.out.println(ngramEng);
		long fin = System.nanoTime();
		lblTiempo.setText("Tiempo : "+(fin-inicio)/1000000+"ms");
	}
	
	public void cargarNGramaIta(){
		long inicio = System.nanoTime();
		File ita = new File (".\\ConsIT.txt");
		String textoIT = "";
		try (Scanner in1 = new Scanner(ita)){
			while (in1.hasNext()){
				textoIT += in1.next();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConjuntoNgramas cnIT = new ConjuntoNgramas(textoIT);
		ngramIta = cnIT.nGramasMasUsados();
		System.out.println("Cargardos nGramas italianos");
		System.out.println(ngramIta);
		long fin = System.nanoTime();
		lblTiempo.setText("Tiempo : "+(fin-inicio)/1000000+"ms");
	}
	
	public void cargarNGramaFrn(){
		long inicio = System.nanoTime();
		File frn = new File (".\\ConsFR.txt");
		String textoFR = "";
		try (Scanner in1 = new Scanner(frn)){
			while (in1.hasNext()){
				textoFR += in1.next();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConjuntoNgramas cnFR = new ConjuntoNgramas(textoFR);
		ngramFrn = cnFR.nGramasMasUsados();
		System.out.println("Cargardos nGramas franceses");
		System.out.println(ngramFrn);
		long fin = System.nanoTime();
		lblTiempo.setText("Tiempo : "+(fin-inicio)/1000000+"ms");
	}
	
	public void compararNgramas(){
		int valES = 0;
		int valEN = 0;
		int valIT = 0;
		int valFR = 0;
		for (String string : misNgramas) {
			if (ngramEsp.contains(string)) valES++;
			if (ngramEng.contains(string)) valEN++;
			if (ngramIta.contains(string)) valIT++;
			if (ngramFrn.contains(string)) valFR++;
		}
		lblEsp.setText("Esp : "+valES+" Coincidencias");
		lblEng.setText("Eng : "+valEN+" Coincidencias");
		lblIta.setText("Ita : "+valIT+" Coincidencias");
		lblFrn.setText("Frn : "+valFR+" Coincidencias");
		if (valES > valEN && valES > valIT && valES > valFR ) lblTextoEscritoEn.setText("Texto escrito en : Español");
		else if (valEN > valES && valEN > valIT && valEN > valFR ) lblTextoEscritoEn.setText("Texto escrito en : Ingles");
		else if (valIT > valEN && valIT > valES && valIT > valFR ) lblTextoEscritoEn.setText("Texto escrito en : Italiano");
		else if (valFR > valEN && valFR > valIT && valFR > valES ) lblTextoEscritoEn.setText("Texto escrito en : Frances");
		else lblTextoEscritoEn.setText("Texto escrito en : Varias posibilades");
		
	}
}
