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
import javax.swing.KeyStroke;
import model.*;

public class FenetreMere extends JFrame implements ActionListener {
	
	
    public FenetreMere() {
    	Evenement bob = new Evenement("Nom du premier événement", new Date (11,2,1999), "Texte de l'événement 1", "KV1", 2);
    	Evenement bob2 = new Evenement("Nom du second événement", new Date (1,12,1987), "Texte du second événement", "KV2", 3);
    	TreeSet <Evenement> test = new TreeSet<Evenement>();
    	test.add(bob);
    	test.add(bob2);
    	Chronologie chrono1 = new Chronologie("Fabrice",test, new Date(5,8,1985),new Date(11,9,2005),5,"save");
        PanelFils contentPane = new PanelFils(chrono1);
        
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
    		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File("save/"+parFile+".txt"))));
    		while((ligneLue=in.readLine())!=null) {
    			texteLu += ligneLue + ".";
    		}
    		in.close();
    	}
    	catch(IOException parExc) {
    		System.exit(0);
    	}
    	String[] output = texteLu.split("\\.");
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
			writeFile("test","texte.nom.date.poids");
			}
		if(e.getActionCommand()=="open") {
			String [] lu = readFile("test");
			for (int i=0; i<lu.length;i++) {
				System.out.println(lu[i]);
			}	
		}
		if(e.getActionCommand()=="settings") {
			System.out.println("settings");
		}
		if(e.getActionCommand()=="exit") {
			System.out.println("exit");
		}
		if(e.getActionCommand()=="help") {
			System.out.println("help");
		}
	}
}