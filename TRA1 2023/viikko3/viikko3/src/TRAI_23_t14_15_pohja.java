// TRAI_23_t14_15.java.java SJ


/**
 * 14. Kirjoita algoritmi joka laskee annetun binÃ¤Ã¤ripuun korkeuden, ts. pisimmÃ¤n polun juuresta
 * lehtisolmuun. Aikavaativuus? Vihje: rekursio.
 * <p>
 * 15. Kirjoita algoritmi joka lisÃ¤Ã¤ sisÃ¤jÃ¤rjestyksessÃ¤ olevaan binÃ¤Ã¤ripuuhun uuden solmun siten,
 * ettÃ¤ puu sÃ¤ilyy sisÃ¤jÃ¤rjestyksessÃ¤. Jos sama alkio (.equals()) oli jo puussa, niin alkiota ei
 * lisÃ¤tÃ¤ puuhun. Parametreina puu ja alkio. Algoritmi luo uuden solmun jos lisÃ¤ys tehdÃ¤Ã¤n.
 * Algoritmi palauttaa totuusarvon lisÃ¤ttiinkÃ¶ alkio vai ei. Algoritmin toiminta kÃ¤ydÃ¤Ã¤n
 * lÃ¤pi luennolla. Aikavaativuus?
 */

// Tarvitset projektiin (tai komentoriville) TRA-kirjaston Moodlesta.

import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

import java.util.Random;

public class TRAI_23_t14_15_pohja {

    public static void main(String[] args) {

        BTree<Integer> puu = new BTree<Integer>();

        int N = 12;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // testataan ensin vakiopuulla

        puu = exampleBTree();
        System.out.println("SisÃ¤jÃ¤rjestyksessÃ¤:");
        inorderPrint(puu);

        System.out.println("Korkeus = " + korkeus(puu));

        System.out.println();


        // tehdÃ¤Ã¤n vielÃ¤ uusi generoitu ja testataan samalla
        // tehtÃ¤vÃ¤Ã¤ 15

        puu = new BTree<>();

        System.out.println("\nPuuhun:");
        Random r = new Random(42);
        Integer x = null;
        for (int i = 0; i < N; i++) {
            x = r.nextInt(N * 2);
            System.out.print(x + " ");
            inorderInsert(puu, x);
        }
        System.out.println();

        System.out.println("SisÃ¤jÃ¤rjestyksessÃ¤:");
        inorderPrint(puu);

        System.out.println("Korkeus = " + korkeus(puu));

        // testataan vielÃ¤ hakemista
        for (int i = 0; i < N * 2; i++) {
            System.out.println("Onko " + i + " : " +
                    inorderMember(puu, i));
        }

    } // main()

    /**
     * 14. Puun korkeus. KÃ¤ynnistysmetodi
     * @param t tarkasteltava binÃ¤Ã¤ripuu.
     * @return puun korkeus tai -1 jos puu on tyhjÃ¤
     **/
    public static <E> int korkeus(BTree<E> t) {
        // TODO
        //tarkistaa onko puu tyhjä, jos on palauttaa heti -1
      if (t.isEmpty()){
            return -1;
      } //jos ei ole tyhjä, siirtyy se korkeus metodiin
        else {
            return korkeus(t.getRoot());
        }
    }

    /**
     * Solmun korkeus.
     * @param n tarkasteltava solmu
     * @return solmun korkeus, tai -1 jos n==null
     */
    public static <E> int korkeus(BTreeNode<E> n) {
        // TODO
        //Tarkistaa onko solmu tyhjä jos on palautta t-1
        if (n == null){
            return -1;
        }
        //Laskee rekursiivisesti oikean ja vasemman puolet lapset käyttäen korkeus-metodia
        int vasKorkeus = korkeus(n.getLeftChild()), oikeaKorkeus = korkeus(n.getRightChild());
        //Vertailee kumpi on korkeampi
        if (vasKorkeus > oikeaKorkeus){
            //Jos vasen on korkeampi palauttaa vasemman korkeuden
            return (vasKorkeus+1);
        } else {
            //Jos oikea on korkeampi palauttaa sen korkeuden.
            return (oikeaKorkeus+1);
        }
    }

    /**
     * 15. LisÃ¤ys sisÃ¤jÃ¤rjestettyyn binÃ¤Ã¤ripuuhun.
     * @param T binÃ¤Ã¤ripuu
     * @param lisattavaAlkio lisÃ¤ttÃ¤vÃ¤ alkio
     * @return tehtiinkÃ¶ lisÃ¤ys vai ei
     */
    public static <E extends Comparable<? super E>> boolean inorderInsert(BTree<E> T, E lisattavaAlkio) {

        // jos puu on tyhjÃ¤, lisÃ¤tÃ¤Ã¤n juureksi
        BTreeNode<E> n = T.getRoot();
        if (n == null) {
            T.setRoot(new BTreeNode<E>(lisattavaAlkio));
            return true;
        }

        // muuten etsitÃ¤Ã¤n paikka ja jos tÃ¶rmÃ¤tÃ¤Ã¤n tyhjÃ¤Ã¤n solmuun, lisÃ¤tÃ¤Ã¤n sen tilalle
        // TODO


        // Silmukka pyörii niin kauan kuin solmu ei ole tyhjä
        while (n != null){
            //Jos solmu on pienempi kuin lisättävä arvo siirrymme vasemmalle solmulle
            if (lisattavaAlkio.compareTo(n.getElement()) < 0) {
                //tarkistaa, onko solmu tyhjä
                if (n.getLeftChild() == null) {
                    //jos solmu on tyhjä lisää alkion
                    n.setLeftChild(new BTreeNode<E>(lisattavaAlkio));
                    return true;
                } else {
                    //muuten siirtyy yhdne solmun eteenpäin
                    n = n.getLeftChild();
                }
                //Jos alkio on suurempi kuin solmu siirytää oikealle.
            } else if (lisattavaAlkio.compareTo(n.getElement()) > 0) {
                if (n.getRightChild() == null) {
                    //Jos oikea solmu on tyhjä, asetetaan arvo kyseiselle solmulle
                    n.setRightChild(new BTreeNode<E>(lisattavaAlkio));
                    return true;
                }  //muuten siirytään yksi solmu eteenpäin
                else {
                    n = n.getRightChild();
                }
                //Jos alkio ja solmu on saman arvoiset lopetetaan tutkiminen, eikä lisätä solmua puuhun.
            } else if (lisattavaAlkio.compareTo(n.getElement()) == 0){
                return false;
            }
            }
        return false;


    } // inorderInsert()


    // -------------------------------
    // esimerkkejÃ¤


    public static BTree<Integer> exampleBTree() {

        BTree<Integer> T = new BTree<Integer>();

        // juuri
        T.setRoot(new BTreeNode<Integer>(9));

        BTreeNode<Integer> n = T.getRoot();

        // juuren lapset
        n.setLeftChild(new BTreeNode<Integer>(5));
        n.setRightChild(new BTreeNode<Integer>(15));

        // vasen haara
        BTreeNode<Integer> l = n.getLeftChild();

        l.setLeftChild(new BTreeNode<Integer>(3));
        l.setRightChild(new BTreeNode<Integer>(8));

        l.getLeftChild().setRightChild(new BTreeNode<Integer>(4));

        // oikea haara
        l = n.getRightChild();

        l.setLeftChild(new BTreeNode<Integer>(12));
        l.setRightChild(new BTreeNode<Integer>(18));

        l.getLeftChild().setLeftChild(new BTreeNode<Integer>(11));
        l.getLeftChild().setRightChild(new BTreeNode<Integer>(13));


        System.out.println("                 ");
        System.out.println("       9        ");
        System.out.println("    __/  \\__     ");
        System.out.println("   5        15   ");
        System.out.println("  / \\      /  \\  ");
        System.out.println(" 3   8   12    18");
        System.out.println("  \\      /\\      ");
        System.out.println("   4    11 13    ");
        System.out.println("                 ");

        return T;

    } // exampleBTree()



    /**
     * Onko alkio sisÃ¤jÃ¤rjestetyssÃ¤ binÃ¤Ã¤ripuussa vai ei
     * @param T sisÃ¤jÃ¤rjestetty binÃ¤Ã¤ripuu
     * @param x etsittÃ¤vÃ¤ alkio
     * @param <E> alkiotyyppi
     * @return true jos alkio x on puussa, muuten false
     */
    public static <E extends Comparable<? super E>>
    boolean inorderMember(BTree<E> T, E x) {
        BTreeNode<E> n = T.getRoot();

        while (n != null) {
            if (x.compareTo(n.getElement()) == 0)
                return true;
            else if (x.compareTo(n.getElement()) < 0)
                n = n.getLeftChild();
            else
                n = n.getRightChild();
        } // while
        return false;

    } // inorderMember()


    /**
     * Tulostus sisÃ¤jÃ¤rjestyksessÃ¤ rekursiivisesti.
     * @param T tulostettava puu
     */
    public static void inorderPrint(BTree T) {
        inorderPrintBranch(T.getRoot());
        System.out.println();
    } // inorderPrint()


    /**
     * Tulostus sisÃ¤jÃ¤rjestyksessÃ¤ rekursiivisesti.
     * @param n tulostettavan alipuun juuri
     */
    public static void inorderPrintBranch(BTreeNode n) {
        if (n == null)
            return;

        inorderPrintBranch(n.getLeftChild());
        System.out.print(n.getElement() + " ");
        inorderPrintBranch(n.getRightChild());

    } // inorderPrintBranch()


} // class TRAI_23_t14_15.java