<template>

  <div>
    <h3>Жанры</h3>
    <el-table :data="this.$store.state.genres"
              ref="singleTable"
              highlight-current-row
              border>
      <el-table-column prop="id" width="100">
        <template slot="header">
          <label>ID</label>
        </template>
      </el-table-column>
      <el-table-column prop="name">
        <template slot="header">
          <label>Жанры</label>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "Genre",
  data() {
    return {
      genres: []
    }
  },
  created() {
    this.$store.dispatch('getAllGenres');
  }
}
</script>
<style scoped>

</style>