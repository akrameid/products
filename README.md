# products
This project is implemented by Spring boot with the following modules :
- h2 database (for simplicity and avoid any installations needed for any other database server)
- junit testing (I have no such time to add more unit testing but I know the main idea behind this topic)
- open-api dependency for documentation ( I have no such time to write description for any module), you can open it through (http://localhost:2000/swagger-ui/index.html)




How to use the tool :
1)  To install the jar, please make sure that you have java 11 installed at your machine
2)  go to Release folder in the repo then run command : java -jar product-0.0.1-SNAPSHOT.jar
3)  go to postman folder and load the collection in the postman to run any api you want
5)  you will find a folder for each entity (Order, Customer, Product) with sample requests 
6)  For orders: User can retrieve all (get all), add , delete, update 
7)  For Customers: User can retrieve all (get all), add , delete 
7)  For Products : User can retrieve all (get all), add , delete

