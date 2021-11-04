import Vue from 'vue'
import Vuex from 'vuex'
import api from "../api/api";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        authors: [],
        genres: [],
        books: []
    },
    mutations: {
        authors (state, data) { state.authors = data },
        genres (state, data) { state.genres = data },
        books (state, data) { state.books = data },
        newBook(state, book) {state.books.push(book)},
        //updateBook(state, body) {state.books[body.index] = body.data;},
        updateBook(state, body) {state.books.splice(body.index, 1, body.data)},
        deleteBook(state, index) {state.books.splice(index, 1)}
    },
    actions: {
        getAllAuthors ({commit}) {
            return api.getAuthors()
                .then(data => {
                    commit('authors', data);
                    return data;
                })
                .catch(err => { throw err });
        },
        getAllGenres({commit}) {
            return api.getGenres()
                .then(data => {
                    commit('genres', data);
                    return data;
                })
                .catch(err => { throw err });
        },
        getAllBooks({commit}) {
            return api.getBooks()
                .then(data => {
                    commit('books', data);
                    return data;
                })
                .catch(err => { throw err });
        },
        saveBook({commit}, body) {
            api.saveBook(body)
                .then(data => {
                    commit('newBook', data)
                })
                .catch(err => { throw err });
        },
        updateBook({commit}, body) {
            api.updateBook(body.bookId, body.data)
                .then(data => {
                    commit('updateBook', {index: body.index, data: data})
                })
                .catch(err => { throw err });
        },
        deleteBook({commit}, body) {
            api.deleteBook(body.bookId)
                .then(response => {
                    commit('deleteBook', body.index)
                })
                .catch(err => { throw err });
        },
        addComment({commit}, body) {
            api.addComment(body.bookId, body.text)
                .then(data => {
                    commit('updateBook', {index: body.index, data: data})
                })
                .catch(err => { throw err });
        }
    }
})
