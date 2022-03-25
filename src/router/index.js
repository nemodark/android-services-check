import { createRouter, createWebHistory } from '@ionic/vue-router';

import HomePage from '../pages/HomePage.vue';
import CheckDateTimePage from '../pages/CheckDateTimePage.vue';
import GeoLocationPage from '../pages/GeoLocationPage.vue';

const routes = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    component: HomePage,
    name: 'homepage'
  },
  {
    path: '/check-datetime',
    component: CheckDateTimePage,
    name: 'checkdatetimepage'
  },
  {
    path: '/geo-location',
    component: GeoLocationPage,
    name: 'geolocation'
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
