Aby uruchomić program należy skorzystać z przygotowanych skryptów.
Skrypt compile.sh skompiluje kod całego programu.
Skrypt run.sh uruchomi program.
Skrypt testRun.sh uruchamia testy.
Są to proste skrypty, któ¶e po prostu dołączają odpowiednie jary.
Te skrypty poprawnie działają na pracowni, w przypadku uruchomienia ich na innej maszynie prawdopodbnie naleźy z zmienić w nich polecenie javac8 na javac i java8 na java lub można po prostu ręcznie skompilować i uruchomić.

Na początku program wczytuje dane zawarte w plikach clientsIN.txt, daneIN.txt oraz holidaysIN.txt. po zakończeniu działania zapisuje dane do plików clientsOUT.txt, daneOUT.txt oraz holidaysOUT.txt. Brak wszystkich tych plików nie stanowi przeszkody dla działania programu.

W przypadku uruchomienia testów wymagane są pliki TESTclients.txt, TESTdanu.txt oraz TESTholidays.txt ich brak luv zmiana ich zawartości zakończy testy niepowodzeniem.
