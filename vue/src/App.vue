<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }" :style="{ color: 'white' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"
        :style="{ color: 'white' }">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<style>
body {
  display: grid;
  justify-content: center;
  align-items: start;
  background-image: url('../../public/dinerBackground.jpg');
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  height: 100vh;
  margin: 0;
  padding: 0;
}
#nav {
  padding: 1%;
  margin: 1%;
  border-radius: 10px;
  font-size: xx-large;
}
</style>
