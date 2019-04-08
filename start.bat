@call echo **************************
@call echo ** Clean and build .jar **
@call echo **************************
call mvn clean package -DskipTests
@call echo.
@call echo *********************************************
@call echo ** Build docker image (schedule-app-image) **
@call echo *********************************************
call docker-compose build
@call echo.
@call echo ***************************
@call echo ** Run docker containers **
@call echo ***************************
call docker-compose up
:End
@cmd /k