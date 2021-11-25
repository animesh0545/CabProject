# Project 14: Uber Cab Booking System

## Description

---

This project implements a basic system to manage cab booking from both customer's and driver's point of view.

- First, the customer runs our application. The application prompts the customer to either log in if the customer already has an account with the Uber Cab application else sign up if he/she is new to the application.

- Next, after the customer has successfully logged in or signed up with our application, the application asks the customer to enter his/her current coordinates with respect to the city grid we have designed for this app. **The coordinates that the customer enters should be zero indexed.**

- After the customer enters his/her current coordinates, the app displays a list of landmarks present in the grid and prompts him/her to choose one of these landmarks as destination point.

- Once the customer has chosen his/her destination point, a list of all the available cabs (along with the information like distance of the cab from the customer, fare charged, driver rating etc) is displayed to him/her. Appropriate message is displayed if no cabs are available at the moment. Customer chooses one of these cabs based on his/her preference.

- Once the customer has chosen the cab, the customer's app will wait for the driver's response. **The driver always accepts the customer's request.**

- Now, the driver opens the app on his/her device. The app prompts the driver to log in. The driver logs in using his/her log in credentials.

- After the driver has successfully logged in, notification would be displayed to the driver.

  - If no customer is calling the driver then the app would display "no new notification received" and terminate.
  - If any customer is calling the driver then driver would get a notification telling that him/her about the calling customer's location and destination to reach. **The app then prompts the driver to accept the customer's request which he/she always does.**

- Once the driver accepts the customer's request, he/she starts moving towards the customer with a constant speed. **The driver's location along with the ETA is dynamically displayed on the app on both customer's side and driver's side.**

- Once the driver reaches the customer's location, the app prompts
