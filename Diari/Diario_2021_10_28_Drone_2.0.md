# Drone 2.0 | Diario di lavoro
##### Michea Colautti, Alessandro Aloise, Gianni Grasso
### Centro Professionale Trevano, 21.10.2021

## Lavori svolti


|Orario        |Lavoro svolto                                                      |Eseguito da         |
|--------------|-------------------------------------------------------------------|--------------------|
|10:50 - 16:30 | Tentativo di implementazione lancetta Altimetro                   | Michea             |
|10:50 - 14:00 | Implementazione logica di registrazione sequenze                  | Alessandro         |
|10:50 - 14:00 | Implementazione Key Listener                                      | Gianni             |
|14:50 - 16:30 | Tentativo di fusione Key Listener con rec e passaggio istruzioni  | Alessandro e Gianni |

##  Problemi riscontrati e soluzioni adottate


1. Per quanto riguarda l'aspetto grafico abbiamo avuto un unico problema. La lancetta. Non siamo riusciti a farla girare e posizionarla come volevamo. Infatti benché essa stazionasse perfettamente al centro dell'altimetro non c'è stato verso di farla girare nel senso giusto. infatti ruotandola immagine si de-posizionava e spostava, abbiamo provato diverse soluzioni; come usare una riga al posto di un immagine, usare le coordinate, usare la trigonometria ecc.
Purtroppo non abbiamo ancora trovato una soluzione.
2. Il secondo problema si è presentato nell'uso delle `Queue`. Infatti per la velocità di input che avevamo esse erano troppo lente. Dovremo quindi optare per l’uso di un nuovo tipo di coda, le `Linked List`.
3. Un altro problema è stato nuovamente la nostra struttura; infatti, spesso abbiamo avuto dubbi che abbiamo potuto in parte risolvere con l'aiuto del professore.  


Ecco quindi le soluzioni riassunte:

>1. Soluzione ancora in elaborazione.
>2. Soluzione ancora in elaborazione.

 


##  Punto della situazione rispetto alla pianificazione

Siamo al passo con i tempi.

## Programma di massima per la prossima giornata di lavoro
1. Implementare lancetta altimetro.
2. Fare qualche prova di volo, una volta risolto il problema delle `Queue`.

