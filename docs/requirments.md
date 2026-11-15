# DAL Taxi – Requirements

## Problem statement
Students at Dalhousie need a cheap, safe way to get around Halifax. DAL Taxi is an
in-house ride service where any verified student can be a rider or a driver,
depending on a toggle in their profile.

## User stories and acceptance criteria

### Accounts & verification
1. As a student, I want to sign up with my Dal email and school ID so that only students can use DAL Taxi.
   - Email must end in @dal.ca
   - School ID must match B00 + 6 digits
   - Password is stored hashed, never as plain text
2. As a student, I want to log in so that I can use the app securely.
   - Correct details return a token; wrong ones return 401
3. As an admin, I want to verify a student's ID so that only real students can ride or drive.
   - Unverified students cannot book, join or drive

### Profile & role toggle
4. As a student, I want to switch between rider and driver mode so that I can do both.
5. As a driver, I want to add my car details (model, plate, seats) so that riders know what to expect.
   - Driver mode is blocked until car details exist

### Normal ride
6. As a rider, I want to request a ride from A to B so that I can get somewhere.
7. As the system, I want to assign an available driver so that rides get picked up.
8. As a driver, I want to accept, start and complete a trip so that riders see its progress.
   - Only valid steps: requested → assigned → in progress → completed (or cancelled)
9. As a rider, I want to cancel before pickup so that I can change my plans.

### Carpool
10. As a driver, I want to post a carpool with destination, route, time and seats so that riders can share my trip.
    - Departure must be in the future; seats at least 1
11. As a rider, I want to search carpools by destination and time so that I can find a match.
    - Only carpools with seats left are shown
12. As a rider, I want to join a carpool so that I get a seat.
    - Seats drop by 1; a full carpool can't be joined; nobody joins twice

### Dashboards
13. As a rider, I want to see my upcoming and past trips.
14. As a driver, I want to see ride requests, my carpools and my earnings.

### DevOps
15. As the team, we want every merge to main built, tested and deployed automatically, with monitoring and alerts.