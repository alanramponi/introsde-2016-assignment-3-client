### Introduction to Service Design and Engineering: 3rd assignment (Alan Ramponi, 179850)

This is the **client repository** (server repository: https://github.com/alanramponi/introsde-2016-assignment-3-server).

The project consists in developing a SOAP web service. An ant `build.xml` file allows to automate the compilation, cleaning and execution; in addition to that, dependencies are managed with ivy. The whole code is well documented and commented.

The project has been developed individually and the server WSDL can be found on heroku at the following url:
https://introsde-assignment3-ar.herokuapp.com/ws/people?wsdl

**Extra tasks**: as in the assignment-2, the health profile is built dinamically (according to measure types).

### Project architecture
The project is structured in several packages in order to organize all the classes in a reasonable way. Inside the main common package (`introsde.assignment.soap`) it is possible to find:
* `client`: a package that contains the client source code:
  * **PeopleClient.java**: a class used to accomplish all 10 the assignment requests in order to print the results to both the console and the log file.
* `ws`: a package that contains all the classes generated with `wsimport`:
    * e.g. **CreatePerson.java**, **CreatePersonResponse.java**, **DeletePerson.java**, **DeletePersonResponse.java**, ...

### Tasks
The `build.xml` file contains some targets in order to execute various operations. In particular, using ant (`ant execute.client`) it is possible to accomplish all the requests of the assignment (that can be found here: https://sites.google.com/a/unitn.it/introsde_2016-17/lab-sessions/assignments/assignment-3). The target `execute.client` depends on `wsimport` and `install`, so it provides an high degree of automation (i.e. it is possible to run only this command to wsimport, install, clean, init and run the client). Among the other things, it is possible to clean the project using the **clean** target. As in the assignment-2, an additional task was performed, i.e. the extra request related to the dynamical health profile.

### How to run it
Since the server is already (correctly) deployed on Heroku, it is only needed to follow the steps below:
* **Clone** the repo: `git clone https://github.com/alanramponi/introsde-2016-assignment-3-client.git`;
* **Navigate** into the project folder: `cd introsde-2016-assignment-3-client`;
* **Execute** the client requests using ant: `ant execute.client`.
