// TRAI_23_t9_10.java SJ
// PÃ¤Ã¤ohjelma viikon 2 tehtÃ¤viin 9-10

import fi.uef.cs.tra.*;
import java.util.*;

/**
 * 11. Kirjoita algoritmi (metodi) joka saa parametrinaan kaksi kasvavassa jÃ¤rjestyksessÃ¤ olevaa
 * linkitettyÃ¤ listaa (java.util.TraLinkedList) ja joka muodostaa ja palauttaa uuden listan joka
 * sisÃ¤ltÃ¤Ã¤ kasvavassa jÃ¤rjestyksessÃ¤ kaikki ne alkiot jotka vain jommassakummassa listassa. Jos
 * alkio a esiintyy yhdessÃ¤ listassa k kertaa, mutta toisessa listassa ei lainkaan, niin se tulee
 * tuloslistaan k kertaa. Jos alkio a esiintyy yhdessÃ¤ listassa k kertaa ja toisessa listassa
 * vÃ¤hintÃ¤Ã¤n kerran, niin sitÃ¤ ei tule tuloslistaan lainkaan. MyÃ¶s tuloslistan tulee olla kasva-
 * vassa jÃ¤rjestyksessÃ¤. Ã„lÃ¤ kÃ¤ytÃ¤ removeAll() tai lajittelu-metodeja, Ã¤lÃ¤kÃ¤ joukkotyyppejÃ¤
 * (Set/Map) apuna. Ã„lÃ¤ oleta alkioiden olevan kokonaislukuja, vaan kÃ¤ytÃ¤ .compareTo() metodia. Voit
 * olettaa, ettei listassa ole null-alkioita. MikÃ¤ on algoritmisi aikavaativuus?
 *
 * 12. Kirjoita algoritmi (metodi) joka saa parametrinaan kaksi kasvavassa jÃ¤rjestyksessÃ¤ olevaa
 * asemapohjaista linkitettyÃ¤ listaa (TraTraLinkedList) ja joka muodostaa ja palauttaa uuden listan
 * joka sisÃ¤ltÃ¤Ã¤ kasvavassa jÃ¤rjestyksessÃ¤ kaikki ne alkiot jotka vain jommassakummas- sa listassa.
 * Jos alkio a esiintyy yhdessÃ¤ listassa k kertaa, mutta toisessa listassa ei lainkaan, niin se
 * tulee tuloslistaan k kertaa. Jos alkio a esiintyy yhdessÃ¤ listassa k kertaa ja toisessa listassa
 * vÃ¤hintÃ¤Ã¤n kerran, niin sitÃ¤ ei tule tuloslistaan lainkaan. MyÃ¶s tuloslistan tulee olla kasvavassa
 * jÃ¤rjestyksessÃ¤. Ã„lÃ¤ kÃ¤ytÃ¤ removeAll() tai lajittelu-metodeja, Ã¤lÃ¤kÃ¤ joukkotyyp- pejÃ¤ (Set/Map)
 * apuna. Ã„lÃ¤ oleta alkioiden olevan kokonaislukuja, vaan kÃ¤ytÃ¤ .compareTo() metodia. Voit olettaa,
 * ettei listassa ole null-alkioita. MikÃ¤ on algoritmisi aikavaativuus?
 */
public class TRAI_23_t11_12_pohja {

    // PÃ¤Ã¤ohjelman kÃ¤yttÃ¶:
    // java TRAI_23_t9_10 [N] [N2] [S]
    // missÃ¤ N on alkioiden mÃ¤Ã¤rÃ¤, N2 on alkoiden mÃ¤Ã¤rÃ¤ toisessa taulukossa
    // ja S on satunnaislukusiemen
    public static void main(String[] args) {

        // alkioiden mÃ¤Ã¤rÃ¤
        int n1 = 10;
        if (args.length > 0) n1 = Integer.parseInt(args[0]);

        int n2 = n1 + 2;
        if (args.length > 1) n2 = Integer.parseInt(args[1]);

        int pituus = 1; // merkkijonojen pituus
        if (n1 > 30) pituus = 2;

        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 2) siemen = Integer.parseInt(args[2]);

        // testataan vaihteeksi merkkijonoilla

        LinkedList<String> LL1 = new LinkedList<>();
        LinkedList<String> LL2 = new LinkedList<>();

        // tÃ¤ytetÃ¤Ã¤n alkioilla
        Random r = new Random(siemen);
        for (int i = 0; i < n1; i++) {
            LL1.add(randomString(r, pituus));
        }

        for (int i = 0; i < n2; i++) {
            LL2.add(randomString(r, pituus));
        }

        Collections.sort(LL1);
        Collections.sort(LL2);

        // tulostetaan taulukot jos alkioita ei ole paljoa
        if (n1 <= 20 && n2 <= 20) {
            System.out.println("LL1: " + LL1);
            System.out.println("LL2: " + LL2);
        }

        // otetaan listoista kopiot tehtÃ¤vÃ¤Ã¤n 12
        TraLinkedList<String> TLL1 = new TraLinkedList<>();
        for (String x : LL1) TLL1.insert(TLL1.EOL, x);
        TraLinkedList<String> TLL2 = new TraLinkedList<>();
        for (String x : LL2) TLL2.insert(TLL2.EOL, x);

        // testataan tehtÃ¤vÃ¤Ã¤ 11

        LinkedList<String> tulos11 = listaXor(LL1, LL2);

        System.out.print("TehtÃ¤vÃ¤ 11, LL1^LL2:   ");
        if (tulos11.size() <= 20) {
            System.out.println(tulos11);
        } else {
            System.out.println(tulos11.size() + " alkiota");
        }

        // testataan tehtÃ¤vÃ¤Ã¤ 12

        TraLinkedList<String> tulos12 = listaXor(TLL1, TLL2);

        System.out.print("TehtÃ¤vÃ¤ 12, TLL1^TLL2: ");
        if (n1 <= 20 && n2 <= 20) {
            System.out.println(TraLLtoString(tulos12));
        } else {
            System.out.println(TraLLtoSize(tulos12) + " alkiota");
        }
    } // main()

    /**
     * Palauttaa satunnaisen len mittaisen merkkijonon.
     *
     * @param r satunnaislukugeneraattori
     * @param len merkkijonon pituus
     * @return uusi merkkijono
     */
    public static String randomString(Random r, int len) {
        char[] C = new char[len];
        for (int i = 0; i < len; i++) C[i] = (char) (r.nextInt(26) + 'a');
        return new String(C);
    }

    public static <E> String TraLLtoString(TraLinkedList<E> L) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        ListNode<E> n = L.first();
        while (n != L.EOL) {
            sb.append(n.getElement());
            n = n.next();
            if (n != L.EOL) sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    public static <E> int TraLLtoSize(TraLinkedList<E> L) {
        int count = 0;
        ListNode<E> n = L.first();
        while (n != L.EOL) {
            count++;
            n = n.next();
        }
        return count;
    }

    /**
     * JÃ¤rjestettyjen listojen joko-tai-yhdiste
     *
     * @param A syÃ¶telista
     * @param B syÃ¶telista
     * @return lista jossa on ne alkiot jotka ovat vain  yhdessÃ¤ listassa
     */
    public static <E extends Comparable<? super E>> LinkedList<E> listaXor(
            LinkedList<E> A, LinkedList<E> B) {

        LinkedList<E> tulos = new LinkedList<>();

        // TODO
        //Iteroidaan listat
        ListIterator<E> iteratorA = A.listIterator();
        ListIterator<E> iteratorB = B.listIterator();
        //Haetaan ensimmäiset alkiot molemmista listoista ja alustetaan niillä alkiota ja -b
        E alkioA = A.getFirst();
        E alkioB = B.getFirst();

        //Silmukka pyörii niin kauan, kunnes molemmat alkiot ovat null.
        while (alkioA != null || alkioB != null){
            //Jos alkiot ovat samoja verrattavat arvot muuttuvat
            if ((alkioA != null  && alkioB != null) && alkioB.compareTo(alkioA) == 0) {
                alkioB = iteratorB.hasNext() ? iteratorB.next(): null;
                alkioA = iteratorA.hasNext() ? iteratorA.next(): null;
            } // Jos allkio B on pienempi lisätään se listalle.
            else if ((alkioA != null  && alkioB != null) && alkioB.compareTo(alkioA) < 0){
                tulos.add(alkioB);
                alkioB = iteratorB.hasNext() ? iteratorB.next(): null;
            } //Jos alkio a on pienempi lisätään se listalle
            else if (alkioA != null  && alkioB != null && alkioA.compareTo(alkioB) < 0) {
                tulos.add(alkioA);
                alkioA = iteratorA.hasNext() ? iteratorA.next(): null;
            }
            //Jos alkioA on null alkio b lisätään
            else if (alkioA == null && alkioB != null){
                tulos.add(alkioB);
                alkioB = iteratorB.hasNext() ? iteratorB.next(): null;
            } //Jos alkio B on null alkio a lisätään.
            else {
                tulos.add(alkioA);
                alkioA = iteratorA.hasNext() ? iteratorA.next(): null;
            }

        }



        return tulos;
    }


    /**
     * JÃ¤rjestettyjen listojen joko-tai-yhdiste
     *
     * @param A syÃ¶telista
     * @param B syÃ¶telista
     * @return lista jossa on ne alkiot jotka ovat vain  yhdessÃ¤ listassa
     */
    public static <E extends Comparable<? super E>> TraLinkedList<E> listaXor(
            TraLinkedList<E> A, TraLinkedList<E> B) {
        TraLinkedList<E> tulos = new TraLinkedList<>();

        // TODO
        //Alustetaan alkioA ja -b asemaksi listojen ensimmäiset alkiot
        ListNode<E> alkioA = A.first();
        ListNode<E> alkioB = B.first();

        //silmukka pyörii niin kauan kun toisella on "asemia" vielä jäljellä
        while (alkioA != A.EOL || alkioB != B.EOL){
            //Jos alkiot ovat samat siirrytään seuraavaan asemaan.
            if ((alkioA != A.EOL  && alkioB != B.EOL) && alkioB.getElement().compareTo(alkioA.getElement()) == 0) {
                alkioB = alkioB.next();
                alkioA = alkioA.next();
            } // Jos allkio B on pienempi lisätään se listalle.
            else if ((alkioA != A.EOL  && alkioB != B.EOL) && alkioB.getElement().compareTo(alkioA.getElement()) < 0){
                tulos.insert(tulos.EOL, alkioB.getElement());
                alkioB = alkioB.next();
            } //Jos alkio a on pienempi lisätään se listalle
            else if (alkioA != A.EOL  && alkioB != B.EOL && alkioA.getElement().compareTo(alkioB.getElement()) < 0) {
                tulos.insert(tulos.EOL, alkioA.getElement());
                alkioA = alkioA.next();
            }
           //Jos alkioA on "määrittelemätön" alkio b lisätään
            else if (alkioA == A.EOL && alkioB != B.EOL){
                tulos.insert(tulos.EOL, alkioB.getElement());
                alkioB = alkioB.next();;
            } //Jos alkio B on "määrittelemätön" alkio a lisätään.
            else {
                tulos.insert(tulos.EOL, alkioA.getElement());
                alkioA = alkioA.next();
            }
        }

        return tulos;
    }
} // class