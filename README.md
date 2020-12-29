# SupportAggregationHub
A support aggregation hub for Intuit hands-on interview.

## For Care Agents
Send a simple GET request to <URL>/myAggregatedHub where <URL> is the deployment endpoint.

For an example see [example](#example)

## For Developers

This project uses AWS cloud infrastructure. For a complete list of resources see [Architecture](#resources-list) 

### Prerequisites
* Java 11+
* Gradle 6.7.1+
* A Heroku account
* An AWS account with all the required permissions
* Heroku CLI
* Optional: AWS CLI

### Deploying
1. Create a [DynamoDB](https://console.aws.amazon.com/dynamodb) table named SupportAggregationHubCache
2. Start a [Lambda](https://console.aws.amazon.com/lambda) function for each of the following packages:
    1. cache - Make sure it has permissions to access the DynamoDB table and with environment variables:
       1. CACHE_TTL - The cache time-to-live in ms (e.g. 900000)
       2. AGGREGATION_INTERVAL - The allowed timed between aggregations in ms (e.g. 1800000)
    2. crm_connector
    3. sterilizer - with environment variables:
       1. SUPPORTED_PRODUCTS - The supported products separated by a comma (,) (e.g. RED,GREEN,BLUE)
    4. filter
    5. mapper
    6. reducer
###### optional: Update the function names in the config script (e.g. CACHE_LAMBDA_FUNCTION_NAME="<CACHE_FUNCTION_NAME>")
3. Create a [Step Function](https://console.aws.amazon.com/states) using the definition at the step-function.json file
   Make sure to replace the relevant lambda ARNs (e.g. "Resource": "arn:aws:lambda:<REGION_NAME>:<ACCOUNT_NAME>:function:<FUNCTION_NAME>")
4. Create a [CloudWatch](https://console.aws.amazon.com/cloudwatch) event to call the Step Function from the previous step with the desired interval (e.g. 4 hours)
5. Create an [API Gateway](https://console.aws.amazon.com/apigateway) with a resource named myAggregatedHub and a GET method. Point the method to invoke the main function. Make sure to pass an AWS Proxy Event.
6. Create a [Heroku](https://dashboard.heroku.com/) app.

### Updating
For Main
1. Run ./gradlew stage
2. Run heroku login
3. Run heroku create
4. Run git push heroku master

For other packages:
1. Run ./gradlew <FUNCTION_NAME>Zip (e.g. ./gradlew mainZip). Note that mainZip is available in the main module and all other scripts are available at the data module.
2. Upload the created .zip file available at <PATH_TO_MODULE>/build/distributions/<FUNCTION_NAME>-1.0.SNAPSHOT.zip to the corresponding Lambda function.

###### optional: For all packages: If you updated the config script in step 2 of the deployment you can use the "deploy" helper script for both steps with: ./deploy update <FUNCTION_NAME_1> <FUNCTION_NAME_2>... (e.g. ./deploy update main cache)

### Configuring
* Cache time-to-live: Change the CACHE_TTL environment variable in the cache Lambda function. (e.g. 900000)
* Data updates interval: Change the interval at the CloudWatch event. (e.g. 4 hours)
* Interval between aggregations: Change the AGGREGATION_INTERVAL environment variable in the cache Lambda function. (e.g. 1800000)
* Maximum requests concurrency: Change the "MaxConcurrency" value of the "Mapper" state in the Step Function. 0 means no limit. (e.g. "MaxConcurrency": 0)

### Future Development
* For splitting products duplicate the sterilizer function and set the SUPPORTED_PRODUCTS environment variable to the desired products. (e.g. "RED,GREEN" and "BLUE") 
* To limit their aggregation intervals duplicate the cache function and set the AGGREGATION_INTERVAL environment variable to the desired value. (e.g. 1800000 and 7200000)
* To limit their maximum concurrency duplicate the Step Function and set the "MaxConcurrency" value of the "Mapper" state to the desired value. (e.g. 10 and 20)
* For a refresh button, add a button to the HTML and trigger the Step Function.

### Assumptions
* Since a Lambda function is limited to 15 minutes runtime, all operations, including fetching a page of data, should take less than 15 minutes. This assumption seems reasonable especially when considering the default 15 minutes cache time-to-live.
* If a scheduled update is called a short time after an on-demand aggregation the data will not be updated again. This may result in data being up to aggregationInterval + updateInterval old (4.5 hours by default).

### Architecture
The following list covers the system services and their function:
* Main - Requests data from the backend and displays the results with a simple HTML.
* Cache - Retrieves data from the cache and returns if data is fresh. If not, it calls the aggregation.
* CRM Connector - Connects to a provided CRM and fetches data. If possible, support pagination.
* Sterilizer - Get a raw data from the CRM and removes irrelevant data, e.g. removes unsupported products.
* Filter - Filters the sterilized data by a given parameter and operation. (Not implemented yet)
* Mapper - Converts the filtered data to a format that allows aggregation.
* Reducer - Aggregates the mapped or partially aggregated data.

DynamoDB is used for caching data. 

CloudWatch events are used for scheduling.

#### Resources list
A list of required AWS resources:
* [DynamoDB](https://console.aws.amazon.com/dynamodb)
* [Lambda](https://console.aws.amazon.com/lambda)
* [Step Functions](https://console.aws.amazon.com/states)
* [CloudWatch](https://console.aws.amazon.com/cloudwatch) events
* [API Gateway](https://console.aws.amazon.com/apigateway)


## Example
An example is available at: []()
Resources are accessible for read-only at:
with:
or credentials: