Logic Layer
============
Der Aufbau der Logikschicht ist wider �hnlich den anderen Schichten. Die LogicFacade bekommt die Controller von der ControllerFactory. Die Controller verwenden die TransferobjectCreator Klasse um DTO zu erzeugen. Diese DTOs werden als �Kommunikationspakete� von den Controllern zur�ckgegeben.
Das Konstrukt der Transferteams ergab sich aus der notwendigen Trennung der Internen und Externen Teams. F�r die Oberfl�che sollten diese gleich aussehen. F�r die Unterscheidung verwendet der Controller �instanceof�  was ansonsten eher zu vermeiden w�re. Allerdings konnten wir somit auch Fehler aus der Persistence-Schicht ausb�geln, ohne die Datenbank zu �ndern.

Lebenszyklus eines Controllers
============
Ein Controller wird erstellt von der ControllerFactory, anschlie�end befindet er sich in einem Modus, in dem er darauf wartet, bis ihn der Anwender startet. Um einen Controller abzuschlie�en kann commit() aufgerufen (speichern) oder abort() verwenden werden (abbrechen).
�ber den Aufruf start() kann sich der Controller eine Session erzeugen und alle ben�tigten Objekte �ber diese Session �refreshen�. Ab diesem Zeitpunkt k�nnen auch die Objekte auf diese Session zugreifen.
W�hrend des Zustands �started� besitzt der Controller eine Session auf die Datenbank und kann Objekte daraus laden. Dieses Laden kann aktiv, also �ber Zugriff auf die Model-DAO Klassen oder passiv, �ber �Lazy-loading� erfolgen. In beiden Varianten kann der Controller, bei einem commit(), �ber den CascadingType alle zugeh�rigen bzw. ver�nderten Objekte speichern. Wenn ein Controller aborted wird, wird die Session beendet, ohne das die Objekte gespeichert werden, dadurch werden �nderungen nicht in die Datenbank geschrieben.
�ber die Setter bzw. Getter des Controllers k�nnen die Daten gesetzt oder abgeholt werden. Diese Funktionen funktionieren allerdings nur, solange der Controller im Zustand �started� ist. Wenn er nicht in diesem Zustand ist, besitzt er keine g�ltige Session und kann dadurch keine Objekt aus der Datenbank laden oder ver�ndern. Auch das �Lazy-loading� der DB Objekte funktioniert in diesen Zust�nden nicht.
