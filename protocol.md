# CHEESE-COIN-SYSTEM PROTOCOL  

 ### This file contains complete details about the protocol implementation which has been adopted in CHEESE COIN SYSTEM
 
 The **CHEESE COIN SYSTEM**  developed including folliwing objects and functionalities.
 
 > ***Tracker*** : The Server which maintains a updated list of details (IP,Port,= and Account name) of peer(s)/member(s). Tracker can distribute the members list to the member on demand. It will keep track of "liveliness" status of the member network.
 >
 > ***Member*** : Peer/Member synchronized with other member/s on the platform to share, update, add cheese to the **CHEESE COIN**. Member/s can perform *Proof of Work* to add a new *cheese* to the *stack*. Also, can validate a block.  

 
 # Protocol of the application 
 This protocol must be followed in order to join the **CHEESE COIN SYSTEM**.  
    
> ## **MESSAGE FORMAT**
>
> **RESPONSE** 
>   
>     [STATUS_CODE] [RESPONSE_N]

>  > **STATUS_CODE**: Status of the response (404 : NOT_FOUND).


>  > **[RESPONSE_N]**: Set of *Response* objects or messages.

> **REQUEST**  : 
>
>     [REQUEST TYPE ] [COMMAND] [ARG_N].. 

>  > **COMMAND**: The purpose of the request (ex - *REQ_CHEESE_CHAIN*).
>  >
>  > **[ARG_N]-Optional**: Argument/s sends with the *COMMAND*. 

## Response Code
We follow response code format in every response to understand the response status. We following HTTP status code standard in **Cheese_Stack_System**. ( See more info : [http status code ref](https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html) )

> * Response codes description
>	 
>   * **200** : Success / Request is success
>   * **201** : Created 
>   * **203** : Accepted 
>   * **205** : Acknowledge
>   * **415** : Unsupported media format



## Request command
We follow request command string format in every request to understand the request type. 
> * Request Command description
>	 
>   * **REQ_CHEESE_CHAIN** : Request cheese chain
>   * **REQ_NEW_TRANS** : new transaction request 
>   * **REQ_ACK** : Acknowedged
>   * **REQ_VALIDATION** : Validation requset
>   * **REQ_NEW_CHEESE** : New Cheese Request
>   * **REQ_DEATH_MEMBER** : Reporting death member - Inactive member
>   * **REQ_TEST** : Test request
>   * **REQ_REGISTER** : Request for register new member 

## Request type
We follow request command string format in every request to understand the request type. 
> * Request Command description
>	 
>   * **GET** : Sympol Request
>   * **POST** : Complex request with request body



## Tracker Server and Member Communication 

Below section discuss protocol used between member and Tracker server.

In this section, we describe all ***request*** and its format send by **member** and corresponding ***response*** and its format returned by **Tracker server** to the requested **member**. Additionally we describe contents of the requests and the responses.

> **Note**: Format used below: `[from]`>>`[to]`. This describes the message sent by `[from]` to the `[to]`. 


##### 1. REGISTER MEMBER TO THE CHEESE_STACK_SYSTEM `[MEMBER]`>`[TRACKER_SERVER]` 

This protocol is used by the Member to register itself on the tracker server as a Member of Cheese Stack System.


 - **Request**
	  
	  `[POST] [REQ_REGISTER]  [Member]`
	  * **Member**  : Member object consist of Member IP and Member Port. 
    
    * ***Time_out-03s*** - Request time is 03 seconds. If response not received within 03 seconds, request will be resent. 

  - **Response** 
	
	`[res_code] [member_list]`
	* **member_list**  : Collection of member object. 
    
	`Ex: 201 - CREATED
  `
- **Sample request(s) and response(s)** 
    
    
    Member A :> [POST] [REQ_REGISTER] [New_Member]
    
    
    Tracker :> [201] [Member_List] 
    ```json
        [
          {"member_id":"1",
          "member_name":"john",
          "member_ip":"192.168.1.2",
          "member_port":"2235",
          "member_status":"active",},
          {"..."}
        ]
    ```

##### 2. REQUESTING LIST_OF_MEMBERS `[MEMBER]`>`[TRACKER_SERVER]` 

**LIST_OF_MEMBERS** This protocol is used by **Member** in order to request ***updated member list*** from **Tracker Server**.

  > **Note**: Format used below: `[from]`>>`[to]`. This describes the message sent by `[from]` to the `[to]`. 

  
- >**Request** - 
   
    `[GET] [REQ_MEMBER_LIST] `
    * ***Time_out-03s*** - Request time is 03 seconds. If response not received within 03 seconds, request will be resent. 
   
- >**Response**: 

    `[res_code] [list_of_members]`
	
	 * variables  description
	   * ***200***: Response code     
       * ***list_of_members
       ***:  *List of member objects in JSONFormat* this JSON object contains data of all active members with following Keys ( **member_ip** and  **member_port** ).

  - **Sample request(s) and response(s)** 
    
    
    Member A :> [POST REQ_MEMBER_LIST]
    
    
    Tracker :> [200 Member_List] 
	 

 

#### 3. LIVELINESS_PING `[TRACKER_SERVER]`>`[MEMBER]` 

***LIVELINESS_PING*** protocol is used by Tracker server in order to assure the availability of the  members in the network. 
 
 - **Request**
 
	 `[GET][PING] ` 
 
  - **Response** 
  
	`[205]`
	
	`Ex: 205 - ACKNOWLEDGE`
 
## Member to Member (Peer to Peer) Communication  

Below section discuss protocol used in member to member communication.

In this section, we describe all ***request*** and its format send by **member** and corresponding ***response*** and its format returned by another **member** to the requested **member**. Additionally we describe contents of the requests and the responses.

> **Note**: Format used below: `[from]`>>`[to]`. This describes the message sent by `[from]` to the `[to]`. 


##### 1. REQUESTING CHEESE STACK `[NEW_MEMBER]`>`[MEMBER]`

This protocol is been followed by new member of *Cheese Stack System*, in order to get complete chain of ***Cheese_Stack***. New member will select one of the existing member randomly and send request for ***Cheese_Stack***.
 
 - **Request**
 
	 `[GET] [REQ_CHEESE_STACK] ` 
 
  - **Response** 
  
	`[res_code] [CHEESE_STACK]`

 - **Sample request(s) and response(s)** 
    
    Member A :> [GET REQ_CHEESE_CHAIN ]
    
    Member B:> [200 Cheese_Chain] 
    ```json
        [
          {"version":"0215xdF",
          "hash":"xkG5wzIkF8KNe4rIpW4IX6uYK1MyRb41hinYDppOGTs=",
          "timestamp":1583101963386,
          "previousHash":"sdfsdfsdfsf",
          "public_transaction_id":"12", 
          "reward":"0.12",
          "member_id":"21"},
          {"..."}
        ]
    ```

##### 2. SHARE TRANSACTION `[MEMBER]`>`[MEMBER]`
 
This Protocol is followed by *Member* in order to share new transaction details to other active *Member*/s for the process of **PROOF OF WORK**. 
 
  
 - **Request**
	 
	`[POST] [NEW_TRAN] [transaction, public key]`
 
    * This request will send to all the active members of the network to inform about new transaction
    * **transaction**: The encrpted transaction will be send to all the members. 
    * **public key**: Public key will be used to decrypt the encrypt the decrypted transaction data. 
 
    * ***`Time_out-03s`*** - Request time is 03 seconds. If response not received within 03 seconds, request will be resent. 

    * ***Kill Connection*** - After second request, if still response pending, then the connection will be closed. 

 - **Response**
	 
	`[res_code] `
 
     * **acknowledge**: Receiver member acknowledged to the sender member. Ex : `[205]` 
    
- **Sample request(s) and response(s)** 
    
    Member A :> POST[ REQ_NEW_TRAN, transaction, public_key ]
    ```json
        [{"amount":20.0,
        "public_transaction_id":12,
        "time_stamp":1583286965000,
        "senderList":
                [{"amount":20.0,
                "date_time":1585022945000,
                "receiver_account_id":2,
                "transaction_id":1,
                "sender_account_id":1,
                "sender_id":1}],
        "date_time":1583294964000,
        "receiver_account_id":2,
        "transaction_id":1},
        {"public_key":"sdjeXjshd32Jjfnvbiie%jdfhne233="}
        ]
    ```
    Member B:> [205]

    
##### 3. BROADCASE NEW CHEESE DATA `[MEMBER]`>`[MEMBER]`
This protocol will be followed by all the members of ***CHEESES_COIN_SYSTEM***, in terms of broadcasting new cheese to the network. Whenever a **Member** created a new **Cheese**, they will broadcast that new **Cheese** to all other member for the purpose of validation. 

 - **Request**
	
	`[POST] [REQ_NEW_CHEESE] [cheese_data]`
	
	 * **cheese_data**: `cheese_data` contains new cheese information including `member_id`. 
   
   * ***`Time_out-03s`*** - Request time is 03 seconds. If response not received within 03 seconds, request will be resent. 

 - **Response**
	 
	`[res_code]`
 
     * **res_code**: Acknowledge [205]. 

  - **Sample request(s) and response(s)** 
    
    Member A :> [POST] [REQ_NEW_CHEESE] [NEW_CHEESE] 
    ```json
          {"version":"0215xdF",
          "hash":"xkG5wzIkF8KNe4rIpW4IX6uYK1MyRb41hinYDppOGTs=",
          "timestamp":1583101963386,
          "previousHash":"sdfsdfsdfsf",
          "public_transaction_id":"12", 
          "reward":"0.12",
          "member_id":"21"
          }
        
    ```
    Member B:> [205] 
    


##### 4. VALIDATION OF NEW CHEESE `[MEMBER]`>`[MEMBER]`

This protocol is been followed by all the members of the *Cheese Stack System*, in order to send validation error to the member who created the ***NEW CHEESE***. 
 
 - **Request**
 
	[POST] [VALIDATION_EERROR] [CHEESE]  
  
    * *Member_A* will inform *Member_B (Creator of new cheese)*, that validation error for ***NEW CHEESE***. So the creator (*Member_B*) will request drop the cheese that added to the stack
    * **CHEESE**: contains the information of cheese

    * ***`Time_out-03s`*** - Request time is 03 seconds. If response not received within 03 seconds, request will be resent. 
 
 - **Response**
	 
	`[res_code] `
 
     * **acknowledge**: Receiver member acknowledged to the sender member. Ex : `[205]`
- **Sample request(s) and response(s)** 
    
    Member A :> [POST] [REQ_VALIDATION] [NEW_CHEESE] 
    ```json
          {"version":"0215xdF",
          "hash":"xkG5wzIkF8KNe4rIpW4IX6uYK1MyRb41hinYDppOGTs=",
          "timestamp":1583101963386,
          "previousHash":"sdfsdfsdfsf",
          "public_transaction_id":"12", 
          "reward":"0.12",
          "member_id":"21"
          }
        
    ```
    Member B:> [205] 

***version*** : ***2.0*** 
`Modification can be occur`
    