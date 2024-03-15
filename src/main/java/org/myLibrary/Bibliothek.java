package org.myLibrary;

import java.util.ArrayList;

class Bibliothek {
    private ArrayList<Buch> buchListe;

    public Bibliothek() {
        buchListe = new ArrayList<>();
    }

    public void buchHinzufuegen(Buch buch) {
        buchListe.add(buch);
    }

    public void buchEntfernen(int index) {
        if (index >= 0 && index < buchListe.size()) {
            buchListe.remove(index);
        }
    }

    public ArrayList<Buch> getBuchListe() {
        return buchListe;
    }
}
