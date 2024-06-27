# Banking Application

This is a simple backend application for adding new customers to bank and managing accounts and transactions of existing customers in a bank. The API allows opening new accounts, retrieving customer information, and handling transactions.

## Technologies Used
- Java
- Spring Boot
- Junit for unit testing
- Postman for API testing

## Setup
1. Clone repository :- git clone https://github.com/ritujad1993/BankingApplication.git
2. Import and open the project in Eclipse IDE/Intellij IDEA
3. Build the project : maven clean install
4. Run the application
5. Test the endpoints in Postman
6. Project contains postman collection which can be used for testing

## Endpoints

#### 1. GET /bank/customers
   - Description: Retreives details of all the existing customers in the bank
   - Returns: JSON object containing customer information
     
#### 2. GET /bank/customers/{customerID}
  - Description: Retrieves customer information including name, surname, balance, and transactions
  - Returns: JSON object containing customer information

#### 3. POST /bank/customers/addCustomer
  - Description: Creates a new customer in the bank
  - Request Body :
    
    ![image](https://github.com/ritujad1993/BankingApplication/assets/171249895/e2b1678a-4418-4927-92ca-52883878d30d)

  - Returns: Success message for customer creation

#### 4. POST /bank/accounts/openAccount
  - Description: Opens a new account for an existing customer with some initial credit
  - Request Body :
    
     ![image](https://github.com/ritujad1993/BankingApplication/assets/171249895/96ca10ad-64e4-4c51-8c65-139a3a8fbbaa)

  - Returns: JSON object containing new account information

#### 5. POST /bank/accounts/addTransaction
  - Description: Creates a new transaction adds it to existing account. Also updates the balance of account as per transaction amount and type.
  - Request Body :
    
     ![image](https://github.com/ritujad1993/BankingApplication/assets/171249895/871f358a-e3c1-48b5-8526-10b4dfb07387)

  - Returns: JSON object containing updated account information

#### 6. GET /bank/accounts
   - Description: Retreives details of all the accounts in the bank
   - Returns: JSON object containing account information
