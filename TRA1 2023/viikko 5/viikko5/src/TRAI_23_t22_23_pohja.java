// TRAI_23_t22_23.java SJ

import java.util.*;

public class TRAI_23_t22_23_pohja {

    public static void main(String[] args) {

        int N = 6;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        Set<Set<Integer>> SS = new HashSet<>();

        Random r = new Random(N + 1);
        System.out.println("SyÃ¶tejoukot:");
        for (int i = 0; i < N; i++) {
            Set<Integer> S = new HashSet<>();
            for (int j = 0; j < N; j++) {
                S.add(r.nextInt(N * 4 / 3));
            }
            SS.add(S);
            System.out.println("S" + i + ": " + S);
        }

        Set<Integer> tulos = leikkaus(SS);
        System.out.println("\nKaikkien leikkaus: " + tulos);
        System.out.println();

        tulos = vainYhdessa(SS);
        System.out.println("Vain yhdessÃ¤: " + tulos);
        System.out.println();


    } // main()


    /**
     * 22. Kirjoita algoritmi joka saa parametrinaan joukkojen joukon (Set<Set<E>>) ja joka
     * palauttaa joukkona (Set<E>) kaikki ne alkiot jotka ovat kaikissa syÃ¶tejoukoissa. Siis
     * leikkauksen. Vihje: iteraattori ja joukko-operaatiot. MikÃ¤ on algoritmisi aikavaativuus?
     *
     * @param SS  syÃ¶tejoukkojen joukko
     * @param <E> alkiotyyppi
     * @return joukkojen leikkaus
     */
    public static <E> Set<E> leikkaus(Set<Set<E>> SS) {
        Set<E> tulos = new HashSet<>();

        // TODO
        Iterator iteraattori = SS.iterator();

        for (Iterator iter = iteraattori; iter.hasNext(); ) {
            Set<E> it = (Set<E>) iter.next();
            if(!tulos.isEmpty()){
                tulos.retainAll(it);
            } else {
                tulos.addAll(it);
            }
        }

        return tulos;
    } // vainYhdessa()


    /**
     * 23. Kirjoita algoritmi joka saa parametrinaan joukkojen joukon (Set<Set<E>>) ja joka pa-
     * lauttaa joukkona (Set<E>) kaikki ne alkiot jotka ovat tasan yhdessÃ¤ syÃ¶tejoukoista. Ne
     * alkiot jotka ovat useammassa kuin yhdessÃ¤ syÃ¶tejoukoista eivÃ¤t tule tulokseen. Ã„lÃ¤ muuta
     * syÃ¶tejoukkoja. Vihje: foreach-lÃ¤pikÃ¤ynti ja kuvaus. MikÃ¤ on algoritmisi aikavaativuus?
     *
     * @param SS  joukkojen joukko
     * @param <E> joukkojen alkioiden tyyppi
     * @return kaikki ne alkiot jotka esiintyvÃ¤t vain yhdessÃ¤ joukossa
     */
    public static <E> Set<E> vainYhdessa(Set<Set<E>> SS) {
        Set<E> tulos = new HashSet<>();

        // TODO

        Iterator iteraattori = SS.iterator();
        Set <E> apujoukko = new HashSet<>();
        Set<E> kaikki_Numerot = new HashSet<>();

        for (Iterator iter = iteraattori; iter.hasNext(); ) {
            Set<E> it = (Set<E>) iter.next();
                for (E x : it) {
                    if (!kaikki_Numerot.contains(x)){
                        kaikki_Numerot.add(x);
                    }
                    else{
                        apujoukko.add(x);
                    }
            }
        }

        kaikki_Numerot.removeAll(apujoukko);

        tulos.addAll(kaikki_Numerot);

        return tulos;
    } // vainYhdessa()


} // class