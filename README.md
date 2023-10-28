# Процедура запуска автотестов
1. Скачать архив с приложением fmh-android.zip
2. Открыть приложение в Android Studio
3. Написать UI-тесты по тестовым сценариям
4. Запустить тесты на эмуляторе с API 29 командой ./gradlew connectedAndroidTest
5. По окончании прогона тестов сохранить результаты прогона из эмулятора /data/data/ru.iteco.fmhandroid/files/allure-results в репозиторий проекта.
6. Открыть Allure-отчет в браузере командой allure serve.