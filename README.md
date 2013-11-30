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

Hint for Linux users! It is possible to run several Sikuli test suites simultaneously when using the xvfb virtual screen buffer and isolated xservers. As an example one can create three xserver instances with xvfb as:

FIRST start separate xservers and assign a virtual screen on them:
startx -- /usr/bin/Xvfb :1 -screen 0 1960x1080x24 &
startx -- /usr/bin/Xvfb :2 -screen 0 1960x1080x24 &
startx -- /usr/bin/Xvfb :3 -screen 0 1960x1080x24 &
THEN start test cases:
DISPLAY=:1 java org.robotframework.RobotFramework run test.txt
DISPLAY=:2 java org.robotframework.RobotFramework run test.txt
DISPLAY=:3 java org.robotframework.RobotFramework run test.txt

Note the dependency between the parameters :1, :2 and :3 between xserver creation and test run startup. In Jenkins I have used the following script:

export CLASSPATH=./testi/robotframework-2.7.7.jar:./testi/SikuliRobotLibrary-1.0-SNAPSHOT-jar-with-dependencies.jar:
startx -- /usr/bin/Xvfb :${BUILD_NUMBER} -screen 0 1960x1080x24 &
psid=$!
DISPLAY=:${BUILD_NUMBER} java org.robotframework.RobotFramework -x rbot.xml run ./test.txt
pkill -TERM -P $psid
exit 0

The ProcessId in this shell-script is used for killing the xserver and all related stuff after the test run, otherwise the xserver will be left running as a zombie process forever. I have noticed that it is not possible to start two or more xservers to the same :x display, therefore the :${BUILD_NUMBER} is used for giving always a new display number for the virtual screen buffer. I have run this setup in a laptop, but I have never tried to run on a headless Linux box. It might work in a headless setup also, but I cannot promise that.
