import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/about',
    name: 'about',
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/',
    redirect: "/about"
  },
  {
    path : "/auswahl",
    name : "auswahl",
    component : () => import ('../views/TBC.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
