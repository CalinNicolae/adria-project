import '@/assets/css/reset.css';
import '@/assets/css/global.css';

import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import { get } from './assets/utils/api/api-methods';

const app = createApp(App);

app.use(router);

app.mount('#app');

get('health')
    .then(() => console.log("API health check OK"))
    .catch(err => console.error("API health check failed:", err));

const rootURL = window.location.origin;
const baseURL = import.meta.env.BASE_URL;
if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register(
        import.meta.env.MODE === 'production'
            ? `${rootURL}${baseURL}service-worker.js`
            : `${rootURL}${baseURL}dev-sw.js?dev-sw`
    );
}
