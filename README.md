Die Geschützte-Lebenslauf-Applikation ist eine Spring-Boot-basierte Anwendung, mit der ein Lebenslauf (CV) online bereitgestellt wird, jedoch nur für Personen sichtbar, die über einen gültigen, zuvor generierten Zugriffsschlüssel verfügen.
Über eine einfache Admin-Oberfläche können Schlüssel flexibel erstellt oder gelöscht werden. Dadurch kann Felix gezielt kontrollieren, wer seinen Lebenslauf einsehen darf – ohne dass er eine PDF versenden oder öffentlich ins Internet stellen muss.

🔑 Zugriffssystem für Lebenslauf
Zugriff auf den CV nur mit gültigem Schlüssel
Schlüssel wird in der URL oder als Eingabefeld im Frontend übergeben
Ungültige oder gelöschte Schlüssel → Zugriff verweigert

🛠 Admin-Funktionalität
Schlüssel erstellen
Schlüssel löschen
Übersicht aller aktiven Schlüssel
Sicherheit: Admin-Login (optional)
Schlüssel sind eindeutige Token (z. B. UUID)

📄 Lebenslauf-Anzeige
Lebenslauf als HTML-Seite oder eingebettete PDF
Darstellung modern, responsive und optimiert für Mobilgeräte
Kein Zugriff ohne gültigen Schlüssel


🛠 Technologie-Stack
Backend (Spring Boot)

Java 17
Spring Boot (Web)
REST-Controller
Token-Validierung
Speicherung der Schlüssel in XML Datei
Frontend
HTML, CSS, JavaScript
Einfaches Formular zur Schlüssel-Eingabe
Lebenslauf-Ansicht als geschützte Seite
