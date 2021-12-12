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
Durante la realizzazione del progetto sono state ridefinite molte cose rispetto alla prima versione, innanzitutto è stato fatto un refactor del codice precedente, andando a cambiare e ottimizzare i files dell'intero progetto.

Abbiamo anche definito una nuova struttura per i files e i package al fine di ottimizzare il lavoro e rendere tutto più pulito:
![Struttura]()

Per quanto riguarda la ristrutturazione delle vecchie classi, molte di esse sono state definitivamente eliminate, anche a causa del cambio di struttura. È stata invece creata una classe principale per poter gestire tutte le varie parti e funzioni che compongono l'applicativo, ovvero la classe Control.

Per quanto riguarda la classe Drone, sono state rimosse parecchie ridondanze e spostate altrettanti metodi, suddividendo le funzionalità in altre classi.

Il KeyListener è stato sostutuito da un KeyDispatcher, al fine di rimuovere un fastidioso problema riguardo al focus dell'applicazione.

