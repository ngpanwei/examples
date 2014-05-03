access-control-1
================

This is the first of the access-control cucumber-jvm example series.
This Eclipse project provides a skeleton folder structure
* req - this is where the requirements are documented using .feature files
* src - this is where the source code goes.
* test - this is where all the test code goes.

# req - Requirements folder
The requirements folder contains simple .features file in English and Chinese.
* user-admin.feature
* user-admin-zh.feature
These two feature files are semantically equivalent.

# src - Source folder
True to Behavior Driven Development, we start off without any code.

# test - Test folder
This is where the test code goes. 
* tests - this folder contains test code to execute the requirements via cucumber-jvm
** UserAdminTests.java - executes the user-admin.feature file
** UserAdminTestsZh.java - executes the user-admin-zh.feature file
* suites - this folder contains suite codes to aggregate the test code to execute.
** AllTest.java - runs both UserAdminTests.java and UserAdminTestsZh.java


