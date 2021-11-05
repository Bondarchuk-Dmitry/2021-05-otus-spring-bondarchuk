<template>

<div>
  <h3>Авторы</h3>
  <el-table :data="this.$store.state.authors"
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
import { authorFullName } from "../js/util/authorUtil.js";

export default {
  name: "Author",
  created() {
    this.$store.dispatch('getAllAuthors');
  },
  methods: {
    authorFullName
  }
}
</script>

<style scoped>

</style>