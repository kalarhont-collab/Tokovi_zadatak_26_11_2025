import java.io.*;
import java.util.Scanner;

public class Zadatak3PDF {
    public static void main(String[] args)  {

        Scanner sc = new Scanner(System.in);
        System.out.println("Unesite putanju do odredišne datoteke (npr. C:/temp/original.pdf): ");
        String putanjaOriginala = sc.nextLine();

        System.out.println("Unesite naziv destinacijske datoteke (npr. C:/temp/kopija.pdf): ");
        String putanjaKopije = sc.nextLine();

        File original = new File(putanjaOriginala);
        File kopija = new File(putanjaKopije);

        try {
            if (!original.exists()) {
                throw new FileNotFoundException("Originalna datoteka nije pronađena.");
            }
            if (!original.canRead()) {
                throw new SecurityException("Nemate prava za čitanje datoteke.");
            }


            try (FileInputStream fis = new FileInputStream(original);
                 FileOutputStream fos = new FileOutputStream(kopija)) {

                int podatak;
                while ((podatak = fis.read()) != -1) {
                    fos.write(podatak);
                }

                if (kopija.exists()) {
                    System.out.println("\nDatoteka " + original.getName() + " kopirana.");
                } else {
                    System.out.println("\nGreška: datoteka " + original.getName() + " nije mogla biti kopirana.");
                }
            }

            if (kopija.delete()) {
                System.out.println("Kopija uspješno obrisana.");
            } else {
                System.out.println("Pogreška! kopija nije mogla biti obrisana.");
            }


        } catch (FileNotFoundException e) {
            System.out.println("Pogreška! " + e.getMessage());

        } catch (SecurityException e) {
            System.out.println("Pogreška! " + e.getMessage());

        } catch (IOException e) {
            System.out.println("Pogreška pri kopiranju datoteke! " + e.getMessage());



        }
    }
}
