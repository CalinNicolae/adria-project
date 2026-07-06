<template>
    <div class="drone">
        <DroneIcon :style="{ fill: batteryColor }" />
        <div class="drone-stats">
            <ul>
                <li>
                    <label>Drone ID</label>
                    <p>{{ drone.id }}</p>
                </li>
                <li>
                    <label>Battery</label>
                    <p>{{ drone.batteryLevel }}%</p>
                </li>
                <li>
                    <label>Time to charge</label>
                    <p >{{ calculateTimeToCharge() }} hours</p>
                </li>
                <li>
                    <label>Status</label>
                    <p>{{ formattedActivity }}</p>
                </li>
                <li>
                    <label>Is the drone functional?</label>
                    <p :style="{color: bool2Color(drone.functional)}">{{ formatBool2Text(drone.functional) }}</p>
                </li>
                <li>
                    <label>Manufacturing Date</label>
                    <p>{{ drone.manufacturingDate }}</p>
                </li>
                <li>
                    <label>Does the drone need repair?</label>
                    <p :style="{color: bool2Color(drone.needsRepair)}">{{ formatBool2Text(drone.needsRepair) }}</p>
                </li>
                <li>
                    <label>Your AdriaId</label>
                    <p>{{ drone.userId }}</p>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
import DroneIcon from '@/assets/images/icons/IconDrone.vue';
import { firstLetterUpperFormat } from '@/assets/utils/util-functions.js';

const MAX_HUE = 120; // Maximum hue value for green
const MAX_BATTERY = 100;
const CHARGE_RATE = 9;

export default {
    name: 'DroneDetails',
    components: {
        DroneIcon
    },
    props: {
        drone: {
            type: Object,
            required: false
        },
    },
    methods: {
        calculateTimeToCharge() {
            const battery = this.drone.batteryLevel;
            const timeToCharge = ((MAX_BATTERY - battery) / MAX_BATTERY) * CHARGE_RATE;
            return timeToCharge.toFixed(2);
        },
        formatBool2Text(bool) {
            return bool ? 'Yes' : 'No';
        },
        bool2Color(bool) {
            return bool ? 'green' : 'red';
        },
    },
    computed: {
        batteryColor() {
            const battery = this.drone.batteryLevel;
            const hue = (battery / 100) * MAX_HUE; // 0 (red) to 120 (green)
            return `hsl(${hue}, 100%, 50%)`;
        },
        formattedActivity() {
            return firstLetterUpperFormat(this.drone.activity);
        },
    }
};
</script>

<style scoped>
.drone {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    padding: 1rem;
    border: 1px solid #ddd;
    border-radius: 0.5rem;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);
    transition: box-shadow 0.3s, transform 0.3s;
    width: 100%;
    max-width: 40rem;
}

.drone:hover {
    box-shadow: 0 1rem 2rem rgba(0, 0, 0, 0.2);
    transform: scale(1.02);
}

.drone svg {
    background-color: #f5f5f5;
    border-radius: 15%;
    margin: 1rem;
    max-width: 10rem;
    max-height: 10rem;
    border: 0.1rem solid #2c3e50;
    border-color: #2ecc71;
}

.drone-stats {
    display: flex;
    flex-direction: column;
    justify-content: center;
    text-align: center;
    font-size: 1.1rem;
    color: #2c3e50;
    width: 100%;
}

.drone-stats ul {
    list-style: none;
    padding: 0;
    margin: 0;
}

/* Removed styles for li, li:hover, and li p */
</style>
