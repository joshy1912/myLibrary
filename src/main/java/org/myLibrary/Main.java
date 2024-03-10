package org.myLibrary;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bibliothek bibliothek = new Bibliothek();

        while (true) {
            System.out.println("\n(1) Bücherliste\n(2) Neues Buch hinzufügen\n(3) Buch löschen\n(4) Ende");
            System.out.print("Wählen Sie eine Option: ");
            int auswahl = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (auswahl) {
                case 1:
                    bibliothek.alleBuecherAnzeigen();
                    break;
                case 2:
                    System.out.print("Titel des Buches: ");
                    String titel = scanner.nextLine();
                    System.out.print("Autor des Buches: ");
                    String autor = scanner.nextLine();
                    System.out.print("ISBN des Buches: ");
                    String isbn = scanner.nextLine();
                    Buch neuesBuch = new Buch(titel, autor, isbn);
                    bibliothek.buchHinzufuegen(neuesBuch);
                    break;
                case 3:
                    bibliothek.alleBuecherAnzeigen();
                    System.out.print("Welches Buch soll gelöscht werden? (Geben Sie den Index ein): ");
                    int index = scanner.nextInt();
                    bibliothek.buchEntfernen(index - 1);
                    break;
                case 4:
                    System.out.println("Programm beendet.");
                    System.exit(0);
                default:
                    System.out.println("Ungültige Auswahl. Bitte geben Sie eine Zahl zwischen 1 und 4 ein.");
            }
        }
    }
}
