// TRAI_23_t29_30.java SJ

import java.util.Arrays;
import java.util.Random;


class TRAI_23_t29_30_pohja {

    static Random rnd = new Random(System.currentTimeMillis());

    public static void main(String[] args) {

        Random rnd = new Random(System.currentTimeMillis());

        // ensimmÃ¤inen komentoriviparametri: alkioiden mÃ¤Ã¤rÃ¤
        int N = 20;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // toinen parametri: tulostetaanko vai ei, >1 = tulosta
        boolean print = true;
        if (args.length > 1 && (Integer.parseInt(args[1]) > 0))
            print = true;

        // kolmas parametri: jÃ¤rjesteyksessÃ¤ vai ei
        // -1 : laskeva jÃ¤rjestys, 1 = kasvava jÃ¤rjestys, 0 = satunnainen, 2 = satunnaisesti joku edellisistÃ¤
        int J = 0;
        if (args.length > 2)
            J = Integer.parseInt(args[2]);

        if (J == 2)
            J = satunnainenLuku(rnd, -1, 1);

        if (J == -1) System.out.println("SyÃ¶te laskevassa jÃ¤rjestyksessÃ¤");
        if (J == 0) System.out.println("SyÃ¶te satunnaisessa jÃ¤rjestyksessÃ¤");
        if (J == 1) System.out.println("SyÃ¶te kasvavassa jÃ¤rjestyksessÃ¤");


        Integer[] taulu;
        long start, end;

        if (N <= 50000) {
            // Kuplalajittelu
            taulu = satunnainenTaulu(N, J, 1);
            if (print && N < 50)
                for (int i = 0; i < N; i++)
                    System.out.print(taulu[i] + " ");
            System.out.println();
            System.out.println("Kuplalajittelu alkaa");
            start = System.nanoTime();
            bubbleSort(taulu);
            end = System.nanoTime();
            System.out.println("               " +
                    (end-start) + " ns");
            if (print && N < 50)
                for (int i = 0; i < N; i++)
                    System.out.print(taulu[i] + " ");
            System.out.println();
        }

        taulu = satunnainenTaulu(N, J, 1);
        System.out.println("Lomituslajittelu  alkaa");
        start = System.nanoTime();
        mergesort(taulu);
        end = System.nanoTime();
        System.out.println("               " +
                (end-start) + " ns");
        if (print && N < 50)
            for (int i = 0; i < N; i++)
                System.out.print(taulu[i] + " ");
        System.out.println();



        // Tavallinen pikalajittelu
        taulu = satunnainenTaulu(N, J, 1);
        System.out.println("Pikalajittelu  alkaa");
        start = System.nanoTime();
        quicksort(taulu, 0, N-1);
        end = System.nanoTime();
        System.out.println("               " +
                (end-start) + " ns");
        if (print && N < 50)
            for (int i = 0; i < N; i++)
                System.out.print(taulu[i] + " ");
        System.out.println();

        // Pikalajittelu viritettynÃ¤
        taulu = satunnainenTaulu(N, J, 1);
        System.out.println("Viritetty pikalajittelu alkaa");
        start = System.nanoTime();
        quicksort30(taulu, 0, N-1);
        end = System.nanoTime();
        System.out.println("               " +
                (end-start) + " ns");
        if (print && N < 50)
            for (int i = 0; i < N; i++)
                System.out.print(taulu[i] + " ");
        System.out.println();

    } // main()

    public static Integer[] satunnainenTaulu(int N, int J, int seed) {
        Integer[] taulu = new Integer[N];

        Random r = new Random(seed);
        for (int i = 0; i < N; i++) {
            if (J == 0)
                taulu[i] = (r.nextInt(N*2));
            else if (J > 0)
                taulu[i] = (i);
            else
                taulu[i] = (N-i);
        }

        return taulu;
    } // satunnainenTaulu()


    /**
     * Koko taulukon lomitujÃ¤rjestÃ¤minen
     * @param A syÃ¶tetaulukko
     * @param <E> alkiotyyppi
     */
    public static <E extends Comparable<? super E>> void mergesort(E[] A) {
        mergesort(A, 0, A.length-1);
    }

    /**
     * Osataulukon lomitusjÃ¤rjestÃ¤minen
     * @param A syÃ¶tetaulukko
     * @param alku ensimmÃ¤inen jÃ¤rjestettÃ¤vÃ¤ indeksi
     * @param loppu viimeinen jÃ¤rjestettÃ¤vÃ¤ indeksi
     * @param <E> alkiotyyppi
     */
    public static <E extends Comparable<? super E>> void mergesort(E[] A, int alku, int loppu) {
        if (alku < loppu) {
            int k = (alku + loppu) / 2;
            mergesort(A, alku, k);
            mergesort(A, k+1, loppu);
            merge(A, alku, k, loppu);
        }
    } // mergesort()

    /**
     * Osataulukoiden lomitus.
     * Lomittaa jÃ¤rjestetyt osataulukot A[alku..keski] ja A[keski+1..loppu]
     * jÃ¤rjestetyksi osataulukoksi A[alku..loppu].
     * @param A syÃ¶tetaulukko
     * @param alku ensimmÃ¤isen osataulukon alkuindeksi
     * @param keski ensimmÃ¤isen osataulukon loppuindeksi
     * @param loppu toisen osataulukon loppuindeksi
     * @param <E> alkiotyyppi
     */
    public static <E extends Comparable<? super E>> void merge(E[] A, int alku, int keski, int loppu) {
        //aputaulukko
        E[] aputaulukko = (E[]) new Comparable[loppu - alku + 1];
        //indeksi osalle A[a..k]
        int i = alku;
        //indeksi osalle A[k+1..l]
        int j = keski + 1;
        //indeksi aputaulukolle b
        int m = 0;


        //kun indeksit ovat vielä A[a..k] sisällä
        while (i <= keski && j <= loppu) {
            //kun A[i] on sama tai pienempi kuin A[j]
            if (A[i].compareTo(A[j]) <= 0) {
                //lisätään aputaulukkoon A[i] arvo
                aputaulukko[m] = A[i];
                i++;
            } else {
                //muuten lisätään A[j] arvo aputaulukkoon
                aputaulukko[m] = A[j];
                j++;
            }
            //siirrytään seuraavaan paikkaan aputaulukossa
            m++;
        }

        //niin kauan kun i on pienempi kuin keski
        while (i <= keski) {
            //lisätään A[i] alkio aputaulukkoon
            aputaulukko[m] = A[i];
            //siirytään indekseissä eteenpäin
            i++;
            m++;
        }

        //lisätään vielä loput alkiot
        while (j <= loppu) {
            aputaulukko[m] = A[j];
            j++;
            m++;
        }

        //lisätään kaikki alkiot aputaulukosta A taulukkoon
        for (int x = 0; x < aputaulukko.length; x++)
            A[alku + x] = aputaulukko[x];
    }




    /**
     * Kuplalajittelu.
     * @param A lajiteltava taulukko
     * @param <E> alkiotyyppi
     */
    public static <E extends Comparable<? super E>> void bubbleSort(E[] A) {
        bubbleSort(A, 0, A.length-1);
    } // bubbleSort()


    /**
     * Kuplalajittelu osataulukolle.
     * @param A lajiteltava taulukko
     * @param alku lajiteltavan osan ensimmÃ¤inen indeksi
     * @param loppu lajiteltavan osan viimeinen indeksi
     * @param <E> alkiotyyppi
     */
    public static <E extends Comparable<? super E>>
    void bubbleSort(E[] A, int alku, int loppu) {
        for (int i = alku; i <= loppu; i++) {
            for (int j = loppu; j > i; j--) {
                if (A[j-1].compareTo(A[j]) > 0) {
                    E tmp = A[j-1];
                    A[j-1] = A[j];
                    A[j] = tmp;
                }
            }
        }
    } // bubbleSort()



    public static <E extends Comparable<? super E>>
    void quicksort30(E[] A, int i, int j) {

        // TODO vaihda kuplajÃ¤rjestÃ¤miseen jos osataulukko on pieni

        if (i >= j){
            return;
        }
        if (j-i+1 < 8){
            bubbleSort(A, i, j);
        }
        else {
            int k = partition30(A, i, j);
            quicksort30(A, i, k-1);
            quicksort30(A, k+1, j);
        }
    } // quicksort()



    public static <E extends Comparable<? super E>>
    int partition30(E[] A, int i, int j) {

        // TODO: ota joku muu jakoalkio (satunnainen tai kolmen mediaani)
        Random random = new Random();
        int satunnainen =random.nextInt(j - i + 1) + i;
        E jakoalkio = A[satunnainen];
        A[satunnainen] = A[i];

        // toistetaan kunnes i ja j tÃ¶rmÃ¤Ã¤vÃ¤t
        while (i < j) {

            // etsitÃ¤Ã¤n lopusta jakoalkiota pienempi
            while ((i < j) && (jakoalkio.compareTo(A[j]) < 0))
                j--;
            A[i] = A[j];

            // etsitÃ¤Ã¤n alusta jakoalkiota suurempi tai yhtÃ¤suuri
            while ((i < j) && (jakoalkio.compareTo(A[i]) >= 0))
                i++;
            A[j] = A[i];

        } // while

        // jakoalkio paikalleen ja palautetaan sijainti
        A[i] = jakoalkio;
        return i;

    } // partition()




    public static <E extends Comparable<? super E>>
    void quicksort(E[] A, int i, int j) {
        if (i < j) {
            int k = partition(A, i, j);
            quicksort(A, i, k-1);
            quicksort(A, k+1, j);
        }
    } // quicksort()

    public static <E extends Comparable<? super E>>
    int partition(E[] A, int i, int j) {

        // jakoalkioksi ensimmÃ¤inen
        // NÃ„IN SAA TEHDÃ„ VAIN OPETUSTARKOITUKSESSA, EI KOSKAAN
        // OIKEASSA OHJELMASSA
        // HarjoitustehtÃ¤vÃ¤nÃ¤ satunnainen alkio
        E jakoalkio = A[i];

        // toistetaan kunnes i ja j tÃ¶rmÃ¤Ã¤vÃ¤t
        while (i < j) {

            // etsitÃ¤Ã¤n lopusta jakoalkiota pienempi
            while ((i < j) && (jakoalkio.compareTo(A[j]) < 0))
                j--;
            A[i] = A[j];

            // etsitÃ¤Ã¤n alusta jakoalkiota suurempi tai yhtÃ¤suuri
            while ((i < j) && (jakoalkio.compareTo(A[i]) >= 0))
                i++;
            A[j] = A[i];

        } // while

        // jakoalkio paikalleen ja palautetaan sijainti
        A[i] = jakoalkio;
        return i;

    } // partition()



    private static int satunnainenLuku(Random r, int min, int max) {
        int d = max - min + 1;
        if (d < 1)
            d = 1;
        return r.nextInt(d) + min;
    }


} // class TRAI_23_t29_30

