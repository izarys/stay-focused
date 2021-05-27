# Stay Focused App
Android application for Software Engineering University Project 2020/2021

# Preview
<img src="https://github.com/izarys/stay-focused/blob/master/apptimer.jpg" width="200" />
<img src="https://github.com/izarys/stay-focused/blob/master/applevel.jpg" width="200" />
<img src="https://github.com/izarys/stay-focused/blob/master/appstats.jpg" width="200" />

# Design Patterns
Two design patterns were used in this project:

### 1. Singleton
The main reason behind using the Singleton pattern was to ensure that Data Class has only one instance, while providing a global access point to this instance.
It is only accessed from Activities to make sure that it's always possible to write unit tests for all Classes.

### 2. Observer
This design pattern is used to call certain functions in MainActivity after events take place in Session.
MainActivity is an observer and Session is being observed. Session notifies MainActivity about events that happen in it through an EventListener Interface.

# Unit Tests
There are 13 unit tests and they all passed:

![alt text](https://github.com/izarys/stay-focused/blob/master/unitTests.PNG "Unit tests results")



# Credits
MaterialProgressBar: https://github.com/zhanghai/MaterialProgressBar

Icons: https://www.flaticon.com/authors/freepik
