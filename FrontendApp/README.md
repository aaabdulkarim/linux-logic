# Linux Logic (Frontend-App)

In diesem Ordner wird das Frontend der App Linux Logic entwickelt, dabei soll dieses möglichst kulant und anwenderfreundlich sein, mit der Intention Linux-Kenntnisse vermitteln zu können und soll dabei einen Gamification Aspekt miteinbeziehen. 

## Forschungsfragen
+ Wie kann die Entwicklung eines Frontends für die mobile Applikation "Linux Logic“, welche für das Android-Betriebssystem konzipiert ist, realisiert werden? 
+ Besteht das Potenzial die App gebrauchstauglich und intuitiv zu gestalten? 
+ Kann durch den Einsatz innovativer Technologien ein erleichterter Einstieg in Linux ermöglicht werden? 
+ Inwieweit können vielfältige Lern-Szenarien und praxisorientierte Übungen ein abwechslungsreiches und effektives Lernen von Linux auf mobilen Geräten optimal unterstützen?

## Geplantes Ergebnis
Im Rahmen des Diplomprojekts wird die Entwicklung eines voll funktionsfähigen Frontends der mobilen Applikation angestrebt, das in Design, Funktionalität und bis zur Backend-Anbindung vollständig realisiert ist. 
Darüber hinaus erfolgt eine umfassende Evaluierung der Gebrauchstauglichkeit, um eine optimale User Experience sicherzustellen.

## Technologien
- Jetpack Compose
  - Dies ist das moderne UI-Toolkit von Android, welches einem deklarative Programmierparadigma entspricht und eng mit nativen Android-APIs integriert ist. Es eignet sich hervorragend für Android-spezifische Apps, bietet jedoch weniger plattformübergreifende Flexibilität.
  - Vorteile:
    - Native Android-Integration: Es besteht ein einfacher Zugriff auf Android-spezifische APIs für Animationen, Backend-Funktionen, Navigation usw. 
    - Optimale Performance: Die direkte Nutzung von Android-Ressourcen ist reich an Performance, da die Architektur nicht komplexer und erweitert wird, wodruch kein Overhead durch zusätzliche Engines entsteht. 
    - Klares UI-Design: Mit deklarativen UI-Ansätzen lassen sich einfache und intuitive Benutzeroberflächen gestalten. (Ähnelt vom Prinzip her sehr der parallelen Technologie SwiftUI für die iOS-Entwicklung)
  - Nachteile:
    - Kein plattformübergreifender Support: Da diese Technologie nativ für Android angedacht ist benötigt es eine separate Implementierungen für andere Betriebssysteme. 
    - Limitierte Libraries spezifische Funktionen: Im Vergleich zu Cross-Plattform-Technologien gibt es weniger fertige Lösungen für sehr individuelle Komponente, beispielsweise webbasierte oder plattformübergreifende Terminals. 
    - Einfaches Design: Für das Design ist nur Material-Design (neuste Version 3) verfügbar man muss ein eigenes UI-Framework entwickeln.

## Fortschritt
Nachdokumentieren wie und was genau umgesetzt wurde in textueller Form
