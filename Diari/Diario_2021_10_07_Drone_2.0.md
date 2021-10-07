# Drone 2.0 | Diario di lavoro
##### Michea Colautti, Alessandro Aloise, Gianni Grasso
### Centro Professionale Trevano, 07.10.2021

## Lavori svolti


|Orario        |Lavoro svolto                                                   |Eseguito da         |
|--------------|----------------------------------------------------------------|--------------------|
|10:50 - 12:30 | Ricerca, "sorveglianza" e manutenzione eliche con stampante 3D | Alessandro         |
|10:50 - 12:30 | Ripresa codice grafica e inizio sviluppo nuovo progetto        | Michea             |
|10:50 - 12:30 | Refactor generale package drone                                | Gianni             |
|13:15 - 14:45 | Refactor generale package drone                                | Gianni, Alessandro |
|13:15 - 16:30 | Refactor package graphics                                      | Michea             |
|14:45 - 16:30 | Refactor classe Drone                                          | Gianni             |
|14:45 - 16:30 | Refactor package Command                                       | Alessandro         |

##  Problemi riscontrati e soluzioni adottate

1. Alessandro ha provato a stampare delle eliche in 3D, ma queste avevano ancora i supporti attaccati ed avevano alcuni difetti. Alessandro ha quindi provato a sistemare il tutto cercando di staccare i supporti, ma non è stato possibile.
2. Anche provando a stampare con le stampanti della scuola abbiamo avuto dei problemi con il materiale dissolubile in acqua. Per questo dobbiamo ancora trovare una soluzione. Se dovesse arrivare il materiale da noi ordinato per il drone potremmo non aver più bisogno della stampante 3D, anche se sarebbe stato comodo.
3. Nel fare il refactor del package drone ci siamo accorti di molte incongruenze, difetti, errori, ecc. nel codice da noi scritto, tra i più comuni troviamo:
	1. Riferimenti senza senso.
	2. Riferimenti doppi.
	3. Errori di struttura e sintassi a livello di convenzioni.

##  Punto della situazione rispetto alla pianificazione
Siamo al passo con i tempi

## Programma di massima per la prossima giornata di lavoro
1. Avanzare con il refactor del package `drone`, `command` e `graphics`
2. Provare a stampare dei nuovi supporti con la stampante 3D

