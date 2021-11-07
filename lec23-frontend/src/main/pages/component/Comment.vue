<template>
  <el-card class="box-card" :model="comment">
    <h4>Комментарии</h4>
    <hr>
    <div v-for="item in book.bookComments" class="text item">
      {{ item.text }}
    </div>
    <hr>
    <el-input v-model="comment.text" style="width: 80%"></el-input>
    <el-button type="primary" @click="onAddComment(rowIndex, book)" round style="margin-left: 10px">Добавить
    </el-button>
  </el-card>
</template>

<script>
export default {
  name: "Comment",
  props: ["rowIndex", "bookId", "book"],
  data() {
    return {
      comment: {
        text: ''
      }
    }
  },
  methods: {
    onAddComment(index, row) {
      console.log(index, row)
      this.$store.dispatch('addComment', {index: index, bookId: row.id, text: this.comment.text})
      this.comment.text = '';
    },
  }
}
</script>

<style scoped>
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