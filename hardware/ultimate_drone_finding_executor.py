from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
import RPi.GPIO as GPIO
import time

# Create a FastAPI instance
app = FastAPI()
TIME_DELAY_LOW = 0.1
TIME_DELAY_MID = 0.13

LED_PINS = [17, 27, 22, 23, 24] # GPIO pins for LEDs
ROTOR_PIN = 25 # GPIO pin for the rotor

# Add CORS Middleware
app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:5173"],  # Replace with your Vue app's origin
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Set up GPIO pin
GPIO.setmode(GPIO.BCM)
GPIO.setup(ROTOR_PIN, GPIO.OUT)
for pin in LED_PINS:
    GPIO.setup(pin, GPIO.OUT)


def led_animations():
    GPIO.output(ROTOR_PIN, GPIO.HIGH)
    for _ in range(2):
        fast_blink()
        chase_effect()
        chase_effect_reverse()
        to_middle()
        chase_effect_reverse()
        chase_effect()
        slow_blink()
    GPIO.output(ROTOR_PIN, GPIO.LOW)

def fast_blink():
    delay = 0.25
    rate = 0.02
    min_delay = 0.01

    while delay > min_delay :
        for pin in LED_PINS:
            GPIO.output(pin, GPIO.HIGH)
        time.sleep(delay)
        for pin in LED_PINS:
            GPIO.output(pin, GPIO.LOW)
        time.sleep(delay)
        delay -= rate

def slow_blink():
    delay = 0.01
    rate = 0.02
    max_delay = 0.25

    while delay < max_delay:
        for pin in LED_PINS:
            GPIO.output(pin, GPIO.HIGH)
        time.sleep(delay)
        for pin in LED_PINS:
            GPIO.output(pin, GPIO.LOW)
        time.sleep(delay)
        
        delay += rate
    


def chase_effect():
    for pin in LED_PINS:
        led_blink(pin)

def chase_effect_reverse():
    for pin in reversed(LED_PINS):
        led_blink(pin)

def to_middle():
    mid_index = len(LED_PINS) // 2

    for i in range(mid_index):
        GPIO.output(LED_PINS[i], GPIO.HIGH)
        GPIO.output(LED_PINS[-(i + 1)], GPIO.HIGH)
        time.sleep(TIME_DELAY_MID)
    GPIO.output(LED_PINS[mid_index], GPIO.HIGH)

    time.sleep(TIME_DELAY_MID)

    for i in range(mid_index):
        GPIO.output(LED_PINS[i], GPIO.LOW)
        GPIO.output(LED_PINS[-(i + 1)], GPIO.LOW)
        time.sleep(TIME_DELAY_MID)
    GPIO.output(LED_PINS[len(LED_PINS) // 2], GPIO.LOW)
    

def led_blink(pin, time_delay = TIME_DELAY_LOW):
    GPIO.output(pin, GPIO.HIGH)
    time.sleep(time_delay)
    GPIO.output(pin, GPIO.LOW)


@app.get("/api/blink")
async def handle_blink_request():
    try:
        led_animations()
        return {"message": "LED blinked!"}
    except Exception as e:
        return {"error": str(e)}

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=5000)

