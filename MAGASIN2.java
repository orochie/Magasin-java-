import java.util.*;

abstract class Produit {
    private String nom;
    private String description;

    public Produit(String nom, String description) {
        if (nom == null || nom.isEmpty() || description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Le nom et la description ne doivent jamais être vides.");
        }
        this.nom = nom;
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public abstract double getPrix();
}

class ArticleUnite extends Produit {
    private double prixUnitaire;

    public ArticleUnite(String nom, String description, double prixUnitaire) {
        super(nom, description);
        this.prixUnitaire = prixUnitaire;
    }

    @Override
    public double getPrix() {
        return prixUnitaire;
    }
}

class ArticlePoids extends Produit {
    private double prixKilo;

    public ArticlePoids(String nom, String description, double prixKilo) {
        super(nom, description);
        this.prixKilo = prixKilo;
    }

    @Override
    public double getPrix() {
        return prixKilo;
    }
}

class AppareilElectronique extends Produit {
    private double prix;

    public AppareilElectronique(String nom, String description, double prix) {
        super(nom, description);
        this.prix = prix;
    }

    @Override
    public double getPrix() {
        return prix;
    }

    public int getGarantie() {
        return 12;
    }
}

class Panier {
    private Map<Produit, Integer> contenu;

    public Panier() {
        contenu = new LinkedHashMap<>();
    }

    public void ajouterProduit(Produit produit, int quantite) {
        contenu.put(produit, quantite);
    }

    public void afficherContenu() {
        System.out.println("Contenu du panier :");
        double prixTotal = 0.0;
        for (Map.Entry<Produit, Integer> entry : contenu.entrySet()) {
            Produit produit = entry.getKey();
            int quantite = entry.getValue();
            System.out.println(produit.getNom() + " - " + produit.getDescription() + " - Quantité : " + quantite);
            prixTotal += produit.getPrix() * quantite;
        }
        System.out.println("Prix total du panier : " + prixTotal);
    }
}

public class MAGASIN2 {
    public static void main(String[] args) {
        List<Produit> produits = new ArrayList<>();
        produits.add(new ArticleUnite("Pomme", "Fruit délicieux", 0.5));
        produits.add(new ArticlePoids("Carotte", "Légume orange", 1.2));
        produits.add(new AppareilElectronique("Smartphone", "Appareil électronique moderne", 799));

        Panier panier = new Panier();

        Scanner scanner = new Scanner(System.in);
        int choix = 0;

        do {
            System.out.println("Menu :");
            System.out.println("1 - Ajouter un nouvel article");
            System.out.println("2 - Liste complète des produits");
            System.out.println("3 - Ajouter un produit au panier");
            System.out.println("4 - Voir le panier et le prix total de celui-ci");
            System.out.println("5 - Quitter");

            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.print("Entrez le nom de l'article : ");
                    String nom = scanner.next();
                    System.out.print("Entrez la description de l'article : ");
                    String description = scanner.next();
                    System.out.print("Entrez le prix de l'article : ");
                    double prix = scanner.nextDouble();
                    produits.add(new ArticleUnite(nom, description, prix));
                    System.out.println("L'article a été créé.");
                    break;

                case 2:
                    System.out.println("Liste des produits à la vente :");
                    for (Produit produit : produits) {
                        System.out.println(produit.getNom() + " - " + produit.getDescription());
                    }
                    break;

                case 3:
                    System.out.println("Liste des produits à la vente :");
                    for (int i = 0; i < produits.size(); i++) {
                        Produit produit = produits.get(i);
                        System.out.println(i + " - " + produit.getNom() + " - " + produit.getDescription());
                    }
                    System.out.print("Entrez l'indice du produit à ajouter : ");
                    int indice = scanner.nextInt();
                    if (indice < 0 || indice >= produits.size()) {
                        System.out.println("Indice invalide.");
                        break;
                    }
                    Produit produit = produits.get(indice);
                    System.out.print("Entrez la quantité : ");
                    int quantite = scanner.nextInt();
                    panier.ajouterProduit(produit, quantite);
                    System.out.println("Le produit a été ajouté au panier.");
                    break;

                case 4:
                    panier.afficherContenu();
                    break;

                case 5:
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide.");
                    break;
            }

            System.out.println();
        } while (choix != 5);

        scanner.close();
    }
}
