SikuliRobotLibrary
==================

Exploratory Sikuli Java library for Robot Framework, built upon the AnnotationLibrary provided by the Robot's javalib-core distribution. Please note that this project is currently in a preliminary version status and needs many improvements! The SikuliRobotLibrary.html provides the current keyword documentation for the library.

This is a basic Maven project. To create a standalone jar package with dependencies, just type: "mvn clean package" in the directory where the pom.xml is located

NOTE! The sikuli-java version 1.0.1 maven dependency in the pom.xml is satisfied by making a local maven installation of the sikuli-java.jar file. This sikuli-java.jar file is obtained by running the sikuli-setup.jar (more details on the sikuli development page). Once the sikuli-java.jar has been obtained, the local maven installation is done with the command:

mvn install:install-file -Dfile=sikuli-java-1.0.1.jar -DgroupId=org.sikuli -DartifactId=sikuli-java -Dversion=1.0.1 -Dpackaging=jar

Here I have just renamed the .jar to contain the version number. After this the build can be run successfully. Please note that due to heavy platform dependencies, one must have the version of the sikuli-java.jar which corresponds to the platform to be tested e.g. Win32, Win64, Linux32, Linux64 ... etc. The native stuff will be populated from the .jar at runtime.

To make things roll with Robot Framework, one can use jybot or the .jar distribution of Robot Framework. When using the .jar distribution, one should set the CLASSPATH as:
set CLASSPATH=robotframework-2.x.x.jar;SikuliRobotLibrary-1.0-SNAPSHOT-jar-with-dependencies.jar;

and then run the test case file with the command:
java org.robotframework.RobotFramework run test.txt

For more hints and tips please see the wiki-page: https://github.com/jatalahd/SikuliRobotLibrary/wiki/Hints-and-Tips
