import java.awt.geom.Area;
import java.util.*;

public class TRAI_23_X6_moilanes implements TRAI_23_X6 {

    /**
     * ITSEARVIOINTI TÃ„HÃ„N:
     *
     * Aikavaativuuden parametrit: n = syÃ¶tejoukkojen mÃ¤Ã¤rÃ¤, m = syÃ¶tejoukkojen _yhteinen_ alkiomÃ¤Ã¤rÃ¤.
     * Algoritmin aikavaativuus olisi:
     * T(n) = n*(n(2m)) = n*(nm) = O(n^2*m)
     * Pahimillaan algoritmin aikavaativuus olisi O(n^2*m)
     *
     * Algoritmin aikavaativuus ei ole tehokas, joten voimme todeta, että ainakin algoritmin aikavaattivuutta voisi parantaa.
     * Mietin kauan voiko tarkastuksen suorittaa yhden for-silmukan avulla, mutta valitettavasti en itse tajunnut miten se tehtäisi.
     * Mielestäni ratkaisuni on kuitenkin toimiva ja tulevaisuutta miettien helppolukuinen ja selkeä.
     */

    /**
     * Laskee tiedon siitÃ¤ mitkÃ¤ joukot leikkaavat keskenÃ¤Ã¤n (siis millÃ¤ on yhteisiÃ¤ alkioista).
     * Tulos palautetaan kuvauksena siten, ettÃ¤ jos syÃ¶tteen joukolla Ji on yhteisiÃ¤ alkioita
     * jonkun muun syÃ¶tteen joukon Jj kanssa, niin kuvauksen avaimeen Ji liittyvÃ¤ssÃ¤ arvossa (joukossa) on
     * viittaus joukkoon Jj (ja pÃ¤invastoin). Kuvaus sisÃ¤ltÃ¤Ã¤ avaimenaan kunkin joukon JJ sisÃ¤ltÃ¤mÃ¤n
     * joukon Ji ja kunkin avaimen arvona on joukko niistÃ¤ joukoista Jj joilla on vÃ¤hintÃ¤Ã¤n yksi yhteinen alkio
     * joukon Ji kanssa.
     *
     * @param JJ  syÃ¶te (joukkojen joukko)
     * @param <E> joukkojen alkiotyyppi
     * @return kuvaus joukkojen leikkaavuuksista
     */
    @Override
    public <E> Map<Set<E>, Set<Set<E>>> leikkaavatJoukot(Set<Set<E>> JJ) {
        Map<Set<E>, Set<Set<E>>> tulos = new HashMap<>();

        // TODO

        //n
        //Käydään läpi JJ:n joukot yksitellen
        for (Set<E> joukko : JJ) {
            //Asetetaan tuloksen avaimeksi joukko ja asetetaan sen arvo apumetodia kutsumalla
            tulos.put(joukko, leikkaavatJoukot(joukko, JJ));
        }

        return tulos;
    }

    //Apumetodi
    public <E> Set<Set<E>> leikkaavatJoukot(Set<E> joukko, Set<Set<E>> JJ) {
        //Alustetaan apujoukko, joka saa arvoksi kaikki joukot, joilla on samoja arvoja tutkittavan joukon kanssa
        Set<Set<E>> apujoukko = new HashSet<>();

        //Käydään läpi yksitellen JJ:n kaikki joukot
        //n
        for (Set<E> x : JJ) {
            //Jos x ei ole sama kuin avain joukko
            if (!x.equals(joukko)) {
                //Alustetaan leikkaus, joka saa arvokseen joukon alkiot
                //m
                Set<E> eriavatAlkiot = new HashSet<>(joukko);
                //poistetaan kaikki samat alkiot joukon x:n kanssa
                //m
                eriavatAlkiot.removeAll(x);
                //Jos joukon koko on pienentynyt samoja alkioita löytyi!
                if (eriavatAlkiot.size() < joukko.size()) {
                    //lisätään x apujoukkoon
                    apujoukko.add(x);
                }
            }
        }
        //palautetaan apujoukko
        return apujoukko;
    }
}