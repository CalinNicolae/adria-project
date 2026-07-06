# Adria Project

A group project developed for the Applied Development (AD) course, combining a hardware component, a backend server, and a web client into a single integrated system.

## Project Status

This repository represents the final submitted version of the AD Project. The team has declared this version complete and no further changes will be made.

## Overview

The Adria Project is composed of three main components that work together:

- A hardware component responsible for physical device interaction
- A backend server that handles application logic, data processing, and communication with the hardware
- A web client that provides the user interface for interacting with the system

## Tech Stack

- **Backend:** Java
- **Frontend:** Vue.js, JavaScript, CSS
- **Hardware:** Dedicated hardware component for device level functionality

## Prerequisites

Before running this project, make sure you have the following installed:

- Java Development Kit (JDK), version compatible with the server module
- Node.js and npm, for building and running the Vue client
- Any hardware specific tools or drivers required by the hardware module
- A code editor such as Visual Studio Code or IntelliJ IDEA

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/CalinNicolae/adria-project.git
cd adria-project
```

### 2. Set up the server

Navigate to the server directory and build the Java backend using your preferred build tool (Maven or Gradle, depending on the project configuration).

```bash
cd server
```

Refer to the build configuration file inside this directory for the exact build and run commands.

### 3. Set up the client

Navigate to the client directory and install the dependencies.

```bash
cd client
npm install
npm run serve
```

The client will start a local development server, typically accessible at `http://localhost:8080` or a similar address.

### 4. Set up the hardware component

Refer to the documentation and code inside the `hardware` directory for setup instructions specific to the physical device used in this project.

## Configuration

Configuration values such as server endpoints, ports, and credentials should be reviewed and adjusted in the respective configuration files within the `client` and `server` directories before running the project in a new environment.

## Team

This project was developed by group05:

- Grzesko Szymon
- Hegedüs Zalán
- Calin Mihai Nicolae
- De Jaeger Gianni
- Debeir Lander
- Declercq Kasper


## Acknowledgments

This project was developed as part of the Applied Development course, combining hardware integration, backend development, and frontend web development into a single collaborative group project.
