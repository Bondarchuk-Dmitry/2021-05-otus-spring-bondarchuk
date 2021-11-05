import Vue from "vue";

const headers = {
    'content-type': 'application/json'
};

export default {
    getBooks() {
        return Vue.http.get('api/books').then(response => {
            return response.data;
        });
    },
    getAuthors() {
        return Vue.http.get('api/authors').then(response => {
           return response.data;
        });
    },
    getGenres() {
        return Vue.http.get('api/genres').then(response => {
            return response.data;
        });
    },
    saveBook(body) {
        return Vue.http.post('api/books', body, headers).then(response => {
            return  response.data;
        })
    },
    updateBook(bookId, body) {
        return Vue.http.put('api/books/' + bookId, body, headers).then(response => {
            return  response.data;
        })
    },
    deleteBook(bookId) {
        return Vue.http.delete('api/books/' + bookId).then(response => {
            return response
        })
    },
    addComment(bookId, text) {
        return Vue.http.put('api/books/' + bookId + '/comment', text, headers).then(response => {
            return  response.data;
        })
    }
}