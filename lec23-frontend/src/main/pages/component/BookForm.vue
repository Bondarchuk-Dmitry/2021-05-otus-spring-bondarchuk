<template>
  <div class="form-container">
    <el-form ref="form" :model="form" label-width="120px" class="form-style">
      <h4>Новая книга</h4>
      <hr>
      <el-form-item label="Название:">
        <el-input v-model="form.name" style="width: 500px"></el-input>
      </el-form-item>
      <el-form-item label="Автор:">
        <el-select v-model="form.authorId" placeholder="Выберите автора" style="width: 500px">
          <el-option v-for="item in this.$store.state.authors" :key="item.id" :label="authorFullName(item)"
                     :value="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="Жанр:">
        <el-select v-model="form.genreId" placeholder="Выберите жанр" style="width: 500px">
          <el-option v-for="item in this.$store.state.genres" :key="item.id" :label="item.name" :value="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item class="form-button-position">
        <el-button type="primary" @click="onSave" round>Сохранить</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

export default {
  name: "BookForm",
  data() {
    return {
      form: {
        name: '',
        authorId: '',
        genreId: ''
      },
    }
  },
  methods: {
    authorFullName(author) {
      if (author) return author.firstName + ' ' + author.lastName;
      return '';
    },
    onSave() {
      this.$store.dispatch('saveBook', this.form)
      this.form = {};
    },
  }
}
</script>

<style scoped>
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
</style>