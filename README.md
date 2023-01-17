# Drones Project - Musala Soft

## Background
There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.

Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

## Task Description
We have developed a REST API using Spring Boot to allow customers to
- register a drone;
- load a drone with medication items;
- check loaded medication items for a given drone;
- check available drones for loading;
- check drone battery level for a given drone;

## API Documentation
### Authorization
All endpoints are open but API_KEY Authorization is coming soon

## Responses

Many API endpoints return the JSON representation of the resources created or edited. However, if an invalid request is submitted, or some other error occurs, The Application returns a JSON response in the following format:

```javascript
{
  "message" : string,
  "success" : bool,
  "data"    : string
}
```

The `message` attribute contains a message commonly used to indicate errors or, in the case of deleting a resource, success that the resource was properly deleted.

The `success` attribute describes if the transaction was successful or not.

The `data` attribute contains any other metadata associated with the response. This will be an escaped string containing JSON data.

## Status Codes

Gophish returns the following status codes in its API:

| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 201 | `CREATED` |
| 400 | `BAD REQUEST` |
| 404 | `NOT FOUND` |
| 500 | `INTERNAL SERVER ERROR` |