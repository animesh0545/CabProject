# Project 14: Cab Booking System
---
## Authors (Group No. 41)
- Animesh Bhargava (2019B3A70545P)
- Utkarsh Yashvardhan (2019B4A70704P)
---

Demonstration videos of the project can be found [here](https://drive.google.com/drive/folders/1Ty3xC4j6TkC7OOE4ab4UsWfZhg2ce5Qb?usp=sharing).

Also, the document having critical analysis of our code for OOP principles is named **SelfEvaluation.md** and can be found in the main folder.

---

# Table of Contents

---
- [Description](#Description)
- [Information about running the code](https://github.com/animesh0545/CabProject/edit/main/README.md#information-about-running-the-code)
- [Assumptions](https://github.com/animesh0545/CabProject/edit/main/README.md#assumptions)
- [Brief description of each class developed](https://github.com/animesh0545/CabProject/edit/main/README.md#brief-description-of-each-class-developed)
   * [class UberCab](https://github.com/animesh0545/CabProject/edit/main/README.md#class-ubercab)
   * [class Login](https://github.com/animesh0545/CabProject/edit/main/README.md#class-login)
   * [class Landmark](https://github.com/animesh0545/CabProject/edit/main/README.md#class-landmark)
   * [class Journey](https://github.com/animesh0545/CabProject/edit/main/README.md#class-journey)
   * [class Driver](https://github.com/animesh0545/CabProject/edit/main/README.md#class-driver)
   * [class Customer](https://github.com/animesh0545/CabProject/edit/main/README.md#class-customer)
   * [class City](https://github.com/animesh0545/CabProject/edit/main/README.md#class-city)
   * [class Cab](https://github.com/animesh0545/CabProject/edit/main/README.md#class-cab)
- [Running a sample test case](https://github.com/animesh0545/CabProject/edit/main/README.md#running-a-sample-test-case)
---

## Description

---

This project implements a basic system to manage cab booking from both customer's and driver's point of view.

The summary of how the app functions is given as follows:-

1. First, the customer runs our application. The application prompts the customer to either log in if the customer already has an account with the Uber Cab application else sign up if he/she is new to the application.

2. Next, after the customer has successfully logged in or signed up with our application(if he/she is new to the app), the application asks the customer to enter his/her current coordinates with respect to the city grid we have designed for this app. **The coordinates that the customer enters should be zero indexed.**

3. After the customer enters his/her current coordinates, the app displays a list of landmarks present in the grid and prompts him/her to choose one of these landmarks as destination point.

4. Once the customer has chosen his/her destination point, a list of all the available cabs (along with the information like distance of the cab from the customer, fare charged, driver rating etc) is displayed to him/her. Appropriate message is displayed if no cabs are available at the moment. Customer chooses one of these cabs based on his/her preference.

5. Once the customer has chosen the cab, the customer's app will wait for the driver's response. **The driver always accepts the customer's request.**

6. Now, the driver opens the app on his/her device. The app prompts the driver to log in. The driver logs in using his/her log in credentials.

7. After the driver has successfully logged in, notification would be displayed to the driver.

   - If no customer is calling the driver then the app would display "no new notification received" and terminate.
   - If any customer is calling the driver then driver would get a notification telling him/her about the calling customer's location and destination to reach. **The app then prompts the driver to accept the customer's request which he/she always does.**

8. Once the driver accepts the customer's request, he/she starts moving towards the customer with a constant speed (denoted by '-' sign). **The driver's location along with the ETA is dynamically displayed on the app on both customer's side and driver's side.**

9. Once the driver reaches the customer's location, the app prompts the driver to start the journey. **The driver always starts the journey.**

10. Once the driver starts the journey, a dynamically changing grid will be shown on both the driver's app and the customer's app. The position of the cab (in which both the driver and the customer are) will dynamically change in this grid (denoted by '+' sign). An ETA to the destination point will also be shown dynamically. The '+' sign will start moving towards the customer's destination point.

11. Once the destination is reached on the grid, the customer's app will prompt him/her to pay the generated fare and rate the journey.

12. Once the customer pays the fare and rates the journey, the driver's app will display the fare received and the rating received.
---

## Information about running the code

---

1. The code uses certain escape sequences which CANNOT run on *Eclipse* due to certain bugs associated with Eclipse. It is recommended that the code be run on **VSCode**.
2. The jar file can be executed to run the code. However, since the project requires the use of .txt files, which are stored in a folder named **data**, the jar file *must* be executed from within the **src** folder.
3. The jar file can be executed (from the src folder) by running the following command in the terminal:
   ```
   java -jar UberCabProject.jar
   ```
---

## Assumptions

---

1. The whole idea of this app works on the premise that we have a small city with 9 landmarks named after prominent locations in BITS Pilani:-

```
1. FD=III
2. Mal-A Bhawan
3. Ram Bhawan
4. Krishna Bhawan
5. Shankar Bhawan
6. SR Bhawan
7. FD-II
8. LTC
9. FD-I
```

2. The whole city is represented in terms of a 15 by 15 grid with each landmarks represented in terms of a unique ID number assigned to it (mentioned on the left of each landmark given above). All the other cells are marked as 0.

3. Each landmark has a predefined location. The grid on which the app works on is as follows:-

```
0 0 0 0 0 0 0 0 7 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 6
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 4 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 5 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 9 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 2 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 8 0 0
0 0 0 0 0 0 0 0 0 0 3 0 0 0 0
```

5. **The app uses zero indexing to describe the location of customer, driver or landmark.**

6. **The login username, password of both the driver and the customer cannot have white spaces in them.** Similarly, the first name and last name of a new customer cannot have white spaces in them.

7. The customer calling for the cab can be at any position on the grid but the final destination has to be a landmark.

8. Any free cab at a given moment can be at any given location on the grid.

9. Every driver has an account in this app. So they only need to login to use the app. On the other hand, any customer trying to use the app can be either new to the app in which case, he/she should signup before using it or he/she is already a user of the app in which case he/she simply has to login the app to use it.

10. The list of cabs that is displayed to the customer is sorted in the ascending order of the distance of the cabs from the customer's location. If the distance is same for some cabs then they are sorted in descending order of the current customer rating of the driver of the cab and if that is also same of some cabs, then they are sorted in alphabetcal order of the registration number of the cabs.

11. When the customer sends a request to a driver for a trip to any particular landmark, the driver always accepts his/her request. Similarly, once the driver reaches the customer's location he/she always starts the trip.

12. When the driver is travelling towards the customer's location, the grid will be displayed on the app on both the driver's side and the customer's side along with the dynamic ETA and dynamic location of the cab denoted by '-'. The position of the customer will be denoted by 'x'. Sample given bellow:-

```
ETA: 13 sec
0 0 0 0 0 0 0 0 7 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 x 0 0 0 0 0 0 0 0 0 0 0 0 6
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 4 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 5 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 9 0 0 0 0 0 0 - 0 0 0 0 0 0
0 0 2 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 8 0 0
0 0 0 0 0 0 0 0 0 0 3 0 0 0 0
```

10. Once the driver has reached the customer's location and started the journey, the grid will again get displayed on both the customer's app and the driver's app along with dynamic ETA to reach the destination and the dynamic position of the cab(in which both the driver and the customer is present) denoted by '+'. Sample given below:-

```
ETA: 10 sec
0 0 0 0 0 0 0 0 7 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 + 0 0 0 0 0 0 0 0 0 0 0 0 6
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 4 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 5 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 9 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 2 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 8 0 0
0 0 0 0 0 0 0 0 0 0 3 0 0 0 0
```

11. Only horizontal or vertical movements are allowed for the cab. It does not matter if any landmark is present in the cab's path, it will simply move through it.

12. The speed of all the cabs is kept constant to 50 kmph. The distance between any two adjacent grid points is 1 km. So in real life the cab would take 72 seconds to move from one grid point to its adjacent one. However, for demonstration we take 1 sec in this app = 72 seconds in real life i.e., it will take one second for a cab to move from one grid point to its adjacent grid point.

13. When the customer sends the journey request to a driver, that driver becomes unavailable to all the other customers until the the trip to the customer's destination has ended and the driver has received his/her due fare. So, through this app, it is possible that at some time all the cabs are booked so a customer is not able to find any cab to reach his/her desired destination.

14. The fare of a trip is calculated in Rs according to the given formula:-

```
x = distance between cab's starting location and customer's initial location

y = distance between customer's initial location and destination

totalDistance = x + y

if current time is between 8 am(inclusive) and 11 pm(exclusive)
    factor = 100
else
    factor = 70

numCabs = total number of cabs available at the given moment

fare = totalDistance*factor/numCabs
```

15. Once the driver drops the customer at his/her desired destination and receives the generated fare and trip rating from him/her, the driver's initial location will change to the final destination of the previous customer for the future customers.
---

## Brief description of each class developed

---

### class UberCab

This class contains the main function through which our program begins its execution. **To accomplish our goal of running the customer's and driver's uber app simultaneously in such a way that both of them interact with each other, we have used concepts of file handling**. We run the same program two times in two different terminals. In one program, the user behaves as a customer and in the other the user behaves as a driver. When we need some information to reach from one user to another (for example if we want customer to know that the driver has accepted his/her request to travel), we make some changes in the `drivers.txt` file. Although, the two programs are running independently from each other, both of them are using the same file for reading and writing information, hence this change done by one running program is detected by the other running program and hence based on the change received by that program, it performs relevant actions. Most of such interaction triggering code is written in the main function itself.

---

### class Login

Login class provides the functionality of login and signup(only if you are a new customer of the app). This class is inhereted by the Driver and the Customer class to use its login and signup functionality. The `boolean signUp(String, String, String)` function returns true if the signup with given username and password is successful, the `boolean login(String, String)` returns true if the login is successful with the given username and password and the `String getDetails()` returns the details of the user who has logged in or signed up.

---

### class Landmark

This class provides blueprint of the landmarks present in our grid.

---

### class Journey

This class provides the blueprint of the journey of the customer in a given cab to his/her desired location. It provides several utility functions like `void travel(int[], int[], char)` to display the grid with the position of customer and cab along with ETA dynamically. This is acheived by writing suitable algorithm for cab movement within the `public void run()` function of a new thread that we create for this purpose and then starting that thread. The `void addCabs()` function helps in displaying the list of available cabs to the customer.

---

### class Driver

The Driver class provides blueprint for the drivers of the cabs. It has several data members, parameterized constructors and member functions. The function `void driverLogin()` helps in driver login, the function `void notification(String)` helps in sending some message from driver to customer by making some changes to `drivers.txt` file etc.

---

### class Customer

This class provides the blueprint for customers of our uber app. It contains several relevant data members, parameterized constructors and member functions like `void customerSignup()`, `void customerLogin()`, `void obtainLocation()` to help our app accomplish the goal of customer signup, customer login and obtaining the the initial coordinates of the customer respectively.

---

### class City

This class provides the blueprint of our city. It contains variables to store information about landmarks and our city's grid. It also contains utility functions to get some landmark's information given some other information about that landmark for example:- `String getLandmarkNameFromID(char)` takes an ID character as an argument and returns the landmark's name corresponding to that ID.

---

### class Cab

This class provides blueprint for the cabs. It contains variables like `String regNum`, `Driver driverDetails` etc to store relevant information about the cab. This class implements `Comparator<Cab>` interface and overrides `public int compareTo(Cab)` function so that the cabs are sorted in ascending order according to their distance from the customer's initial position. If the cabs are at a similar distance from the customer's initial position then they are sorted in descending order according to the current rating of their drivers and if the rating of the drivers are also same then the cabs are sorted according in alphabetical order according to their registration numbers. It also has utility functions like `void calcFare(int, int)` to calculate fare of the journey and `int distance(int)` to calculate the distance of cab from the given coordinates.

---

## Running a sample test case

---

Note that the drivers' data and customers' data are stored in their seperate files in `data` directory and the same has to be used to signup and login as driver or customer.

Initially inside the `data/customers.txt` file:-

```
utkarsh1729 pass123 Utkarsh_Yashvardhan
animesh222 qwerty11 Animesh_Bhargava
harsh007 ABC@CBA Harsh_Butani
shubham331 wannaDaNcE2 Shubham_Keshari
chinchin7 alphaQ1Q Chinmay_Kumar
raju88 iamrajuuu Raju_Rastogi
mike11 passss Mike_Scott
light21 pawser12 Light_Yagami

```

Initially inside the `data/drivers.txt` file (The data beside driver's name denotes the current rating of driver, the number of people who have rated the driver, the registration number of the driver and the initial location of the driver respectively):-

```
alpha11 123456 Vinay_Jain 4.50 2 001 2_3
bravo32 qwerty Ishvit_Bhasin 4.01 8 002 14_5
charlie90 192837 Ritik_Thakur 4.17 12 003 8_6
disco43 password Ishu_Kumar 4.71 10 004 5_5
elephant222 999000 Mihir_Srivastava 3.81 4 005 9_9
jamesbond007 qwwee11 Sahil_Gupta 3.09 4 006 12_10
givemehope01 hehehe1 Saksham_Mahajan 2.56 6 007 1_14

```

The execution of the program from customer's side:-

```
yashvardhan@YASHVARDHANs-MacBook-Pro bin % java UberCab
Enter 1 for Customer or 2 for Driver: 1
Enter 1 for SignUp and 2 for Login: 2
Enter your username: utkarsh1729
Enter password: pass111
Wrong info, enter 1 to try again, 2 to go to signUp: 2
Enter username: utkarsh1729
Enter password: pass111
Enter your first name: Light
Enter your last name: Yagami
The username already exists, please try again
Enter username: light21
Enter password: pawser12
Signup complete
Welcome, Light Yagami
Enter your location: 4 4
Choose destination to reach among the below mentioned landmarks:
1. FD=III
2. Mal-A Bhawan
3. Ram Bhawan
4. Krishna Bhawan
5. Shankar Bhawan
6. SR Bhawan
7. FD-II
8. LTC
9. FD-I
Enter valid choice: 8
Landmark to reach: LTC(8) [Coordinates: (13,12)]
Choose from the following available cabs:
1. Registration no.: 004, Driver's Name: Ishu Kumar, Distance from you: 2 km, Location: (5, 5), Fare charged: Rs 271, Rating: 4.71
2. Registration no.: 001, Driver's Name: Vinay Jain, Distance from you: 3 km, Location: (2, 3), Fare charged: Rs 285, Rating: 4.50
3. Registration no.: 003, Driver's Name: Ritik Thakur, Distance from you: 6 km, Location: (8, 6), Fare charged: Rs 328, Rating: 4.17
4. Registration no.: 005, Driver's Name: Mihir Srivastava, Distance from you: 10 km, Location: (9, 9), Fare charged: Rs 385, Rating: 3.81
5. Registration no.: 002, Driver's Name: Ishvit Bhasin, Distance from you: 11 km, Location: (14, 5), Fare charged: Rs 400, Rating: 4.01
6. Registration no.: 007, Driver's Name: Saksham Mahajan, Distance from you: 13 km, Location: (1, 14), Fare charged: Rs 428, Rating: 2.56
7. Registration no.: 006, Driver's Name: Sahil Gupta, Distance from you: 14 km, Location: (12, 10), Fare charged: Rs 442, Rating: 3.09
Choose any one Cab to travel: 5
Cab is arriving...
Cab reached
0 0 0 0 0 0 0 0 7 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 6
0 0 0 0 - 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 4 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 5 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 9 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 2 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 8 0 0
0 0 0 0 0 0 0 0 0 0 3 0 0 0 0

Cab travelling to destination...
Cab reached
0 0 0 0 0 0 0 0 7 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 6
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 4 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 5 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 9 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 2 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 + 0 0
0 0 0 0 0 0 0 0 0 0 3 0 0 0 0

Fare to be paid: Rs400
Rate the driver(0-5.0): 4
yashvardhan@YASHVARDHANs-MacBook-Pro bin %
```

The execution of the program from driver's side:-

```
yashvardhan@YASHVARDHANs-MacBook-Pro bin % java UberCab
Enter 1 for Customer or 2 for Driver: 2
Enter 1 to Login: 1
Enter your username: bravo32
Enter password: qwertt
Wrong info entered, please try again
Enter username: bravo32
Enter password: qwerty
Notification received, customer calling at coordinates: (4, 4). Requesting to reach LTC(8), coordinates: (13, 12)
Press Y to accept: Y
Cab reached
0 0 0 0 0 0 0 0 7 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 6
0 0 0 0 - 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 4 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 5 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 9 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 2 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 8 0 0
0 0 0 0 0 0 0 0 0 0 3 0 0 0 0

Press 1 to start trip: 1
Cab travelling to destination...
Cab reached
0 0 0 0 0 0 0 0 7 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 6
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 4 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 5 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 9 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 2 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 + 0 0
0 0 0 0 0 0 0 0 0 0 3 0 0 0 0

Fare received: Rs 400
Rating received: 4.0
yashvardhan@YASHVARDHANs-MacBook-Pro bin %
```

The following is how interaction between the driver's and the customer's program takes place (observe how taking input on one user's end affects the program on both the user's end):-

https://user-images.githubusercontent.com/59053792/143475807-b9cb7506-5575-48d1-b45a-6a039184170c.mov
