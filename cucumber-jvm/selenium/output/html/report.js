$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("login.feature");
formatter.feature({
  "id": "login-to-the-website",
  "description": "As a user I want to login to the website from any visitor.\n\n`",
  "name": "Login to the website",
  "keyword": "Feature",
  "line": 1
});
formatter.before({
  "duration": 2688990000,
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
  "location": "LoginUISteps.user_is_on_any_page()"
});
formatter.result({
  "duration": 220852000,
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
  "location": "LoginUISteps.user_enters_and(String,String)"
});
formatter.result({
  "duration": 169483000,
  "status": "passed"
});
formatter.match({
  "location": "LoginUISteps.submit_login()"
});
formatter.result({
  "duration": 1343810000,
  "status": "passed"
});
formatter.match({
  "location": "LoginUISteps.the_user_has_logged_in_successfully()"
});
formatter.result({
  "duration": 9241000,
  "status": "passed"
});
formatter.after({
  "duration": 77132000,
  "status": "passed"
});
});