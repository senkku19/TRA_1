import java.util.ArrayList;
import java.util.Arrays;

public class TRAI_23_t4_5_pohja {

    // PÃ¤Ã¤ohjelman kÃ¤yttÃ¶:
    // java TRAI_23_t4_5_pohja [N] [N2] [S]
    // missÃ¤ N on alkioiden mÃ¤Ã¤rÃ¤, N2 on alkoiden mÃ¤Ã¤rÃ¤ toisessa taulukossa
    // ja S on satunnaislukusiemen
    public static void main(String[] args) {

        // taulukoiden koko
        int n1 = 10;
        if (args.length > 0) n1 = Integer.parseInt(args[0]);

        int n2 = n1 + 2;
        if (args.length > 1) n2 = Integer.parseInt(args[1]);

        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 2) siemen = Integer.parseInt(args[2]);

        // luodaan esimerkkitaulukot
        Integer[] T1 = new Integer[n1];
        Integer[] T2 = new Integer[n2];

        // tÃ¤ytetÃ¤Ã¤n alkioilla
        java.util.Random r = new java.util.Random(siemen);
        for (int i = 0; i < n1; i++) {
            T1[i] = r.nextInt(n1);
        }

        for (int i = 0; i < n2; i++) {
            T2[i] = r.nextInt(n2 * 2);
        }

        // tulostetaan taulukot jos alkioita ei ole paljoa
        if (n1 <= 20 && n2 <= 20) {
            System.out.println("T1: " + Arrays.toString(T1));
            System.out.println("T2: " + Arrays.toString(T2));
        }

        // kutsutaan tehtÃ¤vÃ¤Ã¤ 4
        Integer[] E4 = erotus4(T1, T2);

        System.out.print("TehtÃ¤vÃ¤ 4, erotus = ");
        if (n1 <= 20 && n2 <= 20) {
            System.out.println(Arrays.toString(E4));
        } else {
            System.out.println(E4.length + " alkioinen taulukko");
            // huom: tÃ¤mÃ¤ tulostaa taulukon koon, ei todellisten alkioiden mÃ¤Ã¤rÃ¤Ã¤!
        }

        // Muodostetaan taulukoista ArrayList:t

        ArrayList<Integer> L1 = new ArrayList<>(T1.length);
        ArrayList<Integer> L2 = new ArrayList<>(T2.length);
        for (Integer x : T1) L1.add(x);

        for (Integer x : T2) L2.add(x);

        // kutsutaan tehtÃ¤vÃ¤Ã¤ 5
        ArrayList<Integer> E5 = erotus5(L1, L2);

        System.out.print("TehtÃ¤vÃ¤ 5, erotus = ");
        if (n1 <= 20 && n2 <= 20) {
            System.out.println(E5);
        } else {
            System.out.println(E5.size() + " alkiota");
        }
    } // main()

    /**
     * 4. Palauttaa taulukoiden erotukset, siis ne alkiot jotka ovat taulukossa T1, mutta eivÃ¤t
     * taulukossa T2. Taulukot kÃ¤sitellÃ¤Ã¤n kokonaan.
     *
     * @param T1 ensimmÃ¤inen taulukko
     * @param T2 toinen taulukko
     * @return erotus taulukkona
     */
    // neliÃ¶llinen ratkaisu, tehdÃ¤Ã¤n myÃ¶hemmin tehokkaampi
    public static Integer[] erotus4(Integer[] T1, Integer[] T2) {

        // TODO
        Integer[] tulos;
        int summa = 0;
        boolean loydetty = false;
        for (Integer a : T1) {
            for (Integer b : T2) {
                if (a == b){
                    loydetty = true;
                    break;
                } else if (a != b) {
                    loydetty = false;
                }
            }
            if (!loydetty){
                summa ++;
            }

        }

        // muodostetaan oikean kokoinen tulostaulukko

        tulos = new Integer[summa];

        //muodostetaan taulukko

        int i = 0;

        for (Integer a : T1) {
            for (Integer b : T2) {
                if (a == b){
                    loydetty = true;
                    break;
                } else if (a != b)  {
                    loydetty = false;
                }
            }
            if (!loydetty){
                tulos[i] = a;
                i ++;
            }

        }

        return tulos;


    } // erotus4


    /**
     * 5. Palauttaa taulukkopohjaisten listojen erotuksena uutena listana.
     *
     * @param L1 ensimmÃ¤inen lista
     * @param L2 toinen lista
     * @return erotus listana
     */
    public static ArrayList<Integer> erotus5(ArrayList<Integer> L1, ArrayList<Integer> L2) {

        ArrayList<Integer> tulos = new ArrayList<>();


        // TODO

        for (Integer a : L1){
            if (!L2.contains(a)){
                tulos.add(a);
            }
        }

        return tulos;
    } // erotus5()


} // class