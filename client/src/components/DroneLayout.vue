<template>
    <div class="wrapper">
        <aside>
            <RouterLink to="/drones">
                <ListIcon />
                <p>Drone list</p>
            </RouterLink>
            <RouterLink to="/drones/function">
                <FunctionsIcon />
                <p>Drone function</p>
            </RouterLink>
            <RouterLink to="/drones/return-drone">
                <ReturnIcon />
                <p>Return a Drone</p>
            </RouterLink>
            <RouterLink to="/drones/rent">
                <RentDrone />
                <p>Rent a drone</p>
            </RouterLink>
        </aside>
        <section>
            <RouterView />
        </section>
    </div>

</template>

<script>
import ListIcon from '@/assets/images/icons/IconList.vue';
import FunctionsIcon from '@/assets/images/icons/IconFunction.vue';
import ReturnIcon from '@/assets/images/icons/ReturnIcon.vue';
import RentDrone from '@/assets/images/icons/RentDrone.vue';

export default {
    name: 'DroneListView',
    components: {
        ListIcon,
        FunctionsIcon,
        ReturnIcon,
        RentDrone,
    },
};

</script>

<style scoped>
.wrapper {
    display: grid;
    grid-template-columns: 5% 1fr 5%;
}

aside {
    display: flex;
    flex-direction: column;
    background-color: #2ecc71;
    align-self: start;
    margin-top: 10rem;
    border-radius: 0.75rem;
}

aside a {
    display: flex;
    align-items: center;
    flex-direction: column;
    text-decoration: none;
    text-align: center;
    padding: 0.25rem 0;
    margin-bottom: 1rem;
}

aside a:last-child {
    margin-bottom: 0;
}

section {
    min-height: 50vh;
}

a.router-link-exact-active {
    color: var(--color-text);
}

a.router-link-exact-active:hover {
    background-color: transparent;
    cursor: default;
}
</style>
