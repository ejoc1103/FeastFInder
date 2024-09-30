<template>
  <div id="capstone-app" :style="{ backgroundImage: `url(${$store.state.currentBackground})` }">
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
  margin: 0;
  padding: 0;
}

#capstone-app {
  display: grid;
  justify-content: center;
  align-items: center;
  background-color: green;
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  grid-template-rows: 10% 90%;
  height: 100vh;
  width: 100vw;
}


#nav {
  padding: 1%;
  margin: 1%;
  border-radius: 10px;
  font-size: xx-large;
}
</style>
