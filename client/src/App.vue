<template>
  <div class="wrapper">
    <header>
      <PageTitleBar v-if="showTitleBar" />
      <Navigation v-if="showTitleBar" />
    </header>
    <main>
      <RouterView />
    </main>
  </div>
</template>

<script>
import { RouterView } from 'vue-router';
import PageTitleBar from './components/PageTitleBar.vue';
import Navigation from './components/Navigation.vue';
import { enablePushNotifications } from "./services/push-notification-service.js";

export default {
  methods: {
    enablePush() {
      enablePushNotifications();
    }
  },
  components: {
    RouterView,
    PageTitleBar,
    Navigation
  },
  computed: {
    showNavigation() {
      const basePathsWithNavigation = ['/', '/farms', '/statistics', '/drones', '/notifications'];
      return basePathsWithNavigation.some(basePath => this.$route.path.startsWith(basePath));
    },
    showTitleBar() {
      return this.$route.path !== '/login';
    },
  }
};
</script>

<style scoped>
main {
  width: 100%;
  margin: auto;
}

.wrapper {
  display: flex;
  flex-direction: column;
}
</style>
