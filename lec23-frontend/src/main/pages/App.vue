<template>
  <div>
    <el-menu class="el-menu-demo" mode="horizontal" @select="handleSelect" v-if="$store.state.isLogged">
      <el-menu-item index="book">Книги</el-menu-item>
      <el-menu-item index="author">Авторы</el-menu-item>
      <el-menu-item index="genre">Жанры</el-menu-item>
    </el-menu>
    <router-view/>
  </div>
</template>

<script>
import router from "../js/router/router";

export default {
  methods: {
    handleSelect(key, keyPath) {
      router.push(keyPath[0]);
    }
  }
}
</script>

<style>
</style>