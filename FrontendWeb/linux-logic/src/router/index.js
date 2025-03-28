import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
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
    path: '/terminal',
    name: 'game',
    component: () => import(/* webpackChunkName: "game" */ '../views/GameView.vue')
  },
  {
    path: '/Auswahl',
    name: 'Auswahl',
    component: () => import(/* webpackChunkName: "selection" */ '../views/AuswahlView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import(/* webpackChunkName: "login" */ '../views/LoginView.vue')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import(/* webpackChunkName: "register" */ '../views/RegisterView.vue')
  },
  {
    path: '/profil',
    name: 'profil',
    component: () => import(/* webpackChunkName: "profil" */ '../views/BenutzerView.vue')
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
