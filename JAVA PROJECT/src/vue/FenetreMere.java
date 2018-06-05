package vue;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import model.*;

public class FenetreMere extends JFrame implements ActionListener,Data {
	public int nbChrono=0;
	public PanelFils contentPane;
	private String chNom;
	private Date chDebut;
	private Date chFin;
	private int chPas;
	private String chSave;
	private TreeSet<Evenement> chEvts;
	
    public FenetreMere() {
    	Evenement bob = new Evenement("Le passage à l'an 2000", new Date (1,1,2000), "Feu d'artifice", "2000", 3);
    	Evenement bob2 = new Evenement("Concorde s'écrase", new Date (25,7,2000), "Le concorde s'écrase le 25 juillet 2000, 1min28 après son décollage. ", "CONCORDE", 2);
    	Evenement evt1 = new Evenement("Vladimir Poutine", new Date (15,9,2004), "Vladimir Poutine fringant comme un jeune coq lors de ses vacances en Sibérie.", "POUTINE", 2);
    	Evenement evt2 = new Evenement("Robot curiosity", new Date (7,8,2012), "La première image du robot Curiosity.", "CURIOSITY", 0);
    	TreeSet <Evenement> test = new TreeSet<Evenement>();
    	test.add(bob);
    	test.add(bob2);
    	test.add(evt1);
    	test.add(evt2);
    	Chronologie chrono1 = new Chronologie("Années 2000",test, new Date(5,8,1999),new Date(17,3,2015),5,"save21Century");
        contentPane = new PanelFils(chrono1);
        
      //Barre des menus
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        //Creation
        JMenu creation = new JMenu("Création");
        JMenu nouveau = new JMenu("Nouveau");
        JMenuItem newEvt = new JMenuItem(ITEMS[1],'N');
        JMenuItem newFrise = new JMenuItem("Nouvel Frise",'F');
        JMenuItem open = new JMenuItem("Ouvrir",'O');
        JMenuItem save = new JMenuItem("Sauvegarder",'S');
        newEvt.addActionListener(contentPane);
        newFrise.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        newEvt.setActionCommand(ITEMS[1]);
        newFrise.setActionCommand("newFrise");
        open.setActionCommand("open");
        save.setActionCommand("save");
        newEvt.setAccelerator(KeyStroke.getKeyStroke('N', java.awt.Event.CTRL_MASK));
        newFrise.setAccelerator(KeyStroke.getKeyStroke('F', java.awt.Event.CTRL_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke('S', java.awt.Event.CTRL_MASK));
        creation.add(nouveau);
        nouveau.add(newEvt);
        nouveau.add(newFrise);
        creation.add(open);
        creation.add(save);
        
        //Affichage
        JMenuItem affichage = new JMenuItem(ITEMS[0],'A');
        affichage.addActionListener(contentPane);
        affichage.setActionCommand(ITEMS[0]);
        affichage.setAccelerator(KeyStroke.getKeyStroke('A', java.awt.Event.CTRL_MASK));
        
        //Fermer
        JMenu fermer = new JMenu("Fermer");
        JMenuItem close = new JMenuItem("Fermer la fenetre",'F');
        close.addActionListener(this);
        close.setActionCommand("exit");
        fermer.add(close);
        fermer.addActionListener(contentPane);
        
        //Aide
        JMenu aide = new JMenu("?");
        JMenuItem help = new JMenuItem("Afficher l'aide",'A');
        help.addActionListener(this);
        help.setActionCommand("help");
        help.setAccelerator(KeyStroke.getKeyStroke('A', java.awt.Event.CTRL_MASK));
        aide.add(help);
        
        menuBar.add(creation);
        menuBar.add(affichage);
        menuBar.add(fermer);
        menuBar.add(aide);
        setContentPane(contentPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 830);
        setVisible(true);
        setLocation(0, 0);
        setTitle("Frise chronologique - Kerautret Steven - Brasset Alexis");
    }

    public static void main(String[] args) {
        new FenetreMere();
    }
    
    public static void writeFile(String parFile, String parTexte){
    	try {
    	File file = new File ("save/"+parFile+".txt");
    	BufferedWriter out = new BufferedWriter(new FileWriter(file)); 
    	out.write(parTexte);
    	out.close();	
    	}
    	catch(IOException parExc) {
    		System.exit(0);
    	}
	}
    
    public static String[] readFile(String parFile) {
    	String texteLu = new String();
    	String ligneLue = new String();
    	try {
    		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File("save/"+parFile))));
    		while((ligneLue=in.readLine())!=null) {
    			texteLu += ligneLue;
    		}
    		in.close();
    	}
    	catch(IOException parExc) {
    		System.exit(0);
    	}
    	String[] output = texteLu.split("\\Â§");
    	return output;
    }
    
    public Date getDate(String lu){
    	String[] dateString = lu.split("\\/");
		Integer[] dateInt = new Integer[dateString.length];
		for (int i=0; i<dateString.length; i++){
			dateInt [i] = Integer.parseInt(dateString[i]);
		}
		return new Date(dateInt[0], dateInt[1], dateInt[2]);
    }
    
    public Evenement getEvt(String lu){
    	String[] evenementString = lu.split("\\@");
    	return new Evenement(evenementString[0],getDate(evenementString[1]),evenementString[2],evenementString[3],Integer.parseInt(evenementString[4]));
    }

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="save") {
			Chronologie chronoCourante = contentPane.getChrono();
			String savePath = new String(chronoCourante.getSave());
			Object[] options = {"Oui","Non"};
			int n = JOptionPane.showOptionDialog(
								this,"Voulez vous sauvegarder la chronologie actuelle?",
								"Sauvegarder", 
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null, 
								options,options[1]);
			if (n==0){
				writeFile(savePath,chronoCourante.toString());
			}		
		}
		
		if(e.getActionCommand()=="open") {
			File dossier = new File("save/");
			File[] fichiers = dossier.listFiles();
			String[] sauvegardes = new String[fichiers.length];
		    for (int i = 0; i < fichiers.length; i++) {
		      if (fichiers[i].isFile()) {
		        sauvegardes[i]=fichiers[i].getName();
		      }
		    }
			String choix = (String)JOptionPane.showInputDialog(
			                    this,
			                    "Quel fichier de sauvegarde voulez vous ouvrir?",
			                    "Ouvrir",
			                    JOptionPane.PLAIN_MESSAGE,
			                    null,
			                    sauvegardes,
			                    "save");

			if ((choix != null) && (choix.length() > 0)) {
				String [] lu = readFile(choix);
				chNom = lu[0];
				chDebut = getDate(lu[1]);
				chFin = getDate(lu[2]);
				chPas = Integer.parseInt(lu[3]);
				chSave = lu[4];
				Evenement [] events = new Evenement[lu.length-5];
				TreeSet <Evenement> evts = new TreeSet<Evenement>();
				for (int i=0; i<events.length;i++) {
					events[i]=getEvt(lu[i+5]);
					evts.add(events[i]);
				}
				/*
				//Par dï¿½faut
				Evenement evt1 = new Evenement("Bataille de java", new Date (11,2,1941), "Java is a general-purpose computer-programming language that is concurrent, class-based, object-oriented, and specifically designed to have as few implementation dependencies as possible.", "JAVA", 2);
		    	Evenement evt2 = new Evenement("Guerre du python", new Date (1,12,1960), "Python is an interpreted high-level programming language for general-purpose programming. Created by Guido van Rossum and first released in 1991, Python has a design philosophy that emphasizes code readability, notably using significant whitespace.", "PYTHON", 3);
		    	TreeSet <Evenement> evts = new TreeSet<Evenement>();
		    	evts.add(evt1);
		    	evts.add(evt2);
		*/
				Chronologie open = new Chronologie(chNom, evts, chDebut, chFin, chPas, chSave);
				contentPane = new PanelFils(open);
				remove(this.getContentPane());
				setContentPane(contentPane);
				pack();
			}
		}
		
		if(e.getActionCommand()=="settings") {
			System.out.println("settings");
		}
		
		if(e.getActionCommand()=="exit") {
			System.exit(0);
		}
		
		if(e.getActionCommand()=="help") {
			System.out.println("help");
		}
	}
}