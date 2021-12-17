# Drone 2.0 | Diario di lavoro
##### Michea Colautti, Alessandro Aloise, Gianni Grasso
### Centro Professionale Trevano, 09.12.2021

## Lavori svolti


|Orario        |Lavoro svolto                                                      |Eseguito da         |
|--------------|-------------------------------------------------------------------|--------------------|
|10:50 - 15:00 | Fix acquisizione ed esecuzione sequenze                           | Gianni             |
|10:50 - 15:00 | Fix Live e refactor Toolbar                                       | Michea             |
|10:50 - 16:30 | Implementazione Leap motion                                       | Alessandro         |
|15:00 - 16:00 | Test e calibrazione sequenze                                      | Gianni, Michea     |



## Problemi riscontrati e soluzioni adottate

1. Nello sviluppo della logica delle sequenze abbiamo avuti molti problemi, primo tra i quali l'esecuzione troppo affrettata delle sequenze. Infatti usando la nostra logica, un ciclo `for` che aggiunge i comandi alle sequenze, i movimenti del drone non sono fluidi, ma una serie di scatti e turbolenze. Per risolverlo siamo stati costretti ad aggiungere un `Thread.sleep`. abbiamo dovuto calibrarlo, per avere dei movimenti il più fluido possibile, sia nelle sequenze registrate che nei comandi normali. 
2. Un altro piccolo problema è stato riuscire a trovare un modo per fare funzionare il tutto senza istanziare un istanza di `Control` nella classe `Sequenze`. Per risolvere abbiamo seguito il modello di architettura usato per il resto delle classi.
3. Nello sviluppo del `Leap motion` abbiamo avuto innumerevoli problemi, tutti riconducibili ad un unico problema alla fine. Fondamentalmente testando il codice del progetto ci siamo accorti che non c'era alcun modo di fari funzionare l'invio dei comandi. In realtà gli stessi non erano nemmeno captati dal `Leap`, pareva che esso non fosse nemmeno rilevato dal nostro programma. Abbiamo provato a semplificare il codice, seguire la guida online e scaricare i programmi di test forniti dai produttori del `Leap`, ma senza alcun effetto. Abbiamo quindi interpellato il docente e, dopo molte prove, siamo riusciti a far funzionare il programma di test sul vecchio Mac Mini del docente. La possibile soluzione potrebbe quindi essere la versione di Java. Infatti anche se l'errore era concentrato sulle librerie del `Leap`, non siamo comunque riusciti a correggere l'errore, solo cambiando versione di Java abbiamo corretto il tutto.


Ecco quindi le soluzioni riassunte:

>1. Calibrazione `Thread.sleep`
>3. Soluzione non confermata.
##  Punto della situazione rispetto alla pianificazione
Siamo leggermente in ritardo, ma sistemando il problema del `Leap` dovremmo essere più o meno in pari.

## Programma di massima per la prossima giornata di lavoro
(A patto che tutti siano presenti)
1. Finire Leap Motion.
2. Finire la live.
3. Gantt consuntivo.

