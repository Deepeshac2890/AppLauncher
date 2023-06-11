# AppLauncher

This is an application launcher. 

Architecture Used : MVVM

For networking Retrofit is used.

Features : 

1. Shows the home screen with same background which is set in the system launcher for familiar look and feel.
2. Shows the applications in sorted order.
3. Some applications can be blocked from the api which admin does not users to open as a security measure.
4. Tested the application getting flow.

Focussed Areas : Currently the focus was on the drawer where we display the app and make the calls to block certain applications based on the response. Focuessed more
on the cleaner architecture and code reusablity. Made the code cleaner and testable by trying to make each class have one responsiblity.

Inspiration from : Microsoft Launcher

Tradeoffs Made: They were in the unit testing front. Had to write minimal testcases which were needed due to time constraints. Few more features like search and level wise sort could have been added.

Upcoming Features : 
1. Search for the app drawer. 
2. Ability to add shortcuts
3. Add gestures on the HOme screen to open the drawer.
