
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * ITSEARVIOINTI TÃ„HÃ„N:
 *
 * Loin abstraktin tietotyyppi renkaan taulukkototeutuksella. Mielestäni tietorakenne/algortimi toteuttaa pyydetyt asiat hyvin.
 * Alussa minulla oli ongelmia taulukon suurentamisen kanssa, mutta sain sen lopulta toimimaan ja mielestäni toimivalla tavalla.
 * Tietorakenne on toimiva ja tehokas, jäin miettimään toteutinko iteraattorin oikein, mutta mielestäni se on ainakin
 * toimiva.
 *
 *
 */

/**
 * TÃ¤ydennÃ¤ _ainakin_ TODO-kohtiin, muuallekin voit joutua tÃ¤ydentÃ¤mÃ¤Ã¤n.
 */

/** rengas
 * @param <E> alkiotyyppi
 */

public class TRAI_23_X7_moilanes<E> implements TRAI_23_X7<E> {
    // ^^^^^ oma tunnus tÃ¤hÃ¤n

    int n = 0;  // alkioiden mÃ¤Ã¤rÃ¤ renkaassa
    int indeksi = 0; //Indeksin paikka

    int modCount = 0;

    E[] taulukko; //taulukko, johon renkaan alkioita tallennetaan
    // TODO

    public TRAI_23_X7_moilanes() {
        // TODO
        n = 0;
        //asetetaan taulukon kooksi 10
        taulukko = (E[]) new Object[10];
    }

    //Jos taulukko on mennyt täyteen apumetodi, jolla sen koko saadaan tuplattua
    private void tuplataKoko(){
        //Kopidaan taulukko aputaulukkoon ja muutetaan koko kaksinkertaiseksi
        E[] apuTaulukko = (E[]) Arrays.copyOf(taulukko, taulukko.length*2);
        //Kopioidaan vanhaan taulukkoon uuden taulukon koko
        taulukko = (E[]) Arrays.copyOf(apuTaulukko, apuTaulukko.length);
    }

    /**
     * Alkioiden mÃ¤Ã¤rÃ¤ renkaassa.
     *
     * @return Alkioiden mÃ¤Ã¤rÃ¤n.
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * LisÃ¤Ã¤ renkaaseen alkion.
     *
     * @param x LisÃ¤ttÃ¤vÃ¤ alkio
     * @return palauttaa aina tosi (aina mahtuu)
     */
    @Override
    public boolean add(E x) {
        // TODO
        //Jos taulukko on jo täynnä
        if (n == taulukko.length) {
            //tuplataan taulunkoko apumetodilla
            tuplataKoko();
        }
        //lisätään alkio taulukon viimeiseksi
        taulukko[n] = x;
        //lisätään alkioiden määrää taulukossa
        n++;
        modCount++;
        return true;
    }


    /**
     * Palauttaa seuraavan alkion renkaasta.
     * Alussa palauttaa jonkin alkion.
     * Toistuvasti kutsuttaessa palauttaa kaikkia alkioita vuorotellen.
     *
     * @return Yhden renkaan alkion.
     * @throws NoSuchElementException jollei renkaassa ole alkoita
     */
    @Override
    public E next() {
        if (n == 0)
            throw new NoSuchElementException("Rengas on tyhjÃ¤, next() ei voi kutsua!");
        // TODO
        //Jos indeksi on yhtäsuuri kuin alkioiden määrä
        if (indeksi+1 >= n ){
            //Palautetaan indeksi renkaan alkuun
            indeksi = 0;
        } else {
            //muuten liikutaan indeksissä yksi askel eteenpäin
            indeksi++;
        }
        return taulukko[indeksi];
    }

    @Override
    public E remove(){
        if (n == 0)
            throw new NoSuchElementException("Rengas on tyhjÃ¤, remove() ei voi kutsua!");

        E poistettava = taulukko[indeksi];
        System.out.println(n + "m " + indeksi);

        for (int i = indeksi; i < n; i++) {
            if (taulukko[i+1] == null){
                break;
            }
            taulukko[i] = taulukko[i + 1];
        }

        n--;
        modCount ++;

        return poistettava;
    }

    @Override
    public Iterator<E> iterator() {
        return new iter();
    }

    private class iter implements Iterator<E> {
        // TODO
        //indeksi, jossa iteraattori kulkee.
        private int nykyinen_ind = 0;
        private int expModCount;
        public iter(){
            expModCount = modCount;
        }
        @Override
        public boolean hasNext() {
            return n > 0;
        }

        @Override
        public void remove() {
            if (expModCount != modCount)
                throw new IllegalStateException("Concurrent modification detected.");
            if (n == 0)
                throw new NoSuchElementException("Rengas on tyhjÃ¤, remove() ei voi kutsua!");
            if (nykyinen_ind >= n)
                throw new NoSuchElementException("Ei alkiota, jota voisi poistaa");


            for (int i = nykyinen_ind; i < n; i++){
                if (taulukko[i+1] == null){
                    break;
                } else {
                    taulukko[i] = taulukko[i + 1];
                }

            }

            n--;
            expModCount++;

        }

        @Override
        public E next() {
            // TODO
            if (expModCount != modCount)
                throw new ConcurrentModificationException("Concurrent modification detected.");
            //siirytään yksi indeksi eteenpäin
            nykyinen_ind ++;
            //jos indeksi laskuri ylittää taulukossa olevien alkioiden määrän
            if (nykyinen_ind >= n)
                //palataan taulukon alkuun
                nykyinen_ind = 0;

            return taulukko[nykyinen_ind];
        }
    }
}