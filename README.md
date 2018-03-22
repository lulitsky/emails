#KFZTeile24 Coding Task

## Implementation details
The application reads the email details from the input file into stream and runs the email sending service in the threads.
The thread number is configurable and can be tuned for the specific machine.
I used the standard Java-provided Thread executor, that was good enough for my machine, however, it can be tuned and 
more specific Executors can be built to improve the performance. 
The executor is provided by class ExecutorFactory, that is extendable and allows to build more sofisticated executors.

The email service is defined in interface IEmailService and injected into the application as mock implementation.
If real email sending is implemented, as new service, it can easily replace the mock service.


## Used frameworks and libraries
I implemented the task in pure java, but used Maven as the tool for building the program and Junit as the library for unit tests.
slf4j is used for logging and lombock library for easy logging setup.


## Build and Execution
To build the application run "mvn clean install" command
Then execute the java class EmailApplication with the following arguments: 
* "-i \<path to input file\>" - required
* "-n \<number of threads\>" - optional


