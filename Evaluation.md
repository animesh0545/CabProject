# Self Evaluation of Code on the basis of OOP Principles


## Principle 1: Encapsulate what varies
- According to this principle one should isolate the parts of code that changes and make use of repetitive blocks of code indirectly through functions.
- In our code, we could have made better use of this principle in the main class, where we wrote the code for login for both the customer and driver separately, even though the two involved similar lines of code (taking in the username and password), with the customer case involving an extra case of sign-up, in which case the name is also taken as an input.

- In this case, we could have encapsulated the part where the username and password were taken as input, and used in both the cases.
 
<br>


## Principle 2: Favour Composition over Inheritance
- Making use of composition gives class a polymorphic behavior and should be favoured over inheritance since Java doesn't support multiple inheritance. Composition also allows us to add new behaviors at runtime.


<br>

## Principle 3: Program to an Interface not Implementation
- This principle is based on the notion that the implementation details should be kept separate from the client code. This ensures that any changes in the backend can be made easily without impacting the client-side code and helps in code maintenance.
- In our code, we do not use interfacing. Rather we make use of inheritance to allow both `Customer` and `Driver` class to inherit the `Login` class, which comprises the login and signup functions, which we use through the sub-classes as per need.
- A better approach would have been to create a *Login *interface** since there is a **has a** relation between the `Customer` and `Login` class as well as between `Driver` and `Login` class.

<br>

## Principle 4: Strive for loose coupling between objects that interact
- Loose coupling involves minimizing interdependency between objects. It allows change in any one class without affecting the interdependency with another class, achieved through interface.
- Since our program has concrete classes and is devoid of any interface, we haven't used loose coupling anywhere in our code.

<br>

## Principle 5: Classes should be open for extension and closed for modification
- 

<br>

## Principle 6: Depend on abstraction, do not depend on concrete classes
- This is the dependency inversion principle which helps to make the code more flexible and reusable, since the implementation can always be changed without affecting the abstract classes that are used.
- In our code, we have not used abstraction. In fact, all the classes used are concrete classes.
- There is a significant scope of improvement in terms of incorporating abstractions in the code, with the help of abstract classes and interfaces.

<br>

---

# Evaluation of Code with reference to the Observer Design Pattern

- The observer pattern is a popular design pattern in Object oriented programming. It defines a one-to-many dependency between objects so that when the state of one object changes, all of its dependents are notified of the change. The notifier is the *subject* while the dependents are the *objects*.
- 


