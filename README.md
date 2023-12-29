# READ ME

## PROJECT DESCRIPTION

An example of an e-mobility charging solutions platform, the service provide a REST API that is
capable
of managing Charge Detail Records (CDR) in real time to a network of Charge Point Operators
(CPO).

In order to achieve that goal, a CDR contract and a set of endpoints are required as follows:

### Charge Detail Record fields

• Session identification

• Vehicle identification

• Start time

• End time

• Total cost

### Endpoints

• Create a Charge Detail Record

    • The "End time" cannot be smaller than "Start time"

    • The "Start time" of an upcoming Charge Detail Record for a particular vehicle must always be bigger than the "End time" of any previous Charge Detail Records.

    • The "Total cost" must be greater than 0

• Get a Charge Detail Record by id

• Search all Charge Detail Records for a particular vehicle

    • "Start time" and "End time" fields must be sortable

## DATABASE

When the service start you can use the link http://localhost:8080/h2-console
with following info to access H2 in memory DB

![img.png](img.png)

The DB is created and fill at each start based on files **CDR_DDL.sql** and **CDR_DML.sql** in
resources folder

## SWAGGER-UI

http://localhost:8080/swagger-ui/index.html
