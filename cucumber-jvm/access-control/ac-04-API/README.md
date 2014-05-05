ac-04-API
=========

This is the fourth part of the access-control cucumber-jvm example series.
### src - Source folder
The source folder is now updated with some skeleton API code.

* admin - this package provides functionality for creating and managing user accounts
* session - this package provides functionality for tracking user login, actions and logout

IUserAdminHandler.java
````java
public interface IUserAdminHandler {
	public UserAccount createUser(UserInfo userInfo) ;
}
````

ISessionHandler.java
````java
public interface ISessionHandler {
	public Session login(String username, String password) ;
}
````
