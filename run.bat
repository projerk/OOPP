@echo off

:: Đặt biến cho các đường dẫn
:: link tới javafx lib
set JAVA_FX_LIB_PATH=C:\javafx\lib
:: link tới file jar
set JAR_FILE_PATH=C:\Users\duong\Desktop\OOPP\target\Projerk-1.0-SNAPSHOT.jar
:: link tới thư viện JSON
set JSON_LIB_PATH=C:\java\json.jar
:: link tới thư viện SQLite
set SQLITE_LIB_PATH=C:\java\sqlite.jar

set SQLITE_API=C:\java\sqliteapi.jar
:: Đặt thư mục làm việc (thay đổi theo nơi bạn lưu trữ FXML nếu cần thiết)
set WORKING_DIR=C:\Users\duong\Desktop\OOPP

set LOGBACK=C:\java\slf4j.jar
:: Thay đổi thư mục làm việc
cd /d %WORKING_DIR%

:: Chạy ứng dụng JavaFX
java --module-path "%JAVA_FX_LIB_PATH%" --add-modules javafx.controls,javafx.fxml -cp "%JAR_FILE_PATH%;%JSON_LIB_PATH%;%SQLITE_LIB_PATH%;%SQLITE_API%;%LOGBACK%" app.Projerk

:: Đợi người dùng nhấn phím để thoát
pause