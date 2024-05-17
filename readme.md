#  Java Swing Native Example

Example repo to create Windows EXE from Java/Kotlin code using [GraalVM](https://www.graalvm.org/downloads/).

# Prerequisites

Gradle will download the correct GraalVM JDK, but you have to set the `GRAALVM_HOME` environment variable yourself.

You can check the JDK path with `./gradlew javaToolChain`, look for the correct GraalVM JDK.

Then set the environment variable: `GRAALVM_HOME=.gradle/jdks/graalvm_community-22-amd64-windows/graalvm-community-openjdk-22.0.1+8.1`

On Windows you must have Visual Studio installed to provide a native C compiler.

# Build

Simply run `./gradlew nativeCompile` to create the binary file.

To update metadata required to properly run native images execute `./gradlew updateMetadata`.
This will start the application. Use all the features you want to include in the final image,
so the agent will pick up all required classes. Close the app gracefully to update the metadata.
Afterwards you can compile the project again and see if everything works.
