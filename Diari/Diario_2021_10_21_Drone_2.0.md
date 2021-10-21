# Drone 2.0 | Diario di lavoro
##### Michea Colautti, Alessandro Aloise, Gianni Grasso
### Centro Professionale Trevano, 21.10.2021

## Lavori svolti


|Orario        |Lavoro svolto                                                   |Eseguito da         |
|--------------|----------------------------------------------------------------|--------------------|
|10:50 - 11:15 | Aggiornamento drone nuovo e prove di volo                      | Tutti              |
|11:15 - 12:20 | Discussione Prof. Albertini per consiglio                      | Michea             |
|11:25 - 12:20 | Implementazione Label su altimetro                             | Michea             |
|11:15 - 12:20 | Refactor commandPanel                                          | Alessandro         |
|11:15 - 12:20 | Refactor invio messaggi                                        | Gianni             |
|13:15 - 13:40 | Discussione nuova struttura con Prof. Petrini                  | Tutti              |
|13:40 - 14:00 | Modifiche a drone                                              | Alessandro         |
|13:40 - 16:30 | Implementazione KeyListener + refactor invio messaggi          | Gianni             |
|13:40 - 16:30 | Implementazione Label su Altimetro                             | Michea             |
|14:00 - 16:30 | Implementazione classe control + queue                         | Alessandro         |

##  Problemi riscontrati e soluzioni adottate

1. Per quanto riguarda l'implementazione dell'allineamento del `Label`, sul pannello dell’altimetro abbiamo impiegato tutta la giornata. Infatti non siamo inizialmente riusciti ad elaborare un metodo per rendere l'allineamento perfetto. Sotto consiglio del prof. Albertini abbiamo adottato questo approccio. Il punto in cui l'immagine inizia, sia per l'asse x che per l'asse y. 


```java
int imgStartX = (getWidth() - imageW) / 2;
int imgStartY = (getHeight() - imageH) / 2;

``` 
In questo modo prima togliamo dalla dimensione del pannello la dimensione corrispondente dell'immagine. Poi dividiamo per due, ottenendo la dimensione di uno dei due margini a lato dell'immagine.
Questo è possibile considerando che l'immagine è perennemente alienata al centro.
Dopo aver fatto questo, conoscendo la posizione dell'etichetta su cui il `Label` dell'altitudine dovrà essere posizionato possiamo aggiornare la posizione dello stesso campo di testo ad ogni `repaint`. La aggiorniamo in modo che la proporzione venga sempre rispettata

```java
/*
* Panel is on the field in the image when 
* proportion are 3 for Width and 2 for Heigth
*/
alt.setLocation(imgStartX + panelW / 3 + panelW / 25, 
imgStartY + panelH / 2 + panelH / 10);

//font 30 is ok when panelW is 330 330-->dim= panel/11
font1 = new Font("SansSerif", Font.BOLD, panelW / 12);
alt.setFont(font1);

``` 
Nel `setLocation` abbiamo dovuto aggiungere dei valori per correggere l'imperfezione data dalla dimensione dell'etichetta di sfondo. Ma con qualche tentativo siamo arrivati ad una soluzione soddisfacente.

------
------
Nell'implementazione della nuova struttura abbiamo avuto molti problemi nella comprensione della nuova logica. L'idea di base era usare un oggetto di tipo `Queue`, una specie di buffer, per immagazzinare i flussi di dati provenienti dai vari sistemi di input, come tastiera e `Leap Motion`. Questa classe è diventata la classe principale, infatti dal nostro nuovo `main` istanziamo tutti i componenti della GUI e inviamo al posto giusto i flussi di dati. In questo modo separiamo secondo una logia di tipo `MVC` (**M**odel **V**iew, **Controller**) il nostro progetto. Nello specifico, l'oggetto ti tipo `Queue` ci permette di inserire in un unico punto i flussi di comandi e di, al momento dell'estrazione, eliminarli in contemporaneo. Fare lo stesso lavoro con una lista sarebbe stato folle dal punto di vista delle prestazioni. La stessa classe `Control`  è in realtà una `Thread`, che riceve i dati dagli input.
Inseriamo qui la dichiarazione delle `Queue` e il metodo `run`.

```java
private final Queue<String> commandsBuffer;
...
Queue<String> commandsBuffer = new ArrayDeque<>();
Control = new Control(commandsBuffer);
...
public void run() {
	String command =commandsBuffer.remove();
}

``` 

------
------

Anche nell'implementazione del pannello laterale del drone abbiamo avuto qualche problema, che speriamo venga risolto dalla nuova logica di controllo.

Ecco quindi le soluzioni riassunte:

>1. Colloquio con Albertini + implementazione della nuova logica descritta sopra.
>2. Discussione con Petrini + nuova struttura di progetto 

 


##  Punto della situazione rispetto alla pianificazione

Siamo al passo con i tempi.
## Programma di massima per la prossima giornata di lavoro
1. Implementazione lancetta altimetro + avanzare con la grafica.
2. Implementazione della classe `Control` e sviluppo nuova struttura.
