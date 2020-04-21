# Conference Managment System

#### What you have do before starting the Application assuming you already installed postgres and pgAdmin4

1.Create dev role for dev in pgAdmin\n
2.Create test-profile role for test in pgAdmin\n
3.Create CMS database for dev role in pgAdmin
4.Create CMS-TEST database for test-profile in pgAdmin
5.In application-dev.properties and application-test.properties change the password property to your password(for now)
6.In application.properties change spring.profiles.active=test/dev depending on what you want to use
7.Start the application

