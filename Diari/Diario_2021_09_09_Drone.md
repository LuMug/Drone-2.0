# Drone 2.0 | Diario di lavoro
##### Michea Colautti, Alessandro Aloise, Gianni Grasso
### Centro Professionale Trevano, 09.09.2021

## Lavori svolti


|Orario        |Lavoro svolto                                     |Eseguito da        |
|--------------|------------------------------------------------- |-------------------|
|13:15 - 14:00 | Recupero materiale del progetto precedente       |Alessandro e Gianni|
|14:00 - 14:30 | Revisione repository del progetto precedente     |Alessandro e Gianni|
|14:30 - 14:45 | Discussione del progetto con il mandante         |Tutti              |
|15:00 - 15:40 | Scrittura ed esecuzione dei test                 |Tutti              |
|15:40 - 16:30 | Definizione dei requisiti del progetto           |Tutti              |      

##  Problemi riscontrati e soluzioni adottate
I problemi, per essere stata la prima giornata di lavoro, sono stati molti. Ciò è dato dal fatto che abbiamo preso in mano un progetto che conosciamo molto bene, dato che lo abbiamo sviluppato nello scorso semestre. Il primo problema che ci si è posto è stato il non avere bene in chiaro cosa funzionasse e cosa no. Per Questo abbiamo fatto una ventina di test e raccolto i risultati in una tabella. I test erano volti a testare il funzionamento dei comandi del drone e della GUI. Abbiamo condotto i test sul progetto tramite NetBeans sia tramite file Jar. I problemi principali sono i seguenti:

* La GUI con le immagini del drone rimane immobile 
* Le informazioni sulla batteria non vengono mostrate
* Il log non viene scritto
	* Tutti questi problemi derivano dal non funzionamento della Thread di `Status`, che si occupa di ottenere tutte le informazioni dal drone per poi elaborarle e passarle alle classi `Log` e `ImageFrame`.

Abbiamo quindi deciso di stilare i nostri requisiti: molti di essi si concentrano sulla vera e propria correzione del codice, mentre altri sono obbiettivi nuovi che si siamo posti o ci sono stati proposti dal mandante.
Uno che possiamo citare è un refactor grafico dell'applicazione, modificando il pannello dell'altitudine per inserirci anche altre informazioni. 
Anche dal punto di vista grafico l'app va rivista, infatti il frame tende a sfarfallare se si prova a ridurlo, questo è causato dalla gestione fallacee delle dimensioni minime che l'app può avere.


##  Punto della situazione rispetto alla pianificazione
Ancora da definire.

## Programma di massima per la prossima giornata di lavoro
Visionare i requisiti e iniziare il diagramma di Gantt.
