package org.myLibrary;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BibliothekGUI extends JFrame {

    private Bibliothek bibliothek = new Bibliothek();
    private JTextArea buchTextArea;

    public BibliothekGUI() {
        setTitle("Bibliotheksverwaltung");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        menuPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton showBooksBtn = new JButton("Bücherliste");
        showBooksBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zeigeBuecherliste();
            }
        });

        JButton addBookBtn = new JButton("Neues Buch hinzufügen");
        addBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                neuesBuchHinzufuegen();
            }
        });

        JButton deleteBookBtn = new JButton("Buch löschen");
        deleteBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buchLoeschen();
            }
        });

        menuPanel.add(showBooksBtn);
        menuPanel.add(addBookBtn);
        menuPanel.add(deleteBookBtn);
        add(menuPanel, BorderLayout.WEST);

        buchTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(buchTextArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void zeigeBuecherliste() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Buch> buchListe = bibliothek.getBuchListe();
        if (buchListe.isEmpty()) {
            sb.append("Die Bibliothek ist leer.");
        } else {
            for (Buch buch : buchListe) {
                sb.append(buch.getTitel()).append(" | ").append(buch.getAutor()).append(" | ").append(buch.getIsbn()).append("\n");
            }
        }
        buchTextArea.setText(sb.toString());
    }

    private void neuesBuchHinzufuegen() {
        JTextField titelField = new JTextField();
        JTextField autorField = new JTextField();
        JTextField isbnField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Titel:"));
        panel.add(titelField);
        panel.add(new JLabel("Autor:"));
        panel.add(autorField);
        panel.add(new JLabel("ISBN:"));
        panel.add(isbnField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Neues Buch hinzufügen",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Buch buch = new Buch(titelField.getText(), autorField.getText(), isbnField.getText());
            bibliothek.buchHinzufuegen(buch);
            zeigeBuecherliste();
        }
    }

    private void buchLoeschen() {
        String[] options = bibliothek.getBuchListe().stream()
                .map(buch -> buch.getTitel() + " | " + buch.getAutor() + " | " + buch.getIsbn())
                .toArray(String[]::new);

        if (options.length == 0) {
            JOptionPane.showMessageDialog(null, "Keine Bücher zum Löschen vorhanden.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String selectedBook = (String) JOptionPane.showInputDialog(null, "Welches Buch soll gelöscht werden?",
                "Buch löschen", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (selectedBook != null) {
            int index = -1;
            for (int i = 0; i < options.length; i++) {
                if (options[i].equals(selectedBook)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                bibliothek.buchEntfernen(index);
                zeigeBuecherliste();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BibliothekGUI().setVisible(true);
            }
        });
    }
}