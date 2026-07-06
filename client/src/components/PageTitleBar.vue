<template>
  <header>
    <button @click.prevent="goBack" id="back-button">
      <BackIcon />
    </button>
    <h1>
      <RouterLink to="/farms">{{ this.$route.name }}</RouterLink>
    </h1>
    <RouterLink to="/account">
      <AccountIcon />
    </RouterLink>
  </header>
</template>

<script>
import { RouterLink } from 'vue-router';
import BackIcon from '@/assets/images/icons/IconGoBack.vue';
import AccountIcon from '@/assets/images/icons/IconAccount.vue';

export default {
  name: 'PageTitleBar',
  components: {
    RouterLink,
    BackIcon,
    AccountIcon
  },
  data() {
    return {
      title: 'CropTek',
      isLoggedIn: false // Initial state
    };
  },
  watch: {
    // Watch route changes to update the title dynamically
    $route() { // NOSONAR - built-in Vue function
      this.title = this.pageTitle;
    }
  },
  created() {
    // Check if user is logged in based on localStorage
    this.isLoggedIn = !!localStorage.getItem('AdriaId');
  },
  mounted() {
    // Optionally, add an event listener to update isLoggedIn if the storage changes
    window.addEventListener('storage', this.syncLoginStatus);
  },
  beforeUnmount() {
    window.removeEventListener('storage', this.syncLoginStatus);
  },
  methods: {
    syncLoginStatus() {
      this.isLoggedIn = !!localStorage.getItem('AdriaId');
    },
    goBack() {
      this.$router.back();
    }
  }
};
</script>

<style scoped>
header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-image: url('@/assets/images/titleBarImage.webp');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  height: 5rem;
  padding: 1rem;
  box-shadow: 0 0.2rem 0.5rem rgba(0, 0, 0, 0.1);
  position: relative;
}

header::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.3);
  backdrop-filter: blur(5px);
  z-index: 1;
}

header>* {
  position: relative;
  z-index: 2;
}

#back-button {
  background: transparent;
  color: hsla(160, 100%, 37%, 1);
  transition: 0.4s;
  border: none;
  font-size: 1.5rem;
}

h1 {
  font-size: 2rem;
  color: #2ecc71;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
  margin: 0;
}

a {
  font-size: 1.5rem;
  color: #2ecc71;
  text-decoration: none;
  transition: color 0.3s;
}

a:hover {
  color: #27ae60;
}

#back-button:hover {
  background-color: hsla(160, 100%, 37%, 0.2);
}
</style>
