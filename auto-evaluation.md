# Auto Evaluation
In our project all things work perfectly as planned and we followed our protocol. This will be demonstrated in detail in the demo.

## Achievement of objectives  

We have planned different objectives and milestones in the initialization phase of this project. we faced some difficult situations to manage the time and organization. However, we were able to successfully achieve the objective as we planned with some alteration plan. Below section explains our planned methodology and objective in a different phase.

We have followed Scrum Methodology for this project and we have planned a complete project with all necessary tasks with the utilization of different phases of the Software Development Life Cycle (***SDLC***). 

**Requirement Gathering** 
We have identified the requirement of this project with the help of our course Professor. Also, further, we identified the technical requirement with group meetings.

**Planing** 
We have planned all technical functionalities and the architecture of the application in this phase. Also, we have planned programming language, Integration Development Environment and the protocol of the application. We discussed with our meta group to achieve interoperable communication at the end.  

**Design** 
We have designed our protocol and other UML diagrams in this phase to describe our application with detail. The main objective of this phase is to design the Protocol. We have developed a protocol standard for our application that help us to develop proper P2P application with define standard. 

**Development**  
In this step, we start to develop our application using Java SE (Socket Programming).  

**Testing**  
We did automation testing using JUnit testing framework to assure the functionality of each unit of the application. Also, we did integration testing after integrated all the modules of Tracker and Member to assure the complete functionality. We have faced some errors and we were able to fix those successfully during this period. Also, we made some modifications wherever it is necessary. 



## What works in Tracker-Server

- Member registration
- Liveliness check - Checking the member status by sending request to the selected member and update the member list. 
- Return member list 
- Accept connection
- Read messages from member
- Decode messages to request standard
- Respond to the member based on the request
- Error Handling
    * Invalid message arguments
- Blocking Queue for queue-based execution


## What does not work in Tracker Server/ Limitations
- Cannot Handle large set of request
- Automatic liveliness check not implemented 


## What works in Member

- Accept new connection
- Read messages from member
- Decode messages to request standard
- Respond to the member based on the request
- Proof of Work 
    * Finding new cheese
- Exchanging new transaction
    * Getting new transaction from other members and store to the transaction table
    * Sending new transaction to  other members
- Exchanging new cheese
    * Getting new cheese from other members and store to the cheese stack
    * Sending newly created cheese to  other members
- Exchanging cheese stack
    * Getting cheese stack from other members and store it in local 
    * Sending updated cheese stack to  other members based on request
- Validation
    * Validating new cheese sent by another member.  
    * Send wrong cheese request on failure of validation. 
    * Store in the cheese stack on successful validation. 
- Asymmetric Encryption     
    * All the transactions will be encrypted using private key before it gets transmitted over the network. 
    * Receiver member will decrypt the message with public key.
- Tracker-Member communication 
    * Request for register
    * Inform about the dead member
    * Get member list
- Liveliness check
    * Respond to the server when the server sends liveliness request to check the member status
- Inform to the Tracker server about the dead member
- Error Handling
    * Invalid message arguments
- Blocking Queue for queue-based execution
- Multi-Threading
    * The ability to handle multiple executions of the different task in parallel
    * accepting incoming request and send the request in parallel
- Optimization 
    * Connection will be closed after a successful response and request.
    * Setting variables null - Automatically JR Garbage Collector will clean all null variables after successful execution of functions. 



## What does not work in Member/ Limitations of the Member
- Cannot Handle large set of request






|Functions                |State|
|----------------|-------------------------------|
|Create Member     |OK|
|Add member to the tracker         |OK       |
|Synchronize the member list          |OK|
|Get the member list          |OK|
|Listening to network          |OK|
|Get cheese list from other members          |OK|
|Proof of work         |OK|
|Broadcast new cheese to other members         |OK|
|Broadcast new transaction to other members         |OK|
|Validate a Cheese        |OK|
|Add the Cheese to the Chain          |OK|
|Drop a duplicated/non valid Cheese          |OK|
|Asymmetric Encrption          |OK|

## Time spent and contribution by each member:

|Member	Time        |spent (hours)	|Contribution|
|----------------|-------------------------------|-------------------------------|
|Dhayananth DHARMALINGAM (Project leader)|50	|Scrum Master,Member side and Testing|
|Madhubhani RANCHA GODAGE	|30	|Tracker-server side and Testing|
|Rediet TADESSE	|30	|Member side and Testing|
|Sanjana GOVINDASWAMY	|30	|Tracker-server side and Member Side|


## Good development practices we followed are listed below:

|Category               |Followed best practices                        |
|----------------|-------------------------------|
|Git |Commited only source and configuration.<br/>Used .gitignore file(s). <br/> Do not store a zip of the project.<br/>Commit/push often.<br/>Provided good commit messages.<br/>Used English for all commit messages.<br/>When pair-programming in  single machine and mentioned in the commit message who contributed to the commit.  |
|Code|write code in English.<br/>Indent/format code properly.<br/> Learn how to use tools/editors to do it.<br/>Avoid mixing spaces and tabs.<br/>keep the code clean.|
|Networking|Test a lot and often<br/>Have automated tests<br/>Document how to use, compile, test and start the project.<br/>Document how to understand and continue the project.|



             
