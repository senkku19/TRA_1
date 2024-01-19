// TRAI_23_t13.java SJ

/**
 * 12. Palindromi on merkkijono joka myÃ¶s takaperin luettuna on sama. Kun sana talletetaan pakkaan merkki kerrallaan, on
 * helppoa tarkastaa onko sana palindromi vai ei. Kirjoita algoritmi joka tallettaa merkkijonon merkit pakkaan ja joka
 * tarkastaa onko pakan sisÃ¤ltÃ¶ palindromi vai ei. Ota kurssin Moodlesta pÃ¤Ã¤ohjelma jossa on vinkkejÃ¤ miten merkkijono
 * muutetaan pakaksi. Aikavaativuus?
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Locale;
import java.util.Scanner;

public class TRAI_23_t13_pohja {


    // PÃ¤Ã¤ohjelman kÃ¤yttÃ¶:
    // java TRAI_23_t13 [merkkijono]
    public static void main(String[] args) {

        String mjono = null;

        if (args.length > 0)
            mjono = args[0];

        if (mjono == null) {
            System.out.print("Anna merkkijono : ");
            Scanner sc = new Scanner(System.in);
            mjono = sc.nextLine();
        }

        System.out.print("Merkkijono '" + mjono + "' ");
        if (onkoPalindromi(mjono))
            System.out.println("on palindromi");
        else
            System.out.println("ei ole palindromi");

    } // main()


    /**
     * Merkkijonosta merkkipakka.
     *
     * @param S syÃ¶temerkkijono
     * @return merkit pakkana
     */
    public static Deque<Character> merkkijonostaPakka(String S) {
        Deque<Character> D = new ArrayDeque<>();

        for (int i = 0; i < S.length(); i++)
            D.addLast(S.charAt(i));

        return D;

    } // merkkijonostaPakka()


    /**
     * Onko merkkijono palindromi vai ei?
     *
     * @param S syÃ¶temerkkijono
     * @return totuusarvo
     */
    public static boolean onkoPalindromi(String S) {

        // TODO
        Deque<Character> D = new ArrayDeque<>();

        S = S.toLowerCase();

        //Luodaan merkkijonosta pakka
        for (int i = 0; i < S.length(); i++) {
            D.addLast(S.charAt(i));
        }


        for (int i = 0; i < S.length(); i++) {
            //Jos merkkijonon merkki ja pakkauksen viimeinen merkki on erit palautetaan false
           if (S.charAt(i) != D.getLast()){
               return false;
           }
           //Poistetaan viimeinen merkki pakasta, jotta ei vertailla samaa kirjainta
           D.removeLast();
        }



        return true;
    } // onkoPalindromi()

} // class
