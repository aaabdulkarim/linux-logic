import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    redirect: "/about"
  },
  {
    path: '/profil',
    redirect: "/tbc"
  },
  {
    path: '/auswahl',
    redirect: "/tbc"
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/tbc',
    name: 'tobecontinued',
    component: () => import('../views/TBC.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
