# Drone 2.0 | Diario di lavoro
##### Michea Colautti, Alessandro Aloise, Gianni Grasso
### Centro Professionale Trevano, 02.12.2021

## Lavori svolti


|Orario        |Lavoro svolto                                                      |Eseguito da         |
|--------------|-------------------------------------------------------------------|--------------------|
|10:50 - 16:30 | Documentazione                                                    | Gianni             |
|10:50 - 14:30 | Documentazione (interrotto per tampone e malessere)               | Alessandro         |
|10:50 - 12:20 | Registrazione comandi                                             | Gianni             |
|10:50 - 13:15 | Implementazione AnalyticsFrame                                    | Michea             |
|13:15 - 16:30 | Live                                                              | Michea             |





## Problemi riscontrati e soluzioni adottate

1. Oggi in classe era presente solo Michea. Infatti Gianni e Alessandro erano stati posti in stato di quarantena assieme al resto della classe. Michea invece facendo parte di un altra classe non era toccato da questa situazione. Per questo né Alessandro né Gianni hanno potuto testare codice, per questo si sono dedicati alla documentazione. 

2. Michea ha potuto fare la live, però con molti problemi. infatti una volta preso il codice dell'anno scorso e migliorato un poco, gli script che dovevano eseguire la live non funzionavano. Infatti per far partire la live usiamo uno script `sh` per Mac e rispettivamente `bat` per Windows; in modo da eseguire il giusto comando con `Node.js` per aprire un `socket` che trasmetta la live sulla porta `3000` all'indirizzo `localhost`. Con l'aiuto del professore abbiamo modificato lo script `sh` e i percorsi, più che altro per una questione estetica e logica. Il problema finale era che, anche se lo script veniva eseguito, la live non veniva aperta. Modificando poi il percorso di `ffmpeg` nel file aperto con il comando `node` la live ha finalmente funzionato.
3. Ora però il problema è che `ffmpeg` deve essere installato sotto una precisa posizione, e questo non va bene per la nostra app. Dobbiamo trovare una soluzione.


Ecco quindi le soluzioni riassunte:

> 1. Modifica percorso `ffmpeg`.
> 2. Non ancora trovata.


##  Punto della situazione rispetto alla pianificazione

Data la situazione particolare ci siamo portati avanti con la doc, ma siamo comunque leggermente in ritardo.

## Programma di massima per la prossima giornata di lavoro
(A patto che tutti siano presenti)
1. Finire Leap Motion.
2. Finire la live.
3. Fix del problema con la sovra-scrizione.
4. Esecuzione sequenze.
6. Documentare.

