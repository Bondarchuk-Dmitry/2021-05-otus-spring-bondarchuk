import Vue from 'vue'
import VueRouter from 'vue-router'
import Book from '../../pages/Book.vue';
import Author from '../../pages/Author.vue';
import Genre from "../../pages/Genre.vue";
import Login from "../../pages/Login.vue";

import store from '../store/store'

Vue.use(VueRouter);

const isLogged = (to, from, next) => {
    console.log(store.state.isLogged)
    if (store.state.isLogged) {
        next()
        return
    }
    next('/auth')
}

const routes = [
    { path: '/auth', name: 'login', component: Login },
    { path: '/book', name: 'book', component: Book, beforeEnter: isLogged},
    { path: '/author', name: 'Author', component: Author, beforeEnter: isLogged},
    { path: '/genre', name: 'Genre', component: Genre, beforeEnter: isLogged}
];

export default new VueRouter({
    mode: 'history',
    routes
})