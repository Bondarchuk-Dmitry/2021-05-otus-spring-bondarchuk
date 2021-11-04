<template>
  <div class="container">
    <book-form/>

    <el-table :data="this.$store.state.books"
              ref="singleTable"
              highlight-current-row
              border
    >
      <el-table-column type="expand">
        <template slot-scope="props">
          <comment :row-index="props.$index" :book="props.row"/>
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
              @click="onShowEditDialog(scope.$index, scope.row)"
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
    <book-edit-form
        :dialog-visible="dialogEditFormVisible"
        :row-index="rowIndex"
        :book-id="editBookId"
        :data="editForm"
        v-on:close="dialogEditFormVisible = false"/>
  </div>
</template>

<script>
import BookForm from "./component/BookForm.vue";
import Comment from "./component/Comment.vue";
import BookEditForm from "./dialog/BookEditForm.vue";
import {authorFullName} from "../js/util/authorUtil.js";

export default {
  name: "Book",
  components: {Comment, BookEditForm, BookForm},
  data() {
    return {
      dialogEditFormVisible: false,
      rowIndex: -1,
      editBookId: '',
      editForm: {
        name: '',
        authorId: null,
        genreId: null
      },
    }
  },
  created() {
    this.$store.dispatch('getAllBooks');
    this.$store.dispatch('getAllAuthors');
    this.$store.dispatch('getAllGenres');
  },
  methods: {
    authorFullName,
    onShowEditDialog(index, row) {
      this.editBookId = row.id;
      this.rowIndex = index;
      this.editForm.name = row.name;
      this.editForm.authorId = row.author.id;
      this.editForm.genreId = row.genre.id;
      this.dialogEditFormVisible = true
    },
    onDelete(index, row) {
      this.$store.dispatch('deleteBook', {index: index, bookId: row.id})
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

.form-button-position {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-left: -10%;
}
</style>