# CS 390 - Framework: IA 3

Implement a simple Spring Boot server that supports the RESTful style API documented below. Start with the framework provided here.

The framework code includes classes: PointOfInterest, PoiManager, and GeoLocation. Read through those classes to make sure that you have a clear understanding of them before you start.

Test your server using curl commands from the command line and/or JUnit tests.

# Get a specific point of interest by ID
Returns a JSON representation of a specific point of interest.

# Create a new point of interest
Create a new point of interest.

# Delete a single point of interest
Delete a single point of interest by ID number.

# Find points of interest within a radius, containing tags
Given a target location and a radius and optional search string, find points of interest that are within the radius of the target location, and have tags or name that match the search string (if provided).
