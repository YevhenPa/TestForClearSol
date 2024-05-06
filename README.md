# TestForClearSol

User API Documentation
Jar File: A JAR file containing the application code accompanies this documentation.

Base URL:
/api/v1/users

Endpoints:

1. **Get User by ID**
   - URL: `GET /{id}`
   - Description: Retrieves user details by ID.
   - Request Parameters:
     - id (Long): ID of the user to retrieve.
   - Response:
     - 200 OK: User details retrieved successfully.
     - 404 Not Found: If the user with the specified ID does not exist.

2. **Search Users by Birth Date Range**
   - URL: `GET /search`
   - Description: Retrieves users within a specified birth date range.
   - Request Parameters:
     - from (Date): Start date of the range (ISO format).
     - to (Date): End date of the range (ISO format).
   - Response:
     - 200 OK: Users within the specified birth date range.
     - 400 Bad Request: If the start date is after the end date.

3. **Create User**
   - URL: `POST /`
   - Description: Creates a new user.
   - Request Body: User object in JSON format.
   - Response:
     - 201 Created: User created successfully.
     - 400 Bad Request: If the request body is invalid.

4. **Update User (Partial)**
   - URL: `PATCH /{id}`
   - Description: Updates specified fields of a user.
   - Request Parameters:
     - id (Long): ID of the user to update.
   - Request Body: Map containing fields to update.
   - Response:
     - 200 OK: User updated successfully.
     - 404 Not Found: If the user with the specified ID does not exist.

5. **Delete User**
   - URL: `DELETE /{id}`
   - Description: Deletes a user by ID.
   - Request Parameters:
     - id (Long): ID of the user to delete.
   - Response:
     - 200 OK: User deleted successfully.
     - 404 Not Found: If the user with the specified ID does not exist.
