import java.util.ArrayList;

public class TRAI_23_X2_moilanes implements TRAI_23_X2 {

    /**
     * ITSEARVIONTI TÃ„HÃ„N:
     *Laskin algoritmin aikavaativuudeksi O(n), joka on kohtalaisen hyvä. Aikavaatimusta voisi siis vielä parantaa.
     *En kuitenkaan usko, että sitä voisi parantaa ilman joukko- tai kuvaus-tyyppejen apua. Esim. Hashset:in käyttäminen
     *olisi parantanut algoritmin aikavaatimuutta huomattavasti.
     *
     *Vaikka algoritmi on aikavaativuudeltaan kohtalaisen hyvä, itse koodissa olisi vielä parannettavaa. Koodi voi vaikuttaa
     *toisen näkökulmaan sekavalta, joten tulevaisuutta miettien sitä voisi selkeyttää. Kenties koodin selkeyttämisen kannalta
     *olisin voinut hyödyntää while-silmukoita enemmän, enkä tunkea taulukoiden läpikäyntiä yhden while-silmukan sisään.
     *
     */
    /**
     * Kasvavien listojen yhdiste.
     * Palauttaa uuden listan jossa on sellaiset alkiot jotka lÃ¶ytyvÃ¤t
     * jommastakummasta tai molemmista syÃ¶telistoista.
     * Kukin alkio tulee tuloslistaan vain kerran.
     * Tuloslista tulee myÃ¶s kasvavaan jÃ¤rjestykseen.
     * Jos jompikumpi syÃ¶telistoista on epÃ¤jÃ¤rjestyksessÃ¤, tulosta ei tarvitse tuottaa.
     *
     * @param L1 ensimmÃ¤inen syÃ¶telista, alkiot kasvavassa jÃ¤rjestyksessÃ¤
     * @param L2 toinen syÃ¶telista, alkiot kasvavassa jÃ¤rjestyksessÃ¤
     * @return yhdistelista
     */

    @Override
    public ArrayList<Integer> yhdisteKasvavista(ArrayList<Integer> L1, ArrayList<Integer> L2) {
        ArrayList<Integer> yhdiste = new ArrayList<>();

        // TODO

        // SUUNNITTELE algoritmi huolella ENNEN toteutuksen aloittamista!

        // HyÃ¶dynnÃ¤ listojen L1 ja L2 jÃ¤rjestystÃ¤ jo paperilla, sitten
        // suunnitelmassa ja lopuksi toimivassa algoritmissa.
        // NÃ¤in tuloslista tulee helpommin jÃ¤rjestykseen ja siitÃ¤ on
        // helpompi jÃ¤ttÃ¤Ã¤ duplikaatit pois.

        //Luodaan laskurit, jotka pitäcät kirjaa:
        //Molempien taulukoiden etenemisestä
        int bpaikka = 0;
        int apaikka = 0;
        //Yhdiste taulukon edellisestä numerosta
        int ed_numero = Integer.MIN_VALUE;

        //Ensin taulukko tarkistaa, jos jompikumpi taulukoista on tyhjä ja lisää uuteen taulukkoon vain toisen jos on
        if (L1.size() == 0) {
            yhdiste.addAll(L2);
        } else if (L2.size() == 0) {
            yhdiste.addAll(L1);
        } else {
            //Silmukka, jonka sisällä lajittelu tapahtuu
            while (apaikka < L1.size() && bpaikka < L2.size()) {
                //Ensin taulukko tarkistaa, jos tutkittavissa alkioissa on yhtäläisyyksiä
                if (L1.get(apaikka).equals(L2.get(bpaikka))) {
                    if (!L1.get(apaikka).equals(ed_numero)) {
                        yhdiste.add(L1.get(apaikka));
                    }
                    ed_numero = L1.get(apaikka);
                    //Jotta while silmukka pyörii loppuun asti, tarkistetaan ettei kummankaam taulukon paikka ylitä sen indexiä
                  if (apaikka + 1 == L1.size()) {
                        bpaikka++;
                    } else if (bpaikka + 1 == L2.size()) {
                        apaikka++;
                    } else {
                        apaikka++;
                        bpaikka++;
                    }
                } else if (L1.get(apaikka).equals(ed_numero)) {
                    if ((apaikka + 1) >= L1.size() && L1.get(apaikka) < L2.get(bpaikka) && !L2.get(bpaikka).equals(ed_numero)) {
                        yhdiste.add(L2.get(bpaikka));
                        ed_numero = L2.get(bpaikka);
                        bpaikka++;
                    } else {
                        apaikka++;
                    }
                } else if (L2.get(bpaikka).equals(ed_numero)) {
                    if ((bpaikka + 1) >= L2.size() && L1.get(apaikka) > L2.get(bpaikka) && !L1.get(apaikka).equals(ed_numero)) {
                        yhdiste.add(L1.get(apaikka));
                        ed_numero = L1.get(apaikka);
                        apaikka++;
                    } else {
                        bpaikka++;
                    }
                }
                //Silmukka tarkistaa, mikä alkio tulee seuraavaksi.
                else if (ed_numero < L1.get(apaikka) && L1.get(apaikka) < L2.get(bpaikka)) {
                    yhdiste.add(L1.get(apaikka));
                    ed_numero = L1.get(apaikka);
                    if (apaikka + 1 < L1.size()) {
                        apaikka++;
                    }
                } else if (ed_numero < L2.get(bpaikka) && L2.get(bpaikka) < L1.get(apaikka)) {
                    yhdiste.add(L2.get(bpaikka));
                    ed_numero = L2.get(bpaikka);
                    if (bpaikka + 1 < L2.size()) {
                        bpaikka++;
                    }
                }  else  if (ed_numero < L1.get(apaikka) && L1.get(apaikka) > L2.get(bpaikka)) {
                    yhdiste.add(L1.get(apaikka));
                    ed_numero = L1.get(apaikka);
                    if (apaikka + 1 < L1.size()) {
                        apaikka++;
                    }
                } else if (ed_numero < L2.get(bpaikka) && L2.get(bpaikka) > L1.get(apaikka)) {
                    yhdiste.add(L2.get(bpaikka));
                    ed_numero = L2.get(bpaikka);
                    if (bpaikka + 1 < L2.size()) {
                        bpaikka++;
                    }
                }

            }
        }

        return yhdiste;
    }

}