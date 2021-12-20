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




<div style="page-break-after: always;"></div>

# Progettazione

## Design delle interfacce

### Interfaccia principale
Abbiamo preso la vecchia interfaccia e abbiamo cambiato tutto quello che non ci convinceva e abbiamo cercato di renderla più user friendly e più accattivante:
![Progettazione](Progettazione/GUI/Interfaccia/Drone2.0_GUI.png)
> Interfaccia drone

Come si può vedere rispetto alla prima versione del progetto l'interfaccia é cambiata: sulla sinistra troviamo, come del resto era nella versionep recedente, la lista dei comandi eseguiti. La novità è che al posto di essere una lunga sequenza di istruzioni incomprensibili, stampiamo una traduzione in modo tale che l'utente possa capire meglio cosa sta succedendo. Altre modifiche sostanziali sono: 
* Abbiamo cambiato il riquadro in basso a destra; dove prima c'era un valore numerico con l'altitudine ora rappresentiamo lo stesso valore in 2 modi. Il primo è tramite una lancetta rossa, non visibile nella progettazione, che percorre l'altimetro in base all'altezza. Il secondo sarà invece il valore dell'altitudine numerico, convertito in metri, e inserito nello spazion nero apposito.
* Inoltre abbiamo cambiato completamente la gestione della barra inferiore in modo tale che sia più pulita e ordinata 


### Pop-up informazioni.

In questa versione del progetto abbiamo progettato anche un Pop-up che ci permettera di stampare a schermo le informazioni che il drone colleziona durante il volo. Non è stato fatto nel progetto veccchio poichè con il primo done in nostro possesso non riusicivamo ad ottenre queste infomrazioni, ma ora dato che ci è sato fornito un drone nuovo, siamo in grado di collezionarle tutte. Nello specifico queste infomrazioni sono:

* Beccheggio
* Rollio
* Imbardata
* Altitudine
* Spostamento sull'asse x
* Spostamento sull'asse y
* Spostamento sull'asse z
* Accellerazione sull'asse z
* Accellerazione sull'asse y
* Accellerazione sull'asse x
* Temperatura più bassa
* Temperatura più alta
* Tempo di volo
* Batteria residua
* Pressione in cm
* Tempo d'uso dei motori

Tutte queste informazioni devono essere costantemente aggiornate e devono apparire in maniera fluida all'intenro del nostro programma. Abbiamo quindi pensato ad un frame che apparisse sotto forma di pop-up. Come si può evincere dalla GUI principale il tasto "analytics" sarà quello responsabile dell'apparizione di questo frame.

Abbiamo pensato di implementare questa nuova funzione in maniera molto semplice: un frame diviso a metà, dove nella parte sinistra apparisse la lista di informazioni, mentre in quella sinistra un'immagine che abbellisca il frame e allo stesso tempo dia un senso di continuità al programma.

Ecco quindi la nostra idea:
![FrameStatistiche](Progettazione/GUI/Interfaccia/AnalyticsFrame.png)
>Interfaccia pop-up dati di volo

Già dalla progetztazione sappiamo che potremmo avere qualche problema con il ridimensionamento della finestra. Infatti rischiamo che l'immagine vada a coprire i dati se non gestita in maniera appropriata, quindi la nostra idea è di dare un po' di gioco alla finestra, spostanado e ridimensionando l'immagine e ingrandento e rimpicciolendo il font.
Ma la finestra avrà comunque delle prorporzioni limitate, per garantire l'interezza delle stringhe di dati e dell'immagine a finaco.

### Schema delle classi
//da rifare, classi aggiunte


<div style="page-break-after: always;"></div>

# Implementazione
NOTA BENE: DOVE SONO PRESENTI LE IMMAGINI OCCORRE COMUNQUE ANCORA SPIEGARE IN MODO DETTAGLIATO IL CODICE IN MODO PRATICO.

## Introduzione
Durante la realizzazione del progetto sono state ridefinite molte cose rispetto alla prima versione, innanzitutto è stato fatto un refactor del codice precedente, andando a cambiare e ottimizzare i files dell'intero progetto. Una volta ottimizato e pulito il codice è stato adottato un cambiamento piuttosto rilevante riguardante la struttura dei package e delle classi, in modo da rendere il resto dell'implementazione più semplice, ordinata e migliorare un minimo le prestazioi del programma. Sono poi stati corrette tutte le imperfezioni e i bug trovati inerenti le vecchie funzionalità e infine apportate alcune aggiunte e accorgimenti.

## Refactor generale
Per la definizione della nuova struttura del progetto è stato deciso di dividere i package in base alle funzionalità che offre il software, in modo da poter suddividere il lavoro in modo semplice e non creare conflitti e relazioni non necessarie tra classi non interessate:
![Struttura]()

Riguardo invece la ristrutturazione delle vecchie classi, molte di esse sono state definitivamente eliminate, anche a causa del cambio di struttura. È stata invece creata una classe principale per poter gestire tutte le varie parti e funzioni che compongono l'applicativo, ovvero la classe Control. Essa ha lo scopo di gestire tutti i tool che offre l'applicativo, all'interno di questa classe sono state create delle code alla quale vengono poi passati i dati e comunicati alle varie classi.
![Control]()

### DroneAction
Nella classe Drone sono state rimosse parecchie ridondanze e spostati altrettanti metodi, cercando di rendere la classe il più generica possibile per suddividere le funzionalità in altre classi apposite. Proprio a questo scopo questa classe è stata rinominata DroneAction. Questa classe ha lo scopo di rappresentare il del drone ed è la classe che interagisce direttamente con esso, all'interno di essa sono salvate le sue caratteristiche come ad esempio l'indirizzo ip e le porte d'ascolto, inoltre si occupa della realizzazione dei socket e dell'invio dei messaggi. Infomrazioni come ip e porta sono salvate come costanti, visto che la porta di ascolto e l'ip del drone restano sempre uguali, almeno per quanto riguarda l'invio e la ricezione dei comandi.

Per quanto riguarda l'invio dei dati verso il drone è stato creato un metodo apposito, per prima cosa esso verifica se è la prima volta che viene mandato un comando tramite il flag firstSend, se è così il flag diventa false e viene istanziata e inviata una stringa contenente il comando "command", comando che serve per abilitare il drone a rispondere alle richieste UDP.
Successivamente viene istanziato un array di byte che tramite il metodo getBytes() salva al suo interno i byte della stringa inserita come parametro. Viene poi creato anche un oggetto DatagramPacket che, ricevendo come parametro i byte del comando da inviare, la lunghezza dei dati, l'ip a cui mandare i dati e la relativa porta, invia il pacchetto finale al drone tramite il metodo send(). Infine viene creato un altro oggetto DatagramPacket, stavolta usando dei parametri hardcoded.

```java
public void sendCommand(String command) {
	try {
		if (firstSend) {
			String firstSendCommand = "command";
			byte[] data = firstSendCommand.getBytes();
			DatagramPacket packet = new DatagramPacket(
				data, data.length, InetAddress.getByName(DRONE_IP), 
				COMMANDS_PORT);
				
			socket.send(packet);
			firstSend = false;
		}
		byte[] data = command.getBytes();
		DatagramPacket packet = new DatagramPacket(
			data, data.length, InetAddress.getByName(DRONE_IP), 
			COMMANDS_PORT);
			
		socket.send(packet);
	
		DatagramPacket receivePacket = new DatagramPacket(
			new byte[256], new byte[256].length);
			
	} catch (SocketException ex) {
		System.out.println("ERRORE: " + ex.getMessage());
	} catch (IOException ex) {
		System.out.println("ERRORE: " + ex.getMessage());
	}
}
```

Il metodo sendCommand() è quindi fondamentale per la comunicazione tra client e drone, ma per funzionare correttamente ha bisogno di essere eseguito in loop in modo tale da poter inviare dati continuamente, motivo per il quale è stata fatta estendere la classe Thread a questa classe.
Nel metodo run() che rappresenta la Thread della classe DroneAction viene eseguito un ciclo infinito dove ogni 50 millisecondi viene ridefinito l'attributo command (che rappresenta la stringa da inviare di volta inn volta), salvando al suo interno l'ultimo elemento di una coda che viene usata per salvare la sequenza di comandi da eseguire. Infine, come menzionato prima, viene invocato il metodo sendCommand() passando come parametro l'attributo command appena ridefinito, per poter finalmente inviare il pacchetto al drone.

```java
public void run() {
    while (true) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
        }
        String command = commandsBufferOutputDrone.poll();
        if (command != null) {
            sendCommand(command);
        }
}
```


### KeyDispatcher
Il KeyListener è stato sostituito da un KeyDispatcher, al fine di rimuovere un fastidioso problema riguardante il focus dell'applicazione, così facendo il focus della tastiera è diventato dinamico e l'utente non ha problemi nel passare da un tool all'altro continuando a guidare il drone con la modalità Keyboard. A livello di codice il KeyDispatcher è molto simile a un classico keyListener, tuttavia è stato dovuto fare un accorgimento per poter adattare meglio i controlli da tastiera al drone, ciò che è stato fatto è la riduzione di lettura di comandi da parte del drone, in pratica nel keyDispatcher vengono mandati soltanto la metà dei pacchetti di richiesta di movimento al drone, filtrando queste richieste il drone risulta più reattivo durante la guida e anche più fluido nei movimenti.

Questa classe è molto breve, contiene due attributi, un flag booleano chiamato pressing e un numero intero chiamato dummyCounter, entrambi vengono utilizati nell'unico metodo usato per il funzionamento del programma, ovvero dispatchedKeyEvent, un metodo che contiene tre grosse sezioni divise tramite degli if, l'unico di essi che è stato utilizzato frequentemente è la sezione keyPressed. Questa porzione di codice viene eseguito ogni volta che l'utente preme un tasto sulla tastiera e in base al codice del tasto premuto manda dei comandi al drone, filtrando però la frequenza massima di tasti premuti tramite i due attributi descritti 
prima (pressing e dummyCounter).

```java
if (evt.getID() == KeyEvent.KEY_PRESSED) {
    if (evt.getExtendedKeyCode() == 87) {
        if (!pressing || (dummyCounter & 1) == 0)
            commandBufferInput.add("rc 0 70 0 0");
    }
    if (evt.getExtendedKeyCode() == 65) {
        if (!pressing || (dummyCounter & 1) == 0)
            commandBufferInput.add("rc -70 0 0 0");
    }
    if (evt.getExtendedKeyCode() == 83) {
        if (!pressing || (dummyCounter & 1) == 0)
            commandBufferInput.add("rc 0 -70 0 0");
    }
    if (evt.getExtendedKeyCode() == 68) {
        if (!pressing || (dummyCounter & 1) == 0)
            commandBufferInput.add("rc 70 0 0 0");
    }
    if (evt.getExtendedKeyCode() == 37) {
        if (!pressing || (dummyCounter & 1) == 0)
            commandBufferInput.add("rc 0 0 0 -70");
    }
    if (evt.getExtendedKeyCode() == 39) {
        if (!pressing || (dummyCounter & 1) == 0)
            commandBufferInput.add("rc 0 0 0 70");
    }
    if (evt.getExtendedKeyCode() == 40) {
        if (!pressing || (dummyCounter & 1) == 0)
            commandBufferInput.add("rc 0 0 -79 0");
    }
    if (evt.getExtendedKeyCode() == 38) {
        if (!pressing || (dummyCounter & 1) == 0)
            commandBufferInput.add("rc 0 0 70 0");
    }
    if (evt.getExtendedKeyCode() == 32) {
        if (!pressing || (dummyCounter & 1) == 0)
            commandBufferInput.add("rc 0 0 0 0");
    }
    if (evt.getExtendedKeyCode() == 84) {
        commandBufferInput.add("takeoff");
    }
    if (evt.getExtendedKeyCode() == 76) {
        commandBufferInput.add("land");
    }
    if (evt.getExtendedKeyCode() == 10) {
        commandBufferInput.add("emergency");
    }
    if (evt.getExtendedKeyCode() == 85) {
        commandBufferInput.add("flip f");
    }
    if (evt.getExtendedKeyCode() == 74) {
        commandBufferInput.add("flip b");
    }
    if (evt.getExtendedKeyCode() == 75) {
        commandBufferInput.add("flip r");
    }
    if (evt.getExtendedKeyCode() == 72) {
        commandBufferInput.add("flip l");
    }
    pressing = true;
    ++dummyCounter;
}
```

### Sequence
Il sistema di sequenze è stato rivisto, non solo sono stati risolti i problemi relativi all'esecuzione e al salvataggio di esse ma è stato ricalibrato l'intero sistema per fare in modo che la velocità di esecuzione sia adatta, visto che la sensibilità di base risultava troppo elevata. Una volta cliccato sul bottone per avviare la registrazione la classe Sequence si occupa della creazione del file e della scrittura dei comandi registrati al suo interno e, successivamente, se si decide di salvare la registrazione viene rinominato il file. Anche l'esecuzione delle sequenze sono compito di questa classe, che ha una sua coda dedicata all'interno della quale inserisce tutti i comandi precedentemente letti dal file scelto dell'utente, comandi che vengono poi passati dalla coda di classe alla coda principale di input della classe Control. Per quanto riguarda la registrazione e il salvataggio delle sequenze ci sono due metodi principali, createFile e writeFile, che servono rispettivamente per la creazione e la scrittura dei files.

Nel metodo createFile() che riceve come parametro una stringa (il nome del file), viene creato un oggetto file usando come parametro la stringa ricevuta anche dal metodo stesso. Successivamente viene fatto un controllo per verificare che il file appena istanziato non esista già, in tal caso viene stampata a terminale una stringa di conferma, in caso contrario viene segnalato che il file esiste già e non viene quindi creato.

```java
public void createFile(String fileName) {
    try {
        File myObj = new File(fileName);

        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
            System.out.println("File already exists.");
        }
    } catch (IOException e) {
        System.out.println("An error occurred.");
    }
}
```

Nel metodo writeFIle() invece, viene creato un oggett FileWriter con la stessa modalità del metodo createFile().  Successivamente viene scritto nel file scelto utilizzando il metodo write(), ciò che verrà scritto sarà la stringa message che viene definita tramite il metodo setMessage salvando al suo interno tutti i comandi della coda, andando a capo dopo ognuno di essi.

```java
public void setMessage(LinkedList<String> sequence) {
    for (int i = 0; i < sequence.size(); i++) {
        message = message + "\n" + sequence.get(i);
    }
}
```

```java
public void writeFile(String fileName) {
    try {
        FileWriter myWriter = new FileWriter(fileName);
        myWriter.write(message);
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
        System.out.println("An error occurred.");
    }
}
```

Per la lettura delle sequenze invece è stato sviluppato un solo metodo, ovvero readFile(), che crea un oggetto BufferedReader ricevendo come parametro una stringa che sarà il nome del file (la stessa che viene passata come parametro allo stesso metodo). Successviamente viene istanziata una stringa chiamata line contenente il contenuto della prima riga del file, tramite un ciclo che termina quando non ci sono più righe leggibile nel file viene aggiunta la linea corrente all'oggetto BufferedReader e la variabile line viene ridefinita passando alla prossima riga.

```java
public void readFile(String fileName) throws FileNotFoundException, IOException, InterruptedException {
    BufferedReader bufReader = new BufferedReader(new FileReader(fileName));

    String line = bufReader.readLine();
    while (line != null) {
        Thread.sleep(10);
        commandBufferInput.add(line);
        line = bufReader.readLine();
    }

    for (int i = 0; i < commandBufferInput.size(); i++) {

        System.out.println(commandBufferInput.get(i));
    }
    bufReader.close();
}
```


<div style="page-break-after: always;"></div>

## Refactor e implementazione grafica

Come per il resto del progetto, e come visto anche nel capitolo della progettazione, anche il package `graphics` è stato rivisto. Questo ci ha permesso di migliorare sensibilmente la grafica e semplificarne la gestione.

Il primo passo è stato rileggere tutto il codice e semplificarne alcune parti. La struttura è rimasta tuttavia pressoché la stessa, in quanto precedentemente questa parte del progetto funzionava bene.

Le classi nel progetto sono rimaste quindi quelle, ma abbiamo cambiato i nomi, uniformandolo al resto del progetto:

1. MainPanel
2. Model
3. FrontPanel
4. SidePanel
5. UpPanel
6. AltimeterPanel

Come il nome suggerisce, i 4 dati principali del drone (imbardata, beccheggio, rollio e altitudine) sono rappresentati nei 4 panelli.

Model è invece un pannello speciale, che definisce il modello per la rappresentazione di un pannello: al suo interno sono infatti contenuti i metodi per ridimensionare le immagini, per ruotarle e per disegnarle.
Per far si che i pannelli potessero utilizzare i metodi, abbiamo dovuto creare una relazione tra i pannelli e il modello stesso. Per questo i pannelli estendono la classe modello.

Per la documentazione procederemo in ordine, come in passato.

### MainPanel

MainPanel è il corrispettivo di ImageFrame nella versione 1.0 del progetto. Abbiamo cambiato il nome per meglio descrivere la classe, che è un pannello che poi verrà inserito nel Frame principale dell'applicazione. La sua funzione è rimasta quella di istanziare tutti i pannelli della nostra classe per mostrarli. Inoltre è obbligatorio citare che MainPanel è una Thread, implementa infatti Runnable. Questo è fondamentale se vogliamo chela grafica si aggiorni senza bloccare il resto dell'app. 
MainPanel viene quindi istanziato e fatto partire nella classe `Control`. Qui alleghiamo il metodo costruttore di MainPanel:

```java
public MainPanel() {
		GridLayout MainPanelLayout = new GridLayout(2, 2);
		setLayout(MainPanelLayout);
		frontPanel = new FrontPanel();
		sidePanel = new SidePanel();
		upPanel = new UpPanel();
		altimeterPanel = new AltimeterPanel();
		add(frontPanel);
		add(sidePanel);
		add(upPanel);
		add(altimeterPanel);
}
```
Come si può vedere al pannello principale vengono aggiunti i 4 pannelli secondari.
Per funzionare MainPanel sfrutta la classe già menzionata `Status` e `Control`. A contrario del progetto vecchio, in cui Status aveva un rifermeinto diretto alla grafica, per questo progetto, utilizziamo un sistema di Queue. Nella classe `Status` è quindi istanziato un oggetto di tipo `Queue<String>`, chiamato "statusBufferData". Nella classe è poi presente un metodo setter, che sarà richiamato da MainPanel, come spiegheremo fra poco:

```java
public void setStatusBufferData(Queue<String> statusBufferData) {
	this.statusBufferData = statusBufferData;
}
```
Nella coda verranno quindi inseriti i valori essenziali per il movimento della grafica, quindi:

* *Altitudine o `h`*
* *Rollio o `roll`*
* *Beccheggio o `pitch`*
* *Imbardata o `yaw`*

Nel metodo run di Status quindi, viene richiamato il setter allegato qui sopra, aggiungendo i valori alla coda.

```java
statusBufferData.add("pit:" + status.get("pitch").toString());
statusBufferData.add("rol:" + status.get("roll").toString());
statusBufferData.add("yaw:" + status.get("yaw").toString());
statusBufferData.add("alt:" + status.get("h").toString());
```
Come si può vedere non aggiungiamo solo il valore puro, ma alleghiamo anche una breve stringa di 3 caratteri, un id, che ci permetterà di separare i dati quando arriveranno alla grafica.


Nella grafica, per far sì che il nostro sistema di code funzioni, abbiamo istanziato una coda e un suo setter:

```java
private static volatile Queue<String> statusBufferData;

public void setStatusBufferData(Queue<String> statusBufferData) {
	this.statusBufferData = statusBufferData;
}
```

Il setter non è ridondante come può sembrare, infatti anche se è identico al setter presente in Status, servono entrambi per la nostra relazione.

Il metodo run di MainPanel è forse la parte più importante e che ha visto i maggiori cambiamenti:
Il ciclo infinito è rimasto, ma all'interno abbiamo dovuto mettere uno switch che ci permette di smistare i vari elementi della coda:

```java
public void run() {
	boolean in=true;
	while (in) {
		String status = statusBufferData.poll();
		if (status != null) {
			String id = status.substring(0, 4);
			switch (id) {
				case "pit:" : {
					double pitch = Double.parseDouble(status.substring(
						4,status.length()));
					sidePanel.moving((int) pitch);
					break;
				}
				case "rol:" : {
					double roll = Double.parseDouble(status.substring(
						4,status.length()));
					frontPanel.moving((int) roll);
					break;
				}
				case "yaw:" : {
					double yaw = Double.parseDouble(status.substring(
						4,status.length()));
					upPanel.moving((int) yaw);
					break;
				}
				case "alt:" : {
					double alt = Double.parseDouble(status.substring(
						4,status.length()));
					altimeterPanel.setAltitude(alt);
					break;
				}
			}
		}
	}
}
```

Come si può vedere il primo passo è estrarre l'id dalla stringa appena presa dalla coda con il metodo `.poll()`, che salva e rimuove l'elemento aggiunto.
Poi nello switch, in base all'etichetta appena slavata, viene invocato il metodo dedicato al movimento della classe corretta.

<div style="page-break-after: always;"></div>


### Model

Come detto questa classe definisce un modello per la rappresentazione delle immagini, ed è forse la classe che ha visto lo stravolgimento maggiore.
Al suo interno, come precedentemente, sono contenute le istanze di BufferedImage che ci serviranno nel programma, le istanze sono 3:

`public BufferedImage imageBig`: è l’immagine originale, che verrà direttamente presa dal file png.

`public BufferedImage rotatedImage`: è l’immagine temporanea che verrà ruotata.

`public BufferedImage image`: è l’immagine finale che poi verrà rappresentata.

Oltre alle istanze sono presenti altri 2 metodi di supporto.
Il primo è quello per il ridimensionamento delle immagini, esse infatti erano troppo grosse per poter stare nel nostro panello ed era quindi necessario ridurne di molto la dimensione.

Quello che fa il metodo `resizeImage` in pratica è prendere come argomento un immagine, che sarà ImageBig, e due attributi di tipo int che specificano larghezza e altezza. In seguito il metodo crea una nuova BufferdImage con dimensioni nuove, ma con lo stesso contenuto dell’immagine originale, poi la ritorna.

Il secondo metodo, `rotate`, funziona in maniera simile a quello precedente: prende una BufferdImage come input e un int che specifica la rotazione in gradi, poi tramite formule matematiche e l’uso di `Graphics2D`, usato anche da `resizeImage` tra parentesi, permette di ruotare l’immagine con il metodo apposito. L’immagine ruotata viene poi ritornata.

Un terzo metodo fondamentale è `toBufferedImage`. Esso, data un’immagine di tipo `Image` come input permette di convertirla in BufferdImage.
Questo metodo si è reso necessario quando abbiamo creato il Jar finale, e ci siamo accorti che le immagini non venivano mostrate, in quanto erano compresse nel file Jar stesso.
Questo ci ha costretti a rendere delle immagini delle risorse della classe stessa, per poi essere prese e convertite, in quanto non era possibile creare delle BufferdImage direttamente.
Ma esploreremo questo aspetto meglio più avanti.

L’ultimo metodo fondamentale è, ovviamente, `paintComponent`. Questo metodo viene usato da `FrontPanel` e `SidePanel`; poiché i due pannelli contengono 2 immagini pressoché identiche nei rapporti di dimensione.

Questo metodo prende come prima cosa le dimensioni del pannello, per poi calcolare le dimensioni dell’immagine in base a quelle date data. Rispetto al progetto vecchio, abbiamo modificato del codice per quanto riguarda la gestione della dimensione. Infatti quello che accadeva nel progetto vecchio è che spesso e volentieri le immagini ruotando, con alcune proporzioni, nello specifico quando la lunghezza dell'immagine, o meglio della sua diagonale, era maggiore all'altezza del pannello, l'immagine veniva tagliata o era sproporzionata. Per fare sì che questo non accada più abbiamo pensato di ottenere la diagonale dell'immagine, che a pensarci bene rappresenta la lunghezza massima che l'immagine ha.

Per fare tutto questo il procedimento è stato il seguente:

 
Come primo passo abbiamo dovuto rendere tutte le immagini grandi uguali, in modo da poter sempre applicare delle proporzioni corrette nel ridimensionamento con il metodo `resizeImage`.

Abbiamo poi definito delle proporzioni nuove, calcolando la lunghezza dell'immagine come la metà della lunghezza del pannello, e calcolando l'altezza come 1/4 della nuova lunghezza:

```java
	panelH = getHeight();
	panelW = getWidth();

	int droneW;
	int droneH;
	droneW = panelW - panelW / 2;
	droneH = droneW / 4;
```

In sé questo codice è abbastanza per gestire il caso in cui l'altezza sia sufficiente a contenere l'immagine ruotata, ma questo non è garantito. Infatti come abbiamo detto prima se la lunghezza della diagonale dell'immagine, dovesse essere maggiore all'altezza del pannello, l'immagine verrebbe tagliata. Si pensi di prendere due rettangoli, uno leggermente più piccolo dell'altro, e sovrapporli. Se il triangolo interno si trova al centro di quello esterno non ci sono problemi, ma se il primo dovesse venire ruotato finirebbe con il passare i bordi del rettangolo esterno. Per prevenire tutto questo abbiamo quindi calcolato la diagonale usando Pitagora, se il valore da noi trovato dovesse essere maggiore o uguale all'altezza del pannello, allora eseguiamo una variante del calcolo già citato sopra:

```java
int droneHypo = (int) Math.sqrt(Math.pow(droneW, 2) 
	+ Math.pow(droneH, 2));
if (droneHypo >= panelH) {
		droneW = panelH;
		droneH = droneW / 4;
}
```


L'ultimo passo è sostanzialmente il più semplice, ovvero disegnare l'immagine. Questo processo è variato di poco rispetto al vecchio progetto:

```java
if (imageBig != null) {
	image = resizeImage(imageBig, droneW, droneH);
	x = (this.getWidth() - image.getWidth()) / 2;
	y = (this.getHeight() - image.getHeight()) / 2;
	image = rotate(image, rotDeg);
	
	if (rotDeg > 0) {
		g.drawImage(image, x - image.getWidth() / 5, y - 
			(int) (image.getHeight() / 3.4), this);
	} else if (rotDeg < 0) {
		g.drawImage(image, x + image.getWidth() / 5, y - 
			(int) (image.getHeight() / 3.4), this);
	} else {
		g.drawImage(image, x, y, this);
	}

```
Facendo dei test abbiamo concluso che per tenere sempre l'immagine al centro è necessario spostarla quando viene ruotata. I valori vengono calcolati in base alla lunghezza e all'altezza dell'immagine, mentre i valori hard coded `5` e `3.4` sono proporzioni frutto di svariati test.

Ora è arrivato il momento di passare ai 4 frame dell'applicazione. Per trattare questa parte di codice abbiamo deciso di dividere la nostra documentazione in 3 parti distinte.

1. `SidePanel`+`FrontPanel`
2. `UpPanel`
3. `AltimeterPanel`

Questo perché i primi due pannelli possiedono un codice pressoché identico, è quindi possibile semplificare la spiegazione.

### SidePanel + FrontPanel

Questi pannelli esportano essenzialmente 2 elementi, un metodo costruttore personalizzato e un metodo per gestire il movimento. Partiamo dal costruttore.

Come anticipato precedentemente, il costruttore ci permette di prendere l'immagine da file, per poi convertirla per essere utilizzata. Tuttavia prelevare quest'immagine ci pone di fronte a qualche difficoltà: una volta creato il file Jar infatti, non sarà più possibile prelevare le immagini semplicemente con il loro percorso, in quanto esse vengono compresse.
Per ovviare a questo problema bisogna eseguire 2 semplici passaggi.

Come prima cosa dobbiamo spostare le immagini dalla loro cartella di origine, per posizionarle in un'altra cartella, idealmente in un posto facile e accessibile.
Dopo aver messo le immagini nella nuova locazione, bisogna aggiungere la cartella delle immagini come `ClassPath` al progetto di NetBeans, aggiungendolo alle altre librerie già presenti.

Il secondo passo sarà prelevare le immagini come “risorsa di classe”, e questo può essere fatto nel seguente modo:

```java
 public ImagePanelFront() {
	ImageIcon icon;
	icon = new ImageIcon(getClass().getClassLoader()
		.getResource("DroneFrontale.png"));
	Image image = icon.getImage();
	imageBig=toBufferedImage(image);
}
```
È importante che le immagini siano prese dapprima come `Icon`, in quanto questo tipo di immagine è il più consigliato per essere usato come contenitore per delle risorse di immagini.
In seguito questa icona è convertita in un immagine e, tramite il metodo citato prima, in una `BufferdImage`.

Il secondo metodo importante è, come detto, il metodo per il movimento. Il principio è molto semplice, viene passato un parametro con la pendenza in gradi, i dati dell'immagine vengono aggiornati e la stessa viene ruotata e aggiornata.

Abbiamo potuto rimuovere il controllo sull'inclinazione massima in quanto grazie al nuovo sistema di disegno ci permette di disegnare il drone in qualunque posizione.

```java
public void moving(int rotate) {
	if (rotDeg < 0) {
		rotDeg = rotate;
		validate();
		repaint();
	} else {
		rotDeg = rotate;
		validate();
		repaint();
	}
}
```

<div style="page-break-after: always;"></div>


È necessario fare l'inversione da gradi positivi a negativi in quanto il valore in gradi ritornato dal drone è opposto a quello che viene usato per i piani cartesiani. Si noti che è questo il metodo che lo switch documentato sopra richiama.

> Il codice qui riportato corrisponde a `FrontPanel`, per creare il suo corrispettivo `SidePanel` è sufficiente sostituire nel costruttore `DroneFrontale.png` con `DroneLaterale.png `



### UpPanel
Questa classe differisce leggermente dalle due precedenti, infatti a cambiare è il rapporto dell’immagine.

Tuttavia la logica è pressoché la stessa, il costruttore prende l’immagine allo stesso modo, ma al posto di esserci un metodo di movimento che sfrutta il `paintComponent` definito nel modello `Model`, questa classe ha un suo ha un suo metodo `paintComponent`.

Come si poteva vedere anche nello switch, anche il metodo incaricato di gestire il movimento è diverso. Infatti in questo caso 

In questo metodo, oltre al rapporto nuovo che viene usato, dato dal fatto che l'immagine è un quadrato e non un rettangolo, vengono calcolate in maniera diversa anche le dimensioni dell'immagine.

In primis vengono trovate altezza e lunghezza del pannello, poi il valore maggiore viene assegnato alla dimensione dell'immagine `droneS`.

```java
panelH = getHeight();
panelW = getWidth();
if (panelW >= panelH) {
	droneS = panelH;
} else {
	 droneS = panelW;
}
```


Poi per il calcolo della dimensione effettiva, ricordando che se l'immagine ruota e il pannello ha delle dimensioni particolari, quelle citate sopra, ho adottato una logica simile a quella presentata precedentemente:

```java
int droneHypo = (int) Math.sqrt(Math.pow(droneS, 2) 
	+ Math.pow(droneS, 2));
droneS = droneS - (droneHypo - droneS);
```

In pratica ho la lunghezza massima del drone e la sua diagonale, trovata con Pitagora. Sottraendo la lunghezza dell'immagine alla diagonale ottengo "di quanto l'immagine straborderebbe". Prendo quindi questo ultimo valore e lo sottraggo alla dimensione effettiva dell'immagine. Ne risulta un immagine sempre centrata e mai strabordante.

Il metodo di disegno è poi lo stesso già presentato, ma con delle dimensioni nuove:

```java
if (imageBig != null) {
	image = resizeImage(imageBig, droneS, droneS);
	...
	if (deg != 0) {
		rotatedImage = rotate(image, deg);
		g.drawImage(rotatedImage,
 			x - (int) (rotatedImage.getWidth() / 6.5),
			y - (int) (rotatedImage.getHeight() / 6.5), this);
	} else {
		g.drawImage(image, x, y, this);
	}
}

```

In questo caso non devo controllare se i gradi sono positivi o negativi e modificare il calcolo di conseguenza, in quanto l'immagine, essendo quadrata, si muove sempre allo stesso modo, sia in positivo che in negativo.


### AltimeterPanel


Per il pannello raffigurante l'altitudine abbiamo pensato ad un refactor radicale. Infatti la vecchia rappresentazione, con un semplice valore numerico, ci è sembrata mediocre se comparata agli altri pannelli. Abbiamo quindi pensato ad un modo per rappresentare graficamente l'altitudine, e ci è venuto in mente l'altimetro.

Abbiamo pensato quindi di rappresentare il valore dell'altitudine tramite una ghiera numerica e una lancetta, oltre che con il solito valore numerico.

Come prima cosa abbiamo quindi dovuto disegnare l'immagine dell'altimetro. Per farlo abbiamo usato la stessa logica usata anche in precedenza:

In primi definire il solito costruttore con l'immagine dell’altimetro, poi nel metodo `paintComponent` abbiamo deciso di disegnare l'immagine sfruttando quasi tutto il pannello, lasciando un margine tra il pannello e l'immagine stessa, per meglio adeguarci agli altri pannelli. Abbiamo quindi definito una costante `PAD` per il valore del margine, trovato la dimensioni del pannello e calcolato le dimensioni dell'immagine prendendo il valore minore tra altezza e lunghezza e sottraendogli il margine:

```java
int imageSize;

//calcolo della dimensione
if (panelW >= panelH) {
	imageSize = panelH - PAD;
} else {
	imageSize = panelW - PAD;
}

//disegno
if (imageBig != null) {
	image = resizeImage(imageBig, imageSize, imageSize);
	int x = (this.getWidth() - image.getWidth()) / 2;
	int y = (this.getHeight() - image.getHeight()) / 2;
	g.drawImage(image, x, y, this);
}
```

Il fatto che quest'immagine sia quadrata e non debba ruotare ci semplifica di molto il lavoro.

La parte interessante arriva quando si tratta di disegnare la lancetta. Inizialmente volevamo usare un altra immagine e ruotare quella, ma abbiamo avuto molti problemi nel farlo, in quanto il metodo per il movimento delle immagini non è fatto per ruotare un immagine usando un fulcro come punto di rotazione, ma è fatto per ruotare l'immagine in toto. Per questo abbiamo deciso di utilizzare il costrutto `g.drawLine()` di java. Questo e l'uso della trigonometria ci ha permesso di raggiungere un risultato soddisfacente.

Ancora prima di parlare della lancetta abbiamo però pensato a come posizionare un `JLabel` sull'apposito spazio nero dell'immagine: la sfida era quella di far sì che con qualunque proporzione del frame, il Label fosse sempre posizionato sopra lo spazio apposito, e che il testo al suo interno si ridimensionasse di conseguenza. Per farlo abbiamo come prima cosa posizionato il Label in un punto fisso, poi abbiamo ridimensionato la finestra fino a spostare lo spazio nero esattamente sotto al Label. Abbiamo così ottenuto una proporzione, che diceva che il Label sarebbe stato posizionato correttamente se si fosse trovato a 1/3 dell'altezza e circa a metà rispetto alla lunghezza. Facendo un po' di prove ci siamo accorti che tuttavia non era ancora perfetto; quindi, abbiamo dovuto sommare al valore appena ottenuto (altezza/3, lunghezza/2) un altra proporzione. Questo si è reso necessario poiché il metodo per il posizionamento del Label non accetta valori con la virgola, quindi era impossibile ottenere una proporzione corretta facendo solo /3 o /2.

```java
 alt.setLocation(imgStartX + imageSize / 3 + imageSize / 25,
 	imgStartY + imageSize / 2 + imageSize / 10);
``` 

Le variabili `imgStartX/Y` sono, come il nome suggerisce, le coordinate dove l'altimetro inizia. Per trovarle abbiamo preso l'altezza o la lunghezza del pannello, tolto la l'altezza o la lunghezza dell'immagine e diviso per 2. Siccome l'immagine è sempre posizionata al centro abbiamo la certezza matematica di ottenere il punto corretto.

```java
int imgStartX = (getWidth() - imageSize) / 2;
int imgStartY = (getHeight() - imageSize) / 2;
```
Abbiamo adottato un principio simile anche per quanto riguarda la dimensione del testo, trovate le proporzioni corrette abbiamo scritto un calcolo per la corretta dimensione del font ogni volta:

```java
font1 = new Font("Helvetica", Font.BOLD, imageSize / 17);
alt.setFont(font1);
alt.setText("H: " + altitude + " m");
```

Invece per quanto riguarda la lancetta, l'implementazione è risultata più semplice. Studiando un codice per la creazione di un orologio in java abbiamo isolato un calcolo che ci permetteva di ottenere, data la lunghezza della lancetta e l'angolo di inclinazione, un punto x e un punto y per dove la lancetta sarebbe andata a finire.

Per ottener questi dati abbiamo come prima cosa trovato la lunghezza della lancetta, definita con una proporzione. Il succo del calcolo è che data l'altezza del frame, viene tolta la parte superiore e la parte inferiore all'altimetro. Poi a questo viene tolto 1/3 della dimensione dell'immagine. L'ultimo valore invece, (15), è un valore trovato con molte prove, serve a gestire il fatto che l'altimetro stesso abbia un bordo grigio, che dovrebbe rappresentare la parte in metallo a cui è fissato solitamente l'altimetro negli aerei.

```java
int handH = getHeight() - imgStartY * 2 
	- (imageSize - (imageSize / 3)) + 15;
int handW = handH / 15;
```
La larghezza della lancetta è definita invece come 1/15 della lunghezza.

L'ultimo dato da trovare è quindi l'angolo. Per trovarlo abbiamo quindi prima scritto i calcoli per trovare le coordinate x e y della punta della lancetta, per poi disegnare la lancetta stessa. 

```java
//Calcolo coordinate
double xP = handH * Math.cos(angle) + getWidth() / 2;
double yP = handH * Math.sin(angle) + getHeight() / 2;

//Disegno lancetta, con il giusto spessore
Graphics2D g2d = (Graphics2D) g;
g2d.setStroke(new BasicStroke(handW));
g.setColor(Color.red);
g2d.drawLine((int) xP, (int) yP, getWidth() / 2, getHeight() / 2);
```

Questo ci ha poi permesso di avere un riscontro grafico su dove fosse posizionata la lancetta. Abbiamo quindi scoperto che, per essere sullo 0, il valore `angle` doveva essere uguale a `4.7`. Invece per fare un giro completo esso doveva essere a `11`?. Facendo un breve calcolo otteniamo che il valore `angle` ha un divario di `11-4.7=6.3`.
Facendo quindi `6.3/9`, dove 9 è il numero dei settori, otteniamo che ogni settore ha un "gap" di `0.63°`.

A questo punto mancava solo ottenere di quanto far muovere la lancetta. Siccome volevamo che si muovesse in maniera dinamica e non a scatti e/o a range, abbiamo calcolato per 1 metro di altezza quanto dovesse essere il valore di `angle`. La risposta è `1/0.63=1.58°`. Quindi ora non ci rimane che ottenere l'angolo da passare al calcolo sopra riportato, che è il seguente:

```java
//MIN_ANGLE è il valore minimo, ovvero 4.7
double angle = altitude / 1.58 + MIN_ANGLE;
```

<div style="page-break-after: always;"></div>


## Refactor e implementazione dei tools

Come per lo scroso progetto il nostro applicativo non si limita a permettere la guida del drone, ma ci permette anche di effettuare altre operazioni. Oltre alla live, già presente nel vecchio progetto, e alla posibilità di registrare ed eseguire sequenze, già citata in precedenza, in questa versione del progetto abbiamo implemntato anche un nuovo pannello pop up, che mostrasse tutte le statistiche collezionate dal drone. Infatti oltre a restituire i dati sulla posizione già citati in precedenza, il drone collezziona altre infromazioni, come dati sulla temperautra, il tempo di volo, la batteria, e molto altro.

Per questo capitolo paritremo però dallo sviluppo della live:

### Live
Lo sviluppo della live ha complicato abbastanza il nostro progetto, infatti abbiamo dovuto modifcare un po' sia il codice della classe responsabile di chiamare gli script, sia il codice degli script stessi. Il primo passo è stato creare la classe per la barra inferiore, poi abbiamo modificato la classe `Browser` e i suoi script, e in seguito abbiamo fatto il pannello delle informazioni.

#### ToolBarPanel
Questa è una classe molto semplice, rappresenta la barra inferiore della nostra applicazione, e contiente 2 pulsanti. Abbiamo deciso di rimuovere l'indicazione della batteria, in quanto già presente nel pannello dei dati di volo. 
Il codice della toolbar è quindi molto semplice. Al suo interno c'è un istanza della classe `Browser` e una della classe `AnalyticsFrame`, e due metodi: il primo per l'attivazione della live, e il secondo per l'attivazione del frame Analytics.

```java
private void jButton1ActionPerformed(
	java.awt.event.ActionEvent evt) {                                         
	
	try {
		browser.script();
		browser.openBrowser();
	} catch (IOException ex) {
		System.out.println("Errore apertura live");
		System.out.println(ex.getMessage());
	} catch (InterruptedException ex) {
		System.out.println("Errore browser");
	}
}                                        

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
	AnalyticsFrame anayltics=new AnalyticsFrame();
	new Thread(anayltics).start();
	anayltics.setVisible(true);      
}               

```

Come possiamo vedere nel primo metodo, quello dedicato alla live, vengono chiamati i 2 metodi per l'avvio dello script e per l'aperutra del browser, ma ne parleremo meglio nel prossimo capitolo.

Nel secondo metodo invece viene creato un riferiemnto ad AnalyticsFrame, poi viene istanziata una thread con questo riferimento e viene fatto partire il tutto.

#### Browser

-----

Una premessa importante per quanto concerne la live, se si vuole che essa funzioni bisogna aver installato `nodeJs` e `ffmpeg`.
Essi devono essere poi posizionati nelle loro locazioni di default:

Per MacOs:

NodeJS: `/usr/local/bin/`
FFmpeg: `/usr/local/bin/ffmpeg`

Per windows:

NodeJS: `C:\Program Files\nodejs`
FFmpeg: `C:\FFmpeg\`

-------

Come suggerisce il nome questa classe si occupa della gestione del browser, in questa classe possono essere attivati 2 script diversi. La scelta dello script verrà fatta in base al sistema operativo in uso. In entrambi i casi la live verrà visualizzata in modo automatico. Infatti in questa classe abbiamo solo due metodi che sono `script()` e `openBrowser()`

Parliamo prima del metodo `script()`

```java

public void script() throws IOException, InterruptedException {
	String os = System.getProperty("os.name").toLowerCase();
	if (os.contains("os")) {
		ProcessBuilder pb = new ProcessBuilder();
		pb.redirectErrorStream(true);
		String usrPath = System.getProperty("user.dir") + "/Live/Script/RunLiveMac.sh";
		pb.command("sh", "-c", usrPath);
		Process process = pb.start();
	} else {
		String usrPath = System.getProperty("user.dir") + "\\Live\\Script\\";
		String path = "cmd /c start" + usrPath + "RunLiveWin.bat";
		Runtime rn = Runtime.getRuntime();
		Process pr = rn.exec(path);
	}
}

```

Questo metodo ci permette di identificare su che sistema operativo sta girando il nostro programma e, in base se a questo, far partire due script diversi che si occupano di entrare in una cartella predefinita e attivare il codice della live.

Il metodo `openBrowser()` invece serve ad aprire una pagina web all'indirizzo `http://localhost:3000/index.html`. Qui sarà possibile vedere la live che sarà stata caricata dallo script precedentemente accennato.

```java
public void openBrowser() {
	String url = "http://localhost:3000/index.html";
	if (Desktop.isDesktopSupported()) {
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.browse(new URI(url));
		} catch (IOException | URISyntaxException e) {
			System.out.println("Error:" + e);
		}
	} else {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("xdg-open " + url);
		} catch (IOException e) {
			System.out.println("Error:" + e);
		}
	}
}
```
Per quanto riguarda gli Script per l'avvio di NodeJs, come già detto, parliamo di due file distinti. Entrambi faranno partire NodeJs e il file index.js adatto al sistema corrente. Infatti in questo file è obbligatorio indicare la locazione di FFmpeg, che cambia da sistema a sistema.

Ecco quindi gli script, che fanno essenzialmente la stessa cosa: raggiungere la locazione corretta e poi lanciare il comando Node.

>Script per MacOS

```bash
#!/bin/bash

script_dir=$(dirname "$0")
cd $script_dir
cd ../Tello-live-Nodejs
chmod +x indexMac.js
/usr/local/bin/node indexWin.js
```
-
>Script per Windows

```batch
cd ..
cd Tello-live-Nodejs
node indexWin.js
```

<div style="page-break-after: always;"></div>


#### AnalyticsFrame

Per lo sviluppo del frame con i dati di volo abbiamo, inizialmente, creato un JFrame che veniva fatto partire quando il puslante apposito veniva premuto. Tuttavia ci siamo accorti, nell'aggiunta dell'immagine, che questo approccio non avrebbe funzionato. Infatti abbiamo avuto la necessita di utilizzare il metodo `paintComponent` di Siwng, ma esso non può essere sovrascritto in un JFrame. Abbiamo quidi dovuto atturare un piccolo workaround: al posto che far partire un unico JFrame, che è anche una Thread, abbiamo deciso di creare un JPanel da inserire all'interno del JFrame. Quando il pulsante viene premuto, viene fatta partire la Thread del JFrame, che a sua volta fa partire la Thread del JPanel. In questo modo possiamo sovrascrivere il metodo `paintComponent` nel JPanel e ottenere la grafica da noi desiderata.

Qui il JFrame fa partire il JPanel:

```java
@Override
public void run() {
	new Thread(analyticsPanel1).start();
}
```

Come abbiamo detto nella progettazione questo pannello non deve potersi ingrandire e rimpiccilire troppo, per questo ne abbiamo dovuto forzare le dimensioni nel cotruttore.

```java
this.setMinimumSize(new Dimension(650, 450));
this.setMaximumSize(new Dimension(800, 570)); 
```

#### AnalyticsPanel

Una volta fatto partire dal JFrame, il pannello `AnalyticsPanel` può finalmente fare il suo lavoro. Per ottenere i dati abbiamo utilizzato il sistema di `Queue`, o code, usato in precedenza. Nella classe `Control` abbiamo una LinkedList dedicata solo a questo, chiamata `analyticsBufferData`. Sempre in `Control` viene creata un istanza di `AnalyticsPanel` e viene richiamato il solito metodo setter per impostare il riferimento alla coda.
Poi nella classe `Status` si ripete lo stesso procedimento fatto per `MainPanel`:

Nella classe `Status` è quindi presente un altra coda chiamata `analyticsBufferData; è inoltre presente il suo metodo setter, che sarà richiamato da AnalyticsPanel, come spiegheremo fra poco:

```java
public void setAnalyticsBufferData(Queue<String> analyticsBufferData) {
	this.analyticsBufferData = analyticsBufferData;
}
```
Nella coda verranno quindi inseriti tutti i dati di volo, in questo modo:

```java
analyticsBufferData.add("<html>"+
	"Pitch: " + status.get("pitch").toString()
	+"<br>"+"Roll: " + status.get("roll").toString()
	+"<br>"+"Yaw: " + status.get("yaw").toString()
	+"<br>"+"Altitude: " + status.get("h").toString()
	+"<br>"+"Position x: " + status.get("vgx").toString()
	+"<br>"+"Position y: " + status.get("vgy").toString()
	+"<br>"+"Position z: " + status.get("vgz").toString()
	+"<br>"+"Acceleration x: " + status.get("agx").toString()
	+"<br>"+"Acceleration y: " + status.get("agy").toString()
	+"<br>"+"Acceleration z: " + status.get("agz").toString()
	+"<br>"+"Lowest temperature: " + status.get("templ").toString()
	+"<br>"+"Highest temperature: " + status.get("temph").toString()
	+"<br>"+"Time of flight: " + status.get("tof").toString()
	+"<br>"+"Batteryt: " + status.get("bat").toString()
	+"<br>"+"Baro: " + status.get("baro").toString()
	+"<br>"+"Time of engine use : " + status.get("time")
		.toString()+"</html>");
```
Si noti che non aggiungiamo ad ogni elemento della lista un dato, ma inseriamo sempre i dati nella loro interezza. Questo perchè poi, quando arriveremo a stampre, non dobbiamo fare lunghi cicli per concatenare gli elementi e mostralri tutti, ma basta un unica istruzione per fare tutto.

Poi in AnalyticsPanel, per far sì che il nostro sistema di code funzioni, abbiamo istanziato una coda e un suo setter:

```java
private static volatile Queue<String> analyticsBufferData;

public void setAnalyticsBufferData(Queue<String> analyticsBufferData) {
	this.analyticsBufferData = analyticsBufferData;
}
```

AnalyticsPanel riprende poi alcune dinamiche del pannello con l'altimetro, anche in questo caso abbiamo un JLabel che conterra tutti i dati di volo, un font che verrà modificato e adattato, ed infine una BufferdImage. Il costruttore è quindi molto semplice, l'unico accorgimento che abbiamo preso è stato quello di fare un `import` statico dei metodi `toBufferdImage` e `resizeImage`.

```java
import static graphics.Model.resizeImage;
import static graphics.Model.toBufferedImage;
...

public AnalyticsPanel() {
	this.setLayout(new GridLayout(1, 1));
	this.setSize(200, 200);
	lab = new JLabel();
	this.add(lab);
	ImageIcon icon;
	icon = new ImageIcon(getClass().getClassLoader()
		.getResource("Analytics.png"));
	Image img = icon.getImage();
	buffImg = toBufferedImage(img);
}
```

Dopo il costruttore abbiamo implementato il metodo run, che si occupa solamente di calcolare la grandezza giusta per il font, impostare un margine alla stampa dei dati di volo, e aggiornare il testo all'interno del JLabel.

```java
public void run() {
	while (true) {
		font1 = new Font("Helvetica", Font.BOLD, this.getHeight() / 27);
		lab.setFont(font1);
		lab.setBorder(new EmptyBorder(0, 20, 0, 0));
		if (analyticsBufferData.size() > 0) {
			lab.setText(analyticsBufferData.poll());
		}
	}
}
```

Il metodo più importante è quindi `paintComponent`: esso si occupa di disegnare la BufferdImage. Anche in questo caso abbiamo dovuto fare dei controlli sulla grandezza dell'immagine, ma siccome il pannello ha comunque relativamente poco spazio di movimento e rimane abbastanza statico, è bastato prendere la lunghezza del pannello e trovare la lunghzza dell'immagine, definita come la metà del primo valore. L'altezza dell'immagine risulta poi essere la metà della lunghezza.
Infine l'immagine viene ridimensionata, con il metodo `resizeImage`, e disegnata. Non potendo disegnarla al centro del pannello, avrebbe infatti coperto le informazioni, abbiamo dovuto leggermente spostarla sull'asse delle x, ma con qualche test abbiamo raggiunto un risultato soddisfacente.


```java
public void paintComponent(Graphics g) {
	int panelW = getWidth();

	int iconW;
	int iconH;
	iconW = panelW - panelW / 2;
	iconH = iconW / 2;
	g.clearRect(0, 0, panelW, getHeight());

	g.setColor(Color.black);
	int x, y = 0;
	
	BufferedImage small;
	small = resizeImage(buffImg, (int) (iconW / 1.2), 
		(int) (iconH / 1.2));

	x = (this.getWidth() - small.getWidth()) / 2;
	y = (this.getHeight() - small.getHeight()) / 2;
	g.drawImage(small, (int) (x + x * 0.5), y, this);
}
```


# Test


## Test-case

## Risultati


## Mancanze e limitazioni conosciute



# Consuntivo

Ecco il nostro Gantt consuntivo


# Conclusioni



## Conclusioni personali


| Michea |
|--------|
|        |

| Alessandro |
|------------|
|            |

| Gianni |
|--------|
|        |




## Sitografia



## Allegati

Elenco degli allegati:

-   Files di progettazione
-   Gantt preventivo
-   Gantt consuntivo
-   Diari di lavoro
-   Codice sorgente
-   Prodotto finale
- 	 Qdc