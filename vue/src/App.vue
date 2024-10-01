<template>
  <div>
  <div id="nav">
    <router-link v-bind:to="{ name: 'home' }" :style="{ color: 'white' }">Home</router-link>&nbsp;|&nbsp;
    <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"
      :style="{ color: 'white' }">Logout</router-link>
  </div>
  <div id="capstone-app" :style="{ backgroundImage: `url(${$store.state.currentBackground})` }">
    <div id="#main-view">
      <router-view />
    </div>

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
  justify-content: center;
  align-items: center;
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  height: 100vh;
}

#nav {
  padding: 0 3%;
  margin: 0 3%;
  font-size: xx-large;
  background-color: #009688;
  width: 100vw;
}
</style>
