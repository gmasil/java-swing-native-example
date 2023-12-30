#  Java Swing Native Example

Example repo to create Windows EXE from Java/Kotlin code using [GraalVM](https://www.graalvm.org/downloads/) and [Bellsoft Liberica](https://bell-sw.com/pages/downloads/native-image-kit/#nik-22-(jdk-11)).

# Prerequisites
1. [Download](https://www.graalvm.org/downloads/) and [install](https://www.graalvm.org/22.0/docs/getting-started/windows/#prerequisites-for-using-native-image-on-windows) GraalVM for Windows (including all steps with Visual Studio)
2. [Download](https://bell-sw.com/pages/downloads/native-image-kit/) Liberica 
Native Image Kit
3. Setup Environment (`JAVA_HOME=path-to-bellsoft-sdk`, `GRAALVM_HOME=path-to-graalvm-home` and `PATH`)
    1. `JAVA_HOME`: set to Bellsoft Liberica SDK
    2. `GRAALVM_HOME`: to to GraalVM installation path
    3. `PATH`: add `%JAVA_HOME%\bin` and path to "Developer Command Prompt" from Visual Studio:
        1. `C:\Program Files\Microsoft Visual Studio\2022\Community\VC\Tools\MSVC\14.38.33130\bin\Hostx64\x64`
        2. `C:\Program Files\Microsoft Visual Studio\2022\Community\VC\Auxiliary\Build`

# Usage
1. Open a Windows CMD (not Powershell)
2. Run Gradle build: `.\gradlew jar`
3. Setup Visual Studio Environment: `C:\Program Files\Microsoft Visual Studio\2022\Community\VC\Auxiliary\Build\vcvars64.bat`
4. Create native image: `native-image -Djava.awt.headless=false -jar build\libs\java-swing-native-example-1.0-SNAPSHOT.jar java-swing-native-example`
5. (Optional) Remove terminal from EXE file (only show Java Swing Frontend): `editbin /SUBSYSTEM:WINDOWS java-swing-native-example.exe`

Also take a look at [build.cmd](/build.cmd)
