import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Objects;

import static java.lang.String.*;

public class TRAI_23_X3_moilanes implements TRAI_23_X3 {
    /**
     * ITSEARVIOINTI TÃ„HÃ„N:
     * Algoritmini aikavaatimuus on O(n), elikkä ihan hyvä.
     *
     * Algoritmia voisi saada tehokkaammaksi esim. löytämällä tavan käydä listan kaikki alkiot läpi tehokkaamin.
     * Mielestäni ratkaisuni on kuitenkin toimiva ja selkeä. Uskon, että algoritmistäni löytyy parannettavaa, mutta
     * juuri nyt en keksi, miten olisin voinut koodata siitä paremman muuten kuin löytämällä paremman tavan
     * käydä listan alkiot läpi.
     */


    /**
     * SiirrÃ¤ suuremmat toiseen listaan.
     * Poistaa listasta kaikki sellaiset alkiot jotka seuraavat alkiota raja.
     * (Ovat "suurempia", eli ne alkiot a joille a.compareTo(raja) > 0. Poistetut alkiot
     * siirretÃ¤Ã¤n uuteen listaan joka palautetaan.
     * Lista L sÃ¤ilyy muuten jÃ¤rjestyksessÃ¤.
     * @param L syÃ¶telista
     * @param raja alkio jota suuremmat siirretÃ¤Ã¤n
     * @return siirretyt alkiot listana
     */
    @Override
    public <E extends Comparable<? super E>>  LinkedList<E> siirraSuuremmat(LinkedList<E> L, E raja) {

        LinkedList<E> U = new LinkedList<>();

        // TODO

        for (ListIterator<E> iterator = L.listIterator(); iterator.hasNext(); ) {
            //alustaa Elementin alkio tutkittavalla arvolla
            E alkio = iterator.next();
            //Jos compareTo arvo palautttaa 0 suuremman arvon alkio on suurempi kuin raja
            if (alkio.compareTo(raja) > 0) {
                //lisää alkion uuteen listaan
                U.add(alkio);
                //Poistaa alkion iteraatori listasta
                iterator.remove();
            }
        }


            return U;
    }

}