<template>
    <div>
        <div @click="goToFunctions" class="drone">
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
                        <label>Manufacturing Date</label>
                        <p>{{ drone.manufacturingDate }}</p>
                    </li>
                    <li>
                        <label>Status</label>
                        <p>{{ formattedActivity }}</p>
                    </li>
                </ul>
            </div>
            <DroneIcon :style="{ fill: batteryColor }" />
        </div>
        <div class="find-drone">
            <aside @click="findDrone">
                <a @click.prevent>Find me</a>
            </aside>
            <p v-if="message" class="message messageFadeIn">{{ this.message }}</p>
        </div>

        
    </div>
    <LoadingScreen v-if="loading" @cancel-loading="loading=false" />
</template>

<script>
import DroneIcon from '@/assets/images/icons/IconDrone.vue';
import DroneService from '@/services/drone-service';
import { firstLetterUpperFormat } from '@/assets/utils/util-functions.js';
import LoadingScreen from '@/components/LoadingScreen.vue';

const MAX_HUE = 120; // Maximum hue value for green

export default {
    name: 'DroneDetails',
    components: {
        DroneIcon,
        LoadingScreen
    },
    props: {
        drone: {
            type: Object,
            required: false
        }
    },
    data() {
        return {
            message: null,
            loading: false
        };
    },
    methods: {
        goToFunctions() {
            this.$router.push({ path: '/drones/function', query: { id: this.drone.id } });
        },
        async findDrone() {
            this.loading = false;
            const droneService = new DroneService();
            try {
                this.loading = true;
                const response = await droneService.blinkDrone();
                if (response.success) {
                    this.message = `Drone ${this.done.id} found!`;
                } else {
                    this.message = `Drone ${this.drone.id} not found!`;
                }
            } catch (error) {
                this.message = `Error: ${error.message}`;
            } finally {
                this.loading = false;
            }
        }
    },
    computed: {
        batteryColor() {
            const battery = this.drone.batteryLevel;
            const hue = (battery / 100) * MAX_HUE; // 0 (red) to 120 (green)
            return `hsl(${hue}, 100%, 50%)`;
        },
        formattedActivity() {
            return firstLetterUpperFormat(this.drone.activity);
        }
    }
};
</script>


<style scoped>
.drone-overview {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 1rem;
    border: 1px solid #ddd;
    border-radius: 0.5rem;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);
    transition: box-shadow 0.3s, transform 0.3s;
    width: 100%;
    max-width: 40rem;
}

.drone-overview:hover {
    box-shadow: 0 1rem 2rem rgba(0, 0, 0, 0.2);
    transform: scale(1.02);
}

.find-drone {
    margin-top: 1rem;
    text-align: center;
}

aside {
    align-self: center;
    cursor: pointer;
    transition: transform 0.3s;
    flex-shrink: 0;
}

aside:hover {
    transform: scale(1.1);
}

a {
    font-size: 1.2rem;
    color: #2ecc71;
    text-decoration: none;
    padding: 0.5rem 1rem;
    border: 1px solid #2ecc71;
    border-radius: 0.3rem;
    transition: background-color 0.3s, color 0.3s, transform 0.3s;
}

a:hover {
    background-color: #27ae60;
    color: white;
    transform: scale(1.05);
}

.drone {
    display: flex;
    justify-content: center;
    align-items: center;
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
    max-width: 12rem;
    max-height: 12rem;
    border: 0.1rem solid #2c3e50;
    border-color: #2ecc71;
    cursor: pointer;
}

.drone-stats {
    display: flex;
    flex-direction: column;
    justify-content: center;
    text-align: center;
    margin-right: 1rem;
    font-size: 1.1rem;
    color: #2c3e50;
    width: 100%;
}

.drone-stats ul {
    list-style: none;
    padding: 0;
    margin: 0;
}
</style>
