package vue;

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

public class FenetreMere extends JFrame implements ActionListener {
	public int nbChrono=0;
	public PanelFils contentPane;
	private String chNom;
	private Date chDebut;
	private Date chFin;
	private int chPas;
	private String chSave;
	private TreeSet<Evenement> chEvts;
	
    public FenetreMere() {
    	Evenement bob = new Evenement("Nom du premier événement", new Date (11,2,1999), "Texte de l'événement 1", "KV1", 2);
    	Evenement bob2 = new Evenement("Nom du second événement", new Date (1,12,1987), "Texte du second événement", "KV2", 3);
    	Evenement evt1 = new Evenement("Bataille de java", new Date (15,9,1981), "Java is a general-purpose computer-programming language that is concurrent, class-based, object-oriented, and specifically designed to have as few implementation dependencies as possible.", "JAVA", 1);
    	Evenement evt2 = new Evenement("Guerre du python", new Date (19,6,2002), "Python is an interpreted high-level programming language for general-purpose programming. Created by Guido van Rossum and first released in 1991, Python has a design philosophy that emphasizes code readability, notably using significant whitespace.", "PYTHON", 0);
    	TreeSet <Evenement> test = new TreeSet<Evenement>();
    	test.add(bob);
    	test.add(bob2);
    	test.add(evt1);
    	test.add(evt2);
    	Chronologie chrono1 = new Chronologie("Fabrice",test, new Date(5,8,1980),new Date(11,9,2005),5,"save");
        contentPane = new PanelFils(chrono1);
        
        //Barre des menus
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        //Creation
        JMenu creation = new JMenu("Création");
        JMenuItem nouveau = new JMenuItem("Nouveau",'N');
        JMenuItem open = new JMenuItem("Ouvrir",'O');
        JMenuItem save = new JMenuItem("Sauvegarder",'S');
        nouveau.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        nouveau.setActionCommand("new");
        open.setActionCommand("open");
        save.setActionCommand("save");
        nouveau.setAccelerator(KeyStroke.getKeyStroke('N', java.awt.Event.CTRL_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke('S', java.awt.Event.CTRL_MASK));
        creation.add(nouveau);
        creation.add(open);
        creation.add(save);
        
        //Affichage
        JMenu affichage = new JMenu("Affichage");
        JMenuItem settings = new JMenuItem("Paramètres",'P');
        settings.addActionListener(this);
        settings.setActionCommand("settings");
        affichage.add(settings);
        
        //Fermer
        JMenu fermer = new JMenu("Fermer");
        JMenuItem close = new JMenuItem("Fermer la fenêtre",'F');
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
        setSize(1280, 800);
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
    			texteLu += ligneLue + "§";
    		}
    		in.close();
    	}
    	catch(IOException parExc) {
    		System.exit(0);
    	}
    	String[] output = texteLu.split("\\§");
    	return output;
    }

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="new") {
			PanelFormulaire panelFormulaire = new PanelFormulaire();
			JFrame fenetreFormulaire = new JFrame();
			fenetreFormulaire.setContentPane(panelFormulaire);
			fenetreFormulaire.setDefaultCloseOperation(HIDE_ON_CLOSE);
			fenetreFormulaire.setSize(300, 200);
			fenetreFormulaire.setVisible(true);
			fenetreFormulaire.setLocation(0, 0);
			fenetreFormulaire.setTitle("Nouvelle frise");
		}
		
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
				//chDebut = lu[1]; String vers date?
				//chFin = lu[2];
				chPas = Integer.parseInt(lu[3]);
				chSave = lu[4];
				Evenement [] events = new Evenement[lu.length-5];
				for (int i=5; i<lu.length;i++) {
					//events[i-5]=lu[i]; String vers événement?
				}
				
				//Par défaut
				Evenement evt1 = new Evenement("Bataille de java", new Date (11,2,1941), "Java is a general-purpose computer-programming language that is concurrent, class-based, object-oriented, and specifically designed to have as few implementation dependencies as possible.", "JAVA", 2);
		    	Evenement evt2 = new Evenement("Guerre du python", new Date (1,12,1960), "Python is an interpreted high-level programming language for general-purpose programming. Created by Guido van Rossum and first released in 1991, Python has a design philosophy that emphasizes code readability, notably using significant whitespace.", "PYTHON", 3);
		    	TreeSet <Evenement> evts = new TreeSet<Evenement>();
		    	evts.add(evt1);
		    	evts.add(evt2);
		
				Chronologie open = new Chronologie(chNom, evts, new Date(11,9,1939),new Date(18,02,1969), chPas, chSave);
				contentPane = new PanelFils(open);
				//setContentPane(contentPane);
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