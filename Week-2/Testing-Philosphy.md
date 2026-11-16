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