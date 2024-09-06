@echo off

:: Đặt biến cho các đường dẫn
:: link tới javafx lib
set JAVA_FX_LIB_PATH=C:\javafx\lib
:: link tới file jar
set JAR_FILE_PATH=C:\Users\duong\Desktop\OOPP\target\Projerk-1.0-SNAPSHOT.jar

:: Đặt thư mục làm việc (thay đổi theo nơi bạn lưu trữ FXML nếu cần thiết)
set WORKING_DIR=C:\Users\duong\Desktop\OOPP

:: Thay đổi thư mục làm việc
cd /d %WORKING_DIR%

:: Chạy ứng dụng JavaFX
java --module-path "%JAVA_FX_LIB_PATH%" --add-modules javafx.controls,javafx.fxml -jar "%JAR_FILE_PATH%"

:: Đợi người dùng nhấn phím để thoát
pause