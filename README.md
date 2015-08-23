Macha, to run my code, follow the steps:

#Pre-requisites

1. JDK 1.8
2. Apache tomcat server(any version, I have gone with tomcat 8)
3. Xampp for MySQL.
4. Install maven(it's a zip, unzip it, set the environment variable to ../maven/bin/)
5. Create a folder called .m2 in your users/Manjunath.Amaresh/
6. In MySQL, create database called rare.
7. Import the rare.sql in to the newly created database.

#Run:

1. Import maven project in eclipse, import rare.
2. Right click on rare -> Run -> install.
3. Right click on controller -> Run on Server.

#Test

1. Install advanced rest client on chrome browser or post-man client.
2. http://localhost:8080/controller/user/login
{
    "userName": "pavan",
    "password": "pavan"
}
You should see a response of 200. 