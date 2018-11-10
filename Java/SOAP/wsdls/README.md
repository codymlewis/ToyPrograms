# SENG3400A2
Cody Lewis - c3283349
## Compiling
Either run the shell script `./compile.sh`
or run
`javac -d ./wsdls/WEB-INF/classes/ ./wsdls/WEB-INF/classes/Currencies/Currencies.java
javac -d ./wsdls/WEB-INF/classes/ ./wsdls/WEB-INF/classes/IdentityStorage/IdentityStorage.java
javac ./localhost/axis/CurrencyService/Admin_jws/*.java
javac ./localhost/axis/IdentityService/Authorisation_jws/*.java
javac ./localhost/axis/CurrencyService/Conversion_jws/*.java
javac ./localhost/axis/IdentityService/IdentityService_jws/*.java
javac *.java`
## Running
Move the contents of the wsdls folder into <your tomcat directory>/webapps/axis/
The execute either `java CurrencyClient` or `java AdminClient`
