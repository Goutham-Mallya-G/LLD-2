# Railway Reservation System

A console-based Java application that simulates a railway ticket reservation workflow with confirmed tickets, RAC allocation, waiting list handling, cancellation flow, and ticket availability reporting.

## Overview

This project implements the following operations:

1. Book a ticket
2. Cancel a ticket
3. Print booked tickets
4. Print available tickets

The application runs fully in memory and uses a menu-driven CLI for interaction.

## Features

- Passenger booking with berth preference
- Automatic lower-berth prioritization for:
  - passengers aged 60 and above
  - female passengers traveling with a child under 5
- RAC allocation using side-lower berths
- Waiting-list support when RAC is full
- Automatic promotion flow on cancellation:
  - RAC to confirmed
  - waiting list to RAC
- Ticket and berth availability reporting

## Reservation Rules Implemented

- Total confirmed capacity: `63`
- Total RAC capacity: `18`
- Total waiting-list capacity: `10`
- Children below age 5 do not get a separate ticket, but their details are stored
- If a preferred berth is unavailable, the system allocates the next available berth based on internal priority
- If no confirmed berth is available:
  - allocate RAC if possible
  - otherwise allocate waiting list
  - otherwise reject booking with `No tickets available`

## Berth Layout

The berth model is generated in `9` blocks, each containing:

- `2` lower berths
- `2` middle berths
- `2` upper berths
- `1` side-lower berth
- `1` side-upper berth

This results in:

- Lower: `18`
- Middle: `18`
- Upper: `18`
- Side upper: `9`
- Side lower: `9`

In the current implementation:

- Confirmed tickets are allocated from lower, middle, upper, and side-upper berths
- Side-lower berths are reserved for RAC passengers, with up to `2` passengers per side-lower berth

## Project Structure

```text
.
├── RailwayReservationSystem.java
├── Requirement.txt
├── db/
│   └── DB.java
├── enums/
│   ├── BerthType.java
│   └── Gender.java
├── model/
│   ├── Berth.java
│   ├── Children.java
│   └── Passenger.java
├── service/
│   ├── AllocationService.java
│   └── ListPrinting.java
└── view/
    └── AppViewRailway.java
```

## Main Components

- `RailwayReservationSystem.java`: application entry point
- `view/AppViewRailway.java`: CLI menu, input handling, and user interaction
- `service/AllocationService.java`: booking, RAC, waiting-list, and cancellation logic
- `service/ListPrinting.java`: booked-ticket and available-ticket reporting
- `db/DB.java`: in-memory data store and berth generation
- `model/*`: domain objects such as passenger, child, and berth
- `enums/*`: berth type and gender enums

## Requirements

- Java `21` or later

Java 21 is recommended because the current cancellation logic uses `removeFirst()` on a list-backed collection path supported by newer Java APIs.

## Compile

From the project root:

```bash
javac RailwayReservationSystem.java view/AppViewRailway.java service/*.java model/*.java enums/*.java db/*.java
```

## Run

```bash
java RailwayReservationSystem
```

## Menu Options

When the program starts, it shows these options:

```text
1. Book Ticket
2. Cancel Ticket
3. Print Booked Ticket
4. Print Available Ticket
```

## Sample Flow

Typical usage looks like this:

1. Start the application
2. Choose `1` to book a ticket
3. Enter passenger details:
   - name
   - age
   - gender
   - preferred berth
4. Use `3` to print booked tickets
5. Use `4` to inspect remaining available berths
6. Use `2` with a ticket number to cancel and trigger promotion from RAC or waiting list

## Notes

- The application stores all data in memory only
- No database or external dependencies are required
- Ticket numbers are generated sequentially during runtime
- State resets whenever the application restarts

## Source Requirement

The original problem statement used for this implementation is available in [Requirement.txt](/home/mallya/Productivity/Zoho-Incubation/LLD/RailwayReservation/Requirement.txt).
