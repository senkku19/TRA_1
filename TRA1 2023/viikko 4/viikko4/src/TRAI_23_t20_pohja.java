import fi.joensuu.cs.tra.Set;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TRAI_23_t20_pohja {


    // PÃ¤Ã¤ohjelman kÃ¤yttÃ¶:
    // java TRAI_23_t20 [N] [N2] [S]
    // missÃ¤ N on alkioiden mÃ¤Ã¤rÃ¤, N2 on alkoiden mÃ¤Ã¤rÃ¤ toisessa taulukossa
    // ja S on satunnaislukusiemen
    public static void main(String[] args) {

        // taulukoiden koko
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
        int siemen = n1 + n2;
        if (args.length > 2)
            siemen = Integer.parseInt(args[2]);


        // testataan vaihteeksi merkkijonoilla

        ArrayList<String> AL1 = new ArrayList<>(n1);
        ArrayList<String> AL2 = new ArrayList<>(n2);

        // tÃ¤ytetÃ¤Ã¤n alkioilla
        Random r = new Random(siemen);
        for (int i = 0; i < n1; i++) {
            AL1.add(randomString(r, pituus, 10));
        }

        for (int i = 0; i < n2; i++) {
            AL2.add(randomString(r, pituus, 10));
        }

        // tulostetaan taulukot jos alkioita ei ole paljoa
        if (n1 <= 20 && n2 <= 20) {
            System.out.println("AL1: " + AL1);
            System.out.println("AL2: " + AL2);
        }

        // testataan tehtÃ¤vÃ¤Ã¤ 20

        List<String> xortulos = listaXor(AL1, AL2);

        System.out.print("TehtÃ¤vÃ¤ 20 (xor): ");
        if (n1 <= 20 && n2 <= 20) {
            System.out.println(xortulos);
        } else {
            System.out.println(xortulos.size() + " alkiota");
        }


    } // main()


    /**
     * Palauttaa satunnaisen len mittaisen merkkijonon.
     *
     * @param r   satunnaislukugeneraattori
     * @param len merkkijonon pituus
     * @param s   aakkoston koko
     * @return uusi merkkijono
     */
    public static String randomString(Random r, int len, int s) {
        char[] C = new char[len];
        for (int i = 0; i < len; i++)
            C[i] = (char) (r.nextInt(s) + 'a');
        return new String(C);
    }


    /**
     * 20. Kirjoita algoritmi joka saa parametrinaan kaksi jÃ¤rjestÃ¤mÃ¤tÃ¶ntÃ¤ listaa ja joka muodostaa
     * ja palauttaa uuden listan joka sisÃ¤ltÃ¤Ã¤ kaikki ne alkiot jotka esiintyvÃ¤t vain yhdessÃ¤
     * listassa (siis niiden joko-tai -yhdisteen (xor)). Jos jokin alkio esiintyy yhdessÃ¤ listassa
     * useasti, mutta ei toisessa listassa, niin se tulee tuloslistaan yhtÃ¤ monta kertaa kuin mitÃ¤
     * se esiintyi siinÃ¤ listassa jossa se esiintyi. KÃ¤ytÃ¤ joukko(j)a (Set) apuna ja pyri lineaariseen
     * aikavaativuuteen. Vihje: mieti ensin tarkasti miten kÃ¤ytÃ¤t joukko(j)a hyÃ¶dyksi ja ryhdy
     * tarkentamaan algoritmiasi vasta sitten.
     *
     * @param A   SyÃ¶telista
     * @param B   SyÃ¶telista
     * @param <E> alkioiden tyyppi
     * @return joko-tai-yhdiste syÃ¶telistoista
     */
    public static <E> List<E> listaXor(List<E> A, List<E> B) {
        List<E> tulos;

        // tehdÃ¤Ã¤n listaksi samanlainen kuin parametri A
        try {
            tulos = A.getClass().getConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }

        // TODO
        //Operaation aikavaativuus on pahimillaan O(n)

        List<E> yhdiste = Stream.concat(A.stream(),B.stream()).collect(Collectors.toList());
        List<E> samat = A.stream().filter(B::contains).collect(Collectors.toList());

        tulos.addAll(yhdiste);
        tulos.removeAll(samat);

        return tulos;
    }




} // class
