//TODO: controllare ogni funzione del codice e renderlo "a prova di scimmia"

import java.util.*;//Importo le librerie
import java.io.*;

public class App {

    public static InputStreamReader input = new InputStreamReader(System.in);// creo l'oggetto input e il buffer per
                                                                             // ricevere i dati dalla tastiera
    public static BufferedReader tastiera = new BufferedReader(input);
    public static rubrica funzioni = new rubrica();// dichiaro l'oggetto funzioni appartenete alla classe rubrica
    public static char scelta;// variabile per la ricezione della scelte del menu

    public static void main(String[] args) throws Exception {
        try {// utilizzo un try/Catch per evitare il blocco del programma nel caso
             // avvenissero eventuali errori
            while (scelta != 'x') {// ciclo per la gestione del menu
                System.out.println("Scegli una funzione:");// espongo le funzioni del menu
                System.out.println("A - Aggiungi contatto");
                System.out.println("R - Rimuovi contatto");
                System.out.println("S - Visualizza contatti");
                System.out.println("F - Cerca un contatto");
                System.out.println("M - Modifica un contatto");
                System.out.println("X - Esci");
                scelta = tastiera.readLine().toLowerCase().charAt(0);// ricevo la scelta dalla tastiera, converto la
                                                                     // stringa in lettere minuscole, prendo un singolo
                                                                     // char dell'indice 0 della stringa ricevuta
                switch (scelta) {// confronto la stringa con lo switch
                    case 'a':
                        funzioni.add();// richiamo la funzione aggiungi
                        break;// quando il metodo richiamato conclude le sue operazioni, esco dallo
                              // switch/case
                    case 'r':
                        funzioni.remove();// richiamo la funzione rimuovi
                        break;
                    case 's':
                        funzioni.show();// richiamo la funzione visualizza
                        break;
                    case 'f':
                        funzioni.find();// richiamo la funzione ricerca
                        break;
                    case 'm':
                        funzioni.modify();// richiamo la funzione modifica
                        break;
                    case 'x':// chiudo il programma
                        break;
                    default:// se la scelta non viene identificata stampo una stringa di errore
                        System.out.println("Inserimento non valido." + System.lineSeparator());// stampo una stringa di
                                                                                               // errore con un ritorno
                                                                                               // a capo
                }
            }
        } catch (Exception e) {// in caso di eventuale errore lo catturo e stampo una stringa con il messaggio
                               // di errore, dichiaro un oggetto di Eccezione con nome e
            System.out.println(e.getMessage());// e.GetMessage() lo uso per ritornare a video l'errore
        }
    }
}

class rubrica extends App {// dichiaro la classe rubrica contenete tutte le funzioni di essa, la scelta di
                           // implementazione alla classe App
    // è data da una scelta di sviluppo per evitare continui richiami tra classi e
    // per evitare di dichiarare gli stessi attributi piu volte

    // creo i vettori per il salvataggio dei contatti, tutti i vettori contengono
    // stringhe, iniziano con uno spazio iniziale di 10 elementi con incremento di 1
    public static Vector<String> nome = new Vector<String>(10, 1);// Vettore nome, dove sono presenti Nome e Cognomi
    public static Vector<String> telefono = new Vector<String>(10, 1);// Vettore telefono, dove sono presenti i Numeri
                                                                      // di telefono
    public static Vector<String> email = new Vector<String>(10, 1);// Vettore email, dove sono presenti le Email dei
                                                                   // contatti
    public static Vector<String> gruppi = new Vector<String>(10, 1);// Vettore Gruppi, dove sono presenti i Gruppi di
                                                                    // appartenenza dei contatti
    public static Vector<String> researchGroup = new Vector<String>(10, 1);// Vettore researchGroup, dove vengono
                                                                           // immagazzinati i gruppi in totale

    private String SceltaAdd = "n";// dichiaro una variabile per la scelta se si vuole aggiungere un altro contatto
                                   // o no

                                   private void sort() {// dichiaro un metodo di selection sort
        for (int i = 0; i < nome.size() - 1; i++) {// selezioni il primo elemento del confronto
            for (int j = i + 1; j < nome.size(); j++) {// selezioniìo il secondo elemento da confrontare
                if (nome.elementAt(i).compareToIgnoreCase(nome.elementAt(j)) > 0) {// confronto i 2 elementi che ho
                                                                                   // selezionato, se è il primo è
                                                                                   // maggiore del secondo, li scabio
                    scambiaTutti(i, j);// invio gli indici al metodo di scambio
                }
            }
        }
    }

    private void scambiaTutti(int index1, int index2) {// dichiaro il metodo per lo scmabio di due indici in tutti i
                                                       // vettori
        scambia(index1, index2, nome);// richiamo il metodo comunicando il gli infdici e il vettpre da scambiare
        scambia(index1, index2, telefono);
        scambia(index1, index2, email);
        scambia(index1, index2, gruppi);
    }

    private void scambia(int index1, int index2, Vector<String> vector) {// metodo di scambio effettivo
        // riceve gli indici e il vettore da scambiare
        String temp;// variabile buffer
        temp = vector.elementAt(index1);// salvo in una variabile temporanea l'elemento alla posione index1
        vector.set(index1, vector.elementAt(index2));// assegno il valore dell'elemento in index2 alla posizione di
                                                     // index1
        vector.set(index2, temp);// assegno il valore che era precedentemente nel valore di index1 in index2
    }

    public void add() throws IOException {// metodo per l'aggiunta dei contatti alla rubrica
        try {// utilizzo un try/Catch per evitare il blocco del programma nel caso
             // avvenissero eventuali errori
            String buffer = "";// stringa che funge da tampone per l'inserimento dei contatti
            boolean groupFind = false;// variabine booleana per il controllo [Vedere in seguito alla riga 156]
            boolean firstBoot = true;// variabile booleana per il controllo [Vedere in seguito alla riga 163]
            Long numBuffer = (long) 0;// variabile di comodo per il controllo [vedere in seguito riga 111]
            boolean checkNumPhone = false;
            do {// inizio ciclo do While per l'inserimento dei contatti
                try {// utilizzo un try/Catch per evitare il blocco del programma nel caso
                     // avvenissero eventuali errori
                    System.out.println("Aggiungi Nome");// chiedo all'utente di inserire questo valore
                    buffer = tastiera.readLine();// ricevo il valore e lo assegno alla variabile tampone "Buffer"
                    if (buffer == "") {// Funzione di controllo, se l'utente non inserisce nulla avviene un riempimento
                                       // automatico
                        nome.addElement("N/A");// riempimento automatico
                    } else {// nel caso l'utente inserisce un valore
                        nome.addElement(buffer);// aggiunta al vettore il valore inserito dall'utente
                    }

                    do {
                        checkNumPhone = false;
                        System.out.println("Aggiungi Numero di Telefono");// chiedo all'utenete di inserire questo
                                                                          // valore
                        try {// try/catch per la verifica del corretto inserimento del numero di telefono
                            buffer = tastiera.readLine();// ricevo il valore e lo assegno alla variabile tampone
                                                         // "Buffer"
                            numBuffer = Long.parseLong(buffer);
                            if (buffer == "") {// Funzione di controllo, se l'utente non inserisce nulla avviene un
                                // riempimento automatico
                                telefono.addElement("0");// riempimento automatico con un valore di default
                            } else {// se il valore è corretto

                                try {
                                    telefono.addElement(numBuffer.toString());// aggiungo il valore al vettore
                                } catch (Exception e) {// se riescontro problemi inserisco un valore standard
                                    telefono.addElement("0");// inserimento valore standard
                                    System.out.println(e.getMessage());// visualizzo l'errore
                                    break;
                                }

                            }
                        } catch (NumberFormatException e) {// se il valore inserito non è un numero, catturo l'errore
                            System.out.println("Inserimento errato.");// indico all'utente l'errore
                            checkNumPhone = true;// con questa variabile obbligo l'utente a inserire di nuovo il valore,
                                                 // finche non viene inserito un valore corretto
                        }

                    } while (checkNumPhone);// se viene inserito un valore corretto si esce da ciclo, al contrario si
                                            // ripete finche non viene inserito un valore accettabile

                    boolean checkEmail = false; // dichiarazione variabile di check con valore di default
                    while (!checkEmail) {
                        System.out.println("Aggiungi E-mail");// chiedo all'utenete di inserire questo
                        // valore
                        buffer = tastiera.readLine();// ricevo il valore e lo assegno alla variabile tampone "Buffer"
                        if (buffer == "") {// Funzione di controllo, se l'utente non inserisce nulla avviene un
                                           // riempimento
                                           // automatico
                            email.addElement("N/A");// inserimento automatico con un valore di default
                        } else {
                            for (int i = 0; i < buffer.length(); i++) { // ciclo di scorrimento
                                char a = buffer.charAt(i); // dichiarazione variabile con allocazione
                                                           // del carattere corrispondente all'indice
                                if (a == '@') { // operazione di condizione, controlla se l'utente inserisce una
                                                // chiocciola
                                    checkEmail = true; // se si verifica la condizione, la variabile di check diventa
                                                       // vera
                                    email.addElement(buffer); // aggiunge il contenuto della variabile al vettore email
                                }
                            }
                            if (checkEmail != true) { // operazione di controllo, controlla se il check è vero
                                System.out.println("Inserimento invalido."); // stampa a video l'errore
                            }
                        }
                    }

                    System.out.println("Aggiungi gruppo d'appartenenza");// chiedo all'utente di inserire questo valore
                    buffer = tastiera.readLine();// ricevo il valore e lo assegno alla variabile tampone "Buffer"

                    if (buffer == "") {// Funzione di controllo, se l'utente non inserisce nulla avviene un riempimento
                                       // automatico
                        gruppi.addElement("N/A");// inserimento automatico con un valore di default
                    } else {
                        gruppi.addElement(buffer);// inserimento del valore nel vettore
                    }

                    if (firstBoot == true) {// condizione che verifica se è il primo gruppo inserito all'avvio del
                                            // programma, esso evita un bug nel codice seguente
                        researchGroup.addElement(buffer);// inserimento del valore nel vettore
                        firstBoot = false;// indico al programma che il primo gruppo è stato inserito
                    } else {
                        for (int i = 0; i < researchGroup.size(); i++) {// scorro questo vettore per controllare se il
                                                                        // gruppo inserito esiste
                            if (researchGroup.elementAt(i).compareTo(buffer) == 0) {// controllo se il gruppo inserito è
                                                                                    // gia stato inserito in precedenza
                                groupFind = true;// se il gruppo esiste, il programma esce dal ciclo, ignorando il
                                                 // continuo
                                break;// esco dal ciclo
                            } else {
                                groupFind = false;// se viene identificato un nuovo gruppo, indico al programma la sua
                                                  // esistenza tramite questa variabile
                            }
                        }
                        if (groupFind == false) {// controllo se esiste un nuovo gruppo, se esiste, lo aggiungo
                            researchGroup.addElement(buffer);// aggiungo il gruppo al gruppo di ricerca
                            groupFind = true;// resetto la variabile per evitre l'aggiunta involontaria
                        }
                    }
                    System.out.println("Vuoi aggiungere un altro contatto?");// chiedo all'untente se vuole continuare
                    SceltaAdd = tastiera.readLine().toLowerCase();// ricevo la risposta, la converto in caratteri
                                                                  // minuscoli
                } catch (Exception e) {// se il metodo riscontra un errore lo visualizzo
                    System.out.println(e.getMessage());// visualizzo l'errore
                }
            } while (SceltaAdd.charAt(0) == 's');// se la risposta è uguale a 's' il ciclo ricomincia
        } catch (Exception e) {// se il metodo riscontra un errore lo visualizzo
            System.out.println(e.getMessage());// visualizzo l'errore
        }
        funzioni.sort();
    }

    public void remove() throws IOException {// Dichiaro il metodo remove, per rimuovere i contatti
        try {// utilizzo un try/Catch per evitare il blocco del programma nel caso
             // avvenissero eventuali errori
            funzioni.show();// richiamo la funzione Show per visualizzare i contatti
            System.out.println(
                    System.lineSeparator() + "Inserisci il numero corrispondente al contatto che si vuole rimuovere:");
            // mando a capo e poi chiedo quale contatto rimuovere tramite un indice
            try {// utilizzo un try/Catch per evitare il blocco del programma nel caso
                 // avvenissero eventuali errori
                nome.remove(Integer.parseInt(tastiera.readLine()));// rimuovo gli oggetti dai vettori tramite l'indice
                                                                   // dall'utente digitato
                telefono.remove(Integer.parseInt(tastiera.readLine()));
                email.remove(Integer.parseInt(tastiera.readLine()));
                gruppi.remove(Integer.parseInt(tastiera.readLine()));
            } catch (Exception e) {// se il metodo riscontra un errore lo visualizzo
                System.out.println(e.getMessage());// visualizzo l'errore
                System.out.println("si è verificato un errore, prego riprovare");
            }
        } catch (Exception e) {// se il metodo riscontra un errore lo visualizzo
            System.out.println(e.getMessage());// visualizzo l'errore
        }
    }

    public void show() {// Dischiaro il metodo Show, esso consente di visualizzare i contatti con il
                        // loro indice
        try {// utilizzo un try/Catch per evitare il blocco del programma nel caso
             // avvenissero eventuali errori
            for (int i = 0; i < nome.size(); i++) {// scorro il vettore per avanzare l'indice
                System.out.print(i + "-" + "Nome: " + nome.elementAt(i));// stampo l'indice con gli elementi dei vettori
                System.out.print(" Telefono: " + telefono.elementAt(i));
                System.out.print(" Email: " + email.elementAt(i));
                System.out.println(" Gruppo: " + gruppi.elementAt(i));
            }
        } catch (Exception e) {// se il metodo riscontra un errore lo visualizzo
            System.out.println(e.getMessage());// visualizzo l'errore
        }
    }

    public void find() throws IOException { // dichiarazione del metodo find, consente la ricerca di un contatto tramite
                                            // il nome o gruppi
        try { // utilizzo try/catch per evitare il blocco del programma nel caso avenissero
              // errori
            String sceltaFind; // dichiarazione variabile di tipo stringa
            String nameReserch = ""; // dichiarazione variabile di tipo stringa con valore di default allocato
            boolean checkFind = false; // dichiarazione variabile booleana con valore di default allocato
            System.out.println("Inserisci il metodo di ricerca: "); // chiede all'utente di scegliere il metodo di
                                                                    // ricerca
            System.out.println("N: ricerca per nome.");
            System.out.println("G: ricerca per Gruppo");
            sceltaFind = tastiera.readLine().toLowerCase(); // riceve la scelta e lo alloca in una variabile

            if (sceltaFind.charAt(0) == 'n') { // operazione di controllo, compara il contenuto della varibile ad un
                                               // char
                System.out.println("Inserisci il nome da cercare");//chiedo all'utente il nome del contatto da cercare
                nameReserch = tastiera.readLine();//ricevo il nome e lo assegno a una variabile

                for (int i = 0; i < nome.size(); i++) {//scorro il vettoe
                    if (nome.elementAt(i).compareTo(nameReserch) == 0) {//se trovo il vettore contentente il nome lo stampo
                        System.out.println("Contatto trovato:");//stampo il vettore
                        System.out.print(i + "-" + "Nome: " + nome.elementAt(i));
                        System.out.print(" Telefono: " + telefono.elementAt(i));
                        System.out.print(" Email: " + email.elementAt(i));
                        System.out.println(" Gruppo: " + gruppi.elementAt(i));
                        checkFind = true;//indico che ho svolto la funzione tramite una variabile
                        break;//esco dal ciclo for
                    }
                }

                if (!checkFind) {//se non trovo il contatto indico l'errore
                    System.out.println("Contatto non trovato");
                }
                System.out.println("Ricerca eseguita");//FIXME:da tenere o eliminare?
            } else if (sceltaFind.charAt(0) == 'g') {//se l'utente cerca inserisce la ricerca per gruppo 
                int indexGroupResearch;//variabile di comodo
                System.out.println("Ecco i gruppi presenti");//stampo a video i gruppi esistenti
                for (int i = 0; i < researchGroup.size(); i++) {//scorro il vettore per far visualizzare i gruppi
                    System.out.println(i + " - " + researchGroup.elementAt(i));//stampo l'indice del gruppo con il suo nome
                }

                System.out.println("inserire il numero corrispondete al gruppo da cercare");//chiedo all'utente cosa il gruppo da visualizzare
                indexGroupResearch = Integer.parseInt(tastiera.readLine());//ricevo l'indice e lo converto in int
                funzioni.showGroup(indexGroupResearch);//richiamo la funzione, inviando l'indice a essa
            }
        } catch (Exception e) {//se c'è un erroe lo catturo e lo visualizzo
            System.out.print(e.getMessage());
        }
    }

    public void modify() throws IOException {//dichiaro la funzione modifica
        try {// utilizzo un try/Catch per evitare il blocco del programma nel caso
             // avvenissero eventuali errori
            funzioni.show();//richiamo la funzione show per visualizzare i contatti
            String comodoModify = "";//variabile di comodo per la scelta
            System.out.println("Inserisci il numero corrispondente al contatto che desideri modificare: ");
            int i = Integer.parseInt(tastiera.readLine());//ricevo l'inidce del contatto da modificare
            System.out.println("Inserisci cosa desideri modificare: ");//comunico le operazioni possibili
            System.out.println("N - Modifica nome e cognome.");
            System.out.println("T - Modifica numero di telefono.");
            System.out.println("E - Modifica E-mail.");
            System.out.println("G - Modifica gruppo.");
            String sceltaModify = tastiera.readLine().toLowerCase();//ricevo la scelta
            switch (sceltaModify.charAt(0)) {//verifico che funzione l'utente ha avviato
                case 'n'://modifica il nome
                    System.out.println("Inserisci il nome e il cognome: ");//chiedo all'utente di inserire il nuovo nome
                    comodoModify = tastiera.readLine();//ricevo il valore
                    nome.set(i, comodoModify);//setto il nuovo valore
                    System.out.println("Nome modificato " + nome.elementAt(i));//comunico il nuovo valore
                    break;//esco dal ciclo
                case 't'://modifica del num telefono
                    Long numBuffer;
                    System.out.println("Inserisci il numero di telefono: ");// chiedo all'utente di inserire il nuovo
                                                                            // Num Telefono
                    try {// try/catch per la verifica del corretto inserimento del numero di telefono
                        comodoModify = tastiera.readLine();// ricevo il valore e lo assegno alla variabile tampone
                                                     // "Buffer"
                        numBuffer = Long.parseLong(comodoModify);
                        if (comodoModify == "") {// Funzione di controllo, se l'utente non inserisce nulla avviene un
                            // riempimento automatico
                            telefono.addElement("0");// riempimento automatico con un valore di default
                        } else {// se il valore è corretto

                            try {
                                telefono.set(i, numBuffer.toString());// aggiungo il valore al vettore
                                System.out.println("Numero di telefono modificato " + telefono.elementAt(i));// comunico
                                                                                                             // il nuovo
                                                                                                             // num di
                                                                                                             // telefono
                            } catch (Exception e) {// se riescontro problemi inserisco un valore standard
                                telefono.set(i, "0");// inserimento valore standard
                                System.out.println(e.getMessage());// visualizzo l'errore
                                break;
                            }

                        }
                    } catch (NumberFormatException e) {// se il valore inserito non è un numero, catturo l'errore
                        System.out.println("Inserimento errato.");// indico all'utente l'errore
                    }
                    break;
                case 'e':
                    System.out.println("Inserisci l'E-mail: ");//chiedo di inserire la nuova email
                    comodoModify = tastiera.readLine();//ricevo la nuova email
                    // comunico la nuova email
                    if (comodoModify == "") {// Funzione di controllo, se l'utente non inserisce nulla avviene un
                                       // riempimento
                                       // automatico
                        email.set(i, "N/A");// inserimento automatico con un valore di default
                    } else {
                        boolean checkEmail = false;
                        for (int k = 0; k < comodoModify.length(); k++) { // ciclo di scorrimento
                            char a = comodoModify.charAt(k); // dichiarazione variabile con allocazione
                                                       // del carattere corrispondente all'indice
                            if (a == '@') { // operazione di condizione, controlla se l'utente inserisce una
                                            // chiocciola
                                checkEmail = true; // se si verifica la condizione, la variabile di check diventa
                                                   // vera
                                email.set(i, comodoModify); // aggiunge il contenuto della variabile al vettore email
                                System.out.println("E-mail modificata " + email.elementAt(i));
                            }
                        }
                        if (checkEmail != true) { // operazione di controllo, controlla se il check è vero
                            System.out.println("Inserimento invalido."); // stampa a video l'errore
                        }
                    }
                    break;// esco dallo switch
                case 'g':
                    System.out.println("Inserisci il gruppo: ");//chiedo il nuovo gruppo
                    comodoModify = tastiera.readLine();//ricevo il nuovo gruppo
                    gruppi.set(i, comodoModify);// setto il nuovo gruppo
                    System.out.println("Gruppo modificato " + gruppi.elementAt(i));// comunico il nuovo gruppo
                    break;// esco dallo switch
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public void showGroup(int index) {
        try {// utilizzo un try/Catch per evitare il blocco del programma nel caso
             // avvenissero eventuali errori
            String groupShowReasearch = researchGroup.elementAt(index);//prendo il valore del vettore indicato dall'indice
            for (int i = 0; i < gruppi.size(); i++) {//scorro i contatti 
                if (gruppi.elementAt(i).compareTo(groupShowReasearch) == 0) {//se il contatto è presente nel gruppo di riceca viene visualizzato
                    System.out.print(i + "-" + "Nome: " + nome.elementAt(i));
                    System.out.print(" Telefono: " + telefono.elementAt(i));
                    System.out.print(" Email: " + email.elementAt(i));
                    System.out.println(" Gruppo: " + gruppi.elementAt(i));
                }
            }
        } catch (Exception e) {//se avviene qualche errore lo catturo per evitare la chisura del programma
            System.out.print(e.getMessage());//stampo l'errore
        }
    }
}
