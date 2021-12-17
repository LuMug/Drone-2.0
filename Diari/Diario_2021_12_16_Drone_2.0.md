# Drone 2.0 | Diario di lavoro
##### Michea Colautti, Alessandro Aloise, Gianni Grasso
### Centro Professionale Trevano, 16.12.2021

## Lavori svolti


|Orario        |Lavoro svolto                                                      |Eseguito da         |
|--------------|-------------------------------------------------------------------|--------------------|
|10:50 - 12:20 | Sviluppo Live                                                     | Michea             |
|10:50 - 12:20 | Fix scroll bar                                                    | Gianni             |
|10:50 - 12:20 | Sviluppo live e script                                            | Gianni, Michea     |
|10:50 - 16:30 | LeapMotion                                                        | Alessandro         |
|15:30 - 16:30 | Gantt                                                             | Gianni, Michea     |





## Problemi riscontrati e soluzioni adottate

1. Oggi Alessandro ha dedicato tutta la giornata al `Leap Motion`: come l'altra volta abbiamo avuto problemi su Windows perché non veniva riconosciuto. Invece su Mac, con la versione giusta di Java, siamo riusciti a correggere l'errore. Dopo svariati tentativi abbiamo pensato di chiedere al Professore Muggiasca il suo Mac Mini. Dopo aver apportato un po' di miglioramenti siamo riusciti a far decollare il drone tramite il `Leap Motion` da Mac, l'unico problema è che per ora il `Leap Motion` funziona solo sul pc del docente e non suoi nostri portatili. Per fixare il problema abbiamo provato a cambiare la versione di java installata sui pc ma questa azione non ha avuto risultato. Per la settimana prossima proveremo a informaci in modo da arrivare pronti per la lezione.
2. Il Jar ci ha dato ancora problemi, una volta avviato dopo pochi secondi crasha. La prossima volta proveremo a sistemare questo problema e dovremmo anche trovare un modo per dare la `classpaht` corretta al Jar.
3. Nello sviluppo della live abbiamo avuto molti problemi. Come già accadeva il comando `node` non veniva correttamente richiamato. Abbiamo dovuto adottare una soluzione di ripiego, infatti per ora sia node che ffmpeg devono essere installati forzatamente nei luoghi di default.
4. La barra laterale aveva un piccolo bug, quando veniva premuto il tasto "freccia su" la barra iniziava a risalire. Questo è un comportamento normale in sé, ma non va bene in quanto a noi serve che la barra scorra sempre e mostri sempre l'ultimo comando. la soluzione sta nel forzare la barra a mostrare la sua ultima riga, in questo modo:
 
 ```java
private String commandConversion(String command) {
	JScrollBar sb = jScrollPane1.getVerticalScrollBar();
	sb.setValue( sb.getMaximum());
	...
}
 ```
  
5. In seguito abbiamo quasi sistemato la live anche per Windows, siamo infatti riusciti ad avviare lo script, ma poi il nostro computer è morto e abbiamo dovuto interrompere. LA soluzione è comunque modificare lo script di Windows per l'avvio di node, creato nel vecchio progetto. Tuttavia qui si pone un altro problema, ovvero la posizione di `ffmpeg`. essa è infatti inserita nello script `index.js` stesso. Quindi dovremmo per forza creare due file index, uno per Windows e uno per Mac, per poi cambiare la posizione di `ffmpeg`


Ecco quindi le soluzioni riassunte:

> 1. Usare un Mac mini con java 12.
> 2. Non ancora trovata.
> 3. Modifica degli script per l'avvio di node.
> 4. Forzatura della posizione della barra.


## Punto della situazione rispetto alla pianificazione

Abbiamo recuperato un po' di tempo, lavorando bene settimana prossima finiremo il progetto.

## Programma di massima per la prossima giornata di lavoro
1. Fix Jar.
2. Recupero lavoro live.
3. Documentare.
