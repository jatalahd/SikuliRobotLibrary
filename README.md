SikuliRobotLibrary
==================

Exploratory SikuliX-1.1.0 Java library for Robot Framework, built upon the AnnotationLibrary provided by the Robot's javalib-core distribution. Please note that this project is currently in a preliminary version status and needs many improvements! The SikuliRobotLibrary.html provides the current keyword documentation for the library.

This is a basic Maven project. To create a standalone jar package with dependencies, just type: "mvn clean package" in the directory where the pom.xml is located. The build uses the SikuliX-1.1.0 version of sikulixapi.jar, which is downloaded from an external repository during compilation. At the time of writing it is not yet available in the Maven Central and currently the OCR-related files are missing from the api-distribution. See http://www.sikulix.com/ for details on the progress. The native stuff is packaged in the api-distribution and will be populated from the sikulixapi.jar at runtime.

To make things roll with Robot Framework, one can use jybot or the .jar distribution of Robot Framework. When using the .jar distribution, one should set the CLASSPATH as:
set CLASSPATH=robotframework-2.x.x.jar;SikuliRobotLibrary-1.0-SNAPSHOT-jar-with-dependencies.jar;

and then run the test case file with the command:
java org.robotframework.RobotFramework run test.txt

For more hints and tips please see the wiki-page: https://github.com/jatalahd/SikuliRobotLibrary/wiki/Hints-and-Tips
