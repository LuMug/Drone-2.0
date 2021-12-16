1. [Introduzione](#introduzione)
  - [Informazioni sul progetto](#informazioni-sul-progetto)
  - [Abstract](#abstract)
  - [Scopo](#scopo)

2. [Analisi](#analisi)
  - [Analisi del dominio](#analisi-del-dominio)
  - [Analisi e specifica dei requisiti](#analisi-e-specifica-dei-requisiti)
  - [Use case](#use-case)
  - [Pianificazione](#pianificazione)
  - [Analisi dei mezzi](#analisi-dei-mezzi)

3. [Progettazione](#progettazione)
  - [Design delle interfacce](#design-delle-interfacce)
  	- [Interfaccia principale](#interfaccia-principale)
	- [Interfaccia vista drone](#interfaccia-vista-drone)
  - [Design procedurale](#design-procedurale)
  - [Design di architettura del sistema](#design-di-architettura-del-sistema)

4. [Implementazione](#implementazione)

5. [Test](#test)
  - [Protocollo di test](#protocollo-di-test)
  - [Risultati test](#risultati-test)
  - [Mancanze e limitazioni conosciute](#mancanze-e-limitazioni-conosciute)

6. [Consuntivo](#consuntivo)

7. [Conclusioni](#conclusioni)

  - [Sviluppi futuri](#sviluppi-futuri)
  - [Considerazioni personali](#considerazioni-personali)

8. [Sitografia](#sitografia)

9. [Allegati](#allegati)


# Introduzione
### Informazioni sul progetto
- Allievi coinvolti nel progetto:  Gianni Grasso, Samuele Ganci, Alessandro Aloise, Michea Colautti.
- Classe: I3BB Scuola Arti e Mestieri Trevano, sezione Informatica.
- Docenti responsabili: Luca Muggiasca.
- Data inizio: 09 settembre  2021.
- Data di fine: 23 Dicembre 2021.

### Abstract

  > *Drones are more and  more common in our society. Today we see drone operating everywhere, in every sector. We see drone used in the military, in construction site, Amazon and other companies use drone to ship packet of merch to the people, in some cases drone even deliver the post. For this reason, we want to improve our drone control system, we will make a radical refactor of our old structure, adding new graphics, improving communications, and so on. Thanks to this project piloting a DJI Tello drone will become easy and fun. As the od project we will use, in addition to our drone, a Leap Motion sensor to track movements, the drone will be fully controllable with this sensor and even with the keyboard. 
Our project will be even more secure, we will enable and create some safety feature so that anyone can use our product without hurting someone or themselves.*


### Scopo

  Lo scopo del progetto è di creare un software in grado di collegare i movimenti delle nostre mani ad un drone.
  In pratica, grazie a un sensore chiamato `Leap Motion`, la nostra applicazione deve essere in grado di catturare e analizzare i movimenti
  delle mani e, dopo averli processati, trasmetterli al drone. Per farlo dobbiamo usare la tecnologia `UDP` (**U**ser **D**atagram **P**rotocol) e
  l'`SDK` installata di fabbrica sul drone.
  Dobbiamo, oltre a creare il sistema di pilotaggio, instaurare una comunicazione che permetta lo scambio di dati del drone; non solo dati come l'inclinazione,       l'altitudine, ma anche dati statistici come la batteria.

# Analisi
### Analisi del dominio

È stato richiesto di correggere e migliorare l'interfaccia di controllo drone da noi creata un anno fa. Il programma da noi creato presentava infatti molte imperfezioni ed elementi che non funzionavano. Per questo approfitteremo della presenza di 3 membri del gruppo su 4 per riprendere il progetto, correggerlo, e magari aggiungere nuove funzionalità. 
Alla fine verrà prodotta un interfaccia di controllo per un drone DJI tello, fortemente basata sulla nostra prima versione ma migliorata nell'aspetto grafico, nelle dinamiche di controllo, nella struttura del codice e anche, in maniera minore, negli obbiettivi.


### Analisi e specifica dei requisiti
 

|               |**ID: Req-001**|
|--------------|----------------|
|**Nome**      | Rappresentazioni drone seguono movimenti |
|**Priorità**  | 1              |
|**Versione**  | 1.1            |
|**Note**      | Quando il drone decolla la grafica ne segue i movimenti|

|               |**ID: Req-002**|
|--------------|----------------|
|**Nome**      | Pannello altitudine deve mostrare la batteria, l'altezza, e altri parametri ancora da decidere. |
|**Priorità**  | 2              |
|**Versione**  | 1.1            |
|**Note**      | Rivedere ad (eventuale) arrivo nuovo drone|



|               |**ID: Req-003**|
|--------------|----------------|
|**Nome**      | Sistema di Log deve essere sistemato |
|**Priorità**  | 1              |
|**Versione**  | 1.1            |
|**Note**      | La main thread non viene fatta partire|


|               |**ID: Req-004**|
|--------------|----------------|
|**Nome**      | Fix crash jar |
|**Priorità**  | 1              |
|**Versione**  | 1.1            |
|**Note**      | Il JAR crasha alla selezione del leap.|


|               |**ID: Req-005**|
|--------------|----------------|
|**Nome**      | Gestione dimensioni interfaccia |
|**Priorità**  | 1              |
|**Versione**  | 1.1            |
|**Note**      ||


|               |**ID: Req-006**|
|--------------|----------------|
|**Nome**      | Mostrare percentuale batteria|
|**Priorità**  | 1              |
|**Versione**  | 1.1            |
|**Note**      | Ad oggi il numero non viene mostrato|


|               |**ID: Req-007**|
|--------------|----------------|
|**Nome**      |Rivedere sistema di Live strem|
|**Priorità**  | 1              |
|**Versione**  | 1.1            |
|**Note**      | Durante fase di test: file non trovato|

|               |**ID: Req-008**|
|--------------|----------------|
|**Nome**      | Revisione comandi e input tramite Leap motion|
|**Priorità**  | 1              |
|**Versione**  | 1.1            |
|**Note**      | |


|               |**ID: Req-009**|
|--------------|----------------|
|**Nome**      | Sistema di sicurezza esterno|
|**Priorità**  | 1              |
|**Versione**  | 1.1            |
|**Note**      | Sistema di backup di emercency|


|               |**ID: Req-010**|
|--------------|----------------|
|**Nome**      | Pulsante esterno fisico per emergency |
|**Priorità**  | 3              |
|**Versione**  | 1.1            |
|**Note**      | |


|               |**ID: Req-011**|
|--------------|----------------|
|**Nome**      | Frame Pop-Up per dispay infomrazioni |
|**Priorità**  | 2              |
|**Versione**  | 1.1            |
|**Note**      | |


### Use case

Ecco il nostro Use Case
![Use Case](../Documenti/Use_Case/UseCase.png)
> Use Case

### Pianificazione

Per la pianificazione alleghiamo il Gantt preventivo da noi stabilito:
![Ganttpreventivo](../Documenti/Gantt/GANTT_Preventivo_Completo.jpg)
> Gantt preventivo


### Analisi dei mezzi

 **Software**
 - Java JDK 14.0.2
 - Leap Motion SDK 3.2.1
 - Tello SDK 2.0
 - Apache Netbeans IDE 12.0

 **Hardware**
- Laptop personali
- PC scolastici
- Drone DJI Tello
- Leap Motion





# Progettazione

## Design delle interfacce

### Interfaccia principale
Abbiamo preso la vecchia interfaccia e abbiamo cambiato tutto quello che non ci convinceva e abbiamo cercato di renderla più user friendly e più accattivante:
![Progettazione](Progettazione/GUI/Interfaccia/Drone2.0_GUI.png)
> Interfaccia drone

Come si può vedere rispetto alla prima versione del progetto l'interfaccia é cambiata: sulla sinistra troviamo, come del resto era nella versionep recedente, la lista dei comandi eseguiti. La novità è che al posto di essere una lunga sequenza di istruzioni incomprensibili, stampiamo una traduzione in modo tale che l'utente possa capire meglio cosa sta succedendo. Altre modifiche sostanziali sono: 
* Abbiamo cambiato il riquadro in basso a destra; dove prima c'era un valore numerico con l'altitudine ora rappresentiamo lo stesso valore in 2 modi. Il primo è tramite una lancetta rossa, non visibile nella progettazione, che percorre l'altimetro in base all'altezza. Il secondo sarà invece il valore dell'altitudine numerico, convertito in metri, e inserito nello spazion nero apposito.
* Inoltre abbiamo cambiato completamente la gestione della barra inferiore in modo tale che sia più pulita e ordinata 


### Design procedurale


# Implementazione
NOTA BENE: DOVE SONO PRESENTI LE IMMAGINI OCCORRE COMUNQUE ANCORA SPIEGARE IN MODO DETTAGLIATO IL CODICE IN MODO PRATICO.
## Introduzione
Durante la realizzazione del progetto sono state ridefinite molte cose rispetto alla prima versione, innanzitutto è stato fatto un refactor del codice precedente, andando a cambiare e ottimizzare i files dell'intero progetto. Una volta ottimizato e pulito il codice è stato adottato un cambiamento piuttosto rilevante riguardante la struttura dei package e delle classi, in modo da rendere il resto dell'implementazione più semplice, ordinata e migliorare un minimo le prestazioi del programma. Sono poi stati corrette tutte le imperfezioni e i bug trovati inerenti le vecchie funzionalità e infine apportate alcune aggiunte e accorgimenti.

## Refactor
Per la definizione della nuova struttura del progetto è stato deciso di dividere i package in base alle funzionalità che offre il software, in modo da poter suddividere il lavoro in modo semplice e non creare conflitti e relazioni non necessarie tra classi non interessate:
![Struttura]()

Riguardo invece la ristrutturazione delle vecchie classi, molte di esse sono state definitivamente eliminate, anche a causa del cambio di struttura. È stata invece creata una classe principale per poter gestire tutte le varie parti e funzioni che compongono l'applicativo, ovvero la classe Control. Essa ha lo scopo di gestire tutti i tool che offre l'applicativo, all'interno di questa classe sono state create delle code alla quale vengono poi passati i dati e comunicati alle varie classi.
![Control]()

Nella classe Drone sono state rimosse parecchie ridondanze e spostati altrettanti metodi, cercando di rendere la classe il più generica possibile per suddividere le funzionalità in altre classi apposite.
![Drone]()

Il KeyListener è stato sostituito da un KeyDispatcher, al fine di rimuovere un fastidioso problema riguardo al focus dell'applicazione, così facendo il focus della tastiera sull'applicazione è diventato dinamico e l'utente non ha problemi nel passare da un tool all'altro continuando a guidare il drone con la modalità Keyboard. A livello di codice il KeyDispatcher è molto simile a un classico keyListener, tuttavia è stato dovuto fare un accorgimento per poter adattare meglio i controlli da tastiera al drone, ciò che è stato fatto è la riduzione di lettura di comandi da parte del drone, in pratica nel keyDispatcher vengono mandati soltanto la metà dei pacchetti di richiesta di movimento al drone, filtrando queste richieste il drone risulta più reattivo durante la guida e anche più fluido nei movimenti.
![KeyDispatcher]()

Il sistema di sequenze è stato rivisto, non solo sono stati risolti i problemi relativi all'esecuzione e al salvataggio di esse ma è stato ricalibrato l'intero sistema per fare in modo che la velocità di esecuzione sia adatta, visto che la sensibilità di base risultava troppo elevata. Una volta cliccato sul bottone per avviare la registrazione la classe Sequence si occupa della creazione del file e della scrittura dei comandi registrati al suo interno e, successivamente, se si decide di salvare la registrazione viene rinominato il file. Anche l'esecuzione delle sequenze sono compito di questa classe, che ha una sua coda dedicata all'interno della quale inserisce tutti i comandi precedentemente letti dal file scelto dell'utente, comandi che vengono poi passati dalla coda di classe alla coda principale di input della classe Control.
![Sequence]()