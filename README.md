## Install Softwares
```
    JAVA JDK8
    Maven 3
```

## How to Run Test Automation Scripts

##### 1. Clone this repo
##### 2. Open the project folder in a terminal or command-prompt
```
    cd $project-folder
```

##### 4. Execute tests, run,
```
    mvn clean test
```


##### Test Details
###### Test Case 1
```
    # verifySearchResults - Search card and verify card contains the searched text and log the verified cards
```
###### Test Case 2
```
    # processCards - Search card, open card, log card details, add spoke person and send. Verify second window -  Requested Items & log table data, Files Tab - Verify patient name, DOB & pdf files visibility, ProviderDetails - Verify header
```