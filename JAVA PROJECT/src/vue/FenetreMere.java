package vue;
import java.util.TreeSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.*;

public class FenetreMere extends JFrame {
    public FenetreMere() {
    	Evenement bob = new Evenement("Nom du premier événement", new Date (11,2,1999), "Texte de l'événement 1", "KV1", 2);
    	Evenement bob2 = new Evenement("Nom du second événement", new Date (1,12,1987), "Texte du second événement", "KV2", 3);
    	TreeSet <Evenement> test = new TreeSet<Evenement>();
    	test.add(bob);
    	test.add(bob2);
    	Chronologie chrono1 = new Chronologie("Fabrice",test, new Date(5,8,1985),new Date(11,9,2005),5,"save");
        JPanel contentPane = new PanelFils(chrono1);
        setContentPane(contentPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setVisible(true);
        setLocation(0, 0);
        setTitle("Frise chronologique - Kerautret Steven - Brasset Alexis");
    }

    public static void main(String[] args) {
        new FenetreMere();
    }
}