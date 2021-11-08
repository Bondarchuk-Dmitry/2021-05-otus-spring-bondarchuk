import Vue from "vue";
import router from "../router/router";
import store from "../store/store"

const headers = {
    'content-type': 'application/json'
};

const headersFormData = {
    'content-type': '"multipart/form-data'
}

export default {
    login(body) {
        var bodyFormData = new FormData();
        bodyFormData.append("username", body.username);
        bodyFormData.append("password", body.password);
        return Vue.http.post('login', bodyFormData, headersFormData).then(response => {
            return response.status
        });
    },
    getBooks() {
        return Vue.http.get('api/books').then(response => {
            return response.data;
        }).catch(err => {
            this.checkedStatus401(err.status)
        });
    },
    getAuthors() {
        return Vue.http.get('api/authors').then(response => {
           return response.data;
        }).catch(err => {
            this.checkedStatus401(err.status)
        });
    },
    getGenres() {
        return Vue.http.get('api/genres').then(response => {
            return response.data;
        }).catch(err => {
            this.checkedStatus401(err.status)
        });
    },
    saveBook(body) {
        return Vue.http.post('api/books', body, headers).then(response => {
            return  response.data;
        }).catch(err => {
                this.checkedStatus401(err.status)
            });
    },
    updateBook(bookId, body) {
        return Vue.http.put('api/books/' + bookId, body, headers).then(response => {
            return  response.data;
        }).catch(err => {
            this.checkedStatus401(err.status)
        });
    },
    deleteBook(bookId) {
        return Vue.http.delete('api/books/' + bookId).then(response => {
            return response
        }).catch(err => {
            this.checkedStatus401(err.status)
        });
    },
    addComment(bookId, text) {
        return Vue.http.put('api/books/' + bookId + '/comment', text, headers).then(response => {
            return  response.data;
        }).catch(err => {
            this.checkedStatus401(err.status)
        });
    },
    checkedStatus401(status) {
        if (status === 401) {
            console.log(status)
            store.commit('login', false);
            router.push("/auth")
        }
    }
}