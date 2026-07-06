import { createRouter, createWebHistory } from "vue-router";
import { handleAuth } from "@/services/auth.js";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: () => import("@/views/LoginView.vue")
    },
    {
      path: '/farms',
      name: 'Farms',
      component: () => import("@/views/FarmsView.vue")
    },
    {
      path: '/account',
      name: 'Account',
      component: () => import("@/views/AccountView.vue")
    },
    {
      path: '/support',
      name: 'Support Form',
      component: () => import('@/components/SupportForm.vue')
    },
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/notifications',
      name: 'Notifications',
      component: () => import("@/views/NotificationsView.vue")
    },
    {
      path: '/drones',
      name: 'Drones',
      component: () => import('@/components/DroneLayout.vue'),
      children: [
        {
          path: '',
          name: 'Drones List',
          component: () => import('@/views/DroneListView.vue')
        },
        {
          path: 'function',
          name: 'Drone Function',
          component: () => import('@/views/DroneFunctionView.vue')
        },
        {
          path: 'rent',
          name: 'Rent Drone',
          component: () => import('@/views/RentDroneView.vue')
        },
        {
          path: 'return-drone',
          name: 'Return Drone',
          component: () => import('@/views/ReturnDroneView.vue')
        },
      ]
    },
    {
      path: '/statistics',
      name: 'Statistics',
      component: () => import("@/views/StatisticsView.vue")
    }
  ]
});

router.beforeEach((to, from, next) => {
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    const adriaId = localStorage.getItem("AdriaId");
    if (!adriaId) {
      next({ path: "/login" });
    } else {
      handleAuth(adriaId, next);
    }
  } else {
    next();
  }
});

export default router;
