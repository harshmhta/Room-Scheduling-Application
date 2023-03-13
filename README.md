# Room Scheduling Application
This is a Java-based Room Scheduling application. The goal of the application is to allow the college to book one room per day per faculty, for each day the rooms are available. The rooms are booked by faculty name, with faculty being identified by a single name. The application has a very nice GUI interface and is database-driven using Derby. The database design is also based on Object-Oriented principles.

## Installation
To use this application, follow these steps:

1. Download the project files from GitHub.
2. Extract the files to a desired directory on your computer.
3. Import the project into your preferred Java IDE.
4. Set up the Derby database using the instructions provided in the database folder.
5. Compile and run the program.

## Technologies Used

1. Java
2. Apache Derby (database)

## Usage
The Room Scheduling application has the following user commands:

### Add Faculty
This command adds a new faculty member to the database. The faculty member is identified by only one name.

### Reserve Faculty Day Seats
This command allows a faculty member to request a room reservation for a specific day based on the number of seats they require. The room will be assigned by the program, with faculty not being able to request a specific room. If there are no rooms available or no rooms with enough seats available, the faculty member will be put on the waitlist for that day. The waiting list is maintained in the order the faculty members tried to reserve their rooms.

### Status Reservations by Day
This command displays the faculty members that have rooms reserved on a specific day.

### Status Waiting List
This command displays all the faculty members waiting for rooms. The waitlist is displayed in day order and then in the order of when the reservation was requested.

## Database Considerations

The Rooms Table should be preloaded with several Rooms such as 101, 102 and the number of seats in the room. You should have a different number of seats per room so that you can test the proper reservation of rooms for different class sizes. You will be shown how to preload tables with values.
The Day Table should be preloaded with several days of your choice. These are just normal Dates.
The database tables should not contain redundant data, i.e. relevant data should only appear in one table.

## GUI Guidelines
The user should be required to enter only unknown data. Drop down lists of known data such as Faculty member names, Room names, or Days should be displayed for the user to select. Combo Boxes should be used for the drop down lists on the form. When information is requested to be displayed e.g. for a Status command, all of the requested information must be displayed. When a command is performed, the results of that command should be displayed to the user on the same display without the user needing to check Status to see what was done.

## Credits
This project was developed by Harsh Mehta.

## Academic Integrity Statement

Please note that all work included in this project is the original work of the author, and any external sources or references have been properly cited and credited. It is strictly prohibited to copy, reproduce, or use any part of this work without permission from the author.

If you choose to use any part of this work as a reference or resource, you are responsible for ensuring that you do not plagiarize or violate any academic integrity policies or guidelines. The author of this work cannot be held liable for any legal or academic consequences resulting from the misuse or misappropriation of this work.

In summary, any unauthorized copying or use of this work may result in serious consequences, including but not limited to academic penalties, legal action, and damage to personal and professional reputation. Therefore, please use this work only as a reference and always ensure that you properly cite and attribute any sources or references used.
