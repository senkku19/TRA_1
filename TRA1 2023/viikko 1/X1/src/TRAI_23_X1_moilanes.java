public class TRAI_23_X1_moilanes implements TRAI_23_X1 {
    //                    ^^^^^ OMA TUNNUS TÃ„HÃ„N
    // NimeÃ¤ tiedosto TRAI_23_X1_tunnus.java

    /**
     *  ITSEARVIOINTI TÃ„HÃ„N:
     *
     *Laskin, että algoritmin aikavaativuus olisi O(n^2), joten algoritmissa olisi selkeästi vielä parannettavaa.
     *
     *Mielestäni ratkaisuni toimii, mutta ei ole tehokkain mahdollinen. Silmukoita on paljon sisäkkäin ja
     *uskon, että se syö algoritmin tehokkuutta. Mahdollisesti algoritmia voisi saada tehokkaamaksi, jos
     *keksisin/löytäisin tavan, jolla algoritmi kävisi läpi alkiot vain yhden for silmukan sisällä eikä kahden tai
     *ainakin siten, että silmukoita olisi vähemmän ja vakiokomentoja enemmän. Toinen asia, joka voisi tehdä
     *algoritmista tehokkaaman on, jos saisin return-komennon jo silmukoiden sisälle.
     *
     *
     *
     **/

    /**
     * Ovatko uniikkeja. Jos taulukon kaikki alkiot ovat eri lukuja (tai taulukossa on vain 0 tai 1
     * alkiota), metodi palauttaa true. Jos taas jokin (tai jotkin) luku esiintyy kahdesti tai
     * useammin, metodi palauttaa false. Voit olettaa taulukossa olevan vain kelvollisia
     * kokonaislukuja (ei siis null:eja).
     *
     * @param A SyÃ¶tetaulukko.
     * @return true jos kaikki alkiot ovat eri lukuja, muuten false
     */
    @Override
    public boolean ovatkoUniikkeja(Integer[] A) {

        // TODO
        //pitää kirjaa, milla paikalla tutkittava alkio on
        int paikka = 0;
        //palauttaa halutun boolean arvon
        boolean uniikkeja = true;

        //kay lapi yksitellen taulukon alkiot
        for (Integer a: A){
            //jos alkio on <=1 palauttaa heti true arvon ja lopettaa silmukan
            if (A.length == 0 || A.length == 1) {
                uniikkeja = true;
                break;
            } else {
                //vertaa alkiota muihin taulukon alkioihin
                for (int i = 0; i < A.length; i++) {
                    //tunnistaa ettei alkiota verrata taulukossa itsensa kanssa
                    if (i != paikka) {
                        if (A[i].equals(a)) {
                            uniikkeja = false;
                            break;
                        } else if (!A[i].equals(a)) {
                            uniikkeja = true;
                        }
                    }
                }
            }
            //jos alkio ei ole uniikki silmukka loppuu, muuten tutkittava alkio siirtyy eteenpain
            if (!uniikkeja){
                break;
            } else {
                paikka ++;
            }
        }

        return uniikkeja;
    }
}