import java.util.HashSet;
import java.util.Set;

public class TRAI_23_X5_moilanes implements TRAI_23_X5 {

    /**
     * ITSEARVIOINTI TÃ„HÃ„N:
     * Laskin algoritmini aikavaativuudeksi O(n). Algoritmissä käytetään jo hashsettiä, joten aikavaativuus pysyisi samana.
     *
     * Mielestäni algoritmi on toimiva ja selkeä. Algoritmi toimii hyvin yksiviivaisesti, mutta jos syöte joukkoja olisi esim. enemmän
     * olisi algoritmi turhan iso ja kenties jopa tehoton. Mietin voisiko algoritmia parantaa/selkeyttää löytämällä tavan, jossa kaikkea ei tarvitse tehdä kolmesti.
     * Ehkäpä myös algoritmi parantuisi, jos uusia joukkoja ei tehtäisi 3 kpl:tta (tai niin monta kuin syöte joukkoja on). Tämä auttaisi todennäköisesti tilavaativuuteen, eikä aikavaativuuten.
     * Kenties tutkimalla suoraan syötejoukkoja. Myös koodia voisi lyhentää esim. Apumetodin avulla. En kuitenkaan usko, että algoritmin
     * tehokkuus paranisi sen avulla, mutta kenties selkeyttäsi koodin toimintaa ja pienentää algoritmin tilavaativuutta.
     *
     *
     *
     */

    /**
     * Joukkojen kaksi kolmesta -leikkaus.
     * Luo uuden joukon johon algoritmi laittaa ne syÃ¶tejoukkojen alkiot jotka
     * kuuluvat tasan kahteen kolmesta syÃ¶tejoukosta.
     * Ei muuta syÃ¶tejoukkojensa sisÃ¤ltÃ¶Ã¤ (vaan luo uuden tulosjoukon)
     * Jos mikÃ¤Ã¤n alkio ei tÃ¤ytÃ¤ ehtoa, palautetaan tyhjÃ¤ joukko.
     * @param S1    syÃ¶tejoukko (ei muuteta)
     * @param S2    syÃ¶tejoukko (ei muuteta)
     * @param S3    syÃ¶tejoukko (ei muuteta)
     * @param <E>   alkiotyyppi
     * @return  kaksi-kolmesta tulosjoukko
     */
    @Override
    public <E> Set<E> kaksiKolmestaLeikkaus(Set<E> S1, Set<E> S2, Set<E> S3) {
        Set<E> tulos = new HashSet<>();

        // TODO
        //Luodaan leikkaukset jokaiaisen joukon välillä.
        Set<E> ab_leikkaus = new HashSet<>(S1);
        //S1 ja S2 leikkaus
        ab_leikkaus.retainAll(S2);
        Set<E> ac_leikkaus = new HashSet<>(S1);
        //S1 ja S3 leikkaus
        ac_leikkaus.retainAll(S3);
        Set<E> bc_leikkaus = new HashSet<>(S2);
        //S3 ja S2 leikkaus
        bc_leikkaus.retainAll(S3);


        //Käydään läpi S1 ja S2 leikkaus
       for (E x : ab_leikkaus){
           //Jos leikkauksessa ei ole samoja alkioita kuin S1 ja S3 leikkauksessa
            if (!ac_leikkaus.contains(x)){
                //Lisätään tulos joukkoon
                tulos.add(x);
            }
        }

       //Käydään läpi S2 ja S3 leikkauksen alkio
       for (E x : bc_leikkaus){
           //Jos leikkauksessa ei ole samoja alkioita kuin S1 ja S2 leikkauksessa
            if(!ab_leikkaus.contains(x))
                //Lisätään alkio tulos joukkoon
                tulos.add(x);
        }

       //Käydään läpi S1 ja S3 leikkauksen alkiot
       for (E x : ac_leikkaus){
           //Jos leikkauksessa ei ole samoja alkioita kuin S2 ja S3 leikkauksessa
            if(!bc_leikkaus.contains(x))
                //Lisätään alkio tulos joukkoon
                tulos.add(x);
        }

        return tulos;
    }


}