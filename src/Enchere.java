import java.util.ArrayList;
import java.util.List;

public class Enchere {

    String produit;
    float prix;
    int heure;
    int etat=0;
    public List<Offre> offres=new ArrayList<Offre>();
    public List<Membre> membres=new ArrayList<Membre>();
    static int idGen=0;
    int id= 0;
    public Enchere(String produit,float prix,int heure){
        this.produit=produit;
        this.prix = prix;
        this.heure=heure;
        idGen++;
        id=idGen;
        

    }
    @Override
    public String toString(){
        return id+"#"+produit+"#"+prix+"#"+heure;
    }
    
}
