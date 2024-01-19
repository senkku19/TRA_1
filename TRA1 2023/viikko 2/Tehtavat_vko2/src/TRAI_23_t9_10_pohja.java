// TRAI_23_t9_10.java SJ
// PÃ¤Ã¤ohjelma viikon 2 tehtÃ¤viin 9-10

import java.util.*;


/**
 * 8. Suunnittele huolella algoritmi joka saa parametrinaan kaksi jÃ¤rjestÃ¤mÃ¤tÃ¶ntÃ¤ listaa A ja B
 * (ArrayList) ja joka poistaa listasta A kaikki ne alkiot jotka esiintyvÃ¤t listassa B. Algoritmi
 * palauttaa poistettujen alkioiden mÃ¤Ã¤rÃ¤n. Nyt ei siis luoda uutta listaa, vaan muokataan
 * parametrina saatua. Ã„lÃ¤ kÃ¤ytÃ¤ valmiita remove(Object) tai removeAll() -operaatioita.
 * PiirrÃ¤ ensin kuva ongelmasta, sitten kirjoita ylÃ¶s ratkaisuperiaate selvÃ¤sanaisesti suomeksi.
 * TÃ¤mÃ¤n jÃ¤lkeen ala tÃ¤smentÃ¤mÃ¤Ã¤n ratkaisuperiaatetta tÃ¤smÃ¤llisemmÃ¤ksi algoritmiksi 1-3
 * vaiheessa siten, ettÃ¤ lopullinen ratkaisuperiaate on hyvin tÃ¤smÃ¤llisesti kuvattu ja se on
 * mekaanisesti muutettavissa toimivaksi aliohjelmaksi. Ã„lÃ¤ muuta syÃ¶telistaa B. MikÃ¤ on
 * algoritmisi aikavaativuus? Voisiko sitÃ¤ parantaa?
 *
 * 9. Kirjoita algoritmi joka saa parametrinaan kaksi jÃ¤rjestÃ¤mÃ¤tÃ¶ntÃ¤ listaa A ja B (ArrayList)
 * ja joka poistaa listasta A kaikki ne alkiot jotka esiintyvÃ¤t listassa B. Algoritmi palauttaa
 * poistettujen alkioiden mÃ¤Ã¤rÃ¤n. Nyt ei siis luoda uutta listaa, vaan muokataan parametrina
 * saatua. Ã„lÃ¤ kÃ¤ytÃ¤ valmista remove(Object) tai removeAll() -operaatioita. Aikavaativuus?
 * Miten aikavaativuutta voisi parantaa?
 * >
 * 10. Kirjoita algoritmi joka saa parametrinaan kaksi jÃ¤rjestÃ¤mÃ¤tÃ¶ntÃ¤ listaa A ja B (Linked-
 * List) ja joka poistaa listasta A kaikki ne alkiot jotka esiintyvÃ¤t listassa B. Algoritmi
 * palauttaa poistettujen alkioiden mÃ¤Ã¤rÃ¤n. Nyt ei siis luoda uutta listaa, vaan muokataan
 * parametrina saatua. Ã„lÃ¤ kÃ¤ytÃ¤ valmista remove(Object) tai removeAll() -operaatiota.
 * Aikavaativuus? Miten aikavaativuutta voisi parantaa?
 */

public class TRAI_23_t9_10_pohja {


    // PÃ¤Ã¤ohjelman kÃ¤yttÃ¶:
    // java TRAI_23_t9_10 [N] [N2] [S]
    // missÃ¤ N on alkioiden mÃ¤Ã¤rÃ¤, N2 on alkoiden mÃ¤Ã¤rÃ¤ toisessa taulukossa
    // ja S on satunnaislukusiemen
    public static void main(String[] args) {

        // alkioiden mÃ¤Ã¤rÃ¤
        int n1 = 10;
        if (args.length > 0)
            n1 = Integer.parseInt(args[0]);

        int n2 = n1 + 2;
        if (args.length > 1)
            n2 = Integer.parseInt(args[1]);

        int pituus = 1; // merkkijonojen pituus
        if (n1 > 30)
            pituus = 2;

        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 2)
            siemen = Integer.parseInt(args[2]);


        // testataan vaihteeksi merkkijonoilla

        ArrayList<String> AL1 = new ArrayList<>(n1);
        ArrayList<String> AL2 = new ArrayList<>(n2);

        // tÃ¤ytetÃ¤Ã¤n alkioilla
        Random r = new Random(siemen);
        for (int i = 0; i < n1; i++) {
            AL1.add(randomString(r, pituus));
        }

        for (int i = 0; i < n2; i++) {
            AL2.add(randomString(r, pituus));
        }

        // tulostetaan taulukot jos alkioita ei ole paljoa
        if (n1 <= 20 && n2 <= 20) {
            System.out.println("AL1: " + AL1);
            System.out.println("AL2: " + AL2);
        }

        // otetaan listoista kopiot tehtÃ¤vÃ¤Ã¤n 10
        LinkedList<String> LL1 = new LinkedList<>(AL1);
        LinkedList<String> LL2 = new LinkedList<>(AL2);


        // testataan tehtÃ¤vÃ¤Ã¤ 9

        int poistettu = poistaKaikki9(AL1, AL2);

        System.out.print("TehtÃ¤vÃ¤ 9, AL1-AL2: poistettu " + poistettu + " alkiota");
        if (n1 <= 20 && n2 <= 20) {
            System.out.println(AL1);
        } else {
            System.out.println(AL1.size() + " alkiota");
        }

        // testataan tehtÃ¤vÃ¤Ã¤ 10

        poistettu = poistaKaikki10(LL1, LL2);

        System.out.print("TehtÃ¤vÃ¤ 10, LL1-LL2: poistettu " + poistettu + " alkiota");
        if (n1 <= 20 && n2 <= 20) {
            System.out.println(LL1);
        } else {
            System.out.println(LL1.size() + " alkiota");
        }


    } // main()


    /**
     * Palauttaa satunnaisen len mittaisen merkkijonon.
     *
     * @param r   satunnaislukugeneraattori
     * @param len merkkijonon pituus
     * @return uusi merkkijono
     */
    public static String randomString(Random r, int len) {
        char[] C = new char[len];
        for (int i = 0; i < len; i++)
            C[i] = (char) (r.nextInt(26) + 'a');
        return new String(C);
    }


    /**
     * Poista kaikki B alkioiden esiintymÃ¤t listasta A.
     *
     * @param A lista josta poistetaan
     * @param B alkiot jotka poistetaan
     * @return poistettujen alkioiden mÃ¤Ã¤rÃ¤
     */
    public static <E> int poistaKaikki9(ArrayList<E> A, ArrayList<E> B) {

        int poistettu = 0;

        // TODO

        Set<E> Bkopio = new HashSet(B);
        int akoko = A.size();

        A.removeIf(a ->
            Bkopio.contains(a)
        );

        poistettu = akoko - A.size();

        return poistettu;
    }

    /**
     * Poista kaikki B alkioiden esiintymÃ¤t listasta A.
     *
     * @param A lista josta poistetaan
     * @param B alkiot jotka poistetaan
     * @return poistettujen alkioiden mÃ¤Ã¤rÃ¤
     */
    public static <E> int poistaKaikki10(LinkedList<E> A, LinkedList<E> B) {
        int poistettu = 0;

        // TODO

        Set<E> Bkopio = new HashSet(B);

        for (ListIterator<E> iterator = A.listIterator(); iterator.hasNext(); ) {
            if (Bkopio.contains(iterator.next())){
                poistettu++;
                iterator.remove();
            }

        }
        return poistettu;
    }


} // class
