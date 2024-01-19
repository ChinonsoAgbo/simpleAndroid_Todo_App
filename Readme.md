<img width="223" alt="Screenshot 2024-01-19 214650" src="https://github.com/ChinonsoAgbo/simpleAndroid_Todo_App/assets/81990068/0341ec22-086f-4722-bed4-713ef626c322">
<img width="204" alt="Screenshot 2024-01-19 214737" src="https://github.com/ChinonsoAgbo/simpleAndroid_Todo_App/assets/81990068/e8d4fc6a-4789-4a54-af9f-8d624445b64e">
<img width="212" alt="Screenshot 2024-01-19 214750" src="https://github.com/ChinonsoAgbo/simpleAndroid_Todo_App/assets/81990068/b66db3cd-d8f7-47dd-805c-9494ec4df518">


### Persistence in Android

# Exercise 3

Welcome to your homework assignment. This exercise tests your knowledge about _Room_ and database persistence and lets you set up the persistence from scratch. Also we will challenge your understanding at the end.

## Setup

Set up the project by cloning it to your local machine, syncing it with gradle and running it. See the [Troubleshooting](#troubleshooting) section below if you face any issues.

## Provided Code

The provided code contains a simple Todo list app that works as provided but does currently not persist the data. Also the search bar is not implemented.

## Assignment

- Set up the required extensions and dependencies for _Room_ (see the slides for reference)
- Prepare the _Todo Items_ for persistence by creating an _Entity_ and a _Dao_. Make sure to insert your code/files in places that make sense.
- Implement persistence for all 4 CRUD operations (Create, Read, Update, Delete).
  - Persist newly created _Todo Items_
  - Load and display the _Todo Items_ from the database
  - Persist changes to _Todo Items_
  - Delete _Todo Items_ from the database when they are deleted in the app
- Make sure that all changes to the database are reflected in the UI (i.e. update the todo list)
- To get all points: Implement the search functionality by filtering the _Todo Items_ in the database. The specifics are up to you.

## Grading

| Task                                | Points |
| ----------------------------------- | ------ |
| Todo Item Entity                    | 0.5    |
| Todo Item Dao                       | 0.5    |
| Persistence for adding Todo Items   | 1.0    |
| Loading Todo Items from database    | 1.0    |
| Persistence for updating Todo Items | 1.0    |
| Persistence for deleting Todo Items | 1.0    |
| Implement search in database        | 1.0    |
| _Total_                             | 6.0    |

The points for the Todo Item Entity and Dao are awarded even if the persistence is otherwise not implemented or does not work (provided that the code is correct).

## Submission

To submit your solution follow these steps:

1. Make sure that your code compiles and runs without errors (but if you can't get it to work, rather submit what you have than nothing at all)
2. Zip the project folder (the folder containing the `.git` directory and this `Readme.md` file)
3. Rename the zip file to `ex3.vorname.nachname.zip` (replace `vorname` and `nachname` with your first and last name)
4. Fill out the statutary declaration (Eidesstattliche Erklärung) and name it `vorname.nachname.EidesstattlicheErklaerung.pdf` (replace `vorname` and `nachname` with your first and last name)
5. Upload the two files to the Ilias mailbox (Briefkasten) under _"Aufgabe 9 Persistenz & Datenbankanbindung"_

The assignment is due on **Sunday, 21.01.2024 at 23:59**. Later submissions will not be graded and receive 0 points.

## Troubleshooting

### Android Studio version

Make sure to update your Android Studio to the latest version. Especially if you get an error regarding the Android Gradle Plugin (AGP) version.

### Module Errors

If Android Studio/IntelliJ does not recognize the android app module you could try to delete the `.idea` folder and reopen the project (which will recreate the `.idea` folder).

Also make sure that you open the project in it's root directory (the directory containing the `.git` directory and this `Readme.md` file).

### It still doesn't work

If you have tried the steps above and still have issues with the project setup, please contact us per email. Our email addresses are found in the slides.

