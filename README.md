Logic Layer
============
Der Aufbau der Logikschicht ist wider ähnlich den anderen Schichten. Die LogicFacade bekommt die Controller von der ControllerFactory. Die Controller verwenden die TransferobjectCreator Klasse um DTO zu erzeugen. Diese DTOs werden als „Kommunikationspakete“ von den Controllern zurückgegeben.
Das Konstrukt der Transferteams ergab sich aus der notwendigen Trennung der Internen und Externen Teams. Für die Oberfläche sollten diese gleich aussehen. Für die Unterscheidung verwendet der Controller „instanceof“  was ansonsten eher zu vermeiden wäre. Allerdings konnten wir somit auch Fehler aus der Persistence-Schicht ausbügeln, ohne die Datenbank zu ändern.

Lebenszyklus eines Controllers
============
Ein Controller wird erstellt von der ControllerFactory, anschließend befindet er sich in einem Modus, in dem er darauf wartet, bis ihn der Anwender startet. Um einen Controller abzuschließen kann commit() aufgerufen (speichern) oder abort() verwenden werden (abbrechen).
Über den Aufruf start() kann sich der Controller eine Session erzeugen und alle benötigten Objekte über diese Session „refreshen“. Ab diesem Zeitpunkt können auch die Objekte auf diese Session zugreifen.
Während des Zustands „started“ besitzt der Controller eine Session auf die Datenbank und kann Objekte daraus laden. Dieses Laden kann aktiv, also über Zugriff auf die Model-DAO Klassen oder passiv, über „Lazy-loading“ erfolgen. In beiden Varianten kann der Controller, bei einem commit(), über den CascadingType alle zugehörigen bzw. veränderten Objekte speichern. Wenn ein Controller aborted wird, wird die Session beendet, ohne das die Objekte gespeichert werden, dadurch werden Änderungen nicht in die Datenbank geschrieben.
Über die Setter bzw. Getter des Controllers können die Daten gesetzt oder abgeholt werden. Diese Funktionen funktionieren allerdings nur, solange der Controller im Zustand „started“ ist. Wenn er nicht in diesem Zustand ist, besitzt er keine gültige Session und kann dadurch keine Objekt aus der Datenbank laden oder verändern. Auch das „Lazy-loading“ der DB Objekte funktioniert in diesen Zuständen nicht.
