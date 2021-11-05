<template>
  <el-dialog title="Редактироване книги" :visible.sync="dialogVisible">
    <el-form ref="form" :model="data" label-width="120px">
      <el-form-item label="ID:">
        <el-input v-model="bookId" style="width: 95%" readonly></el-input>
      </el-form-item>
      <el-form-item label="Название:">
        <el-input v-model="data.name" style="width: 95%"></el-input>
      </el-form-item>
      <el-form-item label="Автор:">
        <el-select v-model="data.authorId" placeholder="Выберите автора" style="width: 95%">
          <el-option v-for="item in this.$store.state.authors" :key="item.id" :label="authorFullName(item)"
                     :value="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="Жанр:">
        <el-select v-model="data.genreId" placeholder="Выберите жанр" style="width: 95%">
          <el-option v-for="item in this.$store.state.genres" :key="item.id" :label="item.name"
                     :value="item.id"></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="onEdit">Сохранить</el-button>
        <el-button @click="onClose">Отмена</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { authorFullName } from "../../js/util/authorUtil.js";

export default {
  name: "BookEditForm",
  props: ["dialogVisible", "rowIndex", "bookId", "data"],
  methods: {
    onEdit() {
      this.$store.dispatch('updateBook', {index: this.rowIndex, bookId: this.bookId, data: this.data})
      this.$emit('close')
    },
    onClose() {
      this.$emit('close')
    },
    authorFullName
  }
}
</script>

<style scoped>

</style>