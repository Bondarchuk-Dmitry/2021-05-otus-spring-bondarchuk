<template>
  <div>
    <el-menu class="el-menu-demo" mode="horizontal" @select="handleSelect">
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
      created() {
        console.log('App:');
      },
      methods: {
        handleSelect(key, keyPath) {
          router.push(keyPath[0]);
        }
      }
    }
</script>

<style>
</style>