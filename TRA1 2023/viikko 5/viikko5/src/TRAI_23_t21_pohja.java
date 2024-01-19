// TRAI_23_t21.java SJ

import fi.uef.cs.tra.TraSet;

import java.util.Random;

public class TRAI_23_t21_pohja {

    public static void main(String[] args) {

        int N = 10;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        TraSet<Integer> S1 = new TraSet<>();
        TraSet<Integer> S2 = new TraSet<>();
        TraSet<Integer> S3 = new TraSet<>();

        Random r = new Random(42);
        Integer x, y;
        for (int i = 0; i < N; i++) {
            x = r.nextInt(N + 2);
            y = r.nextInt(N + 2);
            S1.add(x);
            S2.add(x - y);
            S3.add(x + y);
        }

        System.out.println("S1:      " + S1);
        System.out.println("S2:      " + S2);
        System.out.println("S3:      " + S3);

        System.out.println("2/3TRAS: " + kahdessaKolmesta(S1, S2, S3));

    } // main()


    /**
     * 21. Kirjoita algoritmi joka hakee joukkojen â€kaksi kolmestaâ€ leikkauksen. Algoritmi saa siis
     * parametrinaan kolme tietorakennekirjastomme joukkoa (TraSet) ja muodostaa uuden
     * joukon niistÃ¤ alkioista jotka kuuluvat tÃ¤smÃ¤lleen kahteen syÃ¶tejoukoista. Mukana ei siis
     * ole niitÃ¤ alkioita jotka kuuluvat vain yhteen syÃ¶tejoukoista, eikÃ¤ niitÃ¤ alkioita jotka kuu-
     * luvat kaikkiin syÃ¶tejoukkoihin. Ã„lÃ¤ muuta syÃ¶tejoukkoja Ã¤lÃ¤kÃ¤ kÃ¤ytÃ¤ apuna kuvausta
     * (Map) tai Javan vakiokirjaston joukkoa (Set). VihjeitÃ¤: voit ottaa joukoista kopioita,
     * kÃ¤ytÃ¤ joukko-operaatioita, ei kannata lÃ¤hteÃ¤ iteroimaan joukkoja alkioittain. MikÃ¤ on
     * algoritmisi aikavaativuus kun TraSet:n operaatioiden aikavaativuus on kuten vastaavilla
     * TreeSet -operaatioilla?
     *
     * @param A   syÃ¶tejoukko
     * @param B   syÃ¶tejoukko
     * @param C   syÃ¶tejoukko
     * @param <E> alkiotyyppi (ei kÃ¤ytetÃ¤)
     * @return uusi joukko jossa on ne alkiot jotka lÃ¶ytyvÃ¤t tasan kahdessa syÃ¶tejoukoista
     */
    public static <E> TraSet<E> kahdessaKolmesta(TraSet<E> A, TraSet<E> B, TraSet<E> C) {

        // TODO

        //Luodaan tulos-joukko ja apujoukko
        //Lisätään molempiin A-joukon alkiot
        TraSet<E> oikeaTulos = new TraSet<>(A);
        TraSet<E> apuTulos = new TraSet<>(A);

        //tehdään aputuloksesta A ja B joukon leikkaus
        apuTulos = apuTulos.intersection(B);

        //Tehdään oikeastatulosesta A ja C joukon leikkaus
        oikeaTulos = oikeaTulos.intersection(C);

        //poistetaan tuloksesta kaikki alkiot, jotka löytyvät myös apujoukosta
        oikeaTulos.removeAll(apuTulos);

        //Tehdään sama aputuloksesta, poistetaan kaikki alkiot, jotka löytyvät myös C joukosta
        apuTulos.removeAll(C);

        //Lisätään kaikki alkiot yhteen
        oikeaTulos.addAll(apuTulos);


        //Palautetaan arvo
        return oikeaTulos;

    } // yhdessaKolmesta()

} // class TRAI_23_t21