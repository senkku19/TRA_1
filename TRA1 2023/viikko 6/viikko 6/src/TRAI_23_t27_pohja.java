// TRAI_22_29.java SJ

import java.util.*;
import java.util.stream.Collectors;

public class TRAI_23_t27_pohja {

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
                S.add(r.nextInt(N * 2));
            }
            SS.add(S);
            System.out.println("S" + i + ": " + S);
        }

        LinkedList<Set<Integer>> LS = new LinkedList<>(SS);
        System.out.println("Joukkojen lista:");
        tulostaRiveittain(LS);

        jarjestaAlkiomaaranMukaan(LS);

        System.out.println("Lista jÃ¤rjestettynÃ¤ joukkojen alkiomÃ¤Ã¤rÃ¤n mukaan:");
        tulostaRiveittain(LS);

    } // main()

    /**
     * Tulosta kokoelma riveittÃ¤in.
     *
     * @param CC  syÃ¶tekokoelma
     * @param <E> alkiotyyppi
     */
    static <E> void tulostaRiveittain(Collection<E> CC) {
        System.out.println("(");
        for (E x : CC) {
            System.out.println(x.toString());
        }
        System.out.println(")");
    }


    /**
     * 27. Kirjoita algoritmi joka saa parametrinaan joukkojen listan (List<Set<E>>) ja jÃ¤rjestÃ¤Ã¤ listan joukkojen
     * alkiomÃ¤Ã¤rÃ¤n mukaiseen jÃ¤rjestykseen. Vihje: Collections.sort() ja Comparator<Set>. MikÃ¤ on algoritmisi
     * aikavaativuus?
     *
     * @param LS  lista
     * @param <E> joukkojen alkiotyyppi (ei kÃ¤ytetÃ¤)
     */
    public static <E> void jarjestaAlkiomaaranMukaan(List<Set<E>> LS) {
        // TODO
        Collections.sort(LS, new Comparator<Set<E>>() {

            public int compare(Set<E> set1, Set<E> set2) {
                // Vertaillaan joukkojen alkioiden määrää
                int size1 = set1.size();
                int size2 = set2.size();

                // Palautetaan negatiivinen luku, nolla tai positiivinen luku sen perusteella,
                // onko ensimmäinen joukko pienempi, yhtä suuri tai suurempi kuin toinen joukko
                if (size1 < size2) {
                    return -1;
                } else if (size1 == size2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });



    }


} // class