# Drone 2.0 | Diario di lavoro
##### Michea Colautti, Alessandro Aloise, Gianni Grasso
### Centro Professionale Trevano, 18.11.2021

## Lavori svolti


|Orario        |Lavoro svolto                                                      |Eseguito da         |
|--------------|-------------------------------------------------------------------|--------------------|
|10:50 - 13:15 | Key Dispatcher                                                    | Gianni             |
|10:50 - 11:10 | Test sulla grafica e fix problemi                                 | Michea             |
|11:10 - 14:20 | Key Dispatcher assieme a Gianni                                   | Michea             |
|10:50 - 13:15 | Implementazione Leap Motion                                       | Alessandro         |
|13:15 - 14:45 | Implementazione Thread sleep                                      | Tutti              |
|15:00 - 16:30 | Limitazione dei pacchetti                                         | Tutti              |


## Problemi riscontrati e soluzioni adottate

Oggi abbiamo avuto molti problemi:

1. Finendo il Key Dispatcher, iniziato alla fine della scorsa lezione abbiamo avuto alcuni problemi di implementazione, ed abbiamo dovuto provare in molti modi visto che non sapevamo come funzionava visto che l'idea data ci è stata fornita dal sore. Ma alla fine ho risolto (andava istanziato nel main frame), In questo modo si è risolto il problema del focus. 
2. Abbiamo poi cercato un modo per evitare il delay della grafica, un altro dei problemi della scorsa volta, alla fine la soluzione trovata è stata quella si diminuire il tempo impostato nel `Thread.sleep()` da 50ms a 1ms. Abbastanza per dare il tempo alla grafica di aggiornarsi. 
3. Infine abbiamo avuto molti problemi con il Key Dispatcher, infatti come il drone rispondeva in ritardo ai comandi, o meglio: quando molti comandi venivano eseguiti in sequenza si `stashavano` nel drone e lui li eseguiva con un certo ritardo. Questo problema persisteva solo su Windows, infatti a quanto pare MacOS ha di default una funzione che rallenta gli input se inseriti tenendo premuto un tasto. La soluzione è stata quindi limitare l'invio di pacchetti identici al drone.
4. L'ultimo problema è stato quello con il codice del `Leap Motion`, infatti il codice lo aveva fatto Samuele Ganci, e quindi abbiamo dovuto capire e adattare il codice. Ci dovremmo lavorare ancora in futu

##  Punto della situazione rispetto alla pianificazione
Con questi problemi abbiamo guadagnato tempo su alcune parti, ma ne abbiamo perso molto su altri. Quindi siamo leggermente in ritardo.

## Programma di massima per la prossima giornata di lavoro
1. Sviluppo della barra inferiore.
2. Sviluppo del Leap Motion.
3. Correzione funzione di esecuzione e registrazione sequenze.

