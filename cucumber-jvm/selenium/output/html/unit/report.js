$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("login.feature");
formatter.feature({
  "id": "login-to-the-website",
  "description": "As a user I want to login to the website from any visitor.\n\n`",
  "name": "Login to the website",
  "keyword": "Feature",
  "line": 1
});
formatter.before({
  "duration": 80445000,
  "status": "passed"
});
formatter.scenario({
  "id": "login-to-the-website;login-successful",
  "tags": [
    {
      "name": "@Sample",
      "line": 5
    }
  ],
  "description": "",
  "name": "login successful",
  "keyword": "Scenario",
  "line": 6,
  "type": "scenario"
});
formatter.step({
  "name": "user is on any page",
  "keyword": "Given ",
  "line": 7
});
formatter.step({
  "name": "user enters \"username\" and \"password\"",
  "keyword": "When ",
  "line": 8
});
formatter.step({
  "name": "submit login",
  "keyword": "And ",
  "line": 9
});
formatter.step({
  "name": "the user has logged in successfully",
  "keyword": "Then ",
  "line": 10
});
formatter.match({
  "location": "LoginUnitSteps.user_is_on_any_page()"
});
formatter.result({
  "duration": 137771000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "username",
      "offset": 13
    },
    {
      "val": "password",
      "offset": 28
    }
  ],
  "location": "LoginUnitSteps.user_enters_and(String,String)"
});
formatter.result({
  "duration": 2184000,
  "status": "passed"
});
formatter.match({
  "location": "LoginUnitSteps.submit_login()"
});
formatter.result({
  "duration": 663000,
  "status": "passed"
});
formatter.match({
  "location": "LoginUnitSteps.the_user_has_logged_in_successfully()"
});
formatter.result({
  "duration": 1942000,
  "status": "passed"
});
});