# Testing Philosophy: Why Test?
**Testing SPhilosophy** is simply the underlying beliefs, goals, and vocab that controls how we go about testing software

## Table of Contents
- [Testing Philosophy: Why Test?](#testing-philosophy-why-test)
  - [Table of Contents](#table-of-contents)
  - [1. Core Concepts](#1-core-concepts)
    - [1.1 Quality Assurance \& Quality Control](#11-quality-assurance--quality-control)
    - [1.2 Verification \& Validation](#12-verification--validation)
    - [1.3 Error / Defect / Failure](#13-error--defect--failure)
    - [1.4 Requirements](#14-requirements)
  - [2. Tester Mindset](#2-tester-mindset)
  - [3. Static \& Dynamic Testing](#3-static--dynamic-testing)
  - [4. Automated \& Manual Testing](#4-automated--manual-testing)
  - [5. Testing Pyramid](#5-testing-pyramid)
  - [6. Testing Components](#6-testing-components)

## 1. Core Concepts

### 1.1 Quality Assurance & Quality Control
Two terms that represent different approaches for maintaining a high level of "quality" for a product or service.
- **Quality** - Catch-all term used to represent the features, components, and non-tangibles of a service / product that all contribute to the satisfaction of the user


**QA** & **QC** are two approaches to maintaining a high level of product quality.

**Quality Assurance (QA)**
- Process Oriented Approach
    - Asks the question "Do the processes we go through help or hurt the quality of our product?"
- "Ensuring good process leads to a good product"

**Quality Control (QC)**
- Product Oriented Approach
    - This involves identyfing and fixing defects in the product
- "Ensure the product meets quality standards"

### 1.2 Verification & Validation
**Verification** and **Validation** are ways of classifying the type of testing we are doing.

**Verification**
- "Are we building the product correctly?"
    - Test cases passing indiciate the product is being built correctly
    - Test cases failing indicate the product is not being built correctly
- Often done at the unit / integration level
    - Tests are done on the source code itself in small units

**Validation**
- "Are we building the right product?"
    - Test "cases" passing would involve things like the stakeholders giving their approval for the product you are building, users reporting satisfaction with the application, any sort of non-functional positive feedback
    - Test "cases" failing would involve things like stakeholders giving critical feedback on the product, users reporting unhappiness with the application, this kind of non-functional, negative feedback
- Often done at the system / integration level
    - Tests are done on the system as a whole

In other words:
- Verification answers the question "Does the product match the specs?"
- Validation answers the question "Does the user find this useful?"

### 1.3 Error / Defect / Failure
- ***Error*** - Mistake made by a person
    - Any mistake made by the developer. (Referencing wrong variable, forgetting to close a resource, setting up multithreading incorrectly so a live-lock is created, etc...)
- ***Defect*** - Incorrect behavior or failed requirement present in software
    - Error in software &rarr; actual problem, (Defect) present in your source. This means "error" represents the human mistake, "defect" represents the mistake itself within the code
- ***Failure*** - This happens when a "defect" is executed
    - Anytime your code does something unexpected because of a "defect" in the system, you get a "failure". (Wrong value being returned, code crashing, process taking too long, any witness / live deviation from the expected in your runnning code is a "failure")

**Error** (Human Mistake) &rarr; **Defect** (Mistake / Deviation in the Code) &rarr; **Failure** (Observed Deviation in Running Code)

### 1.4 Requirements
**Requirements** specify what should and should not be part of a product
- Technical Requirements
- Business Requirements
- Regulatory Requirements
- Accessiblity Requirements
- Deployment Requirements

Fundamently, anything that should be considered when building / testing / maintaining a product is a type of requirement. It is the collection of requirements that inform us on what the product actually is, and ho we should go about building / testing / maintaining that product.

Requirements can be functional and non-functional.
- **Functional Requirements** - Actions / capabilities of the product
    - Login
    - Registration
    - Withdraw Money
    - Order a Coffee
    - Any feature or capability of the application
- **Non-Functional Requirements** - Auxilary Concerns of the Product
    - Request Respones Times
    - Product Availability
    - Transactions per Second
    - Regulatory Compliance
    - Any need that is not specifically concerned with the features of the application

[&uarr; Return to Table of Contents](#table-of-contents)

## 2. Tester Mindset
Testers need to approach software with a different but complimentary mindset to developers

Developers:
- Goal Oriented
- "How do I make this work?"
- Solution Focused
- Risk is Technical / Performance
- Build the Application

Testers:
- Problem Oriented
- "How do I break this?"
    - Find *defects* created by developer *errors* in testing before those *failures* are discovered in production
- User Perspective Focused
    - Rather specific user perspective - the problematic user
- Risk is Failure Focused - Catch *Defects* in Testing so they Never Reach Production
- Find the Weaknesses and Vulnerabilities of the Application

| Role | Orientation | Question Asked | Primary Focus | Risk | Overall Goal |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Developers** | Goal | "How do I make this work?" | Solution | Technical / Performance | Build the Application |
| **Testers** | Problem | "How do I break this?" | User Perspective | Failure Focused | Find Application Weaknesses and Vulnerabilities |

Together, developers and testers taking these different approaches to the application can lead to a much higher quality product than if one or the other as left off
- No developers means no product
- No testers means you effectively are testing in production - it is never a good idea to have your end users be your testers

Developers and testers are on the same team - they should fill complimentary roles, not adversarial roles. It is always best to cultivate a positive relationship between teh dev team and the test team when they are separate.

[&uarr; Return to Table of Contents](#table-of-contents)

## 3. Static & Dynamic Testing
Testing can be done whether or not you have running testing. The difference in categorization is simply whether or not the software is running. If it is running, you are doing "dynamic" testing, otherwise you are doing "static" testing.

**Dynamic Testing**
- Requires software to be running
- Checks actual code execution - Inputs / Outputs

**Static Testing**
- Does not require running software
- Checks documentation, involves code reviews, static code analysis, linting, etc...

[&uarr; Return to Table of Contents](#table-of-contents)

## 4. Automated & Manual Testing
**Automated Testing** is often used with dynamic testing and involves letting software facilitate the execution and reporting of test results.

**Manual Testing** involves a person looking over your resources and reporting on the results instead of handing off those resposibilities to code.

**Automated Testing**
- Easy to repeat tests
    - Particularly useful for complex, tedious, or repetitive tests
- Setting up automated tests is time consuming, and it requires a level of technical expertise
- Tend to run much quicker than manual tests

**Manual Testing**
- Useful for one-off types of tests
    - Not a great choice if the test is complex, tedious, or repetitve, which should be automated if possible
- Unlike automated testing that requires technical expertise, manual testing has a much lower barrier of entry - give someone instructions, they follow those entries, and if they note any failures, they report them
- These tests take typically much longer to complete than automated tests

[&uarr; Return to Table of Contents](#table-of-contents)

## 5. Testing Pyramid
All testing fits in one of four levels - these levels are known as the **Testing Pyramid**

- Acceptance Testing
    - Testing done on the finished product from the user perspective
        - Acceptability (does the user actually like the product)
        - Regulatory Compliance (does the product meet law requirments)
        - Any subjective tests fits in this level
- System Testing
    - Testing done on the product that incorporates all related systems and components
        - End-to-End Testing (can a user register a new account through the web portal)
        - Performance Testing (do HTTP requests get handled within an acceptable time range)
- Integration Testing
    - Testing done on related components of your software
        - JUnit tests on your business layer which itself utilizes the Repository layer to facilitate database interactions
        - API testing your application controllers
        - Testing your UI loads the correct resources with a mocked backend
- Unit Testing
    - Testing done to individual components of your application in isolation
        - JUnit tests done one individual methods
        - API tests to validate the schema, status codes, etc... of your controllers in isolation
        - Individually testing components of your web app

[&uarr; Return to Table of Contents](#table-of-contents)

## 6. Testing Components
All of your tests are going to have (or should have) a large collection of data and work behind them. Your tests should not exist in a vacuum; you should never say a test exists because "I think it is a good idea". The following tests components should help determine your backing data for every test you create.

- **Test Basis**
    - The complete collection of requirements and documentation you have access to that inform you on the product being built / needing to be tested
    - In other words, **Test Basis** is the stuff that tells you what you need to tests
    - All tests should have some basis, some root cause, a document, requirement, something that gives reason for the test to exist. If you cannot pinpoint a discrete resource that gives legitimacy to your test, it means your test has no "basis", and therefore is not needed
    - Test Basis provides the reasons for your tests
- **Test Analysis**
    - Process of studying your **Test Basis** to determine what actually needs to be tested
    - This process helps us determine the **Test Conditions** we are working with
- **Test Conditions**
    - Aspects of the software that actually need to be tested. These are going to be things like your functional requirements, performance needs, accessibility targets, anything taht can be measured, verified / confirmed is a test condition
    - After we have determine our **Test Conditions**, we are finally able to pin down what our actual **Test Objectives** are
- **Test Objectives**
    - Actual reasons we test. Objectives can be things like validating functional requirements, checking performance meets our standards, etc...
    - **Test Objectives** will have one ore more **Test Objects** associated with them
- **Test Objects**
    - Resources that testing is actually done on
        - If you have a registration method, the **Test Object** is the method itself
        - If you have a user story for users logging in, the user story can be a **Test Object**
    - Your **Test Objects** are the focus of any **Test Cases** you build
- **Test Cases**
    - Formal, detailed sets of the following
        - Preconditions
        - Inputs
        - Actions
        - Expected Results
    - **Test Cases** require **Test Data** and tend to be organized in **Test Suites**
- **Test Data**
    - Values used as inputs, and possibly expected outputs, for your test cases
- **Test Suites**
    - Collections of related **Test Cases**

[&uarr; Return to Table of Contents](#table-of-contents)