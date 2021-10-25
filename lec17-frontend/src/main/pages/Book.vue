<template>
  <div class="container">
    <div class="form-container">
      <el-form ref="form" :model="form" label-width="120px" class="form-style">
        <h4>Новая книга</h4>
        <hr>
        <el-form-item label="Название:">
          <el-input v-model="form.name" style="width: 500px"></el-input>
        </el-form-item>
        <el-form-item label="Автор:">
          <el-select v-model="form.authorId" placeholder="Выберите автора" style="width: 500px">
            <el-option v-for="item in authors" :key="item.id" :label="authorFullName(item)"
                       :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Жанр:">
          <el-select v-model="form.genreId" placeholder="Выберите жанр" style="width: 500px">
            <el-option v-for="item in genres" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item class="form-button-position">
          <el-button type="primary" @click="onSave" round>Сохранить</el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-table :data="books"
              ref="singleTable"
              highlight-current-row
              border
    >
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-card class="box-card" :model="comment">
            <h4>Комментарии</h4>
            <hr>
            <div v-for="item in props.row.bookComments" class="text item">
              {{ item.text }}
            </div>
            <hr>
            <el-input v-model="comment.text" style="width: 80%"></el-input>
            <el-button type="primary" @click="onAddComment(props.row)" round style="margin-left: 10px">Добавить
            </el-button>
          </el-card>
        </template>
      </el-table-column>
      <el-table-column prop="id" width="100">
        <template slot="header">
          <label>ID</label>
        </template>
      </el-table-column>
      <el-table-column prop="name">
        <template slot="header">
          <label>Название</label>
        </template>
      </el-table-column>
      <el-table-column :formatter="row => authorFullName(row['author'])" width="400">
        <template slot="header">
          <label>Автор</label>
        </template>
      </el-table-column>
      <el-table-column prop="genre.name" width="300">
        <template slot="header" slot-scope="scope">
          <label>Жанр</label>
        </template>
      </el-table-column>
      <el-table-column width="150">
        <template slot="header" slot-scope="scope">
          <label>Действия</label>
        </template>
        <template class="form-button-position" slot-scope="scope">
          <el-button
              type="primary"
              icon="el-icon-edit"
              circle
              @click="onShowEditDialog(scope.row)"
          ></el-button>
          <el-button
              type="danger"
              icon="el-icon-delete"
              circle
              @click="onDelete(scope.$index, scope.row)"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="Редактироване книги" :visible.sync="dialogEditFormVisible">
      <el-form ref="form" :model="editForm" label-width="120px">
        <el-form-item label="ID:">
          <el-input v-model="editForm.id" style="width: 95%" readonly></el-input>
        </el-form-item>
        <el-form-item label="Название:">
          <el-input v-model="editForm.name" style="width: 95%"></el-input>
        </el-form-item>
        <el-form-item label="Автор:">
          <el-select v-model="editForm.authorId" placeholder="Выберите автора" style="width: 95%">
            <el-option v-for="item in authors" :key="item.id" :label="authorFullName(item)"
                       :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Жанр:">
          <el-select v-model="editForm.genreId"  placeholder="Выберите жанр" style="width: 95%">
            <el-option v-for="item in genres" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="onEdit">Сохранить</el-button>
        <el-button @click="dialogEditFormVisible = false">Отмена</el-button>
    </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Book",
  data() {
    return {
      dialogEditFormVisible: false,
      books: [],
      authors: [
        {
          id: 0,
          firstName: '',
          lastName: ''
        }
      ],
      genres: [],
      form: {
        name: '',
        authorId: '',
        genreId: ''
      },
      editForm: {
        id: '',
        name: '',
        authorId: null,
        genreId: null
      },
      author: null,
      comment: {
        text: ''
      }
    }
  },
  created() {
    this.$http.get("api/books").then(response => {
      this.books = response.data;
    });
    this.$http.get("api/authors").then(response => {
      this.authors = response.data;
    });
    this.$http.get("api/genres").then(response => {
      this.genres = response.data;
    });
  },
  methods: {
    authorFullName(author) {
      if (author) return author.firstName + ' ' + author.lastName;
      return '';
    },
    onSave() {
      let headers = {headers: {'Content-Type': 'application/json'}};
      this.$http.post("api/book", this.form, headers).then(response => {
        this.form = {};
        this.books = response.data;
      })
    },
    onAddComment(row) {
      let headers = {headers: {'Content-Type': 'application/json'}};
      this.$http.post("api/books/" + row.id + "/comment", this.comment.text, headers).then(response => {
        row.bookComments = response.data;
        this.comment.text = '';
      })
    },
    onShowEditDialog(row) {
      this.$http.get("api/books/" + row.id).then(response => {
        this.editForm.id = response.data.id;
        this.editForm.name = response.data.name;
        this.editForm.authorId = response.data.author.id;
        this.editForm.genreId = response.data.genre.id;
      });
      this.dialogEditFormVisible = true
    },
    onEdit() {
      let headers = {headers: {'Content-Type': 'application/json'}};
      this.$http.put("api/books/" + this.editForm.id, this.editForm, headers).then(response => {
        this.books = response.data;
      });
      this.dialogEditFormVisible = false
    },
    onDelete(index, row) {
      this.$http.delete("api/books/" + row.id).then(response => {
        this.books.splice(index, 1);
      });
    }
  }
}
</script>

<style scoped>
.container {
  width: 100%;
  float: left;
  position: relative;
}

.form-container {
  margin-top: 20px;
  border-color: black;
  display: flex;
  align-items: center;
  justify-content: center;
}

.form-style {
  border: 3px solid #f2f6fc;
  padding: 20px;
  border-radius: 20px;
}

.form-button-position {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-left: -10%;
}

.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  margin-left: 25%;
  width: 900px;
}
</style>