# Drone 2.0 | Diario di lavoro
##### Michea Colautti, Alessandro Aloise, Gianni Grasso
### Centro Professionale Trevano, 18.11.2021

## Lavori svolti


|Orario        |Lavoro svolto                                                      |Eseguito da         |
|--------------|-------------------------------------------------------------------|--------------------|
|10:50 - 16:30 | Implementazione Tool Bar + Analytics frame                        | Michea             |
|10:50 - 16:30 | Implementazione Leap Motion                                       | Alessandro         |
|10:50 - 12:20 | Registrazione comandi                                             | Gianni             |
|13:15 - 14:15 | Creazione file di registrazione                                   | Gianni             |
|14:15 - 16:30 | Scrittura file di registrazione                                   | Gianni             |




## Problemi riscontrati e soluzioni adottate


1. Nell'implementazione del `Leap Motion`, dopo aver implementato le classi precedentemente scritte da Samuele, un lavoro parzialmente svolto la scorsa lezione; abbiamo implementato la classe e la libreria `Leap Motion` in toto. Quindi con riferimenti appropriati. Tuttavia a questo punto all'esecuzione del programma NetBeans restituiva un errore, nello specifico il seguente errore: `nativ library exception`. In pratica il programma non riusciva a trovare il percorso e le dipendenze giuste per accedere alle librerie. Abbiamo quindi scaricato le librerie, preso spunto dal progetto vecchio, modificato i percorsi ed infine siamo riusciti a farlo funzionare su Windows. Per quanto riguarda MacOS abbiamo dovuto cambiare nuovamente percorsi e fare altri tentativi, ma alla fine ha funzionato.
2. Per quanto riguarda lo sviluppo della logica delle registrazioni invece, i comandi inviati al drone continuavano ad essere aggiunti alla nostra coda anche durante il salvataggio del file stesso, causando errori e perdita di dati. Per questo abbiamo dovuto utilizzare un `flag` booleano per distinguere quando il sistema stava lavorando al salvataggio di un file.
3. La scrittura nei file non veniva effettuata correttamente, c'erano problemi di formato. Perciò abbiamo riscritto completamente il metodo per la scrittura del file, implementando il carattere divisore ";" per permettere il corretto funzionamento delle sequenze.
4. L'ultimo problema identificato è che se l'utente dovesse salvare una sequenza con un nome di file già esistente nella cartella, non riuscirebbe a salvare in quanto la procedura di sovra scrizione non è ancora stata implementata.
5. Nello sviluppo della `Toolbar` ci siamo accorti che il pulsante `Leap ON` visibile nella GUI, per rimanere sempre in colonna con la barra laterale, avrebbe dovuto far parte della barra laterale stessa. Per questo la barra in fondo alla schermata avrebbe accolto solo i pulsanti per la live e per le statistiche.
6. Per quanto riguarda il pannello delle statistiche abbiamo dovuto adottare un approccio simile a quello usato per altri pannelli, nello specifico quello della grafica. Infatti non sapevamo bene come far apparire il Popup e come passare i dati. La soluzione è stata quella di far partire una `Thread` quando il pulsante `Analytics` viene premuto. Questo ci permette di creare un novo frame. All'interno di questo frame c'è il codice necessario per ricevere i dati da `Status`, provvisto di una nuova coda. Questa coda è istanziata nella nostra classe principale,`Control`. Essa ci permette di chiamare il metodo setter per la nostra ultima coda in `AnalyticsFrame`. Tutti questi meccanismi sono e saranno spiegati meglio nella documentazione.
7. Anche con un frame funzionante e le informazioni giunte correttamente alla classe con il pannello delle statistiche abbiamo fronteggiato un problema. Infatti siamo bloccati al punto in cui le informazioni vengono effettivamente scritte sul pannello, in quanto la logica per tirarle fuori in blocca dalla Queue per poi mostrarle non è ancora presente.

Ecco quindi le soluzioni riassunte:

> 1. Spunti dal progetto vecchio e molti tentativi.
> 2. Utilizzo `flag`.
> 3. Nuovo metodo di scrittura.
> 5. Cambio nella progettazione.
> 6. Utilizzo di Queue e nuova `Thread`. 


##  Punto della situazione rispetto alla pianificazione
Abbiamo recuperato leggermente rispetto al passato ma c'è ancora molto da fare.


## Programma di massima per la prossima giornata di lavoro1
1. Finire Leap Motion.
2. Iniziare la live.
3. Fix del problema con la sovra scrizione.
4. Esecuzione sequenze.
5. Finire toolbar e analytics.
6. Documentare.

