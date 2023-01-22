# Gestione trasporti

Si vuole realizzare un sistema per la gestione di una compagnia di trasporto pubblico.
Occorre realizzare un progetto Eclipse che includa il modello, le classi DAO e tutte le infrastrutture necessarie alla
gestione della persistenza tramite JPA. Occorre inoltre realizzare il database PostgreSQL a supporto del modello
applicativo.
Una volta realizzato il modello, implementare dei metodi di test che, attraverso main(), permettano di verificare il
corretto funzionameto di tutte le funzionalità.

Il sistema deve permettere l'emissione dei biglietti, sia da distributori automatici che da rivenditori autorizzati,
oltre che l'emissione di abbonamenti settimanali e mensili di tipo nominativo ad utenti dotati di tessera. La tessera
ha validità annuale e deve essere rinnovata alla scadenza. Ogni biglietto ed abbonamento è identificato da un
codice univoco. Deve essere possibile tenere traccia del numero di biglietti e/o abbonamenti emessi in un dato
periodo di tempo in totale e per punto di emissione. I distributori automatici possono essere attivi o fuori servizio.
Occorre inoltre permettere la verifica rapida della validità di un abbonamento in base al numero di tessera
dell'utente controllato.
Il sistema deve inoltre prevedere la gestione del parco mezzi. I mezzi possono essere tram o autobus ed avere
una certa capienza in base al tipo di mezzo. Ogni mezzo può essere in servizio o in manutenzione ed occorre
tenere traccia dei periodi di servizio o manutenzione di ogni mezzo. Quando un biglietto viene vidimato su un
mezzo, esso deve essere annullato e deve essere possibile acquisire il numero di biglietti vidimati su un
particolare mezzo o in totale in un periodo di tempo.
Ogni mezzo in servizio può essere assegnato ad una tratta, che è caratterizzata da una zona di partenza, un
capolinea ed un tempo medio di percorrenza. Occorre tenere traccia del numero di volte che un mezzo percorre
una tappa e del tempo effettivo di percorrenza di ogni tratta.
