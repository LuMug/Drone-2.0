# Drone 2.0 | Diario di lavoro
##### Michea Colautti, Alessandro Aloise, Gianni Grasso
### Centro Professionale Trevano, 11.11.2021

## Lavori svolti


|Orario        |Lavoro svolto                                                      |Eseguito da         |
|--------------|-------------------------------------------------------------------|--------------------|
|10:50 - 11:15 | Prove di volo drone                                               | Tutti              |
|11:15 - 16:30 | Gestione focus                                                    | Gianni             |
|11:15 - 12:30 | Inizio implementazione status                                     | Alessandro         |
|11:15 - 12:30 | Implementazione lancetta su altimetro                             | Michea             |
|13:15 - 14:00 | Gestione passaggio dati da Status a Grafica con code              | Alessandro, Michea |
|14:00 - 14:30 | Prove di volo con nuova coda                                      | Alessandro, Michea |
|14:30 - 16:30 | Implementazione Status                                            | Alessandro         |
|14:30 - 16:30 | Sviluppo codice di riconoscimento stringhe coda e fix bug         | Michea             |




##  Problemi riscontrati e soluzioni adottate

1. Nelle nostre prime prove di volo il drone non volava, abbiamo aggiunto un `while (true)` alla `Thread` che si occupa del volo e abbiamo risolto,
2. In parallelo, Alessandro e Michea, hanno sviluppato la classe `Status` e le sue `Queue`. Quella che interessava Michea era quella contenente il flusso di informazioni prodotto dal drone, nello specifico le info sulla posizione (`pitch`,`roll`,`yaw`, e `altitude`), fondamentali per il funzionamento della grafica.
3. Qui si spiega la logica di riconoscimento della stringa, infatti `Status` istanzia una `Queue` a cui `graphics.MainPanel` fa riferimento. In questa grafica vengono inseriti una serie di dati, con un identificativo della famiglia del dato, es: `yaw`, o `alt`, che permette poi di decidere dove inviare il dato.
4. Nell'implementazione del `KeyListener` ci siamo accorti che, se si preme su un altro elemento del frame, il focus viene perso. A questo proposito non siamo riusciti a trovare una soluzione, ma non ci siamo riusciti inizialmente. Abbiamo poi parlato con il professsor Petrini e lui ha proposto una soluzione usando un `KeyDispatcher`, una classe che catturasse i tasti ad un livello superiore di Java. Ne parliamo meglio nel prossimo diario.
5. Un altro problema nello sviluppo del codice si è presentato quando ci siamo accorti che il drone rispondeva in ritardo ai comandi, o meglio: quando molti comandi venivano eseguiti in sequenza si `stashavano` nel drone e lui li eseguiva con un certo ritardo. La soluzione è in elaborazione.


Ecco quindi le soluzioni riassunte.

>1. Uso del `while (true)`.
>2. Introduzione `Queue`.
>4. Soluzione la prossima settimana.

##  Punto della situazione rispetto alla pianificazione
Al passo con i tempi.

## Programma di massima per la prossima giornata di lavoro

1. Sviluppo `KeyDispatcher`.
2. Sviluppo `Leap Motion`.



