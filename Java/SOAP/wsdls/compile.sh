#!/bin/sh
echo "Started compiling the programs"
javac -d ./wsdls/WEB-INF/classes/ ./wsdls/WEB-INF/classes/Currencies/Currencies.java
javac -d ./wsdls/WEB-INF/classes/ ./wsdls/WEB-INF/classes/IdentityStorage/IdentityStorage.java
javac ./localhost/axis/CurrencyService/Admin_jws/*.java
javac ./localhost/axis/IdentityService/Authorisation_jws/*.java
javac ./localhost/axis/CurrencyService/Conversion_jws/*.java
javac ./localhost/axis/IdentityService/IdentityService_jws/*.java
javac *.java
echo "Finished compilation, now move the contents of the wsdls folder into <your tomcat directory>/webapps/axis/"
