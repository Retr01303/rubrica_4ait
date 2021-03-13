<<<<<<< HEAD

/*
=======
//FIXME: aggiungere un default ad ogni costrutto switch/case e controllare inserimenti nel find e modify
//FIXME: errore se inserisci un indice maggiore al numero di gruppi(righe 410-420)
/*

>>>>>>> 47c745c51d0b627e907c8b960a8036bd07d07332
Compito di realtà Informatica 05/03/21 4A IT
Matteo Monticciolo Gabriele Miciletto
*/
import java.util.*;//Importo le librerie
import java.io.*;

public class App {

    public static InputStreamReader input = new InputStreamReader(System.in);// creo l'oggetto input e il buffer per
                                                                             // ricevere i dati dalla tastiera
    public static BufferedReader tastiera = new BufferedReader(input);
    public static rubrica funzioni = new rubrica();// dichiaro l'oggetto funzioni appartenete alla classe rubrica
    public static String scelta;// variabile per la ricezione della scelte del menu
    public static String x = "x";

    public static void main(String[] args) throws Exception {
        do {
            try {// utilizzo un try/Catch per evitare il blocco del programma nel caso
                 // avvenissero eventuali errori
                 // ciclo per la gestione del menù

                System.out.println("Scegli una funzione:");// espongo le funzioni del menu
                System.out.println("A - Aggiungi contatto");
                System.out.println("R - Rimuovi contatto");
                System.out.println("S - Visualizza contatti");
                System.out.println("F - Cerca un contatto");
                System.out.println("M - Modifica un contatto");
                System.out.println("X - Esci");
                scelta = tastiera.readLine().toLowerCase();// ricevo la scelta dalla tastiera, converto la
                                                           // stringa in lettere minuscole, prendo un singolo
                                                           // char dell'indice 0 della stringa ricevuta
                if (scelta.compareTo("") == 1) {// verifico che l'inserimeto sia valido
                    switch (scelta.trim().charAt(0)) {// confronto la stringa con lo switch
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
                    case 'x':
                        System.out.println("Chiudo il programma");// chiudo il programma
                        break;
                    default:// se la scelta non viene identificata stampo una stringa di errore
                        System.out.println("Inserimento non valido." + System.lineSeparator());// stampo una stringa
                                                                                               // di errore con un
                                                                                               // ritorno a capo
                    }
                } else {
                    System.out.println("Inserimento non valido." + System.lineSeparator());
                }

            } catch (Exception e) {// in caso di eventuale errore lo catturo e stampo una stringa con il messaggio
                                   // di errore, dichiaro un oggetto di Eccezione con nome e
                System.out.println(e.getMessage());// e.GetMessage() lo uso per ritornare a video l'errore
            }
        } while (scelta.compareTo(x) != 0);
    }
}

class rubrica extends App {// dichiaro la classe rubrica contenente tutte le funzioni di essa, la scelta di
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

    private void scambiaTutti(int index1, int index2) {// dichiaro il metodo per lo scambio di due indici in tutti i
                                                       // vettori
        scambia(index1, index2, nome);// richiamo il metodo comunicando il gli indici e il vettore da scambiare
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

                    checkNumPhone = true;
                    while (checkNumPhone) {
                        checkNumPhone = false;
                        // resetto la variabile
                        System.out.println("Aggiungi Numero di Telefono");// chiedo all'utenete di inserire questo
                                                                          // valore
                        try {// try/catch per la verifica del corretto inserimento del numero di telefono
                            buffer = tastiera.readLine();// ricevo il valore e lo assegno alla variabile tampone
                                                         // "Buffer"
                            if (buffer == "") {// Funzione di controllo, se l'utente non inserisce nulla avviene un
                                // riempimento automatico
                                telefono.addElement("00000");// riempimento automatico con un valore di default
                                break;
                            } else {// se il valore è corretto

                                try {
                                    numBuffer = Long.parseLong(buffer);
                                    telefono.addElement(numBuffer.toString());// aggiungo il valore al vettore
                                    checkNumPhone = false;
                                } catch (Exception e) {// se riescontro problemi inserisco un valore standard
                                    System.out.println(e.getMessage() + "Valore inserito errato");// visualizzo l'errore
                                    checkNumPhone = true;
                                }

                            }
                        } catch (NumberFormatException e) {// se il valore inserito non è un numero, catturo l'errore
                            System.out.println("Inserimento errato.");// indico all'utente l'errore
                            checkNumPhone = true;// con questa variabile obbligo l'utente a inserire di nuovo il valore,
                                                 // finche non viene inserito un valore corretto
                        }

                    } // se viene inserito un valore corretto si esce da ciclo, al contrario si
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
                            break;
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
                    } 
                    else {
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
                    char var = 'n';
<<<<<<< HEAD
                    do {
                        System.out.println("Vuoi aggiungere un altro contatto?");// chiedo all'untente se vuole
                                                                                 // continuare
                        SceltaAdd = tastiera.readLine().toLowerCase(); // ricevo la risposta, la converto in caratteri
                                                                       // minuscoli
                        var = SceltaAdd.charAt(0);
                        if (var != 's' && var != 'n') {
                            System.out.println("Inserimento invalido. Inserisci si o no:");
                        }
                    } while (var != 's' && var != 'n');
=======
                    do{
                        System.out.println("Vuoi aggiungere un altro contatto?");// chiedo all'untente se vuole continuare
                        SceltaAdd = tastiera.readLine().toLowerCase(); // ricevo la risposta, la converto in caratteri minuscoli
                        var = SceltaAdd.charAt(0);
                        if(var != 's' && var != 'n'){
                            System.out.println("Inserimento invalido. Inserisci si o no:");
                        }
                    }while(var != 's' && var != 'n');                                      
>>>>>>> 47c745c51d0b627e907c8b960a8036bd07d07332
                } catch (Exception e) {// se il metodo riscontra un errore lo visualizzo
                    System.out.println(e.getMessage());// visualizzo l'errore
                }
            } while (SceltaAdd.charAt(0) == 's');// se la risposta è uguale a 's' il ciclo ricomincia
        } catch (Exception e) {// se il metodo riscontra un errore lo visualizzo
            System.out.println(e.getMessage());// visualizzo l'errore
        }
        funzioni.sort();// richiamo il metodo per l'ordinamento dalla rubrica
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

                System.out.println("Digitare:");
                System.out.println("M: per la rimozione multipla dei contatti");
                System.out.println("S: per la singola rimozione di un contatto");
                String SceltaRemove = tastiera.readLine().toLowerCase();// ricevo
                int firstRemove;// dichiaro le variabili di range
                int lastRemove;
                switch (SceltaRemove.charAt(0)) {// confronto la scelta
                case 'm':// rimozione multipla
                    boolean checkIndexRemove = false;// dichiaro la variabile per il check per la rimozione
                    do {

                        System.out.println(// indico i all'utente come inserire i dati
                                "inserire l'indice iniziale e quello finale separati da un - per la rimozione multipla ");

                        String[] partsRemove = tastiera.readLine().split("-");// dichiaro un vettore di comodo per
                                                                              // lo split dell'input
                        firstRemove = Integer.parseInt(partsRemove[0].trim());// alloco i valori splittati e li
                                                                              // "pulisco"
                        lastRemove = Integer.parseInt(partsRemove[1].trim());

                        if (firstRemove <= nome.size() && lastRemove <= nome.size()) {// controllo se i valori non
                                                                                      // superano la dimensione
                                                                                      // massima del vettore
                            checkIndexRemove = false;// indico che i valori sono corretti
                        } else {
                            checkIndexRemove = true;// indico che i valori sono errati e ripeto il ciclo
                        }

                    } while (checkIndexRemove);// ripeto il ciclo se i valori sono errati

                    if (!checkIndexRemove) {// se i valori sono corretti eseguo il Multiple Remove
                        funzioni.multipleRemove(firstRemove, lastRemove);// eseguo la funzione con i parametri
                                                                         // inseriti dall'utente
                    }

                    break;// esco dal ciclo
                case 's':// scelta per la rimozione singola
                    System.out.println("Inserire l'indice del contatto da rimuovere");// indico all'utente cosa deve
                                                                                      // inserire
                    int singleRemove = Integer.parseInt(tastiera.readLine());// ricevo il valore e lo alloco nella
                                                                             // variabile di comodo
                    funzioni.removeSingleContact(singleRemove);// richiamo il metodo passando l'indice da rimuovere
                    break;// esco dal ciclo
                default:
                    System.out.println("Inserimento non valido." + System.lineSeparator());
                    
                }

            } catch (Exception e) {// se il metodo riscontra un errore lo visualizzo
                System.out.println(e.getMessage());// visualizzo l'errore
                System.out.println("Si è verificato un errore, prego riprovare");
            }
        } catch (Exception e) {// se il metodo riscontra un errore lo visualizzo
            System.out.println(e.getMessage());// visualizzo l'errore
        }
        funzioni.sort();
    }

    private void multipleRemove(int indexRemoveStart, int indexRemoveStop) throws IOException {// metodo per la
                                                                                               // rimozione multipla dei
                                                                                               // contatti
        int indexCalculatePosition = indexRemoveStop - indexRemoveStart;// calcolo il numero di contatti da eliminare
        for (int i = 0; i <= indexCalculatePosition; i++) {// scorro i contatti X volte quanti Y contatti devo eliminare
            funzioni.removeSingleContact(indexRemoveStart);// richiamo la funzione per la rimozione del contatto
                                                           // inviando l'indice da eliminare
        }
    }

    private void removeSingleContact(int removeIndex) throws IOException {
        try {
            nome.remove(removeIndex);// rimuovo gli oggetti dai vettori tramite l'indice dall'utente digitato
            telefono.remove(removeIndex);
            email.remove(removeIndex);
            gruppi.remove(removeIndex);
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
            System.out.println("L: ricerca per la prima lettera");
            sceltaFind = tastiera.readLine().toLowerCase(); // riceve la scelta e lo alloca in una variabile

            // operazione di controllo, compara il contenuto della varibile ad un
            // char
            switch (sceltaFind.charAt(0)) {
            case 'n':
                System.out.println("Inserisci il nome da cercare");// chiedo all'utente il nome del contatto da
                                                                   // cercare
                nameReserch = tastiera.readLine();// ricevo il nome e lo assegno ad una variabile

                for (int i = 0; i < nome.size(); i++) {// scorro il vettoe
                    if (nome.elementAt(i).trim().compareTo(nameReserch) == 0) {// se trovo il vettore contentente il
                                                                               // nome lo
                        // stampo
                        System.out.println("Contatto trovato:");// stampo il vettore
                        System.out.print(i + "-" + "Nome: " + nome.elementAt(i));
                        System.out.print(" Telefono: " + telefono.elementAt(i));
                        System.out.print(" Email: " + email.elementAt(i));
                        System.out.println(" Gruppo: " + gruppi.elementAt(i));
                        checkFind = true;// indico che ho svolto la funzione tramite una variabile
                        break;// esco dal ciclo for
                    }
                }
                if (!checkFind) {// se non trovo il contatto indico l'errore
                    System.out.println("Contatto non trovato");
                    break;
                }
                break;
            case 'g':
            boolean checkRepeat = false;
                // se l'utente cerca inserisce la ricerca per gruppo
                int indexGroupResearch;// variabile di comodo
                System.out.println("Ecco i gruppi presenti:");// stampo a video i gruppi esistenti

                for (int i = 0; i < researchGroup.size(); i++) {// scorro il vettore per far visualizzare i
                                                                // gruppi
<<<<<<< HEAD
                    System.out.println(i + " - " + researchGroup.elementAt(i));// stampo l'indice del gruppo con il suo
                                                                               // nome
                }

                do {
                    System.out.println(
                            "Inserire il numero corrispondete al gruppo da cercare:"); /*
                                                                                        * chiedo all'utente cosa il
                                                                                        * gruppo da visualizzare
                                                                                        */
                    indexGroupResearch = Integer.parseInt(tastiera.readLine());// ricevo l'indice e lo converto in int

                    if (indexGroupResearch > researchGroup.size() - 1) {
                        System.out.println("Errore, il valore inserito supera il numero di contatti");
                        checkRepeat = false;
                    }
                    else{
                        checkRepeat = true;
                    }
                } while (!checkRepeat);
=======
                    System.out.println(i + " - " + researchGroup.elementAt(i));// stampo l'indice del gruppo con il suo nome
                }

                do{
                    System.out.println("Inserire il numero corrispondete al gruppo da cercare:"); /* chiedo all'utente
                                                                                              cosa il
                                                                                              gruppo da
                                                                                              visualizzare */
                    indexGroupResearch = Integer.parseInt(tastiera.readLine());// ricevo l'indice e lo converto in int
                
                    if(indexGroupResearch > gruppi.size()){
                        System.out.println("Errore, il valore inserito supera il numero di contatti"); 
                    }
                }while(indexGroupResearch > gruppi.size());
>>>>>>> 47c745c51d0b627e907c8b960a8036bd07d07332
                funzioni.showGroup(indexGroupResearch);// richiamo la funzione, inviando l'indice a essa
                break;

            case 'l':
                System.out.println("Inserisci la lettra iniziale da cercare:");
                nameReserch = tastiera.readLine();
                for (int i = 0; i < nome.size(); i++) {
                    if (nome.elementAt(i).charAt(0) == nameReserch.charAt(0)) {
                        System.out.print(i + "-" + "Nome: " + nome.elementAt(i));// stampo l'indice con gli elementi
                                                                                 // dei
                                                                                 // vettori
                        System.out.print(" Telefono: " + telefono.elementAt(i));
                        System.out.print(" Email: " + email.elementAt(i));
                        System.out.println(" Gruppo: " + gruppi.elementAt(i));
                    } else {
                        System.out.println("Nessuna corrispondenza con la lettera inserita, riprova.");
                    }
                    else{
                        System.out.println("Nessuna corrispondenza con la lettera inserita, riprova.");
                    }
                }
                break;

            default:// se la scelta non viene identificata stampo una stringa di errore
                System.out.println("Inserimento non valido." + System.lineSeparator());// stampo una stringa
                                                                                       // di errore con un
                                                                                       // ritorno a capo
            }

        } catch (Exception e) {// se c'è un erroe lo catturo e lo visualizzo
            System.out.print(e.getMessage());
        }
    }

    private void showGroup(int index) {
        try {// utilizzo un try/Catch per evitare il blocco del programma nel caso
             // avvenissero eventuali errori
            String groupShowReasearch = researchGroup.elementAt(index);// prendo il valore del vettore indicato
                                                                       // dall'indice
            for (int i = 0; i < gruppi.size(); i++) {// scorro i contatti
                if (gruppi.elementAt(i).compareTo(groupShowReasearch) == 0) {// se il contatto è presente nel gruppo di
                                                                             // riceca viene visualizzato
                    System.out.print(i + "-" + "Nome: " + nome.elementAt(i));
                    System.out.print(" Telefono: " + telefono.elementAt(i));
                    System.out.print(" Email: " + email.elementAt(i));
                    System.out.println(" Gruppo: " + gruppi.elementAt(i));
                }
            }
        } catch (Exception e) {// se avviene qualche errore lo catturo per evitare la chisura del programma
            System.out.print(e.getMessage());// stampo l'errore
        }
    }

    public void modify() throws IOException {// dichiaro la funzione modifica
        try {// utilizzo un try/Catch per evitare il blocco del programma nel caso
             // avvenissero eventuali errori
            funzioni.show();// richiamo la funzione show per visualizzare i contatti
            String comodoModify = "";// variabile di comodo per la scelta
            System.out.println("Inserisci il numero corrispondente al contatto che desideri modificare: ");
            int i = Integer.parseInt(tastiera.readLine());// ricevo l'inidce del contatto da modificare
            System.out.println("Inserisci cosa desideri modificare: ");// comunico le operazioni possibili
            System.out.println("N - Modifica nome e cognome.");
            System.out.println("T - Modifica numero di telefono.");
            System.out.println("E - Modifica E-mail.");
            System.out.println("G - Modifica gruppo.");
            String sceltaModify = tastiera.readLine().toLowerCase();// ricevo la scelta
            switch (sceltaModify.charAt(0)) {// verifico che funzione l'utente ha avviato
            case 'n':// modifica il nome
                System.out.println("Inserisci il nome e il cognome: ");// chiedo all'utente di inserire il nuovo
                                                                       // nome
                comodoModify = tastiera.readLine();// ricevo il valore
                nome.set(i, comodoModify);// setto il nuovo valore
                System.out.println("Nome modificato " + nome.elementAt(i));// comunico il nuovo valore
                break;// esco dal ciclo
            case 't':// modifica del num telefono
                Long numBuffer;
                System.out.println("Inserisci il numero di telefono: ");// chiedo all'utente di inserire il nuovo
                                                                        // Num Telefono
                try {// try/catch per la verifica del corretto inserimento del numero di telefono
                    comodoModify = tastiera.readLine();// ricevo il valore e lo assegno alla variabile tampone
                    // "Buffer"
                    if (comodoModify == "") {// Funzione di controllo, se l'utente non inserisce nulla avviene un
                        // riempimento automatico
                        telefono.addElement("00000");// riempimento automatico con un valore di default
                        break;
                    } else {// se il valore è corretto

                        try {
                            numBuffer = Long.parseLong(comodoModify);
                            telefono.set(i, numBuffer.toString());// aggiungo il valore al vettore
                            System.out.println("Numero di telefono modificato " + telefono.elementAt(i));
                            // comunico il nuovo num di telefono
                        } catch (Exception e) {// se riescontro problemi inserisco un valore standard
                            telefono.set(i, "00000");// inserimento valore standard
                            System.out.println(e.getMessage());// visualizzo l'errore
                            break;
                        }

                    }
                } catch (Exception e) {// se il valore inserito non è un numero, catturo l'errore
                    System.out.println("Inserimento errato.");// indico all'utente l'errore
                }
                break;
            case 'e':
                System.out.println("Inserisci l'E-mail: ");// chiedo di inserire la nuova email
                comodoModify = tastiera.readLine();// ricevo la nuova email
                // comunico la nuova email
                if (comodoModify == "") {// Funzione di controllo, se l'utente non inserisce nulla avviene un
                    // riempimento automatico
                    email.set(i, "N/A");// inserimento automatico con un valore di default
                    break;
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
                System.out.println("Inserisci il gruppo: ");// chiedo il nuovo gruppo
                comodoModify = tastiera.readLine();// ricevo il nuovo gruppo
                if (comodoModify == "") {
                    gruppi.set(i, "N/A");
                }
                gruppi.set(i, comodoModify);// setto il nuovo gruppo
                System.out.println("Gruppo modificato " + gruppi.elementAt(i));// comunico il nuovo gruppo
                break;// esco dallo switch

            default:// se la scelta non viene identificata stampo una stringa di errore
                System.out.println("Inserimento non valido." + System.lineSeparator());// stampo una stringa
                                                                                       // di errore con un
                                                                                       // ritorno a capo
            }
            
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        funzioni.sort();
    }

}