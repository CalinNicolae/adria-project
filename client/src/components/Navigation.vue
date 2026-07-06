<template>
  <nav>
    <RouterLink to="/farms">
      <FarmsIcon />
      <p>Farms</p>
    </RouterLink>
    <RouterLink to="/statistics">
      <StatisticsIcon />
      <p>Statistics</p>
    </RouterLink>
    <RouterLink to="/drones">
      <DroneNavigationIcon />
      <p>Drones Management</p>
    </RouterLink>
    <RouterLink to="/notifications">
      <UnreadNotifications />
      <NotificationsIcon />
      <p>Notifications</p>
    </RouterLink>
  </nav>
</template>

<script>
import { RouterLink } from 'vue-router';
import FarmsIcon from '@/assets/images/icons/FarmsIcon.vue';
import StatisticsIcon from '@/assets/images/icons/StatisticsIcon.vue';
import DroneNavigationIcon from '@/assets/images/icons/DroneNavigationIcon.vue';
import NotificationsIcon from '@/assets/images/icons/NotificationsIcon.vue';
import UnreadNotifications from './UnreadNotifications.vue';

export default {
  components: {
    RouterLink,
    FarmsIcon,
    StatisticsIcon,
    DroneNavigationIcon,
    NotificationsIcon,
    UnreadNotifications
  }
};
</script>

<style scoped>
nav {
  display: flex;
  justify-content: space-between;
  padding: 1rem;
  background-color: #2ecc71;
  box-shadow: 0 0.2rem 0.5rem rgba(0, 0, 0, 0.1);
  transition: background-color 0.3s, box-shadow 0.3s;
}

nav a {
  color: white;
  padding: 0.5rem 1rem;
  margin: 0 0.5rem;
  border-radius: 0.3rem;
  transition: background-color 0.3s, color 0.3s, transform 0.3s;
  text-align: center;
}

nav a:hover {
  background-color: #27ae60;
  transform: scale(1.05);
}

nav:hover {
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.2);
}
</style>
