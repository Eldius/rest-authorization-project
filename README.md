# rest-authorization-project #

[![Build Status](https://travis-ci.org/Eldius/rest-authorization-project.svg?branch=master)](https://travis-ci.org/Eldius/rest-authorization-project)

A set of tools to help provide authentication and access control
to REST (JAX-RS based) applications.

today it uses only Hibernate as persistence provider but I want 
to add the JDBC and MyBaris options in a near (or not so near) future.

## The projects: ##

* **rest-brutauth:**
This project was inspired by the 
[vraptor-brutauth](https://github.com/caelum/vraptor-brutauth).
This project provides useful tools to validate resource access.

* **rest-authorization:**
Provides authorization and identity mechanisms like user and
 profile management and validation.

* **rest-authorization-jparepository:**
This one is the persistency implementation. At this time we just have 
the JPA option.

## Requirements ##
* CDI
* Persistence implementation [0]

[0] It's a dependency to use the authorization library.


## Configuration ##

Actually you just need to add the projects to your project's depenency
list and met the requirements to start using it.

### rest-brutauth usage ###

* **Simple brutauth rule**

First you will need to implement the interface SimpleBrutauthRule
like this exemple below:

        public class MySimpleBrutauthrule implements net.eldiosantos.brutauth.rules.SimpleBrutauthRule {

            public Boolean canAccess(Long accessLevel) {
                return true;
            }
        }

The method canAccess will be called to validate the user permission to access 
the resource.

And then you will need to annotate your resouce method or class with the @SimpleBrutauthRules annotation.

        @SimpleBrutauthRules({MySimpleBrutauthrule.class, MyOtherSimpleBrutauthrule.class})
        public class MyResource {

            @GET
            public Response get() {
                ...
            }
        }

Or

        public class MyResource {
            @SimpleBrutauthRules({MySimpleBrutauthrule.class, MyOtherSimpleBrutauthrule.class})
            @GET
            public Response get() {
                ...
            }
        }


* **Custom brutauth rule**

If you need a more complex validation where you need to validate user acces to the data sent to the resource.
You will need to implement the CustomBrutauthRule interface and annotate the valdiation method with 
@BrutauthValidation annotation. This method will be invoked passing the resource method parameters

        public class EmployeeCustomBrutauthRule implements net.eldiosantos.brutauth.rules.CustomBrutauthRule {

            @net.eldiosantos.brutauth.annotations.BrutauthValidation
            public Boolean canPass(Employee employee) {
                ...
            }
        }

And

        public class MyComplexResource {
            
            @net.eldiosantos.brutauth.annotations.CustomBrutauthRules({EmployeeCustomBrutauthRule.class})
            public Response process(Employee employee, Store store) {
                ...
            }
        }

### rest-authorization usage ###

It's usage is basically restricted to the package *net.eldiosantos.authorization.service*. 
There you can find a class to create the user (login and password), to login and to logout.

* **LoggedUserRule**

The *LoggedUserRule* is a SimpleBrutauthRule implementation that validate the user using the request header 
*Authorization* using the format 'token TOKEN_GENERATED_AT_LOGIN' (started by the String 'token' whith a 
blank space and then the token generated at login).


### Add as a Maven dependency ###

**rest-brutauth:**

        	<dependency>
                <groupId>net.eldiosantos.brutauth</groupId>
                <artifactId>rest-brutauth</artifactId>
                <version>0.0.3</version>
        	</dependency>

**rest-authorization:**

        	<dependency>
                <groupId>net.eldiosantos.brutauth</groupId>
                <artifactId>rest-authorization</artifactId>
                <version>0.0.3</version>
        	</dependency>

**rest-authorization-jparepository:**

        	<dependency>
                <groupId>net.eldiosantos.brutauth</groupId>
                <artifactId>rest-authorization-jparepository</artifactId>
                <version>0.0.3</version>
        	</dependency>


### Maven repository: ###

            <repositories>
                <repository>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                    <id>central</id>
                    <name>bintray</name>
                    <url>http://jcenter.bintray.com</url>
                </repository>
            </repositories>




## TODO LIST ##
* Add resources to change user credentials;
