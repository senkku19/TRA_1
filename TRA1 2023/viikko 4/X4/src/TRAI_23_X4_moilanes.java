import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

import javax.swing.*;

public class TRAI_23_X4_moilanes implements TRAI_23_X4 {

    /**
     * ITSEARVIOINTI TÃ„HÃ„N:
     *Algortimini aikavaativuus: Arvioin sisaViimeinen-metodien aikavaativuudeksi pahimmassa tapauksessa O(n)
     *(ko. metodin aikavaativuudeksi voisin myös sanoa O(puun_korkeus))
     *
     * Arvioin sisaEdellinen-metodin aikavaativuudeksi pahimmassa tapauksessa O(n). Tässäkin tapauksessa aikavaativuuden
     * voisi ilmaista O(puun_korkeus).
     *
     * Mielestäni ratkaisuni on tehokas ja toimiva, mutta kuten aina parannettavaa löytyy. Yritin saada sisEdellisen toimimaan
     * ilman for-silmukka, luomalla apumetodin, mutta valitettavasti en onnistunut luomaan muuta kuin päättymättömän algortimin.
     * Uskon, että metodin voisi saadakkin toimimaan ilman for-silmukkaa ja apumetodin avulla,
     * kuten tein sisViimeinen:kin, mutta tällä kertaa en itse onnistunut siinä.
     *
     * sisViimeinen-metodi ja sen apumetodi ovat kuitenkin mielestäni hyvä lähestymis tapa kyseisen tehtävän ratkaisuun.
     * Metodia voisi varmasti parannella, mutta mielestäni se ei ole välttämätöntä.
     *
     *
     */

    /**
     * Etsii ja palauttaa binÃ¤Ã¤ripuun sisÃ¤jÃ¤rjestyksessÃ¤ viimeisen solmun.
     * @param P Tarkasteltava puu.
     * @return Viimeinen solmu tai null jos puu P on tyhjÃ¤.
     * @param <E> puun solmujen alkioiden tyyppi (ei tarvita)
     **/
    @Override
    public <E> BTreeNode<E> sisaViimeinen(BTree<E> P) {
        // TODO
        //Tarkistetaan, onko puu tyhjä, jos on palautetaan heti arvoksi null
        if (P.isEmpty())
            return null;
        //muuten
        else {
            //luodaan juuresta solmu
            BTreeNode<E> n = P.getRoot();
            //tarkistetaan, onko juurella oikea lapsi.
            if (n.getRightChild() == null){
                //juurella ei ollut oikeaa lasta, palautetaan solmu
                return n;
            } else {
                //muuten siirytään apumetodiin
                return sisaViimeinen(n);
            }
        }
    }

    //Apumetodi
    public <E> BTreeNode<E> sisaViimeinen(BTreeNode<E> n) {
        // TODO
        //jos tutkittavalla solmulla on oikea lapsi
        if (n.getRightChild() != null){
            //kutsutaan apumetodia solmun oikealla lapsella ja alustetaan n sillä.
            n = sisaViimeinen(n.getRightChild());
        }

        //palautetaa solmu.
        return n;
    }



    /**
     * Palauttaa binÃ¤Ã¤ripuun solmun s edeltÃ¤jÃ¤solmun sisÃ¤jÃ¤rjestyksessÃ¤.
     * @param s binÃ¤Ã¤ripuun solmu.
     * @return edeltÃ¤jÃ¤solmu tai null jollei edeltÃ¤jÃ¤Ã¤ ole.
     * @param <E> puun solmujen alkioiden tyyppi (ei tarvita)
     */
    @Override
    public <E> BTreeNode<E> sisaEdellinen(BTreeNode<E> s) {
        // TODO
        //Luodaan apu solmu pitämään kirjaa, mikä solmun edeltäjä oli.
        BTreeNode<E> edellinen_solmu;
        //Jos solmu ei ole tyhjä:
        if (s != null) {
            //Koska kyseessä on ns. viimeinen solmu, tarkistetaan onko sillä vasenta lasta
            //Sisäkkäisjärjestyksessa solmun vasen lapsi on sen edeltäjä.
            if (s.getLeftChild() != null) {
                //etsitään, onko solmun vasemmalla lapsella alipuuta, jossa on oikean puolimmainen lapsi.
                    return sisaViimeinen(s.getLeftChild());
                }
            else {
                //Kun solmulla ei ole vasenta lasta, etsitään sen edeltäjää solmun yläpuolelta.
                //alustetaan edellinen_solmu uudella arvolla.
                for (edellinen_solmu = s.getParent(); edellinen_solmu != null && s == edellinen_solmu.getLeftChild();){
                    //päivitetään tarkasteltavaa solmua
                    s = edellinen_solmu;
                    //päivitetään edellistä solmua
                    edellinen_solmu = s.getParent();
                }
                //Palautetaan edellinen_solmu
                return edellinen_solmu;
            }
        }
        // Jos solmu on tyhjä palautetaan null
        else
            return null;


    }
}