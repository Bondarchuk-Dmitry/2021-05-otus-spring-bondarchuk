import Vue from 'vue'
import VueRouter from 'vue-router'
import Book from '../../pages/Book.vue';
import Author from '../../pages/Author.vue';
import Genre from "../../pages/Genre.vue";

Vue.use(VueRouter);

const routes = [
    { path: '/book', name: 'book', component: Book },
    { path: '/author', name: 'Author', component: Author},
    { path: '/genre', name: 'Genre', component: Genre}
];

export default new VueRouter({
    mode: 'history',
    routes
})