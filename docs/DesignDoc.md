---
geometry: margin=1in
---
# PROJECT Design Documentation

> _The following template provides the headings for your Design
> Documentation.  As you edit each section make sure you remove these
> commentary 'blockquotes'; the lines that start with a > character
> and appear in the generated PDF in italics but do so only **after** all team members agree that the requirements for that section and current Sprint have been met. **Do not** delete future Sprint expectations._

## Team Information
* Team name: lowercase_h
* Team members
  * Yat Long Chan
  * Graden Olson
  * Ben Hemmers
  * Vincent Son
  * Guangcheng Liu

## Executive Summary

This is a summary of the project.

### Purpose
>  _**[Sprint 2 & 4]** Provide a very brief statement about the project and the most
> important user group and user goals._

### Glossary and Acronyms
> _**[Sprint 2 & 4]** Provide a table of terms and acronyms._

| Term | Definition |
|------|------------|
| SPA | Single Page |


## Requirements

This section describes the features of the application.

> _In this section you do not need to be exhaustive and list every
> story.  Focus on top-level features from the Vision document and
> maybe Epics and critical Stories._

### Definition of MVP
> _**[Sprint 2 & 4]** Provide a simple description of the Minimum Viable Product._

### MVP Features
>  _**[Sprint 4]** Provide a list of top-level Epics and/or Stories of the MVP._

### Enhancements
> _**[Sprint 4]** Describe what enhancements you have implemented for the project._


## Application Domain

This section describes the application domain.

![Domain Model](domain-model.png)

> _**[Sprint 2 & 4]** Provide a high-level overview of the domain for this application. You
> can discuss the more important domain entities and their relationship
> to each other._


## Architecture and Design

This section describes the application architecture.

### Summary

The following Tiers/Layers model shows a high-level view of the webapp's architecture. 
**NOTE**: detailed diagrams are required in later sections of this document.
> _**[Sprint 1]** (Augment this diagram with your **own** rendition and representations of sample system classes, placing them into the appropriate M/V/VM (orange rectangle) tier section. Focus on what is currently required to support **Sprint 1 - Demo requirements**. Make sure to describe your design choices in the corresponding _**Tier Section**_ and also in the _**OO Design Principles**_ section below.)_

![The Tiers & Layers of the Architecture](architecture-tiers-and-layers.png)

The web application, is built using the Model–View–ViewModel (MVVM) architecture pattern. 

The Model stores the application data objects including any functionality to provide persistance. 

The View is the client-side SPA built with Angular utilizing HTML, CSS and TypeScript. The ViewModel provides RESTful APIs to the client (View) as well as any logic required to manipulate the data objects from the Model.

Both the ViewModel and Model are built using Java and Spring Framework. Details of the components within these tiers are supplied below.


### Overview of User Interface

This section describes the web interface flow; this is how the user views and interacts with the web application.

> _Provide a summary of the application's user interface.  Describe, from the user's perspective, the flow of the pages in the web application._


### View Tier
> _**[Sprint 4]** Provide a summary of the View Tier UI of your architecture.
> Describe the types of components in the tier and describe their
> responsibilities.  This should be a narrative description, i.e. it has
> a flow or "story line" that the reader can follow._

> _**[Sprint 4]** You must  provide at least **2 sequence diagrams** as is relevant to a particular aspects 
> of the design that you are describing.  (**For example**, in a shopping experience application you might create a 
> sequence diagram of a customer searching for an item and adding to their cart.)
> As these can span multiple tiers, be sure to include an relevant HTTP requests from the client-side to the server-side 
> to help illustrate the end-to-end flow._

> _**[Sprint 4]** To adequately show your system, you will need to present the **class diagrams** where relevant in your design. Some additional tips:_
 >* _Class diagrams only apply to the **ViewModel** and **Model** Tier_
>* _A single class diagram of the entire system will not be effective. You may start with one, but will be need to break it down into smaller sections to account for requirements of each of the Tier static models below._
 >* _Correct labeling of relationships with proper notation for the relationship type, multiplicities, and navigation information will be important._
 >* _Include other details such as attributes and method signatures that you think are needed to support the level of detail in your discussion._

### ViewModel Tier
CupboardController: Used to handle HTTP requests to the API.  

> _**[Sprint 4]** Provide a summary of this tier of your architecture. This
> section will follow the same instructions that are given for the View
> Tier above._

> _At appropriate places as part of this narrative provide **one** or more updated and **properly labeled**
> static models (UML class diagrams) with some details such as critical attributes and methods._
> 
![Replace with your ViewModel Tier class diagram 1, etc.](model-placeholder.png)

### Model Tier
Need: Object representation of a need.  
CupboardFileDAO: Used to interact with the file storage system and perform CRUD operations on Need objects.

> _**[Sprint 2, 3 & 4]** Provide a summary of this tier of your architecture. This
> section will follow the same instructions that are given for the View
> Tier above._

> _At appropriate places as part of this narrative provide **one** or more updated and **properly labeled**
> static models (UML class diagrams) with some details such as critical attributes and methods._
> 
![Replace with your Model Tier class diagram 1, etc.](model-placeholder.png)

## OO Design Principles

**Single Responsibility:** In sprint 1, the single responsibility principle is used to ensure that each class we create for our API  is only responsible for one aspect of the functionality. We separate our classes into the Model, Persistence, and Controller tiers. In the Model tier, we have the Need class, which acts as a reperesentation of a need but is not responsible for managing CRUD operations. In the Persistence tier, we have a CupboardFileDAO class which is responsible for managing only interacting with the file system and performing CRUD operations on Need objects. Finally, in the Controller tier, we have the CupboardController, which is only responsible for responding to client HTTP requests.  

**Open/Closed:** We use the open/closed principle in sprint 1 mainly in the persistence tier. By creating a CupboardDAO interface, we can easily add additional operations to the API without removing any existing code, and we can work with alternative storage systems simply by creating another implementation of the CupboardDAO interface. This also means that no code needs to be changed in the Controller tier if we were to switch to another storage system. 

**Low Coupling:** We will use low coupling to limit the number of unnecessary
relationships between classes. Working together with single responsibility, low coupling can be
used to make sure the relationship between classes fits into that classes’ single responsibility.
Low coupling should not be our biggest priority as long as we don’t overdo the amount of
relationships between classes. For example, the helper does not need to be directly related to the
checkout, instead, the funding basket can handle that relationship. Another way we could use
low coupling is having the manager access needs not through the Need class but through the
Cupboard class.

**Information Expert:** We will use information experts to process data efficiently by
putting those operations in the class with the attribute data. This will make operations, such as
binary operations, easier to understand when working in certain classes. Information experts
basically hide the more complicated looking operations in methods so that they can easily be
repeated and easier to understand when looking over the code. We could use this in the
Authenticator class so that operations to check the user has correctly logged in can be easily
accessed by the user class.

**Dependency Inversion/Injection:** We focus on creating a layer of abstraction between high and low level modules. With these implementation, we do not need to directly couple between the modules. Instead, we will inject low level modules into high level modules to form loose coupling. We can implement this approach on a Cupboard controller that will interact with the interface CupBoardDAO instead of CupboardFileDAO to minimize tight coupling.


**Law of Demeter:** We aim to minimize the direct coupling between objects or classes where it is deemed unnecessary. Instead, we opt for the use of intermediaries that already possess established couplings to exchange data. This approach serves to restrict the influence one class has on another, such as when a class may need to change. By doing so, the impact of modifications becomes localized 
to the immediate coupling, enhancing the maintainability and flexibility of the system. For example, the CupboardDAO exhibits a direct coupling with the Need class. To access the Need class, we follow the route through CupboardDAO, ensuring a more controlled and encapsulated interaction between these components.


**Pure Fabrication:** Pure fabrication is a term for not real objects, nor is it something special. It is similar to abstractions such as facades, or a proxy. They aren’t necessarily tied to the domain or the problem its solving, but it serves as a helper or a connector between classes. This helps make low coupling and high cohesion a success by providing external systems to aid complex operations.

**The Controller:** The controllerprinciple is saying that the thing responsible for handling system operations should be a separate class. They are like a mediator between the user interface and logic which increase the flow of information. It is a non-user interface responsible to receive or handle an event.



> _**[Sprint 2, 3 & 4]** Will eventually address upto **4 key OO Principles** in your final design. Follow guidance in augmenting those completed in previous Sprints as indicated to you by instructor. Be sure to include any diagrams (or clearly refer to ones elsewhere in your Tier sections above) to support your claims._

> _**[Sprint 3 & 4]** OO Design Principles should span across **all tiers.**_

## Static Code Analysis/Future Design Improvements
> _**[Sprint 4]** With the results from the Static Code Analysis exercise, 
> **Identify 3-4** areas within your code that have been flagged by the Static Code 
> Analysis Tool (SonarQube) and provide your analysis and recommendations.  
> Include any relevant screenshot(s) with each area._

> _**[Sprint 4]** Discuss **future** refactoring and other design improvements your team would explore if the team had additional time._

## Testing
> _This section will provide information about the testing performed
> and the results of the testing._

### Acceptance Testing
> _**[Sprint 2 & 4]** Report on the number of user stories that have passed all their
> acceptance criteria tests, the number that have some acceptance
> criteria tests failing, and the number of user stories that
> have not had any testing yet. Highlight the issues found during
> acceptance testing and if there are any concerns._

### Unit Testing and Code Coverage
> _**[Sprint 4]** Discuss your unit testing strategy. Report on the code coverage
> achieved from unit testing of the code base. Discuss the team's
> coverage targets, why you selected those values, and how well your
> code coverage met your targets._

>_**[Sprint 2 & 4]** **Include images of your code coverage report.** If there are any anomalies, discuss
> those._

## Ongoing Rationale
>_**[Sprint 1, 2, 3 & 4]** Throughout the project, provide a time stamp **(yyyy/mm/dd): Sprint # and description** of any _**major**_ team decisions or design milestones/changes and corresponding justification._
