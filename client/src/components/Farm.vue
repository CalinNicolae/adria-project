<template>
    <div class="farm">
        <div class="farm-title">
            {{ farm.name }}
        </div>
        <div class="farm-frame">
            <img src="@/assets/images/croptek-logo.jpeg" alt="farm-image" class="farm-image" />
        </div>
        <div class="links">
            <RouterLink :to="{ path: '/statistics', query: { name: farm.statName } }" class=" button-link">Statistics
            </RouterLink>
            <RouterLink to="/drones" class="button-link">Drone Management</RouterLink>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Farm',
    props: {
        farm: {
            type: Object,
            required: true
        }
    }
};
</script>

<style scoped>
.farm {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin: 2rem;
}

.farm-frame {
    width: 10rem;
    height: 10rem;
    border-radius: 50%;
    border: 0.5rem solid #2ecc71;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
    margin-bottom: 1rem;
}

.farm-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.farm-title {
    font-size: 1.8rem;
    font-weight: bold;
    color: #2ecc71;
    text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
    margin-bottom: 1rem;
    text-align: center;
}

.links {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.button-link {
    display: inline-block;
    margin: 0.5rem 0;
}
</style>
