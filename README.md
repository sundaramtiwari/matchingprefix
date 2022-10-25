## Matching Prefixes

MatchingPrefix service is a web server implementation that has an endpoint

`GET /findLongestPrefix?input=<data>`

this endpoint takes a string as an input and returns the longest matching prefix from a set of existing prefixes.

On startup, the project reads prefix's from a file `prefix.txt` and loads them into memory in form of a trie data structure.
The prefix service has exposed a method `find("")` that returns longest matching prefix or null if no match is found.

### Problem Statement

This project is a solution to below problem statement:

```
Given a list of string prefixes of variable length, the assignment is to implement a method that
takes a string as a parameter, and returns the longest prefix that matches the input string. A prefix
matches if the input string starts with that prefix. The list of prefixes to match should be taken as
configuration by your solution. For instance, given a list of prefixes ‘foo', ‘tru', and ‘true’, and the
input ‘truecaller’, your method should return ‘true’, since it’s the longest matching prefix. We’ve
provided you with a text file with sample prefixes that you can use
```


### How To Run
Run `mvn clean install` from project root to generate the jar file at location:
`target/matchingprefix-$version.jar`

Next, execute below command from project root:

`$> java -jar target/matchingprefix-0.0.1-SNAPSHOT.jar`

Matching Prefix Endpoint:

`curl -X GET 'http://localhost:8080/findLongestPrefix?input=<data>'`
