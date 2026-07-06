# Analysis & Development Project - Adria 2084 - Raspberry pi

This project provides a simple API for controlling LEDs and a rotor connected to a Raspberry Pi with FastAPI.

  
  - [Features](#features)
  - [Usage](#usage)
  - [Requirements](#requirements)
  - [Installation](#installation)
  - [Configuration](#configuration)

## Features
- Control 5 LEDs connected to GPIO Raspberry pi
- Send a GET request to /api/blink to turn the LED on and off.
- Built with FastAPI for a lightweight and efficient API server.
- CORS configuration allows communication from your frontend application.

## Usage
1. run the script (don't forget to activate virtual envirement)
    ```
    python3 ultimate_drone_finding_executor.py
    ```

2. Send GET request to http://<your_raspberry_pi_ip>:5000/api/blink

This will: 
- spin the rotor
- Control animation patterns of LEDs

## Requirements
### Hardware
- Raspberry pi
- 5x LED
- 1x rotor
- 5x 100ohm resistor
- jumper wires

### Software
- Raspberry Pi connected to the same network as the client
- python3
- Required libraries:
    - fastapi
    - uvicorn
    - RPi.GPIO

## Installation

Create virtual envirement
```
python3 -m venv croptek_env 
``` 
**Note**: use `source croptek_env/bin/activate` to open virtual envirement and `deactivate` to close.


Install required libraries:
```
pip install fastapi uvicorn RPi.GPIO
```

Save the provided code from [ultimate_drone_finding_executor.py](./ultimate_drone_finding_executor.py)

## Configuration

Set these variables to the pin numbers where the LEDs (for the best effect they have to be in the right order) and the rotor are connected
```
LED_PINS = [17, 27, 22, 23, 24] # GPIO pins for LEDs
ROTOR_PIN = 25 # GPIO pin for the rotor
```
**Configure CORS:** Set "allow_origins" parameter to match the frontend application
```
# Add CORS Middleware
app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:5173"],  # Replace with your frontend origin
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)
```

**Optional**: Change the port 
```
if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=5000)
```
