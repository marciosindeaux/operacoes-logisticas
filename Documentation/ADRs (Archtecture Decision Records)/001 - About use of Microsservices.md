# 001 - About use of microsservices

|||
|---|---|
|`Participants`| Marcio Alexandre Freire Sindeaux|
|`Date`| 06/09/2025 |

## 1. Index
 * 1. Index
 * 2. Context
 * 3. Justifications
    * 3.1 Performance and Scalability
    * 3.2 Development Flexibility
    * 3.3 Maintenance Flexibility
    * 3.4 Resilience and Fault Tolerance
 * 4. Decision

## 2. Context
When designing a solution for a logistics system, we have to imagine that this system will have several users from different points. Som parts will certeainly be used much more than others ant these parts must correspond to the number of users, be resilient and be flexible.

## 3. Justifications
### 3.1 Performance and Scalability:  
   Microsservices architecture allow us to scale specific components of the system, which are in bottloneck.It also allows us to balance parts of the application so that there is a balance between the less and most used parts
### 3.2 Development Flexibility: 
   Microsservices architecture allows us to develop important parts of the system with the most appropriate tool for the job. Whether with different technologies or with implementations adapted for each project
### 3.3 Maintenance Flexibility:
   Each microsservice can be developed and maintained independently, with simple adjustments
### 3.4 Resilience and Fault Tolerance: 
   Microservices Isolation can increase fault tolerance and make the project more resilient.

## 4. Decision
We decide after some conversations that microsservices would be the most interesting course of action. Flexibility and scalability problems would easily be solved if we did not use a monolithic system. 


