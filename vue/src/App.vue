<template>
  <div id="capstone-app" :style="{ backgroundImage: `url(${$store.state.currentBackground})` }">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }" :style="{ color: 'white' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"
        :style="{ color: 'white' }">Logout</router-link>
    </div>
    <div id="#main-view">
      <router-view />
    </div>

  </div>
</template>

<style>
body {
  margin: 0;
  padding: 0;
}

#capstone-app {
  display: grid;
  grid-template-rows: 5% 95%;
  justify-content: center;
  align-items: center;
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  height: 100vh;
}

#nav {
  padding: 1%;
  margin: 1%;
  border-radius: 10px;
  font-size: xx-large;
  background-color: #009688;
}
</style>
