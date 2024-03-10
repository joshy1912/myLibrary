package org.myLibrary;

import java.util.ArrayList;

public class Bibliothek {
    private ArrayList<Buch> buchListe;
    public Bibliothek(){
        buchListe = new ArrayList<>();
    }
    public void buchHinzufuegen(Buch buch) {
        buchListe.add(buch);
    }
    public void buchEntfernen(int index) {
        if (index >= 0 && index < buchListe.size()) {
            buchListe.remove(index);
            System.out.println("Buch erfolgreich gelöscht.");
        } else {
            System.out.println("Ungültiger Index.");
        }
    }

    public void alleBuecherAnzeigen() {
        if (buchListe.isEmpty()) {
            System.out.println("Die Bibliothek ist leer.");
        } else {
            System.out.println("Buchtitel  | Autor | ISBN");
            for (int i = 0; i < buchListe.size(); i++) {
                Buch buch = buchListe.get(i);
                System.out.println((i+1) + " " + buch.getTitel() + " | " + buch.getAutor() + " | " + buch.getIsbn());
            }
        }
    }
}
