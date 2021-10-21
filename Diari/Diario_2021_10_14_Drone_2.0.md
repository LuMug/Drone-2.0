# Drone 2.0 | Diario di lavoro
##### Michea Colautti, Alessandro Aloise, Gianni Grasso
### Centro Professionale Trevano, 14.10.2021

## Lavori svolti


|Orario        |Lavoro svolto                                                   |Eseguito da         |
|--------------|----------------------------------------------------------------|--------------------|
|10:50 - 11:35 | Refactor genarle del package grafica                           | Michea             |
|10:50 - 16:30 | Refactor Package  drone.command                                | Alessandro         |
|10:50 - 15:45 | Refactor della classe Drone                                    | Gianni             |
|15:45 - 16:30 | Test sulla classe Drone e risoluzione di alcuni accorgimenti   | Gianni             |
|11:35 - 14:45 | Refactor e implementazione vista laterale e frontale drone     | Michea             |
|15:00 - 16:00 | Refactor e implementazione vista superiore drone + altimetro   | Michea             |
|16:00 - 16:30 | Diario personale                                               | Michea             |




##  Problemi riscontrati e soluzioni adottate

1. Nel fare il refactor generale del package `graphics` non ho incontrato particolari problemi, ho dovuto rileggere un po' di codice e rivedere
molte dinamiche nella classe model e `main frame`. Il problema più grande è stato l'impossibilità di far muovere con un ciclo le immagini. Per questo per fare i test con le immagini ruotate ho impostato i gradi di rotazione al massimo manualmente.
2. Nello scrivere le classi per la vista laterale e superiore del drone ho avuto gli stessi problemi dell'anno passato. Infatti quando le immagini erano ruotate e io ridimensionavo la finestra ottenevo sempre delle immagini tagliate, con dimensioni sballate ed esagerate. Per questo dopo un'attenta osservazione mi son accorto che, quando l'immagine era ruotata la sua dimensione massima in lunghezza non era la lunghezza stessa ma, essendo l'immagine un rettangolo, la sua diagonale.
Ho quindi sviluppato un metodo per il movimento dell'immagine: Ho la dimensione in lunghezza massima del drone  e la sua diagonale, trovata con Pitagora. Sottraendo la lunghezza dell'immagine alla diagonale (diag-dim) ottengo "di quanto l'immagine straborderebbe". Prendo quindi questo ultimo valore e lo sottraggo alla dimensione effettiva dell'immagine. ne risulta un immagine sempre centrata e mai strabordante.
3. Per la vista superiore ho sviluppato una logica simile a quella descritta sopra.
4. Nello sviluppare l'altimetro ho avuto difficoltà nel posizionare in maniera dinamica il `Label` con l'altezza. Non ho infatti ancora trovato un modo di metterlo sempre sopra all'etichetta "altezza" presente sull'immagine.
5. Quando si ridimensionava la finestra, facendo sparire le immagini, il metodo `resize` restituiva un errore, in quanto cercava di dare a delle `BufferdImage` una dimensione negativa. Ho risolto ponendo un controllo sul ridimensionamento delle stesse.
6. Non riuscivo a fare comunicare il client con il drone, questo a causa di un comando scritto in modo errato nella SDK di riferimento, dopo alcuni tentativi il problema
è emerso da solo.

7. Abbiamo riscontrato un problema con la classe che si occupa di registrare i comandi una volta premuto il tasto, non é stata trovata ancora una soluzione ottimale. La prossima volta verranno valutato come risolvere tale problema.

Ecco quindi le soluzioni riassunte:

>1. Modifica manuale inclinazione.
>2. Implementazione logica delle diagonali
>3. Soluzione non ancora trovata.
>4. Posto controllo sul ridimensionamento.
##  Punto della situazione rispetto alla pianificazione
Siamo al passo con i tempi

## Programma di massima per la prossima giornata di lavoro
1. Finire di implementare il `Label? dell'altitudine
2. Avanzare con altri aspetti della grafica.
