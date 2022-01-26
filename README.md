# space-trip


## Authorization

[Auth0](https://auth0.com/docs) was used for authorizing the incoming requests. Auth0 is an OAuth2 provider capable of supporting multiple flows. 
The one used in **space-trip** is [Resource Owner Passsowrd Flow](https://auth0.com/docs/get-started/authentication-and-authorization-flow/resource-owner-password-flow/)
Use the following Auth0 request to get an *access token* and use it as an Authorization Bearer in your **space-trip** requests.

```
curl --location --request POST 'https://dev-acnsk803.us.auth0.com/oauth/token' \
--header 'content-type: application/x-www-form-urlencoded' \
--header 'Cookie: did=s%3Av0%3A64bbcd60-7bb1-11ec-9037-a9776ac65845.GfQj33jOc8oY1O9KPXPT6gqQQ9p4PbZ%2FnDBBnCvnwfE; did_compat=s%3Av0%3A64bbcd60-7bb1-11ec-9037-a9776ac65845.GfQj33jOc8oY1O9KPXPT6gqQQ9p4PbZ%2FnDBBnCvnwfE' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'username=captain.kirk@gmail.com' \
--data-urlencode 'password=Abc1234!' \
--data-urlencode 'audience=http://space-trip.andreigog.com' \
--data-urlencode 'client_id=5YIMtvskfTU3Z98i5U3b9yTdKtVUlXIC' \
--data-urlencode 'client_secret=G2N49ic2devh-5KVSGAy9ZJwRotrlhDR_3E9YWE8qAQ9C0jBHSAwVuHIbHtPQwON'
```
```
--header 'Authorization: Bearer {access_token}' \
```

## Planet Service
- **GET** /v1/planets
  - Query Parameters
    - *page* (int >= 0, default = 0, required = false)
    - *pageSize* (0 <= int <= 25, default = 3, required = false )
    - *includeCrew* (default = false, required = false)
- **POST** /v1/planets
  - Request Body
    ``` 
    {
        "name": "Jupiter",
        "image": "image-jupiter-url"
    }
    ```
- **PATCH** /v1/planets/{planetId}
  - Request Body
    ``` 
    {
        "status": "En route"
    }
    ```
  - status is one of "En route", "OK", "TOOD", "!OK"
- **PUT** /v1/planets/{planetId}/assign-crew
  - Request Body
    ``` 
    {
        "crewId": "61efca7a127d4e0c73f213ce"
    }
    ```

## Crew Service (Dummy)
- **GET** /v1/crews
- **GET** /v1/crews/{crewId}
