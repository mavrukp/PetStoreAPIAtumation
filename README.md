# PetStoreAPIAtumation
PetStoreAPIAtumation

Prerequisites: 
Java 8 or higher version should be installed and 
Maven 3 version should be downloaded (maven-3.8.1 is added in project folder) and should be set as maven path in local machine

In project folder, run "mvn install"

TestNG report is created as "emailable-report.html" at the end of the test execution in the "target\surefire-reports" folder. 

PS: if tests would like to be run in docker image, when Dockerfile is built, test will run as well. 
In project folder, run "docker build . -t imagename" 

or docker maven image should be downloaded with "docker pull maven:3.3-jdk-8" command and then run "docker run -it --rm --name my-maven-project -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.3-jdk-8 mvn clean install" command under project folder.

For sonarqube analysis, run command below;

mvn clean verify sonar:sonar  -Dsonar.projectKey=<ProjectKey>   -Dsonar.host.url=<sonar_url>   -Dsonar.login=<projectAuthenticationToken>

default url for sonar in local: http://localhost:9000
ProjectKey= The key while creating new project in SonarQube dashboard page
projectAuthenticationToken = The authentication key which is created while creating new projects in SonarQube dashboard page

jococo coverage report is created as "index.html" under <ProjectFolder>\target\site\jacoco folder and coverage can be 
checked from SonarQube dashboard page

