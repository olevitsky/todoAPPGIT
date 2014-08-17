todoAPP
=======
This is a simple example of to-do list created using Android studio beta 0.8.0

![alt tag](https://github.com/olevitsky/todoAPP/blob/master/todoAPP_image1.gif)


Main features:
1. Add to-do items into the list using the main window (application will check if item is non-empty, i.e. has any text before adding item to the list)
2. Long click (click and hold) on an item will remove the item from the list
3. Click on the item will pop up a separate window where you can edit the item content (leaving content blank will automatically remove the item from the list on commit)


Installation:
Just upload the project into android studio and run. Please use version 0.8.0 as it may not work with older versions. 
The application has been tested using Nexus S emulator (Android 4.3 API level 18

Libraries:
 The project required commons-io-2.4.jar library (it is part of the project and should be loaded automatically)
