Решение тестового задания для Metaquotes

Тестирование мобильного приложения Tradays 

Для запуска необходимо:

1. Запустить своё тестовое мобильное устройство
2. Если мобильное устройство отличается от прописанного в файле Device.java, то 
необходимо добавить его туда и в файле Driver.java в методе setUpDriver() прописать 
тестовое устройство.
По умолчанию тест запустится на устройстве Pixel4, если требуется запустить тест на другом устройстве,
то необходимо добавить его название в переменные среды для поля deviceName. Например: ```deviceName=Pixel4```
3. Запустить Appium-server (его конфигурация тоже прописана в файле Device.java)
4. Запустить сам тест командой 
```mvn clean test```
5. Для построения отчёта запустить команду 
```mvn allure:serve```

Логи автоматически пишутся в файл
```log4j/target/metaquotes-logback.log``` 