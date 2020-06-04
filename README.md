# Conference Managment System

# Team BWay 2.0

#### READ ME BEFORE USING THE APP !!!

1.Create dev role for dev in pgAdmin  
2.Create test-profile role for test in pgAdmin  
3.Create CMS database for dev role in pgAdmin  
4.Create CMS-TEST database for test-profile in pgAdmin  
5.In application-dev.properties and application-test.properties change the password property to your password(for now)  
6.In application.properties change spring.profiles.active=test/dev depending on what you want to use  
7.Start the application  

#### User Manual  

[Manual](./Documentation/Grading.pdf)

#### Members  
[Retegan Catalin-Cristian -Team Leader](https://github.com/ReteganCatalin)   
[Sabadus Alexandru-Bogdan](https://github.com/TheStrangeProgrammer)  
[Rusu Raul-Mihai](https://github.com/RaulRusu)  
[Ravasz Tamás](https://github.com/RavaszTamas)  
[Scherer Edward-Serban](https://github.com/SabaAlex)  

#### Brief Presentation of the app

   The task presented to us was to create an application that is going to be used to streamline the management of conferences from start to finish. The platform should offer a way for users to submit a proposal to a conference but also a way for the steering committee alongside the program committee to manage the reviewing of the incoming proposals and internally organize the conference. Additionally users can buy tickets through the platform.

#### Used technologies

   <pre> 
    • Database server: PostgreSQL, locally hosted  
    • Version Control: Liquibase  
    • Programming languages:   
        ◦ Front-end: Typescript with Angular, HTML, CSS ,Bootstrap  
        ◦ Back-end: Java, Spring, Lombok  
    • ORM: Hibernate   
    • Tools used for diagram creation: StarUML, draw.io   
    • VCS: Git  
    • Task management: Trello   
    • Testing   
       ◦ User Testing and Manual Testing  
    • External tools/APIs we found useful:   
      • <a href="https://sendgrid.com">SendGrid → for email sending</a>
      • <a href="https://www.liquibase.org">Liquibase → version control</a>
     </pre> 
      
    
