CALL gradlew.bat jar

cd build
mkdir native
cd native

CALL vcvars64
CALL native-image -Djava.awt.headless=false -jar ..\libs\java-swing-native-example-1.0-SNAPSHOT.jar java-swing-native-example
editbin /SUBSYSTEM:WINDOWS java-swing-native-example.exe
