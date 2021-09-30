# Drone 2.0 | Diario di lavoro
##### Michea Colautti, Alessandro Aloise, Gianni Grasso
### Centro Professionale Trevano, 30.09.2021

## Lavori svolti


|Orario        |Lavoro svolto                                                   |Eseguito da         |
|--------------|----------------------------------------------------------------|--------------------|
|10:50 - 12:30 | Sviluppo modello di test e inizio documentazione               | Alessandro, Gianni |
|10:50 - 12:30 | Assistito introduzione prof. Montalbetti e verifica modulo 306  | Michea             |
|13:15 - 14:00 | Revisione della documentazione                                 | Michea             |
|13:15 - 14:00 | Revisione schema delle classi e della struttura del codice     | Alessandro, Gianni |
|14:00 - 14:15 | Revisione di tutto il lavoro svolto                            | Tutti              |
|14:15 - 14:45 | Test di volo e discussione su cosa andrà migliorato            | Tutti              |
|15:00 - 15:45 | Revisione personale package graphics e icone GUI               | Michea             |
|15:00 - 15:30 | Test di volo con il drone                                      | Alessandro, Gianni |
|15:45 - 16:30 | Prime prove in progetto di prova con icona del nuovo altimetro | Michea             |
|15:30 - 16:30 | Ricerca progetti 3D per eliche + para eliche                   | Alessandro         |
|15:30 - 16:30 | Refactor struttura codice e struttura icone progettazione      | Gianni             |





##  Problemi riscontrati e soluzioni adottate

Questa mattina Michea ha dovuto recuperare il test, per questo è stato impegnato tutta la mattina, in quanto prima del test ha dovuto assistere anche all'introduzione alla lezione del Prof. Montalbetti.

1. All'inizio della giornata ci siamo accorti che non sapevamo bene cosa funzionasse e cosa no. Infatti quando abbiamo fatto i test la batteria del drone era scarica, indi per cui alcune funzioni erano disabilitate. Abbiamo quindi pensato, e in seguito realizzato, dei nuovi test.
2. Durante i test abbiamo inavvertitamente spaccato un elica, per questo siamo dovuti ricorrere all'uso delle vecchie eliche. Anche per questo abbiamo deciso di provare a stampare qualche parte del drone, come eliche e para-eliche, per vedere se possiamo ovviare a questo problema prima dell'arrivo di nuove eliche.
3. Sempre durante i vecchi test sono emersi alcuni problemi. Il sensore `Leap Motion` è troppo sensibile ai movimenti, inoltre la grafica non sempre funziona come dovrebbe. a volte connesso il sensore la grafica smette di rispondere.

Ecco quindi le soluzioni riassunte:
> 1. Sviluppo di un sistema di test da mettere in pratica
> 2. Ricerca progetti per lo stampo di parti esterne del drone
> 3. Refactor del codice per il sensore `Leap Motion` e della grafica.



##  Punto della situazione rispetto alla pianificazione

Siamo nei tempi previsti.

## Programma di massima per la prossima giornata di lavoro

Iniziare seriamente con il refactor, basandoci sui nostri test.
