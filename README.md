#CS3343 Group_13
#X-BOX
#Box Storage Management System

##1.Background
As a multicultural education city, Hong Kong colleges have many international students worldwide. And when a long vacation comes, the students may return to their hometown. However, their stuff which is heavy to carry, might be left in Hong Kong for convenience. And as the college does not provide storage services. They use the storage service provided by the student union, which is inconvenient and causes mass for the staff to manage. In this case, we come out with the X-BOX system, a Box storage management system, to help them improve efficiency and quality.
##2.Install

Get our project from <https://github.com/AharenDaisuki/xBox>
Using the branch main 
click the run.bat at the Release fold.


##3.User Guide
For Users to use the System, they first need to register and login into their account.
They can request empty boxes to store their things on the Request page.
And input the boxids to store the boxes after finishing packing.
And return the empty boxes after they get back their stuff.
During the whole process, they can press the summary button to check the situation of the boxes.

For Admin, they need to login into the admin account which already exists in the Database.
The Admin can input the client id to know the amount of the bills and check whether they pay the correct amount.
And input the client id and boxid to get the empty boxes returned by the users.
The System also provides details search functions.

For More Details, Please check the [User_Manuals.pdf]()

##4.Release Version
> + 1.0  Command Line Version
 + 1.1 	Write the logic that user may return empty box.
 + 1.2 	Change the Database class to singleton.
 + 1.3	Add detailed basic class for data operation like the RentableAllocator, RentableManager, RequestSearcher.
 + 1.4	Details the status of the Rentable objects. Have more class implement the RenatableStatus class.
+ 2.0 GUI Version
 + 2.1 Add the pop-ups for error message.
 + 2.2 Fix some text label’s position and content.
  + 2.3 Adding new exceptions for command error.
 + 2.4 Fix the bug of the data output is not ordered.
 + 2.5 Fix the bug of the JSON file by using the relative address.
+ 3.0 Add User version of the system
 + 3.1	Add Interface classes to invoke the command for Admin and User.
 + 3.2	Fix the bug for Interface, cancel the singleton pattern.
 + 3.3 Fixing bugs for command intersections of Admin and User.

##5.Contribution
| Name | Post |
| --------- | -----:|
| [DONG Jiajie](https://github.com/dongjiajiedc) |  Assistant Program Manager, Interface Designer |
| [LI Xiaoyang](https://github.com/AharenDaisuki) | Program Manager, Program Developer |
|[SHA Xingchen](https://github.com/rocksxc) | Program Developer, Program Tester|
|[ZHANG Tiantian](https://github.com/crystal-cheung) |  Program Designer, Program Developer|
|[ZHENG Shangkun](https://github.com/bnxcvd) | Assistant Program Manager, Program Developer|
|[ZHOU Yu](https://github.com/yzhou442) | Program Developer, Program Tester|
##6.Project link
[Release Summary]()
[Project Plan]()
[Design Report]()
[Test Report]()
[Bug Report]()