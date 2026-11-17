# Test Team Organization

## Table of Contents
- [Test Team Organization](#test-team-organization)
  - [Table of Contents](#table-of-contents)
  - [1. Test Team Roles](#1-test-team-roles)
  - [2. Software Testing Lifecycle](#2-software-testing-lifecycle)
  - [3. Defect Lifecycle](#3-defect-lifecycle)
  - [4. Testing Documentation](#4-testing-documentation)

## 1. Test Team Roles
Whatever the organization / structure of your testing team, there are at least 4 roles that must be filled
- Test Manager
    - Project Manager - This role handles the cross-team communication, helps manage workloads in the team, and handles communication with stakeholders
- Testers
    - These are the people who do the actual testing. They participate in the whole testing lifecycle and report their work to the test manager
- Business Analysts
    - These people act as a "translator" for the testing team and stakeholders. They are technical individuals so they understand the verbiage, needs, and concerns the testers convey, but they also have business knowledge. This allows them to better understand stakeholder concerns and priorities. Being able to "speak" both technical and business language allows for BAs to be a go-between and help both sides understand what the other is saying
- Stakeholders
    - These are the people you are building the application for; these are your employers, contractors, etc... They are the people "in charge"

[&uarr; Return to Table of Contents](#table-of-contents)

## 2. Software Testing Lifecycle
When you are part of a testing process, there are six standard phases of work you will need to accomplish. The order you do the work may change, the name of the phases may vary, but the work itself should be completed all the time.
1. Requirement Analysis
    - During this phase, the team reviews documentation, expectations from stakeholders, and gather any other resources needed in order to actually determine what needs to be done
        - This is where your **Test Basis** is determined
2. Test Planning
   - Having figured out your basis, it's time to determine what your **Test Objectives** are, which will help to determine your actual **Test Objects**
   - This is also the phase where you put together your test data: depending on the type of testing you are doing, there will be different techniques you can do to optimize the amount of test data you need
3. Test Case Design*
    - This is where we actually make our test cases
        - Determine test preconditions
        - Determine test inputs
        - Determine expected test outputs
    - For automated testing, this is the phase where the code is written for the tests
4. Test Environment Setup*
    - This is where the data and environment necessary for the tests to run are set up
        - Setting up your test database
        - Spinning up the web server
        - Requisitioning cloud services
        - Scheduling manual testers
5. Test Execution
    - This is where the official test results are generated
    - The results of this final test flow should be saved and archived
    - It is common to generate multiple forms of test results
        - Human readable form (HTML)
        - CI / CD friendly form
        - You will typically want to save archived versions of your test reports
6. Test Closure
    - This is where you report on the results of your tests to the stakeholders
    - You generate defect reports and prepare to fix the defects discovered, or at least report those defects to the development team to fix
    - This is the time to have a team retrospective
        - Discuss what went well
        - Discuss what needs to be improved
        - Call out any work that was not completed in the lifecycle of work that needs to be done next lifecycle

[&uarr; Return to Table of Contents](#table-of-contents)

## 3. Defect Lifecycle
The **Defect Lifecycle** is a way of organizing your approach to fixing defects discovered by your tests.
1. Defect Identification
    - Defects are discovered during testing, which means the defect lifecyle itself starts as a sub-workflow of the [Testing Lifecycle](#2-software-testing-lifecycle)
    - All defects discovered should ideally have a defect report generated
2. Categorization & Prioritization
    - All defects should have at least two attributes
        - **Severity Score**
        - **Priority Score**
    - Defects with high priority scores should be handled before defects with low priority scores
    - Severity score should be used to help the fixer for the defect understand the scope of the defect
3. Assignment
    - Someone has to be told to fix the defect
    - Typically this is handled by a project manager, on a smaller team it may be volunteer based
4. Analysis & Planning
    - Once the defect is assigned, the assignee needs to go in and figure out what is causing the defect
        - The assignee may be unable to reproduce the defect, therefore the defect is "rejected"
        - The assignee may learn the defect is being fixed as part of a wider update, therefore the defect can be "rejected"
        - It may be determined the defect is something desirable by the stakeholders, so the defect is "rejected"
            - "It's not a bug, it's a feature"
5. Fix
    - The assignee goes in and actually fixes the error in the code that leaed to the defect
6. Verification
    - Rerun your test to make sure the failure is no longer present
        - If the failure no longer exists, you can close the defect
        - If it still exists, you go back to analysis and planning
7. Closure
    - Close the defect, create a report on work one
    - Periodically aggregate your defect reports and inform stakeholders on the work that has been done

[&uarr; Return to Table of Contents](#table-of-contents)

## 4. Testing Documentation
There are some common documents used by testing teams to help facilitate, order, and keep track of work
- Test Plan
    - Strategic document for the whole testing lifecycle, it incldes multiple important details for your testing work
        - Test Objectives
        - Schedule
        - Estimates
        - Deliverables
        - Reference to other documents
- Test Schedule
    - Timeline for your testing activities
        - Can be a fine-grain schedule with individual test timelines
        - High level overview of the testing timeline, more focused on sprint details than individual test details
- Requirements Traceability Matrix
    - Bidirectional map of requirements
        - User Stories / Features should be mapped to requirements
        - Requirements should be mapped to test data
        - Test data should be mapped to test cases
        - Test cases should be mapped to defects
    - RTMs are meant to be "living documents"
        - As test basis / conditions change, your RTM should be updated to accommodate those changes
            - This could mean adding on new requirements, test data, etc...
            - This cold mean removing old requirements, test data, etc...
- Software Requirements Specification
    - SRSs are detailed descriptions of the software being tested / built
        - This is where the requirements for the application exist
        - This is an invaluable tool for getting started with setting up a RTM: the user stories / features and the requirements for those features can usually be mapped from this document
- Test Progress Report
    - Any test report generated before the end of the test cycle
        - Reports on tests run
        - \# Passing Tests
        - \# Failing Tests
        - \# Skipped Tests
- Test Summary Report
    - The test report generated at the end of the test cycle
    - This is the final report presented to stakeholders and archived for future reference

[&uarr; Return to Table of Contents](#table-of-contents)