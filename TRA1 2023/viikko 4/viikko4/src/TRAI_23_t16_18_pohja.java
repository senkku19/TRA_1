// TRAI_23_t16_18.java.java SJ


/**
 * 16. Kirjoita algoritmi joka etsii binÃ¤Ã¤ripuun vÃ¤hiten syvÃ¤n (lÃ¤himpÃ¤nÃ¤ juurta olevan) leh-
 * tisolmun. Vihje: tasoittainen lÃ¤pikÃ¤ynti. Aikavaativuus?
 *
 * 17. Kirjoita algoritmi, joka vertaa kahta binÃ¤Ã¤ripuuta ja palauttaa toden jos puut ovat raken-
 * teeltaan samat, muuten epÃ¤toden. Puut ovat rakenteeltaan samat, jos juuren molemmat
 * alipuut ovat keskenÃ¤Ã¤n rakenteeltaan samanlaiset. Puun sisÃ¤ltÃ¤miÃ¤ alkioita ei siis vertailla,
 * vain puun rakennetta (â€oksaston muotoaâ€). Aikavaativuus? Tilavaativuus? Vihje: rekursio
 * rinta rinnan molemmissa puissa.
 *
 * 18. Kirjoita algoritmi joka laskee annetun binÃ¤Ã¤ripuun eri solmujen suurimman lasten kor-
 * keuseron. Algoritmi siis selvittÃ¤Ã¤ mille binÃ¤Ã¤ripuun solmulle oikean ja vasemman alipuun
 * korkeusero on suurin ja palauttaa ko. korkeuseron. Algoritmia voisi kÃ¤yttÃ¤Ã¤ AVL-puun
 * tasapainon tarkastamiseen, mutta muistetaan, ettei AVL-puun toteutus tarvise tÃ¤llÃ¤is-
 * ta tarkastamista. MikÃ¤ on algoritmisi aikavaativuus? Aikavaativuus vaikuttaa hieman
 * tehtÃ¤vÃ¤n arvosteluun.
 */

import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;
import fi.uef.cs.tra.LinkedQueue;
import fi.uef.cs.tra.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class TRAI_23_t16_18_pohja {

    public static void main(String[] args) {

        BTree<Integer> puu = null;


        // testataan ensin vakiopuulla

        puu = exampleBTree();
        System.out.println("SisÃ¤jÃ¤rjestyksessÃ¤:");
        inorderPrint(puu);

        System.out.println("Matalin lehtisolmu: " + matalinLehtisolmu(puu));

        int ero = suurinEro(puu);
        System.out.println("Suurin ero on " + ero);

        BTree<Integer> puu2 = exampleBTree();

        System.out.println("Samat rakenteet : " + vertaaRakenne(puu, puu2));

        System.out.println("\nLisÃ¤tÃ¤Ã¤n: 8");
        inorderInsert(puu, 8);
        System.out.println("Samat rakenteet : " + vertaaRakenne(puu, puu2));

        System.out.println("Matalin lehtisolmu: " + matalinLehtisolmu(puu));

        ero = suurinEro(puu);
        System.out.println("Suurin ero on " + ero);

        System.out.println("\nLisÃ¤tÃ¤Ã¤n: 3");
        inorderInsert(puu, 3);

        System.out.println("Matalin lehtisolmu: " + matalinLehtisolmu(puu));

        ero = suurinEro(puu);
        System.out.println("Suurin ero on " + ero);


        System.out.println();


    } // main()

    /**
     * Puun matalin lehtisolmu.
     * @param T syÃ¶tepuu
     * @return matalin lehtisolmu
     * @param <E> puun solmujen alkioiden tyyppi
     */


    public static <E> BTreeNode<E> matalinLehtisolmu(BTree<E> T) {

        if (T.getRoot() == null)
            return null;

        // TODO
        LinkedQueue<BTreeNode> Q = new LinkedQueue<BTreeNode>();
        Q.add(T.getRoot());
        BTreeNode<E> n = null;

        //O(n) aikavaativuus

        while (!Q.isEmpty()) {
            BTreeNode<E> solmu = Q.poll();

            if (solmu.getLeftChild() == null && solmu.getRightChild() == null){
                n = solmu;
                break;
            }

            if (solmu.getLeftChild() != null){
                Q.add(solmu.getLeftChild());
            }
            if (solmu.getRightChild() != null){
                Q.add(solmu.getRightChild());
            }
        }
        return n;
    } // matalinLehtisolmu()




    /**
     * Puiden rakenteiden vertailu.
     * @param T1 syÃ¶tepuu1
     * @param T2 syÃ¶tepuu2
     * @param <E> alkiotyyppi (ei kÃ¤ytetÃ¤)
     * @return ovatko rakenteeltaan samat vai ei
     */
    public static <E> boolean vertaaRakenne(BTree<E> T1, BTree<E> T2) {
        // TODO
        //Aikavaatimus O(n)
        //Tilavaativuus O(1)?
        if (vertaaRakenne(T1.getRoot(), T2.getRoot()))
            return true;
        else return false;
    } // vertaaRakenne()

    static <E> boolean vertaaRakenne(BTreeNode<E> t1, BTreeNode <E> t2) {
        if (t1 == null && t2 == null){
            return true;
        }

        if (t1 != null && t2 != null){
            return (
                    vertaaRakenne(t1.getLeftChild(), t2.getLeftChild()) &&
                            vertaaRakenne(t1.getRightChild(), t2.getRightChild())
                    );
        }

        return false;
    }



    /**
     * Suurin puun solmujen tasapainoero. Siis solmujen lasten korkeusero.
     * @param T tarkasteltava binÃ¤Ã¤ripuu.
     * @return suurin korkeusaro puussa olevien solmujen lapsille
     **/
    public static <E> int suurinEro(BTree<E> T) {
        // TODO
        BTreeNode<E> n = T.getRoot();
        if (n == null){
            return 0;
        } else {
            int vasKorkeus = suurinEro(n.getLeftChild());
            int oikKorkeus = suurinEro(n.getRightChild());

            if (vasKorkeus >= oikKorkeus) {
                int ero = vasKorkeus-oikKorkeus;
                return ero;
            }
            else {
                int ero = oikKorkeus - vasKorkeus;
                return ero;
            }
        }

    }

    public static <E> int suurinEro(BTreeNode<E> t) {
        // TODO
       /* if (t==null)
            return 0;
        else{
            int vasKorkeus = suurinEro(t.getLeftChild()), oikeaKorkeus = suurinEro(t.getRightChild());
            //Vertailee kumpi on korkeampi
            if (vasKorkeus > oikeaKorkeus){
                //Jos vasen on korkeampi palauttaa vasemman korkeuden
                return (vasKorkeus+1);
            } else {
                //Jos oikea on korkeampi palauttaa sen korkeuden.
                return (oikeaKorkeus+1);
            }*/
        //O(n)
        if (t==null)
            return 0;
        else{
            int vasKorkeus = suurinEro(t.getLeftChild()), oikeaKorkeus = suurinEro(t.getRightChild());
            return Math.max(vasKorkeus, oikeaKorkeus) +1;
        }
    }




    // esimerkkejÃ¤ ja pohjia


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

        l.setLeftChild(new BTreeNode<Integer>(1));
        l.setRightChild(new BTreeNode<Integer>(7));

        l.getLeftChild().setRightChild(new BTreeNode<Integer>(2));

        // oikea haara
        l = n.getRightChild();

        l.setLeftChild(new BTreeNode<Integer>(12));
        l.setRightChild(new BTreeNode<Integer>(18));

        l.getLeftChild().setLeftChild(new BTreeNode<Integer>(11));
        l.getLeftChild().setRightChild(new BTreeNode<Integer>(13));
        l.getRightChild().setLeftChild(new BTreeNode<Integer>(17));


        System.out.println("                 ");
        System.out.println("       9        ");
        System.out.println("    __/  \\__     ");
        System.out.println("   5        15   ");
        System.out.println("  / \\      /  \\  ");
        System.out.println(" 1   7   12    18");
        System.out.println("  \\      /\\    / ");
        System.out.println("   2    11 13 17 ");
        System.out.println("                 ");

        return T;

    } // exampleBTree()



    /**
     * 15. LisÃ¤ys sisÃ¤jÃ¤rjestettyyn binÃ¤Ã¤ripuuhun.
     * @param T binÃ¤Ã¤ripuu
     * @param x lisÃ¤ttÃ¤vÃ¤ alkio
     * @return tehtiinkÃ¶ lisÃ¤ys vai ei
     */
    public static <E extends Comparable<? super E>> boolean inorderInsert(BTree<E> T, E x) {
        BTreeNode<E> n = T.getRoot();
        if (n == null) {
            T.setRoot(new BTreeNode<E>(x));
            return true;
        }

        while (true) {

            if (x.compareTo(n.getElement()) == 0)
                // x on jo puussa, eli lisÃ¤tÃ¤
                return false;

            else if (x.compareTo(n.getElement()) < 0) {
                // x edeltÃ¤Ã¤ n:n alkiota
                if (n.getLeftChild() == null) {
                    // lisÃ¤yskohta lÃ¤ydetty
                    n.setLeftChild(new BTreeNode<>(x));
                    return true;
                } else  // alas vasemmalle
                    n = n.getLeftChild();
            } else {
                // x suurempi kuin n
                if (n.getRightChild() == null) {
                    // lisÃ¤yskohta lÃ¤ydetty
                    n.setRightChild(new BTreeNode<>(x));
                    return true;
                } else  // alas oikealle
                    n = n.getRightChild();
            }
        } // while

    } // inorderInsert()


    // Tulostus sisÃ¤jÃ¤rjestyksessÃ¤ rekursiivisesti
    public static <E> void inorderPrint(BTree<E> T) {
        inorderPrintBranch(T.getRoot());
        System.out.println();
    } // inorderPrint()


    public static <E> void inorderPrintBranch(BTreeNode<E> n) {
        if (n == null)
            return;

        inorderPrintBranch(n.getLeftChild());
        System.out.print(n.getElement() + " ");
        inorderPrintBranch(n.getRightChild());

    } // inorderPrintBranch()


} // class TRAI_23_t16_18.java