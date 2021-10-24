<template>

<div>
  <h3>Авторы</h3>
  <el-table :data="authors"
            ref="singleTable"
            highlight-current-row
            border>
    <el-table-column prop="id" width="100">
      <template slot="header">
        <label>ID</label>
      </template>
    </el-table-column>
    <el-table-column :formatter="(row) => authorFullName(row)">
      <template slot="header">
        <label>Авторы</label>
      </template>
    </el-table-column>
  </el-table>
</div>
</template>

<script>
export default {
  name: "Author",
  data() {
    return {
      authors: []
    }
  },
  created() {
    this.$http.get("api/authors").then(response => {
      this.authors =  response.data;
    });
  },
  methods: {
    authorFullName(author) {
      if (author) return author.firstName + ' ' + author.lastName;
      return '';
    }
  }
}
</script>

<style scoped>

</style>