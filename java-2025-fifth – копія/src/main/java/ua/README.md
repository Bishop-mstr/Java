### 8. Cinema
- **Movie**: title, genre, durationMinutes, releaseDate
- **Actor**: firstName, lastName, birthYear
- **Hall**: hallNumber, capacity
- **Screening**: movie, hall, screeningDateTime
- **Ticket**: screening, seatNumber, price

**For the second lab:** Convert Actor and Hall to `record`. Add enums:
- `TicketStatus { AVAILABLE, RESERVED, SOLD, CANCELED }`
- `Genre { ACTION, DRAMA, COMEDY, HORROR, DOCUMENTARY }`
